package hu.bme.soft.arch.colleaguestore.facade;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import hu.bme.soft.arch.colleaguestore.facade.dto.Permission;
import hu.bme.soft.arch.colleaguestore.facade.dto.UserDTO;

@Stateless
public class UserFacade {

	@Resource
	private SessionContext context;

	public UserDTO getLoggedInUser() {
		String currentUser = context.getCallerPrincipal().getName();
		Set<String> set = new HashSet<>();
		for (String p : Permission.getValues()) {
			if (context.isCallerInRole(p)) {
				set.add(p);
			}
		}
		return new UserDTO(currentUser, set);
	}
}