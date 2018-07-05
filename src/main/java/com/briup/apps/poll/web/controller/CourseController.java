package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Course;
import com.briup.apps.poll.service.ICourseService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
@Api(description="课程相关接口")
@RestController
@RequestMapping("/course")
public class CourseController {
	

	@Autowired
	private ICourseService courseService;
	
	 
	@GetMapping("findAllCourse")
	public MsgResponse findAllCourse(){
		try {
			List<Course> list=courseService.findAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败信息
			return MsgResponse.error(e.getMessage());
		}
	}

	@GetMapping("findById")
	public MsgResponse  findById(Long id){
		try {
			
			//返回成功信息
			Course list=new Course();
			list =courseService.findById(id);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败信息	
			return MsgResponse.error(e.getMessage());
		}
	}
	@GetMapping("query")
	public MsgResponse  query(String keyword){
		try {
			List<Course> list=courseService.query(keyword);
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
			courseService.batchDelete(ids);
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
			courseService.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "删除失败";
		}
	}
	
	@PostMapping("/saveOrupdate")
	public String saveOrUpdate(Course course){
		if (course.getId()!=null){
			try {
				courseService.saveOrUpdate(course);
				return "更改成功";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "更改失败"+e.getMessage();
			}
		}
		else{
			try {
				courseService.saveOrUpdate(course);
				return "保存成功";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "保存失败"+e.getMessage();
			}
		}
	}
	

}
