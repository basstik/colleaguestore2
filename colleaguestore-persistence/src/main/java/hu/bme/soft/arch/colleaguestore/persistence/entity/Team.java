package hu.bme.soft.arch.colleaguestore.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team")
@NamedQueries({ @NamedQuery(name = "Team.findAll", query = "SELECT a FROM Team a ORDER BY a.name") })
public class Team extends BaseEntity {

	private static final long serialVersionUID = 2L;

	@Column(name = "name", nullable = true, length = 30)
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOSS_ID")
	@NotNull
	private Person boss;

	@ManyToMany(mappedBy = "teams")
	private List<Person> persons;

	public Team() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getBoss() {
		return boss;
	}

	public void setBoss(Person boss) {
		this.boss = boss;
	}

}
