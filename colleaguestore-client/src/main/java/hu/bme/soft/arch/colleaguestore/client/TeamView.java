package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@ManagedBean(name = "teamView")
@ViewScoped
public class TeamView implements Serializable {
	private final Logger log = Logger.getLogger(getClass().getName());
	private List<TeamDTO> teams;

	private TeamDTO newTeam = new TeamDTO();

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@PostConstruct
	public void init() {
		teams = teamFacade.getTeams();
		// teams.add(new TeamDTO(1L, "SzuperCsapat"));
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		// addMessage("Refresh!");
		teams = teamFacade.getTeams();
		// RequestContext.getCurrentInstance().update(":teamList");
	}

	public void save() {
		log.log(Level.FINEST, "save");
		System.out.println("Név: " + newTeam.getName());
		teamFacade.create(newTeam);
		// RequestContext.getCurrentInstance().update("foo:bar");
	}

	public void deleteUser() {
		teamFacade.remove(1L);
		teams = teamFacade.getTeams();
	}

	public List<TeamDTO> getTeams() {
		log.log(Level.INFO, "getTeams()");
		return teams;
	}

	public TeamDTO getNewTeam() {
		return newTeam;
	}

	public void setNewTeam(TeamDTO newTeam) {
		this.newTeam = newTeam;
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
