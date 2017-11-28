package hu.bme.soft.arch.colleaguestore.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

	private static final long serialVersionUID = 1L;

	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;

	@Column(name = "nationality", nullable = true, length = 30)
	private String nationality;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", nullable = true)
	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(name = "position")
	private Position position;

	// @JoinTable(name = "person_skill", joinColumns = @JoinColumn(name = "ID"))
	// @Enumerated(EnumType.STRING)
	// @ElementCollection
	// @Column(name = "skill", length = 20)
	// private Set<LanguageSkill> skill;

	@ManyToMany
	@JoinTable(name = "PERSON_TEAM", joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID"))
	private List<Team> teams;

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

	// public Collection<LanguageSkill> getSkill() {
	// return skill;
	// }
	//
	// public void setSkill(Set<LanguageSkill> skill) {
	// this.skill = skill;
	// }
}
