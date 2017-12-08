package hu.bme.soft.arch.colleaguestore.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import hu.bme.soft.arch.colleaguestore.domain.dto.PagingPersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.domain.dto.PersonFilterDTO;
import hu.bme.soft.arch.colleaguestore.facade.dto.Permission;
import hu.bme.soft.arch.colleaguestore.persistence.PersonPersistenceManager;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@Singleton
public class PersonFacadeBean implements PersonFacade {

	@Inject
	PersonPersistenceManager personPM;

	@Inject
	Logger logger;

	private List<Long> list = new ArrayList<>();

	@RolesAllowed({ Permission.ADMIN, Permission.VIEW })
	public List<Long> getList() {
		return list;
	}

	@Override
	@RolesAllowed({ Permission.ADMIN, Permission.VIEW })
	public List<Person> getPersons() {
		return personPM.getPersons();
	}

	@Override
	@RolesAllowed({ Permission.ADMIN, Permission.VIEW })
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
	@RolesAllowed({ Permission.ADMIN })
	public void create(Person personDto) {
		personPM.persist(personDto);
	}

	@Override
	@RolesAllowed({ Permission.ADMIN })
	public void update(Person person) {
		Person find = personPM.find(person.getId());
		find.setFirstName(person.getFirstName());
		find.setLastName(person.getLastName());
	}

	@Override
	@RolesAllowed({ Permission.ADMIN})
	public void delete(Long id) {
		logger.info("Delete id: " + id);
		personPM.remove(id);
	}
}
