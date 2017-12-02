package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

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

	@ManagedProperty(value = "#{pickListView}")
	private PickListView pickListView;

	@PostConstruct
	public void init() {
		teams = teamFacade.getTeams();
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
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
		TeamDTO teamDTO = ((TeamDTO) event.getObject());
		FacesMessage msg = new FacesMessage("Team edited id" + teamDTO.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("modify() Name: " + teamDTO.getName());
		teamFacade.modify(teamDTO);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Team edit cancelled id" + ((TeamDTO) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {
		TeamDTO teamDTO = (TeamDTO) event.getObject();
		FacesMessage msg = new FacesMessage("Team Selected" + teamDTO.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// pickListView.setCities(dual());
		pickListView.setPersonsByTeamId(teamDTO.getId());
	}

	private DualListModel<String> dual() {
		List<String> citiesSource = new ArrayList<String>();
		List<String> citiesTarget = new ArrayList<String>();

		citiesSource.add("San Francisco");
		citiesSource.add("London");
		citiesSource.add("Paris");
		citiesSource.add("Istanbul");
		citiesSource.add("Berlin");
		citiesSource.add("Barcelona");
		citiesSource.add("Rome");

		DualListModel<String> dualListModel = new DualListModel<String>(citiesSource, citiesTarget);
		return dualListModel;
	}

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

	public PickListView getPickListView() {
		return pickListView;
	}

	public void setPickListView(PickListView pickListView) {
		this.pickListView = pickListView;
	}

}
