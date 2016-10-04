package com.xo.web.viewdtos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sekar
 *
 */
@SuppressWarnings("serial")
public class ScreenDTO  extends BaseDto<ScreenDTO>{

	public List<BreadCrumbDTO> breadCrumbDtos =  new ArrayList<BreadCrumbDTO>();
	public List<MenuDTO> menuDtos = new ArrayList<MenuDTO>();
	public List<BaseDto<?>> contentDtos = new ArrayList<BaseDto<?>>();
	public String imageUrl = "";
	public String placeHolderImageUrl = "";
	public String errorText;
	public boolean isMainDashboard = false;
	
	public ScreenDTO(){}

	public ScreenDTO(List<MenuDTO> menuDTOs, List<BreadCrumbDTO> breadCrumbDTOs, List<BaseDto<?>> contentDto){
		this.menuDtos = menuDTOs;
		this.breadCrumbDtos = breadCrumbDTOs;
		this.contentDtos = contentDto;
	}
}
