package hu.bme.soft.arch.colleaguestore.client;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@ManagedBean
public class NewButtonView {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

}
