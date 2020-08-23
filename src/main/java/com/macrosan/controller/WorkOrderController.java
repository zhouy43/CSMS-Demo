package com.macrosan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Node;
import com.macrosan.pojo.WorkOrder;
import com.macrosan.service.WorkOrderService;
import com.macrosan.vo.SysResult;

@RestController
@RequestMapping("/workorders/")
public class WorkOrderController {
	
	@Autowired
	private WorkOrderService workOrderService;
	
	//查询工单信息
	@RequestMapping("doFindPageObjects")
	public SysResult doFindPageObjects(String orderName,Integer pageCurrent) {
		PageObject<WorkOrder> pageObject = workOrderService.findObjects(orderName,pageCurrent);
		return SysResult.success(pageObject);
	}
	/*
	 * http://localhost:8100/workorders/doDeleteObjects 
	 * POST
	 * 参数:ids
	 */
	@RequestMapping("doDeleteObjects")
	public SysResult doDeletePageObjects(Integer[] ids) {
		workOrderService.deleteObjects(ids);
		return SysResult.success();
	}
	
	@RequestMapping("doFindZTreeNodes")
	public SysResult doFindZTreeNodes() {
		List<Node> nodes = workOrderService.findZTreeNodes();
		return SysResult.success(nodes);
	}
	
	@RequestMapping("doSaveObject")
	public SysResult doSaveObject(WorkOrder workOrder) {
		workOrderService.saveObject(workOrder);
		return SysResult.success();
	}
	/*
	 * http://localhost:8100/workorders/doFindObjectById?id=9 
	 */
	@RequestMapping("doFindObjectById")
	public SysResult doFindObjectById(Integer id) {
		return SysResult.success(workOrderService.findObjectById(id));
	}
	/*
	 * http://localhost:8100/workorders/doUpdateObject
	 */ 
	@RequestMapping("doUpdateObject")
	public SysResult doUpdateObject(WorkOrder workOrder) {
		return SysResult.success(workOrderService.updateObject(workOrder));
	}
	
	@RequestMapping("doFindOrdersByName")
	public SysResult doFindOrdersByName(String orderName) {
		List<WorkOrder> workOrder = workOrderService.findOrderByName(orderName);
		return SysResult.success(workOrder);
	}
	
}
