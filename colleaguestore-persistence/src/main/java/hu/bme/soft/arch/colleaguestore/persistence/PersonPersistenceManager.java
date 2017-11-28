package hu.bme.soft.arch.colleaguestore.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import hu.bme.soft.arch.colleaguestore.domain.dto.PersonFilterDTO;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@Stateless
public class PersonPersistenceManager extends BasePersistenceManager<Person> {

	public List<Person> getPersons(PersonFilterDTO personFilterDTO) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> person = cq.from(Person.class);
		List<Order> orderList = new ArrayList<>();

		cq.select(person);
		orderList.add(cb.asc(person.get("firstName")));
		orderList.add(cb.asc(person.get("lastName")));
		cq.orderBy(orderList);

		TypedQuery<Person> q = getEntityManager().createQuery(cq);
		q.setFirstResult(personFilterDTO.getOffset());
		q.setMaxResults(personFilterDTO.getLimit());
		return q.getResultList();
	}

	public int countPersons(PersonFilterDTO personFilterDTO) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Person> person = cq.from(Person.class);
		cq.select(cb.count(person));

		return getEntityManager().createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public Class<Person> getEntityClass() {
		return Person.class;
	}

	public List<Person> getPersons() {
		TypedQuery<Person> query = getEntityManager().createNamedQuery("Person.findAll", Person.class);
		return query.getResultList();
	}
}
