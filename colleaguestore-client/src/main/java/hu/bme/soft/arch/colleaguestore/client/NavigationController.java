package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
	private static final long serialVersionUID = 1L;

	// @SuppressWarnings("cdi-ambiguous-dependency")
	// @Inject
	// private PersonFacade personFacade;

	public String moveToPage1() {
		// personFacade.printPacketCount();
		return "page1";
	}
}
