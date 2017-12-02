package hu.bme.soft.arch.colleaguestore.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.bme.soft.arch.colleaguestore.domain.enumeration.Position;

@Entity
@Table(name = "person")
@NamedQueries({
		@NamedQuery(name = "Person.findAll", query = "SELECT a FROM Person a ORDER BY a.firstName, a.lastName") })
public class Person extends BaseEntity {

	// CREATE TABLE person (
	// id INT NOT NULL,
	// firstName VARCHAR(50),
	// lastName VARCHAR(50),
	// nationality VARCHAR(50),
	// dateOfBirth DATE,
	// position VARCHAR(50),
	// ??? VARCHAR(50),
	// );

	// CREATE TABLE PERSON_TEAM (
	// PERSON_ID INT NOT NULL,
	// TEAM_ID INT NOT NULL
	// );

	private static final long serialVersionUID = 1L;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "nationality", nullable = true, length = 50)
	private String nationality;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", nullable = true)
	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(name = "position", length = 50)
	private Position position;

	// @JoinTable(name = "person_skill", joinColumns = @JoinColumn(name = "ID"))
	// @Enumerated(EnumType.STRING)
	// @ElementCollection
	// @Column(name = "skill", length = 20)
	// private Set<LanguageSkill> skill;

	@ManyToMany(mappedBy = "persons")
	private List<Team> teams;

	@Override
	public String toString() {
		return firstName;
	}

	public Person() {
	}

	public Person(String firstName, String lastName, String nationality, Date dateOfBirth, Position position) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
		this.position = position;
	}

	public Person(String firstName, String lastName, String nationality, Date dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.dateOfBirth = dateOfBirth;
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

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
