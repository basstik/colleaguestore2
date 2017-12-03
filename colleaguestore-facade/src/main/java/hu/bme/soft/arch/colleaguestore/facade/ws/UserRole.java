package hu.bme.soft.arch.colleaguestore.facade.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.ws.api.annotation.WebContext;
import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.persistence.UserPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Roles;

//Access wsdl: http://localhost:8080/webservice/UserRoleService?wsdl

@Stateless
@WebService(serviceName = "UserRole")
@WebContext(contextRoot = "/webservice", urlPattern = "/UserRoleService")
public class UserRole {

	@Inject
	Logger logger;

	@Inject
	UserPersistenceManager userPM;

	public UserRole() {
	}

	@WebMethod
	public String getUserRole(String userName) {
		logger.info("UserRoleWS");
		List<String> roles = new ArrayList<>();
		for (Roles role : userPM.getRoles(userName)) {
			roles.add(role.getRoleName());
		}
		return roles.toString();
	}
}
