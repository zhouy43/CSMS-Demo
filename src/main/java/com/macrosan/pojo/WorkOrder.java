package com.macrosan.pojo;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder extends BasePojo{
	private static final long serialVersionUID = -1702979563370273947L;
	private Integer id;
	   private String status;
	   private String name;
	   private String gdType;
	   private String principalUser;
	   private String relatedProject;
}
