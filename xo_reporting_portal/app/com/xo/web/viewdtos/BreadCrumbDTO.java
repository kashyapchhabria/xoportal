package com.xo.web.viewdtos;

/**
 * @author sekar
 *
 */
@SuppressWarnings("serial")
public class BreadCrumbDTO  extends BaseDto<BreadCrumbDTO>{

	public String name;
	public String pageUrl;
	public String imageUrl;

	public BreadCrumbDTO(){}
	public BreadCrumbDTO(String name, String pageUrl, String imageUrl){
		this.name = name;
		this.pageUrl = pageUrl;
		this.imageUrl = imageUrl;
	}
}
