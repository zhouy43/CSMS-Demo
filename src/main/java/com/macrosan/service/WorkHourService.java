package com.macrosan.service;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.WorkHour;
import com.macrosan.vo.WorkHourVo;

public interface WorkHourService {
	PageObject<WorkHourVo> findObjects(String proName, Integer pageCurrent);
	int saveObject(WorkHourVo workHourVo, String relatedProjectName);
	int deleteObjects(Integer[] ids);
	WorkHour findWorkHourById(Integer id);
	int updateObject(WorkHour workHour);
}
