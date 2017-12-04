package hu.bme.soft.arch.colleaguestore.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingPersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonFilterDTO;
import hu.bme.soft.arch.colleaguestore.persistence.PersonPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@Singleton
public class PersonFacadeBean implements PersonFacade {

	@Inject
	PersonPersistenceManager personPM;

	@Inject
	Logger logger;

	private List<Long> list = new ArrayList<>();

	public List<Long> getList() {
		return list;
	}

	@Override
	public List<Person> getPersons() {
		return personPM.getPersons();
	}

	@Override
	public PagingPersonDTO getPersons(PersonFilterDTO personFilterDTO) {
		logger.info("Received list person request");
		List<PersonDTO> personDto = new ArrayList<>();
		List<Person> personEntities = personPM.getPersons(personFilterDTO);

		for (Person person : personEntities) {
			personDto.add(new PersonDTO(person.getId(), person.getFirstName(), person.getLastName(),
					person.getNationality(), person.getDateOfBirth(), person.getPosition()));
		}
		int totalLength = personPM.countPersons(personFilterDTO);
		return new PagingPersonDTO(personDto, totalLength, personFilterDTO.getOffset());
	}

	@Override
	public void create(Person personDto) {
		personPM.persist(personDto);
	}

	@Override
	public void update(Person person) {
		Person a = personPM.find(person.getId());
		a.setFirstName(person.getFirstName());
		a.setLastName(person.getLastName());
		a.setNationality(person.getNationality());
		a.setDateOfBirth(person.getDateOfBirth());
		a.setPosition(person.getPosition());
	}

	@Override
	public void delete(Long id) {
		logger.info("Delete id: " + id);
		personPM.remove(id);
	}
}
