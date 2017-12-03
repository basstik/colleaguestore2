package hu.bme.soft.arch.colleaguestore.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.business.PersonServiceBean;
import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.persistence.TeamPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@Stateless
public class TeamFacadeBean implements TeamFacade {

	@Inject
	TeamPersistenceManager teamPM;

	@Inject
	PersonServiceBean personService;

	@Inject
	Logger logger;

	@Override
	public List<TeamDTO> getTeams() {
		// logger.info("GetTeams()"); // interceptor kéne
		List<TeamDTO> teamDTOs = new ArrayList<>();
		List<Team> teamEntities = teamPM.getTeams();
		logger.info("Size team:" + teamEntities.size());

		for (Team team : teamEntities) {
			teamDTOs.add(new TeamDTO(team.getId(), team.getName()));
		}
		return teamDTOs;
	}

	@Override
	public void create(TeamDTO newTeam) {
		logger.info("create() TeamName: " + newTeam.getName());
		Team team = new Team();
		team.setName(newTeam.getName());
		teamPM.persist(team);
	}

	@Override
	public void remove(Long id) {
		logger.info("remove() TeamName: " + String.valueOf(id));
		teamPM.remove(id);
	}

	@Override
	public void modify(TeamDTO editTeam) {
		logger.info("modify() TeamName: " + editTeam.getName() + " id: " + editTeam.getId());
		Team find = teamPM.find(editTeam.getId());
		find.setName(editTeam.getName());
	}

	@Override
	public List<Person> getPersonsByTeamId(Long teamId) {
		return teamPM.find(teamId).getPersons();
	}

	@Override
	public void updatePersonList(Long teamId, List<Person> persons) {
		teamPM.find(teamId).setPersons(persons);
	}

	@Override
	public List<Project> getProjectsByTeamId(Long teamId) {
		return teamPM.find(teamId).getProjects();
	}

	@Override
	public void updateProjectList(Long teamId, List<Project> projects) {
		teamPM.find(teamId).setProjects(projects);
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
