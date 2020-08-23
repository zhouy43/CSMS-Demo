package com.macrosan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.WorkHour;
import com.macrosan.service.WorkHourService;
import com.macrosan.vo.SysResult;
import com.macrosan.vo.WorkHourVo;

@RestController
@RequestMapping("/workhours/")
public class WorkHourController {
	
	@Autowired
	private WorkHourService workHourService;

	@RequestMapping("doFindPageObjects")
	public SysResult doFindPageObjects(String proName,Integer pageCurrent) {
		PageObject<WorkHourVo> pageObject= workHourService.findObjects(proName,pageCurrent);
		return SysResult.success(pageObject);
	}
	/*
	 * http://localhost:8100/workorders/doSaveObject 
	 */
	@RequestMapping("doSaveObject")
	public SysResult doSaveObject(WorkHourVo workHourVo,String relatedProjectName) {
		int row = workHourService.saveObject(workHourVo,relatedProjectName);
		if(row >= 1) {
			return SysResult.success();
		} else {
			return SysResult.fail();
		}
	}
	/*
	 * workhours/doDeleteObjects 
	 */
	@RequestMapping("doDeleteObjects")
	public SysResult doDelectObjects(Integer[] ids) {
		int rows = workHourService.deleteObjects(ids);
		return SysResult.success(rows);
	}
	
	/*
	 * http://localhost:8100/workhours/doFindObjectById?id=6 
	 */
	@RequestMapping("doFindObjectById")
	public SysResult doFindObjectById(Integer id) {
		WorkHour workHour = workHourService.findWorkHourById(id);
		return SysResult.success(workHour);
	}
	
	/*
	 * http://localhost:8100/workhours/doUpdateObject 
	 */
	@RequestMapping("doUpdateObject")
	public SysResult deUpdateObject(WorkHour workHour) {
		int row = workHourService.updateObject(workHour);
		return SysResult.success(row);
	}
}
