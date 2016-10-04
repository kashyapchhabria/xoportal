package com.xo.web.viewdtos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class MenuDTO  extends BaseDto<MenuDTO> {

	public String name;
	public String pageUrl;
	public int displayOrder = 1;
	public List<MenuDTO> subMenus = new ArrayList<MenuDTO>();

	public MenuDTO(){}
	public MenuDTO(String name, String URL){
		this.name = name;
		this.pageUrl = URL;
	}

	@Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.name).
        		append(this.pageUrl).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MenuDTO)) {
            return false;
        }
        MenuDTO other = (MenuDTO) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        if ((this.pageUrl == null && other.pageUrl != null) || (this.pageUrl != null && !this.pageUrl.equals(other.pageUrl))) {
            return false;
        }
        return true;
    }
}