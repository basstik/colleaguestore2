package hu.bme.soft.arch.colleaguestore.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {

	// INSERT INTO users(username, password) VALUES ('admin', 'admin');
	// INSERT INTO users(username, password) VALUES ('view', 'view');
	// INSERT INTO roles(rolename) VALUES ('LOGIN');
	// INSERT INTO roles(rolename) VALUES ('ADMIN');
	// INSERT INTO roles(rolename) VALUES ('VIEW');
	// INSERT INTO user_roles(username, role) VALUES ('admin', 'LOGIN');
	// INSERT INTO user_roles(username, role) VALUES ('admin', 'ADMIN');
	// INSERT INTO user_roles(username, role) VALUES ('view', 'LOGIN');
	// INSERT INTO user_roles(username, role) VALUES ('view', 'VIEW');
	@Id
	@Column(name = "rolename", length = 50, nullable = false)
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
