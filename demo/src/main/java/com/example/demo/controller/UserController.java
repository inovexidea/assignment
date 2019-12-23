package com.example.demo.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CommonConstant;
import com.example.demo.entity.user.User;
import com.example.demo.service.user.RoleService;
import com.example.demo.service.user.UserService;
import com.example.json.ResponseData;

import sun.misc.BASE64Decoder;




/**
 * UserController class
 *
 * @author Faruk Hossain
 *
 */



@RestController
@RequestMapping("/api")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
//	@Value("${build.version}")
	private String buildVersion = "1.0.0.0";

	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public String getVersion() throws Exception {

		return "Application Version : " + buildVersion;
	}
	
	
	@RequestMapping(value = "/get-users", method = RequestMethod.GET)
	public ResponseData getUsers() throws Exception {

		logger.info("UserController: getUsers start");
		List<User> users = userService.getAllUser();
		
		ResponseData responseData = new ResponseData();
		if (responseData != null && users != null) {
			responseData.setData(users);
			responseData.setStatus(CommonConstant.OK);
			responseData.setStatusCode(CommonConstant.CODE_OK);
			responseData.setMessage(CommonConstant.SUCCESS);
			
		}
		logger.info("UserController: getUsers end");
		return responseData;
	}
	
	@RequestMapping(value = "/user-login", method = RequestMethod.GET,produces = "application/json")
	public ResponseData getUsersByIdAndPassword(@RequestHeader(value="authorization") String authString,@RequestParam String username, @RequestParam String password) throws Exception {

		logger.info("UserController: user login start");

		ResponseData responseData = new ResponseData();
		System.out.println("username : "+username+ " password : "+password);
		if(!username.equals("") && !password.equals("")) {
			
			 String password1 = getMd5(password);		
				int users = userService.searchUserByNameAndPassword(username, password1);
				System.out.println("user status : "+users);
				
				String role=roleService.findRoleById(username);
				
				if(!isUserAuthenticated(authString)){
					System.out.println("in auth string ");
					responseData.setStatus(CommonConstant.RESPONSE_DATA_NOT_FOUND);
					responseData.setStatusCode(CommonConstant.UNAUTHORIZED);
					responseData.setMessage(CommonConstant.UNAUTHORIZED_MESSAGE);
		            return responseData;
		        }
				
				if (users != 0) {						
					responseData.setRole(role);
					responseData.setStatus(CommonConstant.OK);
					responseData.setStatusCode(CommonConstant.CODE_OK);
					responseData.setMessage(CommonConstant.SUCCESS);
					
				}else {
					responseData.setStatus(CommonConstant.RESPONSE_DATA_NOT_FOUND);
					responseData.setStatusCode(CommonConstant.CODE_NOT_FOUND);
					responseData.setMessage(CommonConstant.REQUEST_DATA_NOT_GIVEN);
					
				}
		}else {
			responseData.setStatus("Null value");
			responseData.setStatusCode(CommonConstant.CODE_NOT_FOUND);
			responseData.setMessage("User name or Password null");
			
		}
	   
		logger.info("UserController: user login end");
		return responseData;
	}
	
	private boolean isUserAuthenticated(String authString){
        
        String decodedAuth = "";
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
       
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        String [] substring=decodedAuth.split(":");
        String username=substring[0];
        String password=substring[1];
         
       if(username.equals("demo") && password.equals("demo@123")) {
System.out.println(" auth username "+username+" auth pass "+password);
           return true;  
       }else {

           return false;
       }
         
    }
	
	private  String getMd5(String input) 
    { 
        try { 
  
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
