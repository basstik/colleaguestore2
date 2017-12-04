package hu.bme.soft.arch.colleaguestore.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.facade.dto.Permission;
import hu.bme.soft.arch.colleaguestore.persistence.TeamPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@Stateless
public class TeamFacadeBean implements TeamFacade {

	@Inject
	TeamPersistenceManager teamPM;

	@Inject
	Logger logger;

	@Override
	@RolesAllowed({ Permission.ADMIN, Permission.VIEW })
	public List<TeamDTO> getTeams() {
		List<TeamDTO> teamDTOs = new ArrayList<>();
		List<Team> teamEntities = teamPM.getTeams();
		logger.info("Size team:" + teamEntities.size());

		for (Team team : teamEntities) {
			teamDTOs.add(new TeamDTO(team.getId(), team.getName()));
		}
		return teamDTOs;
	}

	@Override
	@RolesAllowed(Permission.ADMIN)
	public void create(TeamDTO newTeam) {
		logger.info("create() TeamName: " + newTeam.getName());
		Team team = new Team();
		team.setName(newTeam.getName());
		teamPM.persist(team);
	}

	@Override
	@RolesAllowed(Permission.ADMIN)
	public void remove(Long id) {
		logger.info("remove() TeamName: " + String.valueOf(id));
		teamPM.remove(id);
	}

	@Override
	@RolesAllowed(Permission.ADMIN)
	public void modify(TeamDTO editTeam) {
		logger.info("modify() TeamName: " + editTeam.getName() + " id: " + editTeam.getId());
		Team find = teamPM.find(editTeam.getId());
		find.setName(editTeam.getName());
	}

	@Override
	@RolesAllowed({ Permission.ADMIN, Permission.VIEW })
	public List<Person> getPersonsByTeamId(Long teamId) {
		return teamPM.find(teamId).getPersons();
	}

	@Override
	@RolesAllowed(Permission.ADMIN)
	public void updatePersonList(Long teamId, List<Person> persons) {
		teamPM.find(teamId).setPersons(persons);
	}

	@Override
	@RolesAllowed({ Permission.ADMIN, Permission.VIEW })
	public List<Project> getProjectsByTeamId(Long teamId) {
		return teamPM.find(teamId).getProjects();
	}

	@Override
	@RolesAllowed(Permission.ADMIN)
	public void updateProjectList(Long teamId, List<Project> projects) {
		teamPM.find(teamId).setProjects(projects);
	}
}
