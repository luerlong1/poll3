  package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.extend.AnswersVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * 
 * @author luerlong
 *
 */
@Api(description="答题卡相关接口")
@RestController
@RequestMapping("/answers")
public class AnswersController {

	@Autowired
	private IAnswersService answersService;
	
	@ApiOperation(value="删除答卷主观题",
			notes="单选题答案和多选题答案不收影响")
	@GetMapping("deleteAnswerContent")
	public MsgResponse deleteAnswerContent(long id){
		try {
			// 通过id找到答卷
			Answers answer = answersService.findById(id);
			// 设置答卷内容更为空
			answer.setContent("");
			answersService.saveOrUpdate(answer);
			return MsgResponse.success("删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="修改答卷主观题",
			notes="")
	@GetMapping("updateAnswerContent")
	public MsgResponse updateAnswerContent(long id,String content){
		try {
			// 通过id找到答卷
			Answers answer = answersService.findById(id);
			// 设置答卷内容为content
			answer.setContent(content);
			answersService.saveOrUpdate(answer);
			return MsgResponse.success("修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}

}
	
	
	
	@GetMapping("findAllAnswersVM")
	@ApiOperation(value="查询所有 ")
	public MsgResponse findAllAnswersVM(){
		try {
			List<AnswersVM> list = answersService.selectAllVM();
			
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@PostMapping("saveOrUpdate")
	@ApiOperation(value="保存或更新",notes="保存课程信息的时候无需输入id")
	public MsgResponse saveOrUpdate(Answers answers){
		// TODO Auto-generated method stub
		
		try {
			 answersService.saveOrUpdate(answers);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error("失败"+e.getMessage());
		}
	}
	
	@GetMapping("deleteById")	
	@ApiOperation(value="根据id删除")

	public String deleteById(@RequestParam long id){
		// TODO Auto-generated method stub
		try {
			answersService.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败"+e.getMessage();
		}
		
	}
	
	@GetMapping("batchDelete")
	@ApiOperation(value="批量删除课程")

	public String batchDelete(@RequestParam List<Long> ids) {
		// TODO Auto-generated method stub
		
		try {
			answersService.batchDelete(ids);
			return "删除成功";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除成功";

		}
		
	}
}
