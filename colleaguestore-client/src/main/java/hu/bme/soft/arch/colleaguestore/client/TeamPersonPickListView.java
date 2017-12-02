package hu.bme.soft.arch.colleaguestore.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import hu.bme.soft.arch.colleaguestore.domain.dto.PersonDTO;
import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@SessionScoped
@ManagedBean(name = "teamPersonPickListView")
public class TeamPersonPickListView {

	private DualListModel<String> persons;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private PersonFacade personFacade;

	@PostConstruct
	public void init() {
		persons = new DualListModel<String>();
	}

	public DualListModel<String> getPersons() {
		return persons;
	}

	public void setPersons(DualListModel<String> persons) {
		this.persons = persons;
	}

	public void onSelect(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
	}

	public void onUnselect(UnselectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
	}

	public void onReorder() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
	}

	Long selectedTeamId;

	public void setPersonsByTeamId(Long teamId) {
		selectedTeamId = teamId;
		List<PersonDTO> personsByTeam = teamFacade.setPersonsByTeamId(teamId);
		List<PersonDTO> allPersons = personFacade.getPersons();

		List<String> personsSource = new ArrayList<String>();
		for (PersonDTO personDTO : allPersons) {
			personsSource.add(personDTO.getFirstName());
		}

		List<String> personsTarget = new ArrayList<String>();

		persons = new DualListModel<String>(personsSource, personsTarget);
	}

	public List<String> personssource = new ArrayList<String>();

	public void savePersons() {
		System.out.println("Fent: " + persons.getSource());
		System.out.println("Lend: " + persons.getTarget());
		// teamFacade.updatePersonList(selectedTeamId, persons.getTarget());
	}

	public List<String> getPersonssource() {
		return personssource;
	}

	public void setPersonssource(List<String> personssource) {
		this.personssource = personssource;
	}

	// public void savePersons() {
	// System.out.println("Fent: " + persons.getSource());
	// System.out.println("Lend: " + persons.getTarget());
	// }

}