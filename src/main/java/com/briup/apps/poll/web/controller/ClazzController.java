package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.extend.ClazzVM;
import com.briup.apps.poll.service.IClazzService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
@Api(description="班级接口")
@RestController
@RequestMapping("clazz")
public class ClazzController {
	@Autowired
	private IClazzService clazzService;
	
	@GetMapping("findAll")
	public MsgResponse findAll(){
		try {
			List<Clazz> list=clazzService.findAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败信息
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@GetMapping("findAllVM")
	public MsgResponse findAllVM(){
		try {
			List<ClazzVM> list=clazzService.findAllClazzVM();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败信息
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@PostMapping("findById")
	public MsgResponse  findById(Long id){
		try {
			
			//返回成功信息
			Clazz list=new Clazz();
			list =clazzService.findById(id);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败信息	
			return MsgResponse.error(e.getMessage());
		}
	}
	@PostMapping("query")
	public MsgResponse  query(String keyword){
		try {
			List<Clazz> list=clazzService.query(keyword);
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败信息	
			return MsgResponse.error(e.getMessage());
		}
	}
	 
	@GetMapping("batchDelete")
	public String batchDelete(@RequestParam List<Long> ids){
		try {
			clazzService.batchDelete(ids);
			return "删除成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "删除失败"+e.getMessage();
		}
	}
	
	@GetMapping("/deleteById")
	public String deleteById(Long id){
		try {
			clazzService.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "删除失败";
		}
	}
	
	@GetMapping("/saveOrupdate")
	public String saveOrUpdate(Clazz clazz){
		if (clazz.getId()!=null){
			try {
				clazzService.saveOrUpdate(clazz);
				return "更改成功";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "更改失败"+e.getMessage();
			}
		}
		else{
			try {
				clazzService.saveOrUpdate(clazz);
				return "保存成功";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "保存失败"+e.getMessage();
			}
		}
	}
}
