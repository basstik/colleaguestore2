package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.facade.UserFacade;
import hu.bme.soft.arch.colleaguestore.facade.dto.UserDTO;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {

	@Inject
	private UserFacade userFacade;

	private UserDTO user;

	private boolean admin;

	@PostConstruct
	public void init() {
		user = userFacade.getLoggedInUser();
		admin = user.isAdmin();
		System.out.println("User: " + user.getUsername() + " " + user.getPermission());
	}

	public boolean isLoggedIn() {
		return user != null;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getWelcomeMessage() {
		return "Hello " + user.getUsername();
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}