package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.ProjectDTO;
import hu.bme.soft.arch.colleaguestore.facade.dto.Permission;
import hu.bme.soft.arch.colleaguestore.persistence.ProjectPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;

@Stateless
public class ProjectFacadeBean implements ProjectFacade {

	@Inject
	ProjectPersistenceManager projectPM;

	@Inject
	Logger logger;

	@Override
	@RolesAllowed({ Permission.ADMIN, Permission.VIEW })
	public List<Project> getProjects() {
		return projectPM.getProjects();
	}

	@Override
	@RolesAllowed({ Permission.ADMIN })
	public void create(Project newProject) {
		projectPM.persist(newProject);
	}

	@Override
	@RolesAllowed({ Permission.ADMIN })
	public void delete(Long projectid) {
		projectPM.remove(projectid);

	}

	@Override
	@RolesAllowed({ Permission.ADMIN })
	public void update(ProjectDTO projectDTO) {
		Project find = projectPM.find(projectDTO.getId());
		find.setName(projectDTO.getName());
	}
}
