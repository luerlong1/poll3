package com.briup.apps.poll.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.SurveyExample;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.dao.SurveyMapper;
import com.briup.apps.poll.dao.extend.SurveyVMMapper;
import com.briup.apps.poll.service.ISurveyService;
/**
 * 
 * @author luerlong
 *
 */
@Service
public class SurveyServiceImpl implements ISurveyService {

	
	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private SurveyVMMapper surveyVMMapper;
	

	@Override
	public SurveyVM findByIdVM(long id) throws Exception {
		
		return surveyVMMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Survey> query(String keywords) throws Exception {
		SurveyExample example = new SurveyExample();
		//添加一个条件，name属性中包含keywords关键字
		example.createCriteria().andSurveydateLike(keywords);
		
		
		return surveyMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Survey survey) throws Exception {
		

		if(survey.getId()!=null){
			//更新
			surveyMapper.updateByPrimaryKey(survey);
		}else{
			//插入
			Date now = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String surveyDate = sf.format(now);
			survey.setStatus(Survey.STATUS_INIT);
			survey.setCode("");
			survey.setSurveydate(surveyDate);
			surveyMapper.insert(survey);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		
		surveyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		

		for(long id:ids){
			surveyMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<SurveyVM> selectAll() throws Exception {
		
		return surveyVMMapper.selectAll();
	}

	@Override
	public Survey findSurveyById(long id) throws Exception {
		
		return surveyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SurveyVM> findByClazzIdAndCheckPass(long id) throws Exception {
		
		return surveyVMMapper.selectByClazzIdAndCheckPass(id);
	}

}
