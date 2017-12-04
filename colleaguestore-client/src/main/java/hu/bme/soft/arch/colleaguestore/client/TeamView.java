package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@ManagedBean(name = "teamView")
@ApplicationScoped
public class TeamView implements Serializable {
	private List<Team> teams;

	private Team newTeam = new Team();;

	private Team editTeam = new Team();

	private Team selectedTeam;

	private Person newLeader = new Person();

	private Long teamid;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private PersonFacade personFacade;

	@ManagedProperty(value = "#{teamPersonPickListView}")
	private TeamPersonPickListView teamPersonPickListView;

	@ManagedProperty(value = "#{teamProjectPickListView}")
	private TeamProjectPickListView teamProjectPickListView;

	@PostConstruct
	public void init() {
		newTeam = new Team();
		teams = teamFacade.getTeams();
	}

	List<Person> leaders = new ArrayList<Person>();

	public List<Person> getLeaders() {
		leaders = personFacade.getPersons();
		return leaders;
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		System.out.println("refreshButtonAction");
		teams = teamFacade.getTeams();
	}

	public void save() {
		System.out.println("Név: " + newTeam.getName());
		// newTeam.setLeader(newLeader);
		teamFacade.create(newTeam);
		newTeam = new Team();
	}

	public void deleteTeam() {
		teamFacade.remove(teamid);
	}

	public void onRowEdit(RowEditEvent event) {
		Team team = ((Team) event.getObject());
		FacesMessage msg = new FacesMessage("Team edited id" + team.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("modify() Name: " + team.getName());
		teamFacade.modify(team);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Team edit cancelled id" + ((Team) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {
		Team team = (Team) event.getObject();
		FacesMessage msg = new FacesMessage("Team Selected" + team.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		teamPersonPickListView.setPersonsByTeamId(team.getId());
		teamProjectPickListView.setProjectsByTeamId(team.getId());
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team getNewTeam() {
		return newTeam;
	}

	public void setNewTeam(Team newTeam) {
		this.newTeam = newTeam;
	}

	public Person getNewLeader() {
		return newLeader;
	}

	public void setNewLeader(Person newLeader) {
		this.newLeader = newLeader;
	}

	public Team getEditTeam() {
		return editTeam;
	}

	public PersonFacade getPersonFacade() {
		return personFacade;
	}

	public void setPersonFacade(PersonFacade personFacade) {
		this.personFacade = personFacade;
	}

	public void setLeaders(List<Person> leaders) {
		this.leaders = leaders;
	}

	public void setEditTeam(Team editTeam) {
		this.editTeam = editTeam;
	}

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public Long getTeamid() {
		return teamid;
	}

	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}

	public TeamFacade getTeamFacade() {
		return teamFacade;
	}

	public void setTeamFacade(TeamFacade teamFacade) {
		this.teamFacade = teamFacade;
	}

	public TeamPersonPickListView getTeamPersonPickListView() {
		return teamPersonPickListView;
	}

	public void setTeamPersonPickListView(TeamPersonPickListView teamPersonPickListView) {
		this.teamPersonPickListView = teamPersonPickListView;
	}

	public TeamProjectPickListView getTeamProjectPickListView() {
		return teamProjectPickListView;
	}

	public void setTeamProjectPickListView(TeamProjectPickListView teamProjectPickListView) {
		this.teamProjectPickListView = teamProjectPickListView;
	}
}
