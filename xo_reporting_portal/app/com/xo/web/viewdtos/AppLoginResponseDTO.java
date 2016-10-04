package com.xo.web.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class AppLoginResponseDTO  extends BaseDto<AppLoginResponseDTO> {

	public String token;
	public String name;
	public String email;
	public String company;
	public String domain = "xoanonanalytics.net";

	public AppLoginResponseDTO(){}
	public AppLoginResponseDTO(String token, UserDto user){
		this.token = token;
		if(user != null) {
			this.name = user.firstName + " " + user.secondName;
			this.email = user.email;
			this.company = user.clientName;
		}
	}

	@Override
    public int hashCode() {
    	return new HashCodeBuilder().
        		append(this.token).
        		append(this.email).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AppLoginResponseDTO)) {
            return false;
        }
        AppLoginResponseDTO other = (AppLoginResponseDTO) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }
}