package com.briup.apps.poll.service.impl;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.extend.GradeVM;
import com.briup.apps.poll.dao.GradeMapper;
import com.briup.apps.poll.dao.extend.GradeVMMapper;
import com.briup.apps.poll.service.IGradeService;

@Service
public class GradeServiceImpl implements IGradeService{

	@Autowired
	private GradeMapper gradeMapper;
	
	@Autowired
	private GradeVMMapper gradeVMMapper;
	
//	//查找所有
//	@Override
//	public List<Grade> findAll() throws Exception {
//	
//		GradeExample example = new GradeExample();
//		return gradeMapper.selectByExampleWithBLOBs(example);
//	}

	//按id查找
	@Override
	public GradeVM selectById(long id) throws Exception {
		
		return gradeVMMapper.selectById(id);
	}
	

	//按关键字查找
	@Override
	public List<GradeVM> query(String keywords) throws Exception {
		
//		GradeExample example = new GradeExample();
//		example.createCriteria().andNameLike("%"+keywords+"%");
		return gradeVMMapper.query(keywords);
	}

	
	//保存及修改
	@Override
	public void saveOrUpdate(Grade grade) throws Exception {
		
		if(grade.getId()!=null){
			gradeMapper.updateByPrimaryKeyWithBLOBs(grade);
		}else{
			gradeMapper.insert(grade);
		}
		
	}

	
	//按id删除
	@Override
	public void deleteById(long id) throws Exception {
		
		gradeMapper.deleteByPrimaryKey(id);
	}

	
	//批量删除
	@Override
	public void batchDelete(Long[] ids) throws Exception {
		
		for(long id : ids)
			gradeMapper.deleteByPrimaryKey(id);
		
	}

	
	//外键查询school_id
	@Override
	public List<GradeVM> selectAll() throws Exception {
		
		return gradeVMMapper.selectAll();
	}
}
