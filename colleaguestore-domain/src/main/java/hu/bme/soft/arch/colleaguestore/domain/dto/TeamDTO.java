package hu.bme.soft.arch.colleaguestore.domain.dto;

import java.io.Serializable;

public class TeamDTO implements Serializable {

	private Long id;

	private String name;

	private PersonDTO boss;

	public TeamDTO() {
	}

	public TeamDTO(Long id, String name, PersonDTO person) {
		this.id = id;
		this.name = name;
		this.setBoss(person);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonDTO getBoss() {
		return boss;
	}

	public void setBoss(PersonDTO boss) {
		this.boss = boss;
	}

}
