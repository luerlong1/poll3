package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Qq;
import com.briup.apps.poll.bean.QqExample;
import com.briup.apps.poll.bean.extend.QqVM;
import com.briup.apps.poll.dao.QqMapper;
import com.briup.apps.poll.dao.extend.QqVMMapper;
import com.briup.apps.poll.service.IQqService;

@Service
public class QqService implements IQqService {

	@Autowired
	private QqMapper qqMapper;
	@Autowired
	private QqVMMapper qqVMMapper;
	@Override
	public List<Qq> findAll() throws Exception {
		// TODO Auto-generated method stub
		QqExample example = new QqExample();
		
		return qqMapper.selectByExample(example);
	}

	@Override
	public Qq findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return qqMapper.selectByPrimaryKey(id);
	}



	@Override
	public void saveOrUpdate(Qq qq) throws Exception {
		// TODO Auto-generated method stub

		if(qq.getId()!=null){
			//更新
			qqMapper.updateByPrimaryKey(qq);
		}else{
			//插入
			qqMapper.insert(qq);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub

		qqMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		// TODO Auto-generated method stub

		for(long id:ids){
			qqMapper.deleteByPrimaryKey(id);
		}
	}



	@Override
	public List<QqVM> findAllQqVM() throws Exception {
		// TODO Auto-generated method stub
		return qqVMMapper.selectAll();
	}




}
