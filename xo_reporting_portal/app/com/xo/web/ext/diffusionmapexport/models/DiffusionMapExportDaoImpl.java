package com.xo.web.ext.diffusionmapexport.models;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.GenericDAOImpl;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.hibernate.Query;

public class DiffusionMapExportDaoImpl extends GenericDAOImpl<DiffusionMapExport, Integer> implements DiffusionMapExportDao {

	private Client client;
	private Settings settings;
	private BoolQueryBuilder queryBuilder,statusQuery,subSegmentQuery,locationTypeQuery,xTileQuery,yTileQuery,regionQuery,spendSegmentQuery,lifetimeBucketQuery;
	
	public String ES_INDEX = XoUtil.getConfig(XoAppConfigKeys.ES_INDEX);
	public String ES_TYPE = XoUtil.getConfig(XoAppConfigKeys.ES_TYPE);
	public List<String> ES_ORDER = (List<String>) XoUtil.getConfigsAsList(XoAppConfigKeys.ES_ORDER);
	public String ES_HOST = XoUtil.getConfig(XoAppConfigKeys.ES_HOST) ;
	public String ES_CLUSTER_NAME = XoUtil.getConfig(XoAppConfigKeys.ES_CLUSTER_NAME) ;
	
	SearchRequestBuilder requestBuilder;
	SearchResponse response;
	
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
	
	private void prepQueryFilters(String deviceDate, String subsegmentLabel, String region, String status,
			String locationType, String xTile, String yTile, String spendSegment,String lifetimeBucket) {
		
		String[] subSegment = subsegmentLabel.split(",");
		String[] Status = status.split(",");
		String[] LocationType = locationType.split(",");
		String[] Region = region.split(",");
		
		if(spendSegment.equals("null") && lifetimeBucket.equals("null"))
		{
			String[] XTile = xTile.split(",");
			int[] XTyleArray = Arrays.stream(XTile).mapToInt(Integer::parseInt).toArray();
			String[] YTile = yTile.split(",");
			int[] YTileArray = Arrays.stream(YTile).mapToInt(Integer::parseInt).toArray();
			xTileQuery = QueryBuilders.boolQuery();
			for (String XTilelabel : XTile) {
				xTileQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(6),XTilelabel));
			}
			yTileQuery = QueryBuilders.boolQuery();
			for (String YTilelabel : YTile) {
				yTileQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(7),YTilelabel));
			}
		} else {
			String[] SpendSegment = spendSegment.split(",");
			String[] LifetimeBucket = lifetimeBucket.split(",");
			spendSegmentQuery = QueryBuilders.boolQuery();
			for (String spendseg : SpendSegment) {
				spendSegmentQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(8),spendseg));
			}
			lifetimeBucketQuery = QueryBuilders.boolQuery();
			for (String lifetimebucket : LifetimeBucket) {
				lifetimeBucketQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(9),lifetimebucket));
			}
		}
		subSegmentQuery = QueryBuilders.boolQuery();
		for (String subSeg : subSegment) {
			subSegmentQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(2),subSeg));
		}
		regionQuery = QueryBuilders.boolQuery();
		for (String regionlabel : Region) {
			regionQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(3),regionlabel));
		}
		statusQuery = QueryBuilders.boolQuery();
		for (String statuslabel : Status) {
			statusQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(4),statuslabel));
		}
		locationTypeQuery = QueryBuilders.boolQuery();
		for (String locationTypelabel : LocationType) {
			locationTypeQuery.should(QueryBuilders.matchPhraseQuery(ES_ORDER.get(5),locationTypelabel));
		}
		
	}
	
	public SearchRequestBuilder getMsisdns(String deviceDate, String subsegmentLabel, String region, String status,
			String locationType, String xTile, String yTile,String spendSegment,String lifetimeBucket) {
		
		try {
			
			prepESClient();
			if(region.equals("")) {
				region="SOUTH EAST,SOUTH WEST,SOUTH SOUTH,NORTH_1,LAGOS,Unavailable,NORTH_2";
				System.out.println(region);
			}
			if(subsegmentLabel.equals("")) {
				subsegmentLabel="MA,Unknown,HV,EV,LA,Other,EA,ED,HA";
				System.out.println(subsegmentLabel);
			}
			if(locationType.equals("")) {
				locationType="SUBURBAN,RURAL,DENSE URBAN,URBAN,Unavailable";
				System.out.println(locationType);
			}
			prepQueryFilters(deviceDate, subsegmentLabel, region, status, locationType, xTile, yTile , spendSegment , lifetimeBucket );
			
			if(xTile.equals("null") && yTile.equals("null")){
				queryBuilder = QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery(ES_ORDER.get(1),deviceDate))
						.must(statusQuery)
						.must(subSegmentQuery)
						.must(locationTypeQuery)
						.must(spendSegmentQuery)
						.must(lifetimeBucketQuery)
						.must(regionQuery);
			} else {
				queryBuilder = QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery(ES_ORDER.get(1),deviceDate))
						.must(statusQuery)
						.must(subSegmentQuery)
						.must(locationTypeQuery)
						.must(xTileQuery)
						.must(yTileQuery)
						.must(regionQuery);

			}
			
			requestBuilder = client.prepareSearch(ES_INDEX) 
                    .setTypes(ES_TYPE)
                    .setQuery(queryBuilder);
			
			for (String fields : ES_ORDER) {
				requestBuilder.addField(fields);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requestBuilder;
	}
	
	public Long getMsisdnCount(String deviceDate, String subsegmentLabel, String region, String status,
			String locationType, String xTile, String yTile, String spendSegment,String lifetimeBucket) {
		try {
			
			prepESClient();
			if(region.equals("")) {
				region="SOUTH EAST,SOUTH WEST,SOUTH SOUTH,NORTH_1,LAGOS,Unavailable,NORTH_2";
				System.out.println(region);
			}
			if(subsegmentLabel.equals("")) {
				subsegmentLabel="MA,Unknown,HV,EV,LA,Other,EA,ED,HA";
				System.out.println(subsegmentLabel);
			}
			if(locationType.equals("")) {
				locationType="SUBURBAN,RURAL,DENSE URBAN,URBAN,Unavailable";
				System.out.println(locationType);
			}
			prepQueryFilters(deviceDate, subsegmentLabel, region, status, locationType, xTile, yTile , spendSegment, lifetimeBucket);
			
			if(xTile.equals("null") && yTile.equals("null")){
				queryBuilder = QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery(ES_ORDER.get(1),deviceDate))
						.must(statusQuery)
						.must(subSegmentQuery)
						.must(locationTypeQuery)
						.must(spendSegmentQuery)
						.must(lifetimeBucketQuery)
						.must(regionQuery);
			} else {
				queryBuilder = QueryBuilders.boolQuery()
						.must(QueryBuilders.matchPhraseQuery(ES_ORDER.get(1),deviceDate))
						.must(statusQuery)
						.must(subSegmentQuery)
						.must(locationTypeQuery)
						.must(xTileQuery)
						.must(yTileQuery)
						.must(regionQuery);

			}
			
			requestBuilder = client.prepareSearch(ES_INDEX) 
                    .setTypes(ES_TYPE)
                    .setQuery(queryBuilder);
			
			for (String fields : ES_ORDER) {
				requestBuilder.addField(fields);
			}
			//System.out.println(requestBuilder);
			response = requestBuilder.execute().actionGet();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.getHits().getTotalHits();
	}
}
