package com.macrosan.service;

import java.util.List;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Node;
import com.macrosan.pojo.WorkOrder;

public interface WorkOrderService {
	PageObject<WorkOrder> findObjects(String orderName, Integer pageCurrent);
	int deleteObjects(Integer[] ids);
	List<Node> findZTreeNodes();
	int saveObject(WorkOrder workOrder);
	WorkOrder findObjectById(Integer id);
	int updateObject(WorkOrder workOrder);
	List<WorkOrder> findOrderByName(String orderName);
}
