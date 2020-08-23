package com.macrosan.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.ProjectMapper;
import com.macrosan.mapper.WorkHourMapper;
import com.macrosan.pojo.Project;
import com.macrosan.pojo.WorkHour;
import com.macrosan.service.WorkHourService;
import com.macrosan.vo.WorkHourVo;

@Service
public class WorkHourServiceImpl implements WorkHourService {
	@Autowired
	private WorkHourMapper workHourMapper;
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public PageObject<WorkHourVo> findObjects(String proName, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = workHourMapper.getRowCount(proName);
		if (rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		}
		;
		int pageSize = 5;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<WorkHourVo> WorkHourVo = workHourMapper.findObjects(proName, startIndex, pageSize);
		PageObject<WorkHourVo> pageObject = new PageObject<>();
		pageObject.setPageCount((rowCount - 1) / pageSize + 1).setPageCurrent(pageCurrent).setPageSize(pageSize)
				.setRecords(WorkHourVo).setRowCount(rowCount);
		return pageObject;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int saveObject(WorkHourVo workHourVo, String relatedProjectName) {
		if (StringUtils.isEmpty(workHourVo.getWorkOrderId())
				|| StringUtils.isEmpty(workHourVo.getRelatedProjectName())) {
			throw new IllegalArgumentException("工单编号和关联项目不能为空");
		}
		if (StringUtils.isEmpty(workHourVo.getHours()) || StringUtils.isEmpty(workHourVo.getPrincipalUser())
				|| StringUtils.isEmpty(workHourVo.getNote())) {
			throw new IllegalArgumentException("工时相关信息不能为空");
		}
		// 根据关联项目名字查询项目编号
		// 项目工单中名称为拼接,需要处理
		String[] splitName = relatedProjectName.split("###");
		Integer relatedProjectId = 0;
		Project projectsFinded = projectMapper.findObjectsByName(splitName[0]).get(0);
		relatedProjectId = projectsFinded.getId();
		// 新建对象
		WorkHour workHour = new WorkHour();
		workHour.setHours(workHourVo.getHours()).setWorkOrderId(workHourVo.getWorkOrderId())
				.setRelatedProjectId(relatedProjectId).setPrincipalUser(workHourVo.getPrincipalUser())
				.setNote(workHourVo.getNote()).setCreatedTime(new Date()).setModifiedTime(new Date());
		int row = workHourMapper.saveObject(workHour);
		return row;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int deleteObjects(Integer[] ids) {
		if (ids.length <= 0 || ids == null) {
			throw new IllegalArgumentException("请选择要删除的工时");
		}
		int rows = workHourMapper.deleteObjects(ids);
		if(rows == 0) {
			throw new ServiceException("未找到该记录");
		}
		return rows;
	}

	@Override
	public WorkHour findWorkHourById(Integer id) {
		if(StringUtils.isEmpty(id) || id < 1) {
			throw new IllegalArgumentException("请先选择工时记录");
		}
		WorkHour workHour = workHourMapper.findObjectById(id);
		if(workHour == null) {
			throw new ServiceException("未查到相关的记录");
		}
		return workHour;
	}

	@RequiresPermissions("sys:engineer")
	@Override
	public int updateObject(WorkHour workHour) {
		if(StringUtils.isEmpty(workHour.getId())) {
			throw new IllegalArgumentException("ID不能为空");
		}
		if(StringUtils.isEmpty(workHour.getHours()) || StringUtils.isEmpty(workHour.getNote())) {
			throw new IllegalArgumentException("工时信息和工作内容不能为空");
		}
		int row = workHourMapper.updateObject(workHour);
		return row;
	}
}
