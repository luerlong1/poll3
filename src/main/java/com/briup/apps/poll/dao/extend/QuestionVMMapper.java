package com.briup.apps.poll.dao.extend;

import java.util.List;

import com.briup.apps.poll.bean.extend.QuestionVM;

public interface QuestionVMMapper {

	List<QuestionVM> selectAll();
	
	QuestionVM selectById(Long id);


	QuestionVM slectAllQuestion(Long id);

	List<QuestionVM> selectAllQuestion(Long id);
	
	List<QuestionVM> query(String keywords);
	


}
