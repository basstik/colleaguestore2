package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@ManagedBean(name = "teamView")
@ViewScoped
public class TeamView implements Serializable {

	private List<TeamDTO> teams;

	// @ManagedProperty("#{teamService}")
	// private TeamService service;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@PostConstruct
	public void init() {
		teams = new ArrayList<TeamDTO>();
		teams.add(new TeamDTO(1L, "SzuperCsapat"));
	}

	public List<TeamDTO> getTeams() {
		// return teams;
		return teamFacade.getTeams();
	}

	// public void setService(CarService service) {
	// this.service = service;
	// }
}
