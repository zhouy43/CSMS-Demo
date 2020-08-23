package com.macrosan.vo;

import java.util.List;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRoleVo extends BasePojo{
	private static final long serialVersionUID = -8987724028447105961L;
	private Integer id;
	private String userName;
	private String roleName;
}
