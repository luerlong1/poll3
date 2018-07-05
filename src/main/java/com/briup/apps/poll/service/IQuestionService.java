package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.extend.QuestionVM;

public interface IQuestionService {
	
	//通过关键词搜索信息
	List<QuestionVM> query(String keywords) throws Exception;
	
	//通过id删除信息
	void deleteById(long id) throws Exception;
	//批量删除
	void batchDelete(Long[] ids) throws Exception;
    //查询所有信息，包括选项 
	List<QuestionVM> findAllQuestionVM() throws Exception;
	//更新及保存信息，包含选项
	void saveOrUpdateVM(QuestionVM questionVM) throws Exception;
	//通过id查询信息,包含选项
	QuestionVM findById(Long id) throws Exception;
}
