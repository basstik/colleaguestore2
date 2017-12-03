package hu.bme.soft.arch.colleaguestore.facade;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.facade.dto.Permission;
import hu.bme.soft.arch.colleaguestore.facade.dto.UserDTO;

@Stateless
public class UserFacade {

	@Inject
	Logger logger;

	@Resource
	private SessionContext context;

	public UserDTO getLoggedInUser() {
		logger.info("getLoggedInUser");

		String currentUser = context.getCallerPrincipal().getName();
		boolean isAdmin = false;
		Set<String> set = new HashSet<>();
		for (String p : Permission.getValues()) {
			if (context.isCallerInRole(p)) {
				set.add(p);
				if (p.equals(Permission.ADMIN)) {
					logger.info("This user is ADMIN");
					isAdmin = true;
				}
			}
		}
		return new UserDTO(currentUser, set, isAdmin);
	}
}