package com.macrosan.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.SpareMapper;
import com.macrosan.pojo.Spare;
import com.macrosan.pojo.SpareAddress;
import com.macrosan.service.SpareService;

@Service
public class SpareServiceImpl implements SpareService {
	@Autowired
	private SpareMapper spareMapper;

	@Override
	public PageObject<SpareAddress> getAllAddress(Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("页码参数错误");
		}
		int rowCount = spareMapper.getRowCount();
		if (rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		}
		Integer pageSize = 5;
		Integer startIndex = (pageCurrent - 1) * pageSize;
		List<SpareAddress> addressList = spareMapper.getAllAddress(pageSize, startIndex);
		PageObject<SpareAddress> pageObject = new PageObject<>(addressList, rowCount, pageCurrent, pageSize);
		return pageObject;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int saveSpareAddress(SpareAddress spareAddress) {
		if (spareAddress == null) {
			throw new IllegalArgumentException("提交参数不能为空");
		}
		if (StringUtils.isEmpty(spareAddress.getAddress())) {
			throw new IllegalArgumentException("备件地址不能为空");
		}
		int row = spareMapper.saveSpareAddress(spareAddress);
		return row;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int deleteSpareAddress(Integer id) {
		if (id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		int row = spareMapper.deleteSpareAddress(id);
		if (row == 0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return row;
	}

	@Override
	public SpareAddress FindSpareAddressById(Integer id) {
		if (id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		SpareAddress address = spareMapper.FindSpareAddressById(id);
		if(address == null) {
			throw new ServiceException("未找到相关记录");
		}
		return address;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int updateSpareAddress(SpareAddress spareAddress) {
		if(StringUtils.isEmpty(spareAddress.getId())) {
			throw new IllegalArgumentException("ID不能为空"); 
		}
		int row = spareMapper.updateSpareAddress(spareAddress);
		if(row == 0) {
			throw new ServiceException("记录更新失败");
		}
		return row;
	}

	@Override
	public List<SpareAddress> getAllAddress() {
		List<SpareAddress> addressList = spareMapper.getAllSpareAddressAsJSON();
		if(addressList.size() <= 0) {
			throw new ServiceException("未查询到相关记录");
		}
		return addressList;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int saveSpareObject(Spare spare) {
		//参数校验
		if(StringUtils.isEmpty(spare.getWorkOrderId())) {
			throw new IllegalArgumentException("关联工单信息不能为空");
		}
		if(StringUtils.isEmpty(spare.getRecipients()) || StringUtils.isEmpty(spare.getPhone()) || StringUtils.isEmpty(spare.getAddress())) {
			throw new IllegalArgumentException("收件人信息不能为空");
		}
		if(StringUtils.isEmpty(spare.getMaterialsCode()) || StringUtils.isEmpty(spare.getMaterialsName()) || StringUtils.isEmpty(spare.getMaterialsNum())) {
			throw new IllegalArgumentException("申请物料信息不能为空");
		}
		spare.setCreatedTime(new Date()).setModifiedTime(new Date());
		int row = spareMapper.saveSpareObject(spare);
		if(row > 0) {
			return row;
		} else {
			throw new ServiceException("备件单信息保存失败");
		}
		
	}

	@Override
	public PageObject<Spare> findSpareList(String proName, Integer pageCurrent) {
		if(pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("当前页码不正确");
		int rowCount = spareMapper.getSpareRowCount(proName);
		if(rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		};
		int pageSize = 5;
		int startIndex = (pageCurrent-1)*pageSize;
		List<Spare> projectsList = spareMapper.findSpareObjects(proName,startIndex,pageSize);
		PageObject<Spare> pageobject = new PageObject<>();
		pageobject.setPageCurrent(pageCurrent)
				.setPageSize(pageSize)
				.setRecords(projectsList)
				.setRowCount(rowCount)
				.setPageCount((rowCount-1)/pageSize+1);
		return pageobject;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int deleteSpareObjects(Integer[] ids) {
		if(ids.length < 1 || ids == null) {
			throw new IllegalArgumentException("ID参数不合法");
		}
		int rows = spareMapper.deleteSpareObjects(ids);
		if(rows == 0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return rows;
	}
}
