package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.extend.AnswersVM;
/**
 * 
 * @author luerlong
 *
 */


public interface IAnswersService {

	
	
	
	List<Answers> query(String keywords) throws Exception;
	
	void saveOrUpdate(Answers answers) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(List<Long> ids) throws Exception;
	
	List<AnswersVM> selectAllVM() throws Exception;
	
	//通过surveyID查询answers
	List<Answers> findAnswersBySurveyId(long id) throws Exception;

	Answers findById(long id) throws Exception;
}
