package hu.bme.soft.arch.colleaguestore.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "project")
@NamedQueries({ @NamedQuery(name = "Project.findAll", query = "SELECT a FROM Project a ORDER BY a.name") })
public class Project extends BaseEntity {

	@Column(name = "name", nullable = true, length = 30)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "deadline", nullable = true)
	private Date deadline;

	@ManyToMany(mappedBy = "projects")
	private List<Team> teams;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
