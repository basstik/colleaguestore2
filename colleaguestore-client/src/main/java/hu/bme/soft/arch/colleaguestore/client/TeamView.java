package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;

@ManagedBean(name = "teamView")
@ViewScoped
public class TeamView implements Serializable {

	private List<TeamDTO> teams;

	// @ManagedProperty("#{teamService}")
	// private TeamService service;

	@PostConstruct
	public void init() {
		teams = new ArrayList<TeamDTO>();
		teams.add(new TeamDTO(1L, "SzuperCsapat"));
	}

	public List<TeamDTO> getTeams() {
		return teams;
	}

	// public void setService(CarService service) {
	// this.service = service;
	// }
}
