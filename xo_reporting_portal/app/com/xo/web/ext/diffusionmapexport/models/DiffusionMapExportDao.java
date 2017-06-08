package com.xo.web.ext.diffusionmapexport.models;

import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.GenericDAO;

public interface DiffusionMapExportDao extends GenericDAO<DiffusionMapExport, Integer> {

	public SearchRequestBuilder getMsisdns(String deviceDate, String subsegmentLabel, String region, String status,String locationType,  String xTile, String yTile ,String spendSegment, String lifetimeBucket);

	public Long getMsisdnCount(String deviceDate, String subsegmentLabel, String region, String status,
			String locationType, String xTile, String yTile, String spendSegment,
			String lifetimeBucket);

}
