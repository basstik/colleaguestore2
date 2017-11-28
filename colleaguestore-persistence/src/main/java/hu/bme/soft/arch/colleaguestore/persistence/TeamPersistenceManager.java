package hu.bme.soft.arch.colleaguestore.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamFilterDTO;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@Stateless
public class TeamPersistenceManager extends BasePersistenceManager<Team> {

	public List<Team> getTeams(TeamFilterDTO teamFilterDTO) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		Root<Team> team = cq.from(Team.class);
		List<Order> orderList = new ArrayList<>();

		cq.select(team);
		orderList.add(cb.asc(team.get("name")));
		cq.orderBy(orderList);

		TypedQuery<Team> q = getEntityManager().createQuery(cq);
		q.setFirstResult(teamFilterDTO.getOffset());
		q.setMaxResults(teamFilterDTO.getLimit());
		return q.getResultList();
	}

	public int countTeams(TeamFilterDTO teamFilterDTO) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Team> person = cq.from(Team.class);
		cq.select(cb.count(person));

		return getEntityManager().createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public Class<Team> getEntityClass() {
		return Team.class;
	}

	public List<Team> getTeams() {
		TypedQuery<Team> query = getEntityManager().createNamedQuery("Team.findAll", Team.class);
		return query.getResultList();
	}

}
