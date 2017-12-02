package hu.bme.soft.arch.colleaguestore.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@Stateless
public class PersonServiceBean {

	public List<PersonDTO> convertList(List<Person> persons) {
		List<PersonDTO> result = new ArrayList<>();
		for (Person person : persons) {
			result.add(new PersonDTO(person.getId(), person.getFirstName(), person.getLastName(),
					person.getNationality(), person.getDateOfBirth()));
		}
		return result;
	}

}
