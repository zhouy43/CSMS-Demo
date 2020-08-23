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
public class Project extends BasePojo {
	private Integer id;
	private String name;
	private String serviceDept;
	private String weibao;
	private String proSource;
	private String proWeihu;
	private String proType;
}
