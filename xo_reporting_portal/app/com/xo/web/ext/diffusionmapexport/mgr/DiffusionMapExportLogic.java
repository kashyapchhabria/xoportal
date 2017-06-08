package com.xo.web.ext.diffusionmapexport.mgr;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;

import com.sun.javafx.collections.MappingChange.Map;
import com.univocity.parsers.common.processor.*;
import com.univocity.parsers.conversions.*;
import com.univocity.parsers.csv.*;
import com.univocity.parsers.fixed.*;
import com.univocity.parsers.tsv.*;
import com.xo.web.core.XODAOException;
import com.xo.web.ext.diffusionmapexport.models.DiffusionMapExport;
import com.xo.web.ext.diffusionmapexport.models.DiffusionMapExportDao;
import com.xo.web.ext.diffusionmapexport.models.DiffusionMapExportDaoImpl;
import com.xo.web.ext.diffusionmapexport.viewdtos.DiffusionMapExportDto;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.User;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;

public class DiffusionMapExportLogic extends BaseLogic<DiffusionMapExport, Integer> { 
	
	private final DiffusionMapExportDao DiffusionMapExportDAO;
	private final UserDAO userDAO;
	
	private Client client;
	private Settings settings;
	
	protected static final String SYMBOL_NEW_LINE = "\n";
	protected static final String SYMBOL_COMMA = ",";
	protected static final String SYMBOL_SEMICOLON = ";";
	public String ES_HOST = XoUtil.getConfig(XoAppConfigKeys.ES_HOST) ;
	public String ES_CLUSTER_NAME = XoUtil.getConfig(XoAppConfigKeys.ES_CLUSTER_NAME) ;
	protected static final int MAX_SIZE = 3000;
	
	public static final List<String> ES_FIELDS = (List<String>) XoUtil.getConfigsAsList(XoAppConfigKeys.ES_ORDER);
	
	public DiffusionMapExportLogic() {
		super(new DiffusionMapExportDaoImpl());
		this.DiffusionMapExportDAO = (DiffusionMapExportDao) entityDao;       
		this.userDAO = new UserDAOImpl();
	}

	public DiffusionMapExport save(DiffusionMapExportDto DiffusionMapExportDto) throws ParseException {
		DiffusionMapExport diffusionMapExport = null;
		Date datetime=null;	
		if(DiffusionMapExportDto != null) {
			User user = userDAO.findByEmail(DiffusionMapExportDto.user);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(DiffusionMapExportDto.createdDate);
			datetime =(Date) df.parse(df.format(new Date(Long.parseLong(DiffusionMapExportDto.createdDate))));
			diffusionMapExport = new DiffusionMapExport(datetime,user,DiffusionMapExportDto.deviceDate,DiffusionMapExportDto.xTile,DiffusionMapExportDto.yTile,DiffusionMapExportDto.subsegmentLabel,DiffusionMapExportDto.region,DiffusionMapExportDto.status,DiffusionMapExportDto.locationType,DiffusionMapExportDto.lifetimeBucket,DiffusionMapExportDto.spendSegment);
			diffusionMapExport = this.DiffusionMapExportDAO.save(diffusionMapExport);
		}
		return diffusionMapExport;
	}
	
	private void prepESClient() {
		try {
			settings = Settings.settingsBuilder()
				.put("cluster.name", ES_CLUSTER_NAME)
				.put("client.transport.ignore_cluster_name", true)
				.put("client.transport.sniff", true) 
				.build(); 
			client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ES_HOST), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	private void getReportMetaData(CsvWriter writer,DiffusionMapExportDto filterDto) {
		writer.commentRow("Applied Filters");
		writer.commentRow("Date:" + filterDto.deviceDate);
		writer.commentRow("Sub Segment:" + String.join("-", filterDto.subsegmentLabel));
		writer.commentRow("Regions:" + String.join("-", filterDto.region));
		writer.commentRow("Status:" + String.join("-", filterDto.status));
		writer.commentRow("Location:" + String.join("-", filterDto.locationType));
		writer.commentRow("XTile:" + String.join("-", filterDto.xTile));
		writer.commentRow("Ytile:" + String.join("-", filterDto.yTile));
		writer.commentRow("Spend Segment:" + String.join("-", filterDto.spendSegment));
		writer.commentRow("Lifetime Bucket:" + String.join("-", filterDto.lifetimeBucket));
	}

	private void getReportHeaders(CsvWriter writer) {
		writer.writeHeaders("msisdn", "Sub Segment", "Region","Status","Location","Xtile","Ytile","Spend Segment","Lifetime Bucket");
	}
	
	public FileWriter getMsisdns(DiffusionMapExportDto filterDto, FileWriter writer2) throws XODAOException, IOException {
		
		ByteArrayOutputStream csvResult = new ByteArrayOutputStream();
		Writer outputWriter = new OutputStreamWriter(csvResult);
		CsvWriter writer = new CsvWriter(outputWriter, new CsvWriterSettings());
		
		getReportMetaData(writer,filterDto);
		
		getReportHeaders(writer);
		
		prepESClient();
		
		SearchRequestBuilder requestBuilder = DiffusionMapExportDAO.getMsisdns(filterDto.deviceDate, filterDto.subsegmentLabel, filterDto.region,filterDto.status,filterDto.locationType,filterDto.xTile,filterDto.yTile,filterDto.spendSegment,filterDto.lifetimeBucket);
		requestBuilder.setSize(MAX_SIZE);
		int totalHits = Integer.parseInt(getMsisdnCount(filterDto));
		int hitCounter = 0;
		SearchResponse response = requestBuilder.setScroll(new TimeValue(60000)).execute().actionGet();
		while(hitCounter <= totalHits / MAX_SIZE) {
			SearchHit[] searchHits = response.getHits().getHits();
			for ( SearchHit filterHit : searchHits) {
				java.util.Map<String, SearchHitField> filterFields = filterHit.getFields();
				StringBuilder recordData = new StringBuilder();
				if ( filterFields != null ) {
					recordData.append(filterFields.get(ES_FIELDS.get(0)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(2)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(3)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(4)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(5)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(6)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(7)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(8)).getValue().toString());
					recordData.append(SYMBOL_COMMA);
					recordData.append(filterFields.get(ES_FIELDS.get(9)).getValue().toString());
//					recordData.append(SYMBOL_COMMA);
//					recordData.append(filterFields.get(ES_FIELDS.get(10)).getValue().toString());
					writer.writeRow(recordData.toString());
				}
			}
			response = client.prepareSearchScroll(response.getScrollId())
					.setScroll(new TimeValue(60000))
					.execute().actionGet();
			hitCounter++;
		}

		writer.close();
		writer2.write(csvResult.toString());
		return writer2;
	}
	
	public String getMsisdnCount(DiffusionMapExportDto filterDto) throws XODAOException, IOException {
		Long hitSize = DiffusionMapExportDAO.getMsisdnCount(filterDto.deviceDate, filterDto.subsegmentLabel, filterDto.region,filterDto.status,filterDto.locationType,filterDto.xTile, filterDto.yTile , filterDto.spendSegment , filterDto.lifetimeBucket);
		return Long.toString(hitSize);
	}

	
}
