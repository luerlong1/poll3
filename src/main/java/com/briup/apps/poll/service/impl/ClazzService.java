package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.ClazzExample;
import com.briup.apps.poll.bean.extend.ClazzVM;
import com.briup.apps.poll.dao.ClazzMapper;
import com.briup.apps.poll.dao.extend.ClazzVMMapper;
import com.briup.apps.poll.service.IClazzService;
@Service
public class ClazzService implements IClazzService {
	@Autowired
	private ClazzMapper clazzMapper;
	
	@Autowired
	private ClazzVMMapper clazzVMMapper;
	
	@Override
	public List<Clazz> findAll() throws Exception {
		// TODO Auto-generated method stub
		ClazzExample example=new ClazzExample();
		return clazzMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Clazz findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return clazzMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Clazz> query(String keyword) throws Exception {
		// TODO Auto-generated method stub
		ClazzExample example=new ClazzExample();
		example.createCriteria().andNameLike(keyword);
		return clazzMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(Clazz clazz) throws Exception {
		// TODO Auto-generated method stub
		if (clazz.getId()!=null) {
			clazzMapper.updateByPrimaryKeyWithBLOBs(clazz);
		} else {
			clazzMapper.insert(clazz);
		}
		
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		clazzMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		// TODO Auto-generated method stub

		for(Long id:ids){
			clazzMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<ClazzVM> findAllClazzVM() throws Exception {
		// TODO Auto-generated method stub
		return clazzVMMapper.selectAll();
	}

}
