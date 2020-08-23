package com.macrosan.vo;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkHourVo extends BasePojo{
	private static final long serialVersionUID = -2879312565954405130L;
	private Integer id;
	private Integer workOrderId;
	private Integer hours;
	private String relatedProjectName;
	private String principalUser;
	private String note;
}
