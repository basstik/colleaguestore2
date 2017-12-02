package hu.bme.soft.arch.colleaguestore.facade;

import java.util.List;

import javax.ejb.Local;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingPersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonFilterDTO;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@Local
public interface PersonFacade {

	List<Person> getPersons();

	PagingPersonDTO getPersons(PersonFilterDTO personFilterDTO);

	void create(Person personDto);

	void update(Person person);

	void delete(Long id);
}
