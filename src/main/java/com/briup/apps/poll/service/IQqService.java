package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Qq;
import com.briup.apps.poll.bean.extend.QqVM;

public interface IQqService {
	
    List<Qq> findAll() throws Exception;
	
	Qq findById(long id) throws Exception;
	
	void saveOrUpdate(Qq qq) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(List<Long> ids) throws Exception;
	
	List<QqVM> findAllQqVM()throws Exception;


}


