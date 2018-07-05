package com.briup.apps.poll.dao.extend;

import java.util.List;

import com.briup.apps.poll.bean.extend.AnswersVM;

public interface AnswersVMMapper {

	List<AnswersVM> selectAll();

	AnswersVM selectByPrimaryKey(long id);
}
