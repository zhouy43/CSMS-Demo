package com.macrosan.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.WorkOrderMapper;
import com.macrosan.pojo.Node;
import com.macrosan.pojo.WorkOrder;
import com.macrosan.service.WorkOrderService;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
	
	@Autowired
	private WorkOrderMapper workOrderMapper;

	@Override
	public PageObject<WorkOrder> findObjects(String orderName, Integer pageCurrent) {
		if(pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("当前页码不正确");
		int rowCount = workOrderMapper.getRowCount(orderName);
		if(rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		};
		int pageSize = 5;
		int startIndex = (pageCurrent-1)*pageSize;
		List<WorkOrder> workOrderList = workOrderMapper.findObjects(orderName, startIndex, pageSize);
		PageObject<WorkOrder> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent)
				.setPageSize(pageSize)
				.setRecords(workOrderList)
				.setRowCount(rowCount)
				.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}

	@RequiresPermissions("sys:manager")
	@Override
	public int deleteObjects(Integer[] ids) {
		if(ids.length <= 0 || ids == null) {
			throw new IllegalArgumentException("参数不合法");
		}
		int rows = 0;
		try {
			rows = workOrderMapper.deleteObjects(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统故障,请联系管理员");
		}
		if(rows==0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return rows;
	}

	@Override
	public List<Node> findZTreeNodes() {
		List<Node> zTreeNodes = workOrderMapper.findZTreeNodes();
		return zTreeNodes;
	}

	@RequiresPermissions("sys:manager")
	@Override
	public int saveObject(WorkOrder workOrder) {
		int row = 0;
		if(StringUtils.isEmpty(workOrder.getName())) {
			throw new IllegalArgumentException("工单名称不能为空");
		}
		if(StringUtils.isEmpty(workOrder.getRelatedProject())) {
			throw new IllegalArgumentException("工单必须关联项目");
		}
		if(StringUtils.isEmpty(workOrder.getGdType())) {
			throw new IllegalArgumentException("工单类型不能为空");
		}
		if(StringUtils.isEmpty(workOrder.getPrincipalUser())) {
			throw new IllegalArgumentException("工单必须指定负责人");
		}
		String name = ""+workOrder.getRelatedProject()+"###"+workOrder.getName();
		workOrder.setStatus("开启").setName(name).setCreatedTime(new Date()).setModifiedTime(new Date());
		try {
			row = workOrderMapper.saveObject(workOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("工单信息保存失败");
		}
		return row;
	}

	@Override
	public WorkOrder findObjectById(Integer id) {
		if(id==null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		WorkOrder workOrder = workOrderMapper.findObjectById(id);
		return workOrder;
	}

	@RequiresPermissions("sys:manager")
	@Override
	public int updateObject(WorkOrder workOrder) {
		int row = 0;
		if(StringUtils.isEmpty(workOrder.getName())) {
			throw new IllegalArgumentException("工单名称不能为空");
		}
		if(StringUtils.isEmpty(workOrder.getGdType())) {
			throw new IllegalArgumentException("工单状态不能为空");
		}
		if(StringUtils.isEmpty(workOrder.getRelatedProject())) {
			throw new IllegalArgumentException("工单必须关联项目");
		}
		if(StringUtils.isEmpty(workOrder.getGdType())) {
			throw new IllegalArgumentException("工单类型不能为空");
		}
		if(StringUtils.isEmpty(workOrder.getPrincipalUser())) {
			throw new IllegalArgumentException("工单必须指定负责人");
		}
//		String name = ""+workOrder.getRelatedProject()+"###"+workOrder.getName();
//		workOrder.setName(name);
		workOrder.setModifiedTime(new Date());
		try {
			row = workOrderMapper.updateObject(workOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("工单信息更新失败");
		}
		return row;
	}

	@Override
	public List<WorkOrder> findOrderByName(String orderName) {
		List<WorkOrder> orderList = workOrderMapper.findObjectByName(orderName);
		return orderList;
	}
	
}
