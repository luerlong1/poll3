package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Qq;
import com.briup.apps.poll.bean.extend.QqVM;
import com.briup.apps.poll.service.IQqService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="题目问卷相关接口")
@RestController
@RequestMapping("/qq")
public class QqController {
	
	
	@Autowired
	private IQqService qqService;
	
	
	@GetMapping("findAllQqVM")
	public MsgResponse findAllQqVM(){
		try {
		List<QqVM> list = qqService.findAllQqVM();
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询题目问卷信息")
	@GetMapping("findAllQq")
	public MsgResponse findAllQq(){
		try {
		List<Qq> list = qqService.findAll();
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}

//  保存或更新题目问卷信息
  @ApiOperation(value="保存或更新题目问卷信息",notes="保存信息时无需输入id")
  @PostMapping("saveOrUpdateQq")
  public String save(Qq qq){
  	
  	try {
			qqService.saveOrUpdate(qq);
			return "保存成功！";
		} catch (Exception e) {
			e.printStackTrace();
			return "保存失败"+e.getMessage();
		}
  	
  }
  @ApiOperation(value="通过id查找题目问卷信息")
  @GetMapping("findQqById")
  public MsgResponse findQqById(@RequestParam long id){

  	try {
  		qqService.findById(id);
  		return MsgResponse.success("success",qqService.findById(id));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//返回失败
		return MsgResponse.error(e.getMessage());
	}
  	}
  @ApiOperation(value="通过id删除题目问卷信息")
  @GetMapping("deleteQqById")
  public String deleteQqById(@RequestParam long id){
//  	调用service层代码完成题目问卷信息的删除
  	try {
  		qqService.deleteById(id);
  		return "删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败！"+e.getMessage();
		}
  	
  }
  
  
  @ApiOperation(value="通过id批量删除题目问卷信息")
  @GetMapping("batchDelete")
  public String batchDelete(@RequestParam List<Long> ids){
	  
  	try {
  		qqService.batchDelete(ids);
  		return "删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败！"+e.getMessage();
		}
  	
  }
}
