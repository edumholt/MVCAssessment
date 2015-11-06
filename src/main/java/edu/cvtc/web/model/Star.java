package edu.cvtc.web.model;

/**
 * @author edumholt
 *
 */
public class Star {
	
	private String name;
	private Integer age;
	
	public Star(final String name, final Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(final int age) {
		this.age = age;
	}
	
}
