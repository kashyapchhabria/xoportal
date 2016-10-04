package com.xo.web.viewdtos;

import java.util.Collection;

/**
 * @author sekar
 *
 */
public class PaginatedRecords extends BaseDto<PaginatedRecords>{

	public Integer start = -1;
	public Integer length = 10;
	public Collection<?> data;
	public Long recordsTotal;

	public PaginatedRecords(){}

	public PaginatedRecords(Integer fromIndex, Integer rowLimit, Long totalRecords, Collection<?> records){
		this.start = fromIndex;
		this.length = rowLimit;
		this.recordsTotal = totalRecords;
		this.data = records;
	}
}
