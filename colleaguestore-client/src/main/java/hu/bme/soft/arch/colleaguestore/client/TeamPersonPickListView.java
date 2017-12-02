package hu.bme.soft.arch.colleaguestore.client;

import java.util.ArrayList;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;
import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@ApplicationScoped
@ManagedBean(name = "teamPersonPickListView")
public class TeamPersonPickListView {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private PersonFacade personFacade;

	private DualListModel<Person> persons;

	Long selectedTeamId;

	@PostConstruct
	public void init() {
		persons = new DualListModel<Person>();
	}

	public void setPersonsByTeamId(Long teamId) {
		System.out.println("setPersonsByTeamId");
		selectedTeamId = teamId;
		HashSet<Person> personsByTeam = new HashSet<Person>(teamFacade.getPersonsByTeamId(teamId));
		HashSet<Person> allPersons = new HashSet<Person>(personFacade.getPersons());

		persons = new DualListModel<Person>(new ArrayList<Person>(allPersons), new ArrayList<Person>(personsByTeam));
	}

	public void savePersons() {
		if (!persons.getSource().isEmpty()) {
			System.out.println("Fent: " + persons.getSource().get(0).getFirstName());
		}
		if (!persons.getTarget().isEmpty()) {
			// System.out.println("Lend: " + persons.getTarget().get(0).getFirstName());
			System.out.println("Lend: " + persons.getTarget());
		}
		teamFacade.updatePersonList(selectedTeamId, persons.getTarget());
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

	public DualListModel<Person> getPersons() {
		return persons;
	}

	public void setPersons(DualListModel<Person> persons) {
		this.persons = persons;
	}

	public Long getSelectedTeamId() {
		return selectedTeamId;
	}

	public void setSelectedTeamId(Long selectedTeamId) {
		this.selectedTeamId = selectedTeamId;
	}
}