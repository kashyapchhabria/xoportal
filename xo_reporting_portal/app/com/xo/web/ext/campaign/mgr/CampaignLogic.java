package com.xo.web.ext.campaign.mgr;

import com.xo.web.ext.campaign.models.CampaignDAO;
import com.xo.web.ext.campaign.models.CampaignDAOImpl;
import com.xo.web.ext.campaign.viewdtos.CampaignDto;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import com.xo.web.core.XOException;
import com.xo.web.ext.campaign.models.Campaign;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;

public class CampaignLogic extends BaseLogic<Campaign, Integer> {
	
	private final CampaignDAO campaignDao;
	
	protected static final List<String> ES_FIELDS = (List<String>) XoUtil.getConfigsAsList(XoAppConfigKeys.ES_ORDER);
	protected static final String SYMBOL_COMMA = ",";
	
	private String getNestedPath(String nestedPath) {
		String newPath = "";
		newPath = "/"+nestedPath.replace(".", "/");
		return newPath;
	}
	
	private void getReportMetaData(CsvWriter writer,CampaignDto campaignDto) {
		writer.commentRow("Campaign Name:" + campaignDto.name.toUpperCase());
		writer.commentRow("Campaign Description:" + campaignDto.description);
		writer.commentRow("Applied Filters");
		writer.commentRow("Top Segment:" + campaignDto.filterJson.getTopSegment());
		writer.commentRow("Sub Segment:" + String.join("-", campaignDto.filterJson.getSubSegment()));
		writer.commentRow("Regions:" + String.join("-", campaignDto.filterJson.getHomeLocation()));
		writer.commentRow("Lifetime:" + campaignDto.filterJson.getLifetime());
		writer.commentRow("Data ARPU:" + campaignDto.filterJson.getDataArpu());
		writer.commentRow("VAS Plan:" + campaignDto.filterJson.getVasPlan());
	}

	private void getReportHeaders(CsvWriter writer) {
		writer.writeHeaders("MSISDN", "Sub Segment", "Region", "Lifetime", "Data ARPU", "VAS Plan");
	}
	
	public CampaignLogic() {
		super(new CampaignDAOImpl());
		campaignDao = (CampaignDAO) entityDao;
	}

	public FileWriter getMsisdnsAsList(CampaignDto campaignDto, FileWriter writer2, int setAb) throws Exception {
		ByteArrayOutputStream csvResult = new ByteArrayOutputStream();
		Writer outputWriter = new OutputStreamWriter(csvResult);
		CsvWriterSettings writerSettings = new CsvWriterSettings();
		CsvWriter writer = new CsvWriter(outputWriter, writerSettings);
		ObjectMapper mapper = new ObjectMapper();
		
		getReportMetaData(writer,campaignDto);
		getReportHeaders(writer);
		
		writer.commentRow("Set A");
		
		List<String> allHits = this.campaignDao.getMsisdnsAsList(campaignDto.filterJson.getTopSegment(), campaignDto.filterJson.getSubSegment(), campaignDto.filterJson.getHomeLocation());
		
		int setB = (setAb * allHits.size()) / 100 ;
		int count = 0;
		for ( String filterHit : allHits ) {
			count++;
			StringBuilder recordData = new StringBuilder();
			JsonNode actualObj = mapper.readTree(filterHit);
			if ( actualObj != null ) {
				recordData.append(actualObj.at(getNestedPath(ES_FIELDS.get(0))).toString());
				recordData.append(SYMBOL_COMMA);
				recordData.append(actualObj.at(getNestedPath(ES_FIELDS.get(1))).toString());
				recordData.append(SYMBOL_COMMA);
				recordData.append(actualObj.at(getNestedPath(ES_FIELDS.get(2))).toString());
				recordData.append(SYMBOL_COMMA);
				recordData.append(campaignDto.filterJson.lifetime);
				recordData.append(SYMBOL_COMMA);
				recordData.append(campaignDto.filterJson.dataArpu);
				recordData.append(SYMBOL_COMMA);
				recordData.append(campaignDto.filterJson.vasPlan);
				writer.writeRow(recordData.toString());
			}
			if(count == setB) {
				writer.commentRow("Set B");
			}
		}
		writer.close();
		writer2.write(csvResult.toString());
		return writer2;
	}
	
	public Campaign save(CampaignDto campaignDto) throws XOException {
		Campaign campaign = null;
		if(campaignDto != null) {
			campaign = campaignDto.asEntityObject();
			campaign.setCreatedDate(new Date());
			campaign.setLastModifiedDate(new Date());
			campaign = this.campaignDao.save(campaign);
		}
		return campaign;
	}

	public String getMsisdnCount(CampaignDto campaignDto) {
		try {
			Long hitSize = this.campaignDao.getQueryHits(campaignDto.filterJson.getTopSegment(), campaignDto.filterJson.getSubSegment(), campaignDto.filterJson.getHomeLocation());
			return Long.toString(hitSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Long.toString(0);
	}

	public Long getTotalCount() {
		Long totalCount = 0l;
		try {
			totalCount = this.campaignDao.getTotalHits();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("Total Customers - "+ totalCount);
		return totalCount;
		
	}
}