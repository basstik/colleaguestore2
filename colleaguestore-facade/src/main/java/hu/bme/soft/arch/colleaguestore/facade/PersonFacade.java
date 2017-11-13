package hu.bme.soft.arch.colleaguestore.facade;

import java.util.LinkedHashMap;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingPersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonFilterDTO;

@Local
public interface PersonFacade {

	PagingPersonDTO getPersons(PersonFilterDTO personFilterDTO);

	void createPerson(PersonDTO personDto);

	void updatePerson(PersonDTO person);

	void deletePerson(Long id);

	Long printPacketCount();

	LinkedHashMap<Object, Number> getMap();

}
