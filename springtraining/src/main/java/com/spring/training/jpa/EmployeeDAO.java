package com.spring.training.jpa;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {
	
	Future<List<Employee>> findByName(String name);
	
	
	List<Employee> findByNameAndSurname(String name,String surname);
	List<Employee> findByNameIn(List<String> names);
	
	@Query("select e from Employee e where e.name = :isim")
	List<Employee> myCustomQuery(@Param("isim") String name);
	
	@Query(value= "SELECT * FROM EMPLOYEE WHERE NAME = :isim",nativeQuery=true)
	List<Employee> myCustomNativeQuery(@Param("isim") String name);
	
	List<Employee> selectByName(@Param("isim") String name);
}
