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

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

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

	public void deleteTeam() {
		teamFacade.remove(teamid);
	}

	public void onRowEdit(RowEditEvent event) {
		TeamDTO teamDTO = ((TeamDTO) event.getObject());
		FacesMessage msg = new FacesMessage("Team edited id" + teamDTO.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("modify() Name: " + teamDTO.getName());
		System.out.println("id: " + teamDTO.getId());
		teamFacade.modify(teamDTO);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Team edit cancelled id" + ((TeamDTO) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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
	}
}
