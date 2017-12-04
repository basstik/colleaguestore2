package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
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

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@ManagedBean(name = "teamView")
@ApplicationScoped
public class TeamView implements Serializable {
	private List<Team> teams;

	private Team newTeam = new Team();

	private Team editTeam = new Team();

	private Team selectedTeam;

	private Long teamid;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@ManagedProperty(value = "#{teamPersonPickListView}")
	private TeamPersonPickListView teamPersonPickListView;

	@ManagedProperty(value = "#{teamProjectPickListView}")
	private TeamProjectPickListView teamProjectPickListView;

	@PostConstruct
	public void init() {
		teams = teamFacade.getTeams();
	}

	public String goToPage1() {
		return "aaaa";
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		System.out.println("refreshButtonAction");
		teams = teamFacade.getTeams();
	}

	public void save() {
		System.out.println("Név: " + newTeam.getName());
		teamFacade.create(newTeam);
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
		FacesMessage msg = new FacesMessage("Team edit cancelled id" + ((TeamDTO) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {
		TeamDTO teamDTO = (TeamDTO) event.getObject();
		FacesMessage msg = new FacesMessage("Team Selected" + teamDTO.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		teamPersonPickListView.setPersonsByTeamId(teamDTO.getId());
		teamProjectPickListView.setProjectsByTeamId(teamDTO.getId());
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

	public Team getEditTeam() {
		return editTeam;
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
