package hu.bme.soft.arch.colleaguestore.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;
import hu.bme.soft.arch.colleaguestore.facade.ProjectFacade;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@ApplicationScoped
@ManagedBean(name = "teamProjectPickListView")
public class TeamProjectPickListView {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private PersonFacade personFacade;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ProjectFacade projectFacade;

	private DualListModel<String> projects;

	Long selectedTeamId;

	@PostConstruct
	public void init() {
		List<String> citiesSource = new ArrayList<String>();
		List<String> citiesTarget = new ArrayList<String>();

		citiesSource.add("San Francisco");
		citiesSource.add("London");
		citiesSource.add("Paris");
		citiesSource.add("Istanbul");
		citiesSource.add("Berlin");
		citiesSource.add("Barcelona");
		citiesSource.add("Rome");

		projects = new DualListModel<String>(citiesSource, citiesTarget);
	}

	public void setProjectsByTeamId(Long teamId) {
		System.out.println("setProjectsByTeamId");
		selectedTeamId = teamId;
		// HashSet<Person> personsByTeam = new
		// HashSet<Person>(teamFacade.getPersonsByTeamId(teamId));
		// HashSet<Person> allPersons = new HashSet<Person>(personFacade.getPersons());
		// project = new DualListModel<Person>(new ArrayList<Person>(allPersons), new
		// ArrayList<Person>(personsByTeam));
	}

	public void saveProjects() {
		System.out.println("Fent: " + projects.getSource());
		System.out.println("Lend: " + projects.getTarget());
		// teamFacade.updatePersonList(selectedTeamId, project.getTarget());
	}

	public DualListModel<String> getProjects() {
		return projects;
	}

	public void setProjects(DualListModel<String> projects) {
		this.projects = projects;
	}

	public Long getSelectedTeamId() {
		return selectedTeamId;
	}

	public void setSelectedTeamId(Long selectedTeamId) {
		this.selectedTeamId = selectedTeamId;
	}

}