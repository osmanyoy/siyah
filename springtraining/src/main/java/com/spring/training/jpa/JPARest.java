package com.spring.training.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JPARest {
	
	@Autowired
	private EmployeeDAO empDAO;
	
	
	@PostMapping("/employee/add")
	public String addEmployee(@RequestBody Employee emp) {
		ExtraInfo extraInfo = emp.getExtraInfo();
		if (extraInfo != null) {
			extraInfo.setEmployee(emp);
		}
		empDAO.save(emp);
		
		return "OK";
	}
	
}
