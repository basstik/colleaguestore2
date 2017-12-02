package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@Local
public interface TeamFacade {

	List<TeamDTO> getTeams();

	void create(TeamDTO newTeam);

	void remove(Long l);

	void modify(TeamDTO editTeam);

	List<Person> setPersonsByTeamId(Long teamId);

	void updatePersonList(Long teamId, List<Person> persons);
}
