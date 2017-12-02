package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;

@Local
public interface TeamFacade {

	List<TeamDTO> getTeams();

	void create(TeamDTO newTeam);

	void remove(Long l);

	void modify(TeamDTO editTeam);

	List<PersonDTO> setPersonsByTeamId(Long teamId);

	void updatePersonList(Long teamId, List<PersonDTO> persons);
}
