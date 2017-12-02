package hu.bme.soft.arch.colleaguestore.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;

@Stateless
public class ProjectPersistenceManager extends BasePersistenceManager<Project> {

	public List<Project> getProjects() {
		TypedQuery<Project> query = getEntityManager().createNamedQuery("Project.findAll", Project.class);
		return query.getResultList();
	}

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}
}
