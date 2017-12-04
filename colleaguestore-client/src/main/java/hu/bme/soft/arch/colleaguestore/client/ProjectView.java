package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import hu.bme.soft.arch.colleaguestore.domain.dto.ProjectDTO;
import hu.bme.soft.arch.colleaguestore.facade.ProjectFacade;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;

@ManagedBean(name = "projectView")
@RequestScoped
public class ProjectView implements Serializable {
	private List<Project> projects;

	private Project newProject = new Project();

	private Project selectedProject;

	private Long projectid;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ProjectFacade projectFacade;

	@PostConstruct
	public void init() {
		projects = projectFacade.getProjects();
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		projects = projectFacade.getProjects();
	}

	public void save() {
		projectFacade.create(newProject);
		newProject = new Project();
	}

	public void deleteProject() {
		projectFacade.delete(projectid);
	}

	public void onRowEdit(RowEditEvent event) {
		ProjectDTO projectDTO = ((ProjectDTO) event.getObject());
		FacesMessage msg = new FacesMessage("Project edited id" + projectDTO.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("modify() Name: " + projectDTO.getName());
		projectFacade.update(projectDTO);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Project edit cancelled id" + ((ProjectDTO) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Project> getProjects() {
		return projects;
	}

	public Project getNewProject() {
		return newProject;
	}

	public void setNewProject(Project newProject) {
		this.newProject = newProject;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public Long getProjectid() {
		return projectid;
	}
}
