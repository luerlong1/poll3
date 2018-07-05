package com.briup.apps.poll.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.School;
import com.briup.apps.poll.bean.SchoolExample;
import com.briup.apps.poll.dao.SchoolMapper;
import com.briup.apps.poll.service.ISchoolService;

@Service
public class SchoolService  implements ISchoolService{
	
	@Autowired
	private SchoolMapper schoolMapper;
	
	@Override
	public List<School> findAll() throws Exception {
		SchoolExample example = new SchoolExample();
		return schoolMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public School findSchoolById(long id) throws Exception {
		return schoolMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<School> findSchoolByKeyword(String keywords) throws Exception {
		SchoolExample example = new SchoolExample();
		example.createCriteria().andNameLike(keywords);
		return schoolMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdateSchool(School school) throws Exception {
		// TODO Auto-generated method stub
		if(school.getId()!=null){
			schoolMapper.updateByPrimaryKeyWithBLOBs(school);
		}
		else{
			schoolMapper.insert(school);
		}
		
	}

	@Override
	public void deleteSchoolById(long id) throws Exception {
		
		schoolMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void bathDeleteSchool(List<Long> ids) throws Exception {
		for(long id:ids)
		{
			schoolMapper.deleteByPrimaryKey(id);
		}
		
	}

}
