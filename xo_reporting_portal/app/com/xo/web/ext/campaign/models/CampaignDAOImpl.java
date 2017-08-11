package com.xo.web.ext.campaign.models;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.xo.web.ext.campaign.models.Campaign;
import com.xo.web.models.dao.GenericDAOImpl;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;
import com.xoanon.es.sql.ESSqlRestConnector;

public class CampaignDAOImpl extends GenericDAOImpl<Campaign, Integer> implements CampaignDAO {
	
	private ESSqlRestConnector esSql;
	private static final List<String> ES_ORDER = (List<String>) XoUtil.getConfigsAsList(XoAppConfigKeys.ES_ORDER);
	private static final String ES_INDEX = XoUtil.getConfig(XoAppConfigKeys.ES_INDEX);
	private static final String ES_TYPE = XoUtil.getConfig(XoAppConfigKeys.ES_TYPE);
	private static final String ES_HOST = XoUtil.getConfig(XoAppConfigKeys.ES_URL) ;
	
	private String prepQuery(String fieldName, String[] fieldData) {
		String fieldQuery = fieldName + " in ";
		String fields = "(";
		for(int i=0; i< fieldData.length; i++) {
			if(i < (fieldData.length-1)) {
				fields = fields + "\"" + fieldData[i] + "\",";
			} else {
				fields = fields + "\"" + fieldData[i] + "\")";
			}
		}
		return fieldQuery+fields;
	}
	
	public CampaignDAOImpl() {
		esSql = new ESSqlRestConnector();
	}

	@Override
	public List<String> getMsisdnsAsList(String[] topSegment,String dateWeek, String[] lifetime ,String[] dataArpu,String[] vasPlan, String[] regions) throws Exception {
		String topSgmtQuery = prepQuery("segment", topSegment);
		String lifetimeQuery = prepQuery("lifetime", lifetime);
		String dataArpuQuery = prepQuery("dataArpu", dataArpu);
		String vasPlanQuery = prepQuery("vasPlan", vasPlan);
		String regionsQuery = prepQuery("region", regions);
		List<String> allHits = esSql.fetchAllHits(
				ES_HOST, " from " + ES_INDEX + 
				"where date_week='" + dateWeek + 
				"' and " + topSgmtQuery + 
				" and " + lifetimeQuery + 
				" and " + dataArpuQuery + 
				" and " + vasPlanQuery +
				" and " + regionsQuery, 1000, 600000);
		return allHits;
	}

	@Override
	public Long getTotalHits() throws Exception {
		int totalHits = esSql.getTotalHits(ES_HOST, " from " + ES_INDEX );
		return Integer.toUnsignedLong(totalHits);
	}

	@Override
	public Long getQueryHits(String[] topSegment,String dateWeek, String[] lifetime ,String[] dataArpu,String[] vasPlan, String[] regions) throws Exception {
		String topSgmtQuery = prepQuery("segment", topSegment);
		String lifetimeQuery = prepQuery("lifetime", lifetime);
		String dataArpuQuery = prepQuery("dataArpu", dataArpu);
		String vasPlanQuery = prepQuery("vasPlan", vasPlan);
		String regionsQuery = prepQuery("region", regions);
		int totalHits = esSql.getTotalHits( 
				ES_HOST, " from " + ES_INDEX + 
				" where date_week='" + dateWeek + 
				"' and " + topSgmtQuery + 
				" and " + lifetimeQuery + 
				" and " + dataArpuQuery + 
				" and " + vasPlanQuery + 
				" and " + regionsQuery);
		return Integer.toUnsignedLong(totalHits);
	}
	
}
