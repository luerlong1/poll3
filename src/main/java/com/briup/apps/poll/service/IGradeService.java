package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.extend.GradeVM;

public interface IGradeService {
	

    //根据id查找所有(school_id)
    GradeVM selectById(long id) throws Exception;
	
    //根据关键字查找(school_id)
	List<GradeVM> query(String keywords) throws Exception;
	
	//保存及修改
	void saveOrUpdate(Grade grade) throws Exception;
	
	//根据id删除
	void deleteById(long id) throws Exception;
	
	//批量删除
	void batchDelete(Long[] ids) throws Exception;
	
	
	//外键链接查询所有(school_id)
	List<GradeVM> selectAll() throws Exception;

}
