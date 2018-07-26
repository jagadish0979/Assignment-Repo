package com.assignments.controller;

import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignments.domain.LoginModel;
import com.assignments.domain.Result;

/**
 * @author Jagadish Anala
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private Environment env;
	
	@PostMapping(value = "/doLogin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> doLogin(@RequestBody LoginModel loginModel) throws LoginException {
		logger.info("Trying to login using userName [ " + loginModel.getUserName() +" ] and password [ " + loginModel.getPassword() + " ]" );
		if("admin".equals(loginModel.getUserName()) && "admin".equals(loginModel.getPassword())) {
			Result result = new Result();
			result.setMessage("Success");
			result.setUrl("/main");
			return new ResponseEntity<Result>(result,HttpStatus.OK);
		}
		else throw new LoginException(env.getProperty("error.login.invalid")); 
	}

}