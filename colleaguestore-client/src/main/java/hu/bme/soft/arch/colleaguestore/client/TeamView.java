package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	private List<TeamDTO> teams;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@PostConstruct
	public void init() {
		teams = new ArrayList<TeamDTO>();
		teams.add(new TeamDTO(1L, "SzuperCsapat"));
		teams = teamFacade.getTeams();
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		addMessage("Welcome to Primefaces!!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<TeamDTO> getTeams() {
		return teams;
	}
}
