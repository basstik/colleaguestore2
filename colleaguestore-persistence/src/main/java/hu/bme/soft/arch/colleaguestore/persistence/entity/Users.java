package hu.bme.soft.arch.colleaguestore.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column(name = "username", nullable = false, length = 50)
	private String userName;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@ManyToMany
	@JoinTable(name = "USER_ROLES",
			joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "userName"),
			inverseJoinColumns = @JoinColumn(name = "ROLE", referencedColumnName = "roleName"))
	private Set<Roles> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

}
