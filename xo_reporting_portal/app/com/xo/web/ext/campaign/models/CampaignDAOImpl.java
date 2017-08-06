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
	public List<String> getMsisdnsAsList(String topSegment, String[] subSegment, String[] homeLocation) throws Exception {
		String subSgmtQuery = prepQuery("segment.subsegment_name", subSegment);
		String homeLocQuery = prepQuery("demographics.homeregion", homeLocation);
		List<String> allHits = esSql.fetchAllHits(ES_HOST," "+ES_ORDER.get(0)+","+ES_ORDER.get(1)+","+ES_ORDER.get(2)+" from easycampaign where segment.segment_name='" + topSegment + "' and " + subSgmtQuery + " and " + homeLocQuery, 1000, 600000);
		return allHits;
	}

	@Override
	public Long getTotalHits() throws Exception {
		int totalHits = esSql.getTotalHits(ES_HOST, " from " + ES_INDEX + " where not segment.subsegment_name=\"unallocated\"");
		return Integer.toUnsignedLong(totalHits);
	}

	@Override
	public Long getQueryHits(String topSegment, String[] subSegment, String[] homeLocation) throws Exception {
		String subSgmtQuery = prepQuery("segment.subsegment_name", subSegment);
		String homeLocQuery = prepQuery("demographics.homeregion", homeLocation);
		int totalHits = esSql.getTotalHits(ES_HOST, " from " + ES_INDEX + " where segment.segment_name='" + topSegment + "' and " + subSgmtQuery + " and " + homeLocQuery);
		return Integer.toUnsignedLong(totalHits);
	}
	
}
