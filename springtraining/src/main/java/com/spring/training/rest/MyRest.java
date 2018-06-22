package com.spring.training.rest;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.training.IMyInterface;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/myrest")
public class MyRest {

	@Autowired
	@Qualifier("function2")
	private IMyInterface myInt;

	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring";
	}

	@GetMapping("/execute")
	public String execute() {
		return "Execute Result : "
		       + this.myInt.execute();
	}

	@GetMapping("/hello1/{isim}/{soyisim}")
	public String hello1(@PathVariable("isim") final String name,
	                     @PathVariable("soyisim") final String surname) {

		return "Hello Spring : "
		       + name
		       + " "
		       + surname;
	}

	@GetMapping("/hello2")
	public String hello2(@RequestParam("isim") final String name,
	                     @RequestParam("soyisim") final String surname) {

		return "Hello 2 Spring : "
		       + name
		       + " "
		       + surname;
	}

	@GetMapping("/hello3/{yas}")
	public String hello3(@RequestParam("isim") final String name,
	                     @RequestParam("soyisim") final String surname,
	                     @PathVariable("yas") final int age,
	                     @RequestHeader("user") final String username,
	                     @RequestHeader("pass") final String password)
	        throws MyValidationException {
		if (password.length() <= 6) {
			throw new MyValidationException("password min 6 karakter olmalı",
			                                100);
		}
		return "Hello 3 Spring : "
		       + name
		       + " "
		       + surname
		       + " yas : "
		       + age
		       + " user : "
		       + username
		       + " pass : "
		       + password;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MyValidationException.class)
	public ErrorObj handleMyException(final MyValidationException myException) {
		ErrorObj errorObj = new ErrorObj();
		errorObj.setCode(myException.getCode());
		errorObj.setDesc(myException.getDesc());
		return errorObj;
	}

	@GetMapping("/hello4/{yas}")
	public String hello4(@RequestParam("isim") final String name,
	                     @RequestParam("soyisim") final String surname,
	                     @PathVariable("yas") final int age,
	                     @RequestHeader("user") final String username,
	                     @Size(min = 6,
	                           message = "password should be min 5 chars") @RequestHeader("pass") final String password) {
		return "Hello 4 Spring : "
		       + name
		       + " "
		       + surname
		       + " yas : "
		       + age
		       + " user : "
		       + username
		       + " pass : "
		       + password;
	}

	@PostMapping("/hello5")
	public String hello5(@Valid @RequestBody final User user) {
		return "Hello 5 Spring : "
		       + user.getName()
		       + " "
		       + user.getSurname()
		       + " yas : "
		       + user.getAge()
		       + " user : "
		       + user.getUsername()
		       + " pass : "
		       + user.getPassword();
	}

	@RequestMapping(path = "/hello6",
	                method = RequestMethod.POST,
	                consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
	                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public User hello6(@Valid @RequestBody final User user) {
		user.setName("ali");
		return user;
	}

	@RequestMapping(path = "/hello7",
	                method = RequestMethod.POST,
	                consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
	                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "my api desc",
	              notes = "Bu api validasyonsuz çağrım için",
	              response = User.class)
	@ApiResponses({ @ApiResponse(code = 200,
	                             response = User.class,
	                             message = "User class girilmeli"),
	        @ApiResponse(code = 400,
	                     response = ErrorObj.class,
	                     message = "Validation error") })
	public ResponseEntity<?> hello7(@RequestBody final User user) {
		user.setName("ali");
		if (user.getPassword()
		        .length() < 5) {
			ErrorObj errorObj = new ErrorObj();
			errorObj.setDesc("Password en az 5 karakter olmalı");
			errorObj.setCode(200);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			                     .body(errorObj);
		}
		return ResponseEntity.ok(user);
	}

}
