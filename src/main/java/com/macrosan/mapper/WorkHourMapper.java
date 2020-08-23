package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.WorkHour;
import com.macrosan.vo.WorkHourVo;

@Mapper
public interface WorkHourMapper {
	List<WorkHourVo> findObjects(@Param("proName")String proName, 
								@Param("startIndex")Integer startIndex,
								@Param("pageSize")Integer pageSize);
	int getRowCount(@Param("proName")String proName);
	int saveObject(@Param("workHour")WorkHour workHour);
	int deleteObjects(@Param("ids")Integer[] ids);
	WorkHour findObjectById(@Param("id")Integer id);
	int updateObject(@Param("workHour")WorkHour workHour);
}
