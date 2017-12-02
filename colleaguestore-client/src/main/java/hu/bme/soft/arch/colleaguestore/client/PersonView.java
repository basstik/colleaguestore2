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

import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;

@ManagedBean(name = "personView")
@RequestScoped
public class PersonView implements Serializable {
	private List<PersonDTO> persons;

	private PersonDTO newPerson = new PersonDTO();

	private PersonDTO editPerson = new PersonDTO();

	private PersonDTO selectedPerson;

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
	}

	public void deletePerson() {
		personFacade.delete(personid);
	}

	public void onRowEdit(RowEditEvent event) {
		PersonDTO personDTO = ((PersonDTO) event.getObject());
		FacesMessage msg = new FacesMessage("Person edited id" + personDTO.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("modify() Name: " + personDTO.getFirstName());
		personFacade.update(personDTO);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Person edit cancelled id" + ((PersonDTO) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<PersonDTO> getPersons() {
		return persons;
	}

	public PersonDTO getNewPerson() {
		return newPerson;
	}

	public PersonDTO getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(PersonDTO selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public void setNewPerson(PersonDTO newPerson) {
		this.newPerson = newPerson;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}

	public Long getPersonid() {
		return personid;
	}

	public PersonDTO getEditPerson() {
		return editPerson;
	}

	public void setEditPerson(PersonDTO editPerson) {
		this.editPerson = editPerson;
	}
}
