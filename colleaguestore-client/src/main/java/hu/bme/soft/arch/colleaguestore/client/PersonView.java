package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@ManagedBean(name = "personView")
@RequestScoped
public class PersonView implements Serializable {

	private List<Person> persons;

	private Person newPerson = new Person();

	private Person selectedPerson;

	private Long personid;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private PersonFacade personFacade;

	@PostConstruct
	public void init() {
		persons = personFacade.getPersons();
	}

	public void refreshButtonAction(ActionEvent actionEvent) {
		persons = personFacade.getPersons();
	}

	public void save() {
		personFacade.create(newPerson);
		newPerson = new Person();
	}

	public void deletePerson() {
		personFacade.delete(personid);
	}

	public void onRowEdit(RowEditEvent event) {
		Person person = ((Person) event.getObject());
		FacesMessage msg = new FacesMessage("Person edited id" + person.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("modify() Name: " + person.getFirstName());
		personFacade.update(person);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Person edit cancelled id" + ((Person) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person getNewPerson() {
		return newPerson;
	}

	public void setNewPerson(Person newPerson) {
		this.newPerson = newPerson;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public Long getPersonid() {
		return personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}
}
