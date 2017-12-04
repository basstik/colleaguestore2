package hu.bme.soft.arch.colleaguestore.domain.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import hu.bme.soft.arch.colleaguestore.domain.enumaration.LanguageSkill;
import hu.bme.soft.arch.colleaguestore.domain.enumaration.Position;

public class PersonDTO implements Serializable {

	private Long id;

	private String firstName;

	private String lastName;

	private String nationality;

	private Date dateOfBirth;

	private Position position;

	private Collection<LanguageSkill> skill;

	private List<TeamDTO> teams;

	public PersonDTO() {
	}

	public PersonDTO(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public PersonDTO(Long id, String firstName, String lastName, String nationality, Date dateOfBirth,
			Position position) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
		this.position = position;
	}

	public PersonDTO(Long id, String firstName, String lastName, String nationality, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Collection<LanguageSkill> getSkill() {
		return skill;
	}

	public void setSkill(Collection<LanguageSkill> skill) {
		this.skill = skill;
	}

}
