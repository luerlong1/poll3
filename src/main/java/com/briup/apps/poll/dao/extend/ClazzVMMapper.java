package com.briup.apps.poll.dao.extend;

import java.util.List;

import com.briup.apps.poll.bean.extend.ClazzVM;

public interface ClazzVMMapper {
	
	List<ClazzVM> selectAll();

	ClazzVM selectAllClazz(Long id);
}
