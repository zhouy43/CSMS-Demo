package com.macrosan.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysResult implements Serializable {
	private Integer status = 200; // 标识状态码 200为成功,201为失败
	private String msg = "ok"; // 提示信息
	private Object data; // 回传页面数据信息

	// 成功
	public static SysResult success() {
		return new SysResult(200, "操作成功", null);
	}

	public static SysResult success(Object data) {
		return new SysResult(200, "操作成功", data);
	}

	public static SysResult success(String msg, Object data) {
		return new SysResult(200, msg, data);
	}

	// 失败
	public static SysResult fail() {
		return new SysResult(201, "操作失败", null);
	}
	
	public static SysResult fail(String msg) {
		return new SysResult(201, msg, null);
	}

}
