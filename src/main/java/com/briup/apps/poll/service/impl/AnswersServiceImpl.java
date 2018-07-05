package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.AnswersExample;
import com.briup.apps.poll.bean.extend.AnswersVM;
import com.briup.apps.poll.dao.AnswersMapper;
import com.briup.apps.poll.dao.extend.AnswersVMMapper;
import com.briup.apps.poll.service.IAnswersService;
/**
 * 
 * @author luerlong
 *
 */
@Service
public class AnswersServiceImpl implements IAnswersService {

	@Autowired
	private AnswersMapper answersMapper;
	@Autowired
	private AnswersVMMapper answersVMMapper;

	@Override
	public Answers findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return answersMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Answers> query(String keywords) throws Exception {
		AnswersExample example = new AnswersExample();
		//添加一个条件，name属性中包含keywords关键字
		example.createCriteria().andContentLike(keywords);
		
		
		return answersMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Answers answers) throws Exception {
		// TODO Auto-generated method stub
		if(answers.getId()!=null){
			//更新
			answersMapper.updateByPrimaryKey(answers);
			
		}else{
			//插入
			answersMapper.insert(answers);
			
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		answersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		// TODO Auto-generated method stub
		for(long id:ids){
			answersMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<AnswersVM> selectAllVM() throws Exception {
		// TODO Auto-generated method stub
		return answersVMMapper.selectAll();
	}

	@Override
	public List<Answers> findAnswersBySurveyId(long id) throws Exception {
		AnswersExample example = new AnswersExample();
		example.createCriteria().andSurveyIdEqualTo(id);
		return answersMapper.selectByExample(example);
	}
	


}
