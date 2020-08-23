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
public class Spare extends BasePojo {
	private static final long serialVersionUID = -1217275421166554191L;
	private Integer id;
	private Integer workOrderId;
	private String recipients;
	private String phone;
	private String address;
	private String materialsCode;
	private String materialsName;
	private Integer materialsNum;
	private String materialsNote;
}
