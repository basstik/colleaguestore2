package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.enterprise.inject.Default;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import hu.bme.soft.arch.colleaguestore.facade.MyWrapper;
import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;

// https://www.primefaces.org/showcase/ui/ajax/poll.xhtml

@ManagedBean(eager = true)
@ApplicationScoped
public class CounterView implements Serializable {

	@Default
	@SuppressWarnings({ "cdi-ambiguous-dependency" })
	@Inject
	private PersonFacade personFacade;

	private static final long serialVersionUID = 12L;

	private LinkedHashMap<Object, Number> linkedHashMapPacket = new LinkedHashMap<Object, Number>();

	private LinkedHashMap<Object, Number> linkedHashMapByte = new LinkedHashMap<Object, Number>();

	@Inject
	ChartViewPacket packet;

	@Inject
	ChartViewByte bytee;

	public void increment() {
		MyWrapper wrapper = personFacade.getMap();
		linkedHashMapPacket = wrapper.getLinkedHashMapPacket();
		linkedHashMapByte = wrapper.getLinkedHashMapByte();

		packet.increment(linkedHashMapPacket);
		bytee.increment(linkedHashMapByte);
		RequestContext.getCurrentInstance().update("alma:packetCounterLineChart");
		RequestContext.getCurrentInstance().update("korte:byteCounterLineChart");
	}
}
