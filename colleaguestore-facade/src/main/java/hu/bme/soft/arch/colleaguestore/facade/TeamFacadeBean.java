package hu.bme.soft.arch.colleaguestore.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingTeamDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.TeamFilterDTO;
import hu.bme.soft.arch.colleaguestore.persistence.TeamPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@Stateless
public class TeamFacadeBean implements TeamFacade {

	@Inject
	TeamPersistenceManager teamPM;

	@Inject
	Logger logger;

	@Override
	public PagingTeamDTO getTeams(TeamFilterDTO teamFilterDTO) {
		List<TeamDTO> teamDto = new ArrayList<>();
		List<Team> teamEntities = teamPM.getTeams(teamFilterDTO);

		for (Team team : teamEntities) {
			Person bossEntity = team.getBoss();
			PersonDTO boss = new PersonDTO(bossEntity.getId(), bossEntity.getFirstName(), bossEntity.getLastName());
			teamDto.add(new TeamDTO(team.getId(), team.getName(), boss));
		}

		int totalLength = teamPM.countTeams(teamFilterDTO);

		return new PagingTeamDTO(teamDto, totalLength, teamFilterDTO.getOffset());

	}

}
