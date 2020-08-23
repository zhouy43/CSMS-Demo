package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.Node;
import com.macrosan.pojo.WorkOrder;

@Mapper
public interface WorkOrderMapper {
	List<WorkOrder> findObjects(@Param("orderName")String orderName, 
								@Param("startIndex")Integer startIndex,
								@Param("pageSize")Integer pageSize);
	Integer getRowCount(@Param("orderName")String orderName);
	int saveObject(@Param("workOrder")WorkOrder workOrder);
	int deleteObjects(@Param("ids")Integer[] ids);
	List<Node> findZTreeNodes();
	WorkOrder findObjectById(Integer id);
	int updateObject(@Param("workOrder")WorkOrder workOrder);
	List<WorkOrder> findObjectByName(@Param("orderName")String orderName);
}
