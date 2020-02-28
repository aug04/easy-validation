package personal.aug.easy.validation;

import personal.aug.convert.MapAndObjectConversion;
import personal.aug.convert.annotations.MapKey;

public class User extends MapAndObjectConversion {

	@MapKey("USER_ID")
	private String id;
	
	@MapKey("AGE")
	private Integer age;
	
	@MapKey("EMAIL")
	private String email;

	public User() {
		super();
	}

	public User(String id, Integer age, String email) {
		this.id = id;
		this.age = age;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", email=" + email + "]";
	}
	
}
