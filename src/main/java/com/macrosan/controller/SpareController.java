package com.macrosan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Spare;
import com.macrosan.pojo.SpareAddress;
import com.macrosan.service.SpareService;
import com.macrosan.vo.SysResult;

@RestController
@RequestMapping("/spares/")
public class SpareController {
	@Autowired
	private SpareService spareService;
	
	@RequestMapping("doGetSpareAddress")
	public SysResult doGetSpareAddress(Integer pageCurrent) {
		PageObject<SpareAddress> pageObject = spareService.getAllAddress(pageCurrent);
		return SysResult.success(pageObject);
	}
	//spares/doSaveSpareAddress 
	@RequestMapping("doSaveSpareAddress")
	public SysResult doSaveSpareAddress(SpareAddress spareAddress) {
		int row = spareService.saveSpareAddress(spareAddress);
		return SysResult.success(row);
	}
	//http://localhost:8100/spares/doDeleteObject 
	@RequestMapping("doDeleteObject")
	public SysResult doDeleteSpareAddress(Integer id) {
		int row = spareService.deleteSpareAddress(id);
		return SysResult.success(row);
	}
	//deGetSpareAddressById
	@RequestMapping("deGetSpareAddressById")
	public SysResult doFindSpareAddressById(Integer id) {
		SpareAddress spareAddressData = spareService.FindSpareAddressById(id);
		return SysResult.success(spareAddressData);
	}
	//http://localhost:8100/spares/doUpdateSpareAddress
	@RequestMapping("doUpdateSpareAddress")
	public SysResult doUpdateSpareAddress(SpareAddress spareAddress) {
		int row = spareService.updateSpareAddress(spareAddress);
		return SysResult.success(row);
	}
	//http://localhost:8100/spares/doGetAllSpareAddress
	@RequestMapping("doGetAllSpareAddress")
	public SysResult doGetAllSpareAddress() {
		List<SpareAddress> addressList = spareService.getAllAddress();
		return SysResult.success(addressList);
	}
	//http://localhost:8100/spares/doSaveObject
	@RequestMapping("doSaveObject")
	public SysResult doSaveSpareObject(Spare spare) {
		int row = spareService.saveSpareObject(spare);
		return SysResult.success(row);
	}
	//http://localhost:8100/spares/doGetSpareList?pageCurrent=1
	@RequestMapping("doGetSpareList")
	public SysResult doGetSpareList(String proName,Integer pageCurrent) {
		PageObject<Spare> pageObject = spareService.findSpareList(proName,pageCurrent);
		return SysResult.success(pageObject);
	}
	//http://localhost:8100/spares/doDeleteObjects
	@RequestMapping("doDeleteObjects")
	public SysResult doDeleteSpareObject(Integer[] ids) {
		int rows = spareService.deleteSpareObjects(ids);
		return SysResult.success(rows);
	}
	
}
