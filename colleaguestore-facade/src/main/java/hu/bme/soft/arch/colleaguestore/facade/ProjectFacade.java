package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.domain.dto.ProjectDTO;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;

@Local
public interface ProjectFacade {

	List<Project> getProjects();

	void create(Project newProject);

	void delete(Long projectid);

	void update(ProjectDTO projectDTO);

}
