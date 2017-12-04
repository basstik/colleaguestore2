package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Project;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Team;

@Local
public interface TeamFacade {

	List<Team> getTeams();

	void create(Team newTeam);

	void remove(Long l);

	void modify(Team editTeam);

	List<Person> getPersonsByTeamId(Long teamId);

	void updatePersonList(Long teamId, List<Person> persons);

	List<Project> getProjectsByTeamId(Long teamId);

	void updateProjectList(Long teamId, List<Project> persons);
}
