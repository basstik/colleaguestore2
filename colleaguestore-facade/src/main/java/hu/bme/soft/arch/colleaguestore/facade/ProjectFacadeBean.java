package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.ProjectDTO;
import hu.bme.soft.arch.colleaguestore.persistence.ProjectPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;

@Stateless
public class ProjectFacadeBean implements ProjectFacade {

	@Inject
	ProjectPersistenceManager projectPM;

	@Inject
	Logger logger;

	@Override
	public List<Project> getProjects() {
		return projectPM.getProjects();
	}

	@Override
	public void create(Project newProject) {
		projectPM.persist(newProject);
	}

	@Override
	public void delete(Long projectid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ProjectDTO projectDTO) {
		// TODO Auto-generated method stub

	}

}
