package hu.bme.soft.arch.colleaguestore.facade.dto;

import java.io.Serializable;
import java.util.Set;

public class UserDTO implements Serializable {

	private String username;

	private Set<String> permissions;

	private boolean admin;

	public UserDTO(String currentUser, Set<String> set, boolean isAdmin) {
		this.username = currentUser;
		this.permissions = set;
		this.admin = isAdmin;
	}

	public String getPermissionString() {
		return permissions.toString();
	}

	public String getUsername() {
		return username;
	}

	public Set<String> getPermission() {
		return permissions;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}