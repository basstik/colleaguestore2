package hu.bme.soft.arch.colleaguestore.facade.dto;

import java.io.Serializable;
import java.util.Set;

public class UserDTO implements Serializable {

	private String username;

	private Set<String> permissions;

	public UserDTO() {
	}

	public UserDTO(String username, Set<String> permissions) {
		this.username = username;
		this.permissions = permissions;
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

}