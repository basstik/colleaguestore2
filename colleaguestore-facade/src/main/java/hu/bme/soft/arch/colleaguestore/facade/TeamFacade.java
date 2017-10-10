package hu.bme.soft.arch.colleaguestore.facade;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingTeamDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.TeamFilterDTO;

@Local
public interface TeamFacade {

	PagingTeamDTO getTeams(TeamFilterDTO teamFilterDTO);

}
