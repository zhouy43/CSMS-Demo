package com.macrosan.service;

import java.util.List;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Spare;
import com.macrosan.pojo.SpareAddress;

public interface SpareService {
	PageObject<SpareAddress> getAllAddress(Integer pageCurrent);
	int saveSpareAddress(SpareAddress spareAddress);
	int deleteSpareAddress(Integer id);
	SpareAddress FindSpareAddressById(Integer id);
	int updateSpareAddress(SpareAddress spareAddress);
	List<SpareAddress> getAllAddress();
	int saveSpareObject(Spare spare);
	PageObject<Spare> findSpareList(String proName, Integer pageCurrent);
	int deleteSpareObjects(Integer[] ids);
}
