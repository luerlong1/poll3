package com.briup.apps.poll.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.User;
import com.briup.apps.poll.service.IUserService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value="查询所有用户")
	@GetMapping("findAllUser")
	public MsgResponse findAllUser(){
		try {
			List<User> list = userService.findAll();
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id查询用户信息")
	@GetMapping("fingById")
	public MsgResponse findById(long id){
		
		try{
			User list= new User();
			list=userService.findById(id);
			return MsgResponse.success("success", list);
		}catch (Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="关键字查询")
	@GetMapping("query")
	public MsgResponse query(String keywords){
		List<User> list= new ArrayList<>();
		try{
			list=userService.query(keywords);
			return MsgResponse.success("success", list);
		}catch (Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="保存或更新")
	@PostMapping("/saveOrupdate")
	public String saveOrUpdate(User user){
		if (user.getId()!=null){
			try {
				userService.saveOrUpdate(user);
				return "更改成功";
			} catch (Exception e) {
				e.printStackTrace();
				return "更改失败"+e.getMessage();
			}
		}
		else{
			try {
				userService.saveOrUpdate(user);
				return "保存成功";
			} catch (Exception e) {
				e.printStackTrace();
				return "保存失败"+e.getMessage();
			}
		}
	}
	
	@ApiOperation(value="通过id删除用户信息")
	@GetMapping("deleteUserById")
	public String deleteUserById(long id){
		try{
			userService.deleteById(id);
			return "删除成功";
		}catch(Exception e){
			e.printStackTrace();
			return "删除失败"+e.getMessage();
		}
	}
	
	
	@ApiOperation(value="批量删除")
	@GetMapping("batchDelete")
	public String batchDelete(@RequestParam List<Long> ids){
		try{
			userService.batchDelete(ids);
			return "删除成功";
		}catch(Exception e){
			e.printStackTrace();
			return "删除失败"+e.getMessage();
		}
	}
	
	

}
