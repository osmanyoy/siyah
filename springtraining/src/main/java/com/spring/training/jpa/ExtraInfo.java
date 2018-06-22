package com.spring.training.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ExtraInfo {

	@Id
	private long exId;
	
	private String spouseName;
	
	@Enumerated(EnumType.STRING)
	private EMarriageStatus marriageStatus;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@MapsId
	@JsonIgnore
	@XmlTransient
	private Employee employee; 
	
	public long getExId() {
		return exId;
	}
	public void setExId(long exId) {
		this.exId = exId;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public EMarriageStatus getMarriageStatus() {
		return marriageStatus;
	}
	public void setMarriageStatus(EMarriageStatus marriageStatus) {
		this.marriageStatus = marriageStatus;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
