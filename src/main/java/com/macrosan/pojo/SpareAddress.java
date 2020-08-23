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
public class SpareAddress extends BasePojo {
	private static final long serialVersionUID = 7479616519607625728L;
	private Integer id;
	private String address;
	private String note;
}
