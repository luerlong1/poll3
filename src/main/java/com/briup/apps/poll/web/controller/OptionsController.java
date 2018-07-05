package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.service.IOptionsService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="题目选项接口")
@RestController
@RequestMapping("/Options")
public class OptionsController {
	
	@Autowired
	private IOptionsService optionsService;
	//查询所有题目
	@ApiOperation(value="查询所有选项")
	@GetMapping("findAllOptions")
	public MsgResponse findAllOptions() {
		
		try {
			List<Options> list = optionsService.findAllOptions();
			
			//调用MsgResponsegoon工具类返回查询状态信息
			return MsgResponse.success("success", list);
			
		} catch (Exception e) {
			
			
			return MsgResponse.error(e.getMessage());
		}	
	}
	//通过id查询题目
	@ApiOperation(value="通过id查询选项")
	@GetMapping("findById")
	public MsgResponse findById(long id) {
		
		Options options;
		try {
			options = optionsService.findById(id);
			return MsgResponse.success("success", options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return MsgResponse.error(e.getMessage());
		}
	}
	//通过关键字查询label
	@ApiOperation(value="通过关键字查询")
	@GetMapping("query")
	public MsgResponse query(String keywords) {
		List<Options> list;
		try {
			list = optionsService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return MsgResponse.error(e.getMessage());
		}
		
	}
	//跟新或插入选题
	@ApiOperation(value="更新或插入")
	@RequestMapping(value="saveOrUpdate",method=RequestMethod.POST)
	public String saveOrUpdate(Options option) {
		try {
			int count =optionsService.saveOrUpdate(option);
			if(option.getId()==0) {
				return "插入成功";
			}else {
				if(count==1) {
					
					return "跟新成功";
				}else {
					return "跟新的id不存在";
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("数据库操作失败");	
		}	
	}
	//根据id删除
	@ApiOperation(value="根据id删除")
	@GetMapping("deleteById")
	public String deleteById(long id) {
		try {
			optionsService.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除失败");
		}	
	}
	@ApiOperation(value="根据给出的id集合删除")
	@GetMapping("batchDelete")
	public String batchDelete(List<Long> ids) {
		try {
			optionsService.batchDelete(ids);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("删除失败");
		}
	}
	
}
