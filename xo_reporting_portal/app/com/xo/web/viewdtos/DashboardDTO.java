package com.xo.web.viewdtos;

/**
 * @author sekar
 *
 */
@SuppressWarnings("serial")
public class DashboardDTO extends BaseDto<DashboardDTO>{

	public String name;
	public String pageUrl;
	public String imageUrl;

	public DashboardDTO(){}
	public DashboardDTO(String name, String pageUrl, String imageUrl){
		this.name = name;
		this.pageUrl = pageUrl;
		this.imageUrl = imageUrl;
	}
}
