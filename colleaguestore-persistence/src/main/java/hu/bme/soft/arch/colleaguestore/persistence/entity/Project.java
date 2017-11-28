package hu.bme.soft.arch.colleaguestore.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "project")
@NamedQueries({ @NamedQuery(name = "Project.findAll", query = "SELECT a FROM Project a ORDER BY a.name") })
public class Project extends BaseEntity {

	private static final long serialVersionUID = 1432L;

	@Column(name = "name", nullable = true, length = 30)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
