package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserListModel;
import com.example.demo.service.UserListService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class UserListController {
	@Resource UserListService userListService;
	
	@GetMapping("/userlist")
	public ResponseEntity<?> userList(UserListModel userListModel){
		//管理者チェック
		 List<UserListModel> admincheck = userListService.adminCheck(userListModel);
		    boolean isAdmin = admincheck.stream().anyMatch(u -> "ADMIN".equals(u.getRole_name()) || "LEADERADMIN".equals(u.getRole_name()));
		    if (isAdmin) 
		    	{
		        List<UserListModel> userList = userListService.userList(userListModel);
		        if (userList.size() != 0) 
		        {
		            return ResponseEntity.ok(userList);
		        } else {
		            return ResponseEntity.status(400).body("ERROR");
		        }
		        
		    	} else {
		    		return ResponseEntity.status(400).body("ERROR");
		    	}
		

	}
	}
	
