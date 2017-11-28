package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.facade.TeamFacade;

@ManagedBean(name = "teamViewReq")
@RequestScoped
public class TeamViewReq implements Serializable {

	// @ManagedProperty(value = "#{teamid}")
	private Long teamid; // +setter

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private TeamFacade teamFacade;

	// update="myForm:team:teamList"
	// <f:param name="teamId" value="#{team.id}" />

	public void deleteTeam() {
		// HttpServletRequest request = (HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequest();
		// String id = request.getParameter("teamId");
		// Map<String, String> parameterMap =
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequestParameterMap();
		// String id = parameterMap.get("teamId");

		// teamFacade.remove(Long.valueOf(id));
		teamFacade.remove(2L);
	}

	public Long getTeamid() {
		return teamid;
	}

	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}

}
