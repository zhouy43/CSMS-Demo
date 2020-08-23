package com.macrosan.common;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PageObject<T> implements Serializable{
	private List<T> records;
	private Integer rowCount=0;
	private Integer pageCount=0;
	private Integer pageCurrent=1;
	private Integer pageSize=5;
	public PageObject(List<T> records, Integer rowCount, Integer pageCurrent, Integer pageSize) {
		super();
		this.records = records;
		this.rowCount = rowCount;
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.pageCount=(rowCount-1)/pageSize+1;
	}
}
