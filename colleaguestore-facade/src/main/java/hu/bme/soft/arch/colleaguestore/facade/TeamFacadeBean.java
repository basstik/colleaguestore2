package hu.bme.soft.arch.colleaguestore.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.persistence.TeamPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@Stateless
public class TeamFacadeBean implements TeamFacade {

	@Inject
	TeamPersistenceManager teamPM;

	@Inject
	Logger logger;

	@Override
	public List<TeamDTO> getTeams() {
		List<TeamDTO> teamDTOs = new ArrayList<>();
		List<Team> teamEntities = teamPM.getTeams();
		logger.debug("Size team:" + teamEntities.size());
		logger.error("Size team:" + teamEntities.size());
		System.out.println("Size team:" + teamEntities.size());

		for (Team team : teamEntities) {
			teamDTOs.add(new TeamDTO(team.getId(), team.getName()));
		}

		return teamDTOs;
	}

	// @Override
	// public PagingTeamDTO getTeams(TeamFilterDTO teamFilterDTO) {
	// List<TeamDTO> teamDto = new ArrayList<>();
	// List<Team> teamEntities = teamPM.getTeams(teamFilterDTO);
	//
	// for (Team team : teamEntities) {
	// Person bossEntity = team.getBoss();
	// PersonDTO boss = new PersonDTO(bossEntity.getId(), bossEntity.getFirstName(),
	// bossEntity.getLastName());
	// teamDto.add(new TeamDTO(team.getId(), team.getName(), boss));
	// }
	//
	// int totalLength = teamPM.countTeams(teamFilterDTO);
	//
	// return new PagingTeamDTO(teamDto, totalLength, teamFilterDTO.getOffset());
	// }

}
