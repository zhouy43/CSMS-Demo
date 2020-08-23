package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.Spare;
import com.macrosan.pojo.SpareAddress;

@Mapper
public interface SpareMapper {
	List<SpareAddress> getAllAddress(Integer pageSize, Integer startIndex);
	int getRowCount();
	int saveSpareAddress(@Param("spareAddress")SpareAddress spareAddress);
	int deleteSpareAddress(Integer id);
	SpareAddress FindSpareAddressById(Integer id);
	int updateSpareAddress(@Param("address")SpareAddress spareAddress);
	List<SpareAddress> getAllSpareAddressAsJSON();
	int saveSpareObject(@Param("spare")Spare spare);
	int getSpareRowCount(String proName);
	List<Spare> findSpareObjects(@Param("proName")String proName, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);
	int deleteSpareObjects(@Param("ids")Integer[] ids);
}
