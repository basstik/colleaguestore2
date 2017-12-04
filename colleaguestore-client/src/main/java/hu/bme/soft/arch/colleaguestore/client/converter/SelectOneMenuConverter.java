package hu.bme.soft.arch.colleaguestore.client.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import hu.bme.soft.arch.colleaguestore.client.TeamView;
import hu.bme.soft.arch.colleaguestore.persistence.entity.Person;

@FacesConverter("con")
public class SelectOneMenuConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				TeamView service = (TeamView) fc.getExternalContext().getApplicationMap().get("teamView");
				List<Person> leaders = service.getLeaders();
				for (Person person : leaders) {
					if (person.getId().equals(Integer.parseInt(value))) {
						return person;
					}
				}
				return null;
				// return service.getThemes().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Person) object).getId());
		} else {
			return null;
		}
	}

	// <p:column headerText="Leader" id="leader">
	// <p:cellEditor>
	// <f:facet name="output2">
	// <h:outputText value="#{team.leader.firstName}" />
	// </f:facet>
	// <f:facet name="input2">
	// <h:selectOneMenu value="#{team.leader}" style="width:100%"
	// converter="personConverter">
	// <f:selectItems value="#{teamView.leaders}" var="pers"
	// itemLabel="#{pers.firstName}" itemValue="#{pers}" />
	// </h:selectOneMenu>
	// </f:facet>
	// </p:cellEditor>
	// </p:column>

	// <h:outputText value="Boss" />
	// <h:selectOneMenu value="#{teamView.newLeader}" style="width:100%"
	// converter="con">
	// <f:selectItems value="#{teamView.leaders}" var="pers"
	// itemLabel="#{pers.firstName}" itemValue="#{pers}">
	// </f:selectItems>
	// </h:selectOneMenu>
}
