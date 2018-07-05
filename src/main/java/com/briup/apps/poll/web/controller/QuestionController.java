package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.service.IQuestionService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "题库相关接口")
@RequestMapping("/question")
@RestController
public class QuestionController {

	@Autowired
	private IQuestionService questionService;

	@ApiOperation(value = "查询题库信息时显示选项")
	@GetMapping("findAllQuestionVM")
	public MsgResponse findAllQuestionVM() {
		try {
			List<QuestionVM> list = questionService.findAllQuestionVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 返回失败
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "通过id查询题库信息，并且显示选项信息")
	@GetMapping("findByIdVM")
	public MsgResponse findByIdVM(Long id) {
		try {
			return MsgResponse.success("success", questionService.findById(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 返回失败
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "通过id删除题库信息")
	@GetMapping("deleteById")
	public MsgResponse deleteById(@RequestParam long id) {
		try {
			questionService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			// TODO: handle exceptionS
			e.printStackTrace();
			// 返回失败
			return MsgResponse.error(e.getMessage());
		}

	}

	@ApiOperation(value = "通过关键词查询题库信息")
	@GetMapping("query")
	public MsgResponse query(String keywords) {
		try {
			List<QuestionVM> list = questionService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 返回失败
			return MsgResponse.error(e.getMessage());
		}
	}

	
	@ApiOperation(value="批量删除题库内的信息",notes="批量删除时输入的id号之间用,隔开" )
	@GetMapping("batchDelete")
	public MsgResponse batchDelete(Long[] ids){

		try {
			questionService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 返回失败
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="保存或修改问题",
  			notes="当id不为空表示修改，否则表示更新，保存和更新的时候需要提交选项数据")
  	@PostMapping("saveOrUpdateQuestionVM")
	public MsgResponse saveOrUpdateQuestion(QuestionVM questionVM){
 		try {
 			questionService.saveOrUpdateVM(questionVM);
 			return MsgResponse.success("保存成功", null);
 		} catch (Exception e) {
 			e.printStackTrace();
 			return MsgResponse.error(e.getMessage());
 		}
 		
}

	@ApiOperation(value = "保存或修改问题", notes = "当id不为空表示修改，否则表示更新，保存和更新的时候需要提交选项数据")
	@PostMapping("saveOrUpdateQuestionVM")
	public MsgResponse saveOrUpdateQuestionVM(QuestionVM questionVM) {
		try {
			questionService.saveOrUpdateVM(questionVM);
			return MsgResponse.success("保存成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}

	}

}
