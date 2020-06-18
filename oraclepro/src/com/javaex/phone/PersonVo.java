package com.javaex.phone;

public class PersonVo {

	private String name;
	private String hp;
	private String company;
	private int PersonID;
	
	public PersonVo() {}
	
	public PersonVo(int PersonID, String name, String hp, String company)
	{
		this.PersonID = PersonID;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	public PersonVo(String name, String hp, String company)
	{
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPersonID() {
		return PersonID;
	}

	public void setPersonID(int personID) {
		PersonID = personID;
	}

	@Override
	public String toString() {
		return "PersonVo [name=" + name + ", hp=" + hp + ", company=" + company + ", PersonID=" + PersonID + "]";
	}
	
	
}
