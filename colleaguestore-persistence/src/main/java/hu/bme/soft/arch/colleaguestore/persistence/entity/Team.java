package hu.bme.soft.arch.colleaguestore.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "team")
@NamedQueries({ @NamedQuery(name = "Team.findAll", query = "SELECT a FROM Team a ORDER BY a.name") })
public class Team extends BaseEntity {

	@Column(name = "name", nullable = true, length = 30)
	private String name;

	@OneToOne
	@JoinColumn(name = "leader_fk", referencedColumnName = "id")
	private Person leader;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "TEAM_PERSON",
			joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"))
	private List<Person> persons;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "TEAM_PROJECT",
			joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"))
	private List<Project> projects;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getLeader() {
		return leader;
	}

	public void setLeader(Person leader) {
		this.leader = leader;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", persons=" + persons + ", projects=" + projects + "]";
	}
}