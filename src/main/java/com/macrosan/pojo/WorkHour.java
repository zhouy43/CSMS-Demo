package com.macrosan.pojo;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WorkHour extends BasePojo{
	private static final long serialVersionUID = -6955051682446429490L;
	private Integer id;
	private Integer workOrderId;
	private Integer hours;
	private Integer relatedProjectId;
	private String principalUser;
	private String note;
}
