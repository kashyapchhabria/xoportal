package com.xo.web.ext.campaign.models;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.xo.web.ext.campaign.models.Campaign;
import com.xo.web.models.dao.GenericDAO;


public interface CampaignDAO extends GenericDAO<Campaign, Integer> {

	List<String> getMsisdnsAsList(String topSegment, String[] subSegment, String[] homeLocation) throws Exception;

	Long getTotalHits() throws Exception;

	Long getQueryHits(String topSegment, String[] subSegment, String[] homeLocation) throws Exception;
	
}