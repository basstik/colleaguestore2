package hu.bme.soft.arch.colleaguestore.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import hu.bme.soft.arch.colleaguestore.persistence.entity.Roles;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Users;

@Stateless
public class UserPersistenceManager extends BasePersistenceManager<Users> {

	public Set<Roles> getRoles(String userName) {
		try {
			TypedQuery<Users> query = getEntityManager().createNamedQuery("Users.findByUserName", Users.class)
					.setParameter("userName", userName);
			return query.getSingleResult().getRoles();
		} catch (NoResultException exc) {
			return new HashSet<Roles>();
		}
	}

	@Override
	public Class<Users> getEntityClass() {
		return Users.class;
	}

}
