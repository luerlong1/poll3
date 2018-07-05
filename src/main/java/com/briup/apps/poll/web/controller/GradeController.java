package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.extend.GradeVM;
import com.briup.apps.poll.service.IGradeService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="年级相关接口")
@RestController
@RequestMapping("/grade")
public class GradeController {
	
	@Autowired
	private IGradeService gradeService;
//	
//	@ApiOperation(value="查询所有")
//	@GetMapping("findAllGrade")
//	public MsgResponse findAllGrade(){
//		try {
//			List<Grade> list = gradeService.findAll();
//			return MsgResponse.success("success", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//		
//	}
//	
	
	@ApiOperation(value="按id删除年级信息")
	@GetMapping("deleteById")
	public MsgResponse deleteById(@RequestParam long id){
		try {
			gradeService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="按id查找年级信息(包括school_id)")
	@GetMapping("selectGradeById")
	public MsgResponse selectGradeById(@RequestParam long id){
		try {
			return MsgResponse.success("success", gradeService.selectById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="按关键字查找(包括school_id)")
	@GetMapping("queryKeywords")
	public MsgResponse queryKeywords(@RequestParam String keywords){
		try {
			List<GradeVM> list = gradeService.query(keywords);
		    return MsgResponse.success("success", list);
		    
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}
	
	@ApiOperation(value="批量删除",notes="输入id并用逗号隔开，如：3,4,5")
	@GetMapping("batchDelete")
	public MsgResponse batchDelete(Long[] ids){
		try {
			gradeService.batchDelete(ids);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());		}
	}
	
	@ApiOperation(value="保存及修改",notes="保存不可输入id，修改输入id要与数据库中一致")
	@PostMapping("saveOrUpdate")
	public MsgResponse saveOrUpdate(Grade grade){
		try {
			gradeService.saveOrUpdate(grade);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	
	@ApiOperation(value="查询所有(包括school_id)")
	@GetMapping("findAllGradeVM")
	public MsgResponse findAllGradeVM(){
		try {
			List<GradeVM> list = gradeService.selectAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败信息
			return MsgResponse.error(e.getMessage());
		}
	}

}
