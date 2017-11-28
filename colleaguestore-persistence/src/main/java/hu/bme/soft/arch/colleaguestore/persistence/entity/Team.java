package hu.bme.soft.arch.colleaguestore.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "team")
@NamedQueries({ @NamedQuery(name = "Team.findAll", query = "SELECT a FROM Team a ORDER BY a.name") })
public class Team extends BaseEntity {

	@Column(name = "name", nullable = true, length = 30)
	private String name;

	@ManyToMany(mappedBy = "teams")
	private List<Person> persons;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

// CREATE TABLE team (
// id INT NOT NULL,
// name VARCHAR(50)
// );

// INSERT INTO team (name) VALUES ('Krisztián');