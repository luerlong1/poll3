package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Questionnaire;

import com.briup.apps.poll.bean.extend.QuestionnaireVM;

public interface IQuestionnaireService {
	List<QuestionnaireVM> query(String keywords) throws Exception;
	
	List<Questionnaire> findAll() throws Exception;
	
	Questionnaire findById(long id) throws Exception;
	
	void saveOrUpdate(Questionnaire questionnaire , long[] questionIds) throws Exception;
		
	void deleteById(long id) throws Exception;
	
	void batchDelete(Long[] ids) throws Exception;
	
	List<QuestionnaireVM> findAllQuestionnaireVM() throws Exception;
	
	QuestionnaireVM selectById(long id) throws Exception;
}
