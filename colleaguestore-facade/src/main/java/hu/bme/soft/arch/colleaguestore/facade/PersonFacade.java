package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingPersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonFilterDTO;

@Local
public interface PersonFacade {

	List<PersonDTO> getPersons();

	PagingPersonDTO getPersons(PersonFilterDTO personFilterDTO);

	void create(PersonDTO personDto);

	void update(PersonDTO person);

	void delete(Long id);
}
