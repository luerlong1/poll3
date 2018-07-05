package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.School;
import com.briup.apps.poll.service.ISchoolService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;


@Api(description="学校相关接口")
@RestController
@RequestMapping("/school")
public class SchoolController {

	
	@Autowired
	private ISchoolService schoolService;
	
	@GetMapping("findAllSchool")
	public MsgResponse findAllSchool(){
		try {
			List<School> list = schoolService.findAll();
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@GetMapping("findSchoolById")
	public MsgResponse findSchoolById(Long id){
		try {
			School list = new School();
			//返回成功
			list=schoolService.findSchoolById(id);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@GetMapping("findSchoolByKeyword")
	public MsgResponse findSchoolByKeyword(String keywords){
		try {
			List<School> list = schoolService.findSchoolByKeyword(keywords);
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@PostMapping("saveOrupdate")
	public String saveOrUpdate(School school){
		try{
			schoolService.saveOrUpdateSchool(school);
			return"成功";
		}catch (Exception e) {
			e.printStackTrace();
			return"失败"+e.getMessage();
		}
	}
	
	
	@GetMapping("deleteSchoolById")
	public String deleteSchoolById(long id){
		try{
			schoolService.deleteSchoolById(id);
			return"成功";
		}catch (Exception e) {
			e.printStackTrace();
			return"失败"+e.getMessage();
		}
	}
	
	
	@GetMapping("bathDeleteSchool")
	public String bathDeleteSchool(@RequestParam List<Long> ids){
		try{
			schoolService.bathDeleteSchool(ids);
			return"成功";
		}catch (Exception e) {
			e.printStackTrace();
			return"失败"+e.getMessage();
		}
	}

}
