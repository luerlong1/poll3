package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.bean.OptionsExample;
import com.briup.apps.poll.dao.OptionsMapper;
import com.briup.apps.poll.service.IOptionsService;

@Service
public class OptionsService	implements IOptionsService {
	@Autowired
	private OptionsMapper optionsMapper;
	//查询所有
	@Override
	public List<Options> findAllOptions() throws Exception {
		
		OptionsExample example=new OptionsExample();
		//数据表中description类型为text 数据比较大 所以用selectByExampleWithBLOBs(example)方法
		return optionsMapper.selectByExampleWithBLOBs(example);
	}
	//根据id查询
	@Override
	public Options findById(long id) throws Exception {
		
		return optionsMapper.selectByPrimaryKey(id);
	}
	//根据关键字查询
	@Override
	public List<Options> query(String keywords) throws Exception {
		OptionsExample example = new OptionsExample();
		
		example.createCriteria().andLabelLike(keywords);
		//andLabeilike为模糊查询
		return optionsMapper.selectByExampleWithBLOBs(example);
	}
	//更新或者插入
	@Override
	public int saveOrUpdate(Options options) throws Exception {
		if(options.getId()==null) {
			int count=optionsMapper.insert(options);
			return  count;
		}else {
			int count=optionsMapper.updateByPrimaryKey(options);
			return count;
		}
	}
	//根据id删除
	@Override
	public void deleteById(long id) throws Exception {
		optionsMapper.deleteByPrimaryKey(id);
		
	}
	//根据集合id删除
	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		for(long id:ids) {
			optionsMapper.deleteByPrimaryKey(id);
		}
		
	}
	
	

}
