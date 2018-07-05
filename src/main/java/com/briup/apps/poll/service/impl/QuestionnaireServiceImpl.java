package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Qq;
import com.briup.apps.poll.bean.QqExample;
import com.briup.apps.poll.bean.Questionnaire;
import com.briup.apps.poll.bean.QuestionnaireExample;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.dao.QqMapper;
import com.briup.apps.poll.dao.QuestionnaireMapper;
import com.briup.apps.poll.dao.extend.QuestionnaireVMMapper;
import com.briup.apps.poll.service.IQuestionnaireService;
@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService{
	@Autowired
	private QuestionnaireMapper questionnaireMapper;
	@Autowired
	private QqMapper qqMapper;
	@Override
	public List<Questionnaire> findAll() throws Exception {
		// TODO Auto-generated method stub
		QuestionnaireExample example = new QuestionnaireExample();
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Questionnaire findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return questionnaireMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<QuestionnaireVM> query(String keywords) throws Exception {
		// TODO Auto-generated method stub
		
		return questionnaireVMMapper.query(keywords);
	}

	@Override
	public void saveOrUpdate(Questionnaire questionnaire,
			long[] questionIds) throws Exception {
		//1. 判断是保存操作还是更新
		if(questionnaire.getId() == null){
			//1.1 保存
			//1.1.1 保存问卷信息 {id:null,name:'',description:''}
			questionnaireMapper.insert(questionnaire);
			long  questionnaireId = questionnaire.getId();
			//1.1.2 维护问卷和问题的关系 qq
			for(long questionId : questionIds){
				Qq qq = new Qq();
				qq.setQuestionId(questionId);
				qq.setQuestionnaireId(questionnaireId);
				qqMapper.insert(qq);
			}
		} else {
			//1.2 修改
			//1.2.1 修改问卷信息
			questionnaireMapper.updateByPrimaryKey(questionnaire);
			long questionnaireId = questionnaire.getId();
			//1.2.2 删除问卷下所有的问卷问题关系
			// delete from poll_qq where questionnaire_id = #{id}
			QqExample example = new QqExample();
			example.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);
			qqMapper.deleteByExample(example);
			//1.2.3 保存新的问卷问题关系
			for(long questionId : questionIds){
				Qq qq = new Qq();
				qq.setQuestionId(questionId);
				qq.setQuestionnaireId(questionnaireId);
				qqMapper.insert(qq);
			}
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		questionnaireMapper.deleteByPrimaryKey(id);	
	}

	@Override
	public void batchDelete(Long[] ids) throws Exception {
		// TODO Auto-generated method stub
	for (long id : ids) {
		questionnaireMapper.deleteByPrimaryKey(id);
	}			 
	}
	@Autowired
	private QuestionnaireVMMapper questionnaireVMMapper;
	@Override
	public List<QuestionnaireVM> findAllQuestionnaireVM() throws Exception {
		// TODO Auto-generated method stub
		return questionnaireVMMapper.selectAll();
	}

	@Override
	public QuestionnaireVM selectById(long id) throws Exception {
		// TODO Auto-generated method stub
		return questionnaireVMMapper.selectById(id);
	}

	

	
}
