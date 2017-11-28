package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@ManagedBean(name = "teamView")
@RequestScoped
public class TeamView implements Serializable {
	private List<TeamDTO> teams;

	private TeamDTO newTeam = new TeamDTO();

	// @ManagedProperty(value = "#{teamid}")
	private Long teamid; // +setter

	private DataModel<TeamDTO> datamodel;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@PostConstruct
	public void init() {
		teams = teamFacade.getTeams();
		datamodel = new ListDataModel<TeamDTO>(teams);

		// teams.add(new TeamDTO(1L, "SzuperCsapat"));
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		// addMessage("Refresh!");
		teams = teamFacade.getTeams();
		// RequestContext.getCurrentInstance().update(":teamList");
	}

	public void save() {
		System.out.println("Név: " + newTeam.getName());
		teamFacade.create(newTeam);
		// RequestContext.getCurrentInstance().update("foo:bar");
	}

	// update="myForm:team:teamList"
	// <f:param name="teamId" value="#{team.id}" />
	// <f:setPropertyActionListener target="#{teamView.teamid}" value="#{team.id}"
	// />
	public void deleteTeam() {
		// HttpServletRequest request = (HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequest();
		// String id = request.getParameter("teamId");
		// Map<String, String> parameterMap =
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequestParameterMap();
		// String id = parameterMap.get("teamId");

		// teamFacade.remove(Long.valueOf(id));
		// teamFacade.remove(2L);
		teamFacade.remove(teamid);
		teams = teamFacade.getTeams();
	}

	public List<TeamDTO> getTeams() {
		return teams;
	}

	public TeamDTO getNewTeam() {
		return newTeam;
	}

	public DataModel<TeamDTO> getDatamodel() {
		return datamodel;
	}

	public void setDatamodel(DataModel<TeamDTO> datamodel) {
		this.datamodel = datamodel;
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

	public void setTeams(List<TeamDTO> teams) {
		this.teams = teams;
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
