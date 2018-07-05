package com.briup.apps.poll.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.service.ISurveyService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "学生相关接口")
@RestController
@RequestMapping("/survey")
public class StudentController {

	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private IAnswersService answersService;
	
	@ApiOperation(value="登录课调",notes="只有课调状态为开启时 才能登录")
	@GetMapping("loginSurvey")
	public MsgResponse loginSurvey(long id){
		try {
			//通过id查找课调
			SurveyVM surveyVM = surveyService.findByIdVM(id);
			//如果课调存在，并且状态为开启才能返回课调信息，否则返回错误信息
			if(surveyVM!=null && surveyVM.getStatus().equals(Survey.STATUS_BEGIN)){
				return MsgResponse.success("success", surveyVM);
			}else{
				return MsgResponse.error("课调状态不合法");
			}
			
		} catch (Exception e) {
			
			return MsgResponse.error(e.getMessage());
		}
		
		
	}
	

	@ApiOperation(value="提交答卷",notes="")
	@PostMapping("submitAnswers")
	public MsgResponse submitAnswers(Answers answers){
		try {
			answersService.saveOrUpdate(answers);
			return MsgResponse.success("提交成功", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return MsgResponse.error(e.getMessage());
		}
	}
}
