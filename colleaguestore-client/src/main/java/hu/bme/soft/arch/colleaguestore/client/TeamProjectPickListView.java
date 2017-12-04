package hu.bme.soft.arch.colleaguestore.client;

import java.util.ArrayList;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import hu.bme.soft.arch.colleaguestore.facade.ProjectFacade;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;

@ApplicationScoped
@ManagedBean(name = "teamProjectPickListView")
public class TeamProjectPickListView {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ProjectFacade projectFacade;

	private DualListModel<Project> projects;

	Long selectedTeamId;

	@PostConstruct
	public void init() {
		projects = new DualListModel<Project>(new ArrayList<Project>(), new ArrayList<Project>());
	}

	public void setProjectsByTeamId(Long teamId) {
		System.out.println("setProjectsByTeamId");
		selectedTeamId = teamId;
		HashSet<Project> allProject = new HashSet<Project>(projectFacade.getProjects());
		HashSet<Project> projectsByTeam = new HashSet<Project>(teamFacade.getProjectsByTeamId(teamId));
		allProject.removeAll(projectsByTeam);
		System.out.println(allProject);
		projects = new DualListModel<Project>(new ArrayList<Project>(allProject),
				new ArrayList<Project>(projectsByTeam));
	}

	public void saveProjects() {
		System.out.println("Fent: " + projects.getSource());
		System.out.println("Lend: " + projects.getTarget());
		if (!projects.getTarget().isEmpty()) {
			teamFacade.updateProjectList(selectedTeamId, projects.getTarget());
		}
	}

	public DualListModel<Project> getProjects() {
		return projects;
	}

	public void setProjects(DualListModel<Project> projects) {
		this.projects = projects;
	}

	public Long getSelectedTeamId() {
		return selectedTeamId;
	}

	public void setSelectedTeamId(Long selectedTeamId) {
		this.selectedTeamId = selectedTeamId;
	}

}