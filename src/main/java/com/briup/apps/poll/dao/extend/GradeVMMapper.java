package com.briup.apps.poll.dao.extend;

import java.util.List;

import com.briup.apps.poll.bean.extend.GradeVM;


/*
 * 级联表查找
 * 
 * */
public interface GradeVMMapper {
	
	//查找所有
	List<GradeVM> selectAll();
	
	//按id查找
	GradeVM selectById(Long id);
	 
	//按关键字查找
	List<GradeVM> query(String keywords);
	 
	 


}
