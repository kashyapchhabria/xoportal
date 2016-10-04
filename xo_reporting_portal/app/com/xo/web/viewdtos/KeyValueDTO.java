package com.xo.web.viewdtos;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author sekar
 *
 */
@SuppressWarnings("serial")
public class KeyValueDTO  extends BaseDto<KeyValueDTO>{

	public Object key;
	public Object value;

	public KeyValueDTO(){}

	public KeyValueDTO(Object key, Object value){
		this.key = key;
		this.value = value;
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder().append(this.key).
				append(this.value).toHashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof UserDto)) {
            return false;
        }
        KeyValueDTO other = (KeyValueDTO) object;
        if ((this.key == null && other.key != null) || (this.key != null && !this.key.equals(other.key))) {
            return false;
        }
        return true;
    }

    public String toString() {
    	return this.key.toString() + ":" + this.value.toString();
    }
}
