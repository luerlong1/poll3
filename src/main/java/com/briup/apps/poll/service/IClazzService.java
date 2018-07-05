 package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.extend.ClazzVM;

public interface IClazzService {
	List<Clazz> findAll() throws Exception;
	Clazz findById(Long id) throws Exception;
	List<Clazz> query( String keyword) throws Exception;
	void saveOrUpdate(Clazz clazz) throws Exception;
	void deleteById(Long id) throws Exception;
	void batchDelete(List<Long> ids) throws Exception;
	
	List<ClazzVM> findAllClazzVM() throws Exception;
}
