package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@ManagedBean(name = "teamView")
@RequestScoped
public class TeamView implements Serializable {
	private List<TeamDTO> teams;

	private TeamDTO newTeam = new TeamDTO();

	private TeamDTO editTeam = new TeamDTO();

	private TeamDTO selectedTeam;

	private Long teamid;

	private LazyTeamDataModel datamodel;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@PostConstruct
	public void init() {
		getTeamsFromServer();

		// teams.add(new TeamDTO(1L, "SzuperCsapat"));
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		getTeamsFromServer();
	}

	public void save() {
		System.out.println("Név: " + newTeam.getName());
		teamFacade.create(newTeam);
	}

	public void modify() {
		TeamDTO rowData = editTeam;
		System.out.println("modify() Name: " + rowData.getName());
		System.out.println("id: " + rowData.getId());
		teamFacade.modify(rowData);
	}

	public void deleteTeam() {
		teamFacade.remove(teamid);
	}

	// public void printTeam() {
	// System.out.println("Name: " + editTeam.getName() + " id: " +
	// editTeam.getId());
	// }

	public List<TeamDTO> getTeams() {
		return teams;
	}

	public TeamDTO getNewTeam() {
		return newTeam;
	}

	public TeamDTO getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(TeamDTO selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public DataModel<TeamDTO> getDatamodel() {
		return datamodel;
	}

	public void setNewTeam(TeamDTO newTeam) {
		this.newTeam = newTeam;
	}

	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}

	public Long getTeamid() {
		return teamid;
	}

	public TeamDTO getEditTeam() {
		return editTeam;
	}

	public void setEditTeam(TeamDTO editTeam) {
		this.editTeam = editTeam;
	}

	private void getTeamsFromServer() {
		teams = teamFacade.getTeams();

		TeamDTO[] stockArr = new TeamDTO[teams.size()];
		stockArr = teams.toArray(stockArr);

		// datamodel = new LazyTeamDataModel(teams);
	}
}
