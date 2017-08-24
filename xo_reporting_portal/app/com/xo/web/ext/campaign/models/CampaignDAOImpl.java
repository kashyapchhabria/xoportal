package com.xo.web.ext.campaign.models;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hibernate.Query;

import com.xo.web.ext.campaign.models.Campaign;
import com.xo.web.ext.comment.models.Comment;
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
	
	private static final String QUERY_CAMPAIGN_EXISTS = "isCampaignExists";
	private static final String PARAM_CAMPAIGN_NAME = "campaignName";
	
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
		String topSgmtQuery = prepQuery(ES_ORDER.get(1), topSegment);
		String lifetimeQuery = prepQuery(ES_ORDER.get(4), lifetime);
		String dataArpuQuery = prepQuery(ES_ORDER.get(5), dataArpu);
		String vasPlanQuery = prepQuery(ES_ORDER.get(6), vasPlan);
		String regionsQuery = prepQuery(ES_ORDER.get(2), regions);
		List<String> allHits = esSql.fetchAllHits( 
				ES_HOST, " * from " + ES_INDEX + 
				" where " + ES_ORDER.get(3) + "='" + dateWeek + 
				"' and " + topSgmtQuery + 
				" and " + lifetimeQuery + 
				" and " + dataArpuQuery + 
				" and " + vasPlanQuery + 
				" and " + regionsQuery, 7500, 600000);
		return allHits;
	}

	@Override
	public Long getTotalHits(String selDate) throws Exception {
		int totalHits = esSql.getTotalHits(ES_HOST, " from " + ES_INDEX + 
				" where " + ES_ORDER.get(3) + "='" + selDate + "'" );
		return Integer.toUnsignedLong(totalHits);
	}

	@Override
	public Long getQueryHits(String[] topSegment,String dateWeek, String[] lifetime ,String[] dataArpu,String[] vasPlan, String[] regions) throws Exception {
		String topSgmtQuery = prepQuery(ES_ORDER.get(1), topSegment);
		String lifetimeQuery = prepQuery(ES_ORDER.get(4), lifetime);
		String dataArpuQuery = prepQuery(ES_ORDER.get(5), dataArpu);
		String vasPlanQuery = prepQuery(ES_ORDER.get(6), vasPlan);
		String regionsQuery = prepQuery(ES_ORDER.get(2), regions);
		int totalHits = esSql.getTotalHits( 
				ES_HOST, " from " + ES_INDEX + 
				" where " + ES_ORDER.get(3) + "='" + dateWeek + 
				"' and " + topSgmtQuery + 
				" and " + lifetimeQuery + 
				" and " + dataArpuQuery + 
				" and " + vasPlanQuery + 
				" and " + regionsQuery);
		return Integer.toUnsignedLong(totalHits);
	}

	@Override
	public Boolean isCampaignExist(String campaignName) {
		Query query = getNamedQuery(QUERY_CAMPAIGN_EXISTS);
		query.setParameter(PARAM_CAMPAIGN_NAME, campaignName);
        return query.list().isEmpty();
	}
	
}
