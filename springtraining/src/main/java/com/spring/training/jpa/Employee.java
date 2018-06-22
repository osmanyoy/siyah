package com.spring.training.jpa;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NamedQueries({ @NamedQuery(name = "Employee.selectByName",
                            query = "select e from Employee e where e.name = :isim") })
@NamedNativeQueries({ @NamedNativeQuery(name = "test",
                                        query = "SELECT * FROM EMPLOYEE WHERE NAME = :isim") })

@Entity
@Table(name = "calisan",
       indexes = { @Index(columnList = "isim,surname",
                          name = "myIndex1") })
@SecondaryTable(name = "extralar")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="type")
public class Employee {

	@Id
	@GeneratedValue
	private long empId;

	@NotEmpty
	@Column(name = "isim",
	        nullable = false,
	        length = 50)
	private String name;

	@Size(min = 3,
	      max = 25)
	private String surname;
	private int age;

	@OneToOne(fetch = FetchType.EAGER,
	          cascade = CascadeType.ALL,
	          mappedBy = "employee")
	private ExtraInfo extraInfo;

	@OneToMany(fetch = FetchType.EAGER,
	           cascade = CascadeType.ALL,
	           mappedBy = "employee")
	private List<Address> addressList;

	@Column(table = "extralar")
	private String str1;
	@Column(table = "extralar")
	private String str2;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(column = @Column(name = "bilgi1"),
	                                         name = "info1") })
	private MyInfo myInfo;

	@Convert(converter=MyJPATestConverter.class)
	private MyJPATest myJPATest;
	
	@Transient
	private String dontWrite;

	@PrePersist
	public void beforeInsert() {

	}

	@PostPersist
	public void afterInsert() {

	}

	@PostLoad
	public void afterSelect() {

	}

	@PreRemove
	public void beforeRemove() {

	}

	@PostRemove
	public void afterRemove() {

	}

	@PreUpdate
	public void beforeUpdate() {

	}

	@PostUpdate
	public void afterUpdate() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDontWrite() {
		return dontWrite;
	}

	public void setDontWrite(String dontWrite) {
		this.dontWrite = dontWrite;
	}

	public ExtraInfo getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(ExtraInfo extraInfo) {
		this.extraInfo = extraInfo;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public MyJPATest getMyJPATest() {
		return myJPATest;
	}

	public void setMyJPATest(MyJPATest myJPATest) {
		this.myJPATest = myJPATest;
	}

}
