package com.briup.apps.poll.service;

import java.util.List;


import com.briup.apps.poll.bean.Options;


public interface IOptionsService {

	List<Options> findAllOptions()throws Exception;

	Options findById(long id)throws Exception;

	List<Options> query(String keywords)throws Exception;

	int saveOrUpdate(Options option) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(List<Long> ids) throws Exception ;

	
	
} 
