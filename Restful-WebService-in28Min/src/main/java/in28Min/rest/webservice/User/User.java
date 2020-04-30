package in28Min.rest.webservice.User;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

	private Integer id;
	
	@Size(min = 2, max = 50, message = "Name should have atleast 2 characters and a maximum of 50.")
	private String name;
	
	@Past(message = "Birth Date should be in the past. No future dates allowed.")
	private Date birthDate;
	
/**************************************** C O N S T R U C T O R S ********************************/
	public User(Integer id, String name, Date birthDate) {
		
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public User() {
		
	}
	
	
/************************************** M E T H O D S *********************************************/
	
	@Override
	public String toString() {
		
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

/*************************************** G E T T E R S  &  S E T T E R S **************************/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
