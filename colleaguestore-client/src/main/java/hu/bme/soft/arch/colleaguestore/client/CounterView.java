package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import hu.bme.soft.arch.colleaguestore.facade.MyWrapper;
import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;

// https://www.primefaces.org/showcase/ui/ajax/poll.xhtml

@ManagedBean
public class CounterView implements Serializable {

	@Default
	@SuppressWarnings({ "unused", "cdi-ambiguous-dependency" })
	@Inject
	private PersonFacade personFacade;

	private static final long serialVersionUID = 12L;

	public LinkedHashMap<Object, Number> linkedHashMapPacket = new LinkedHashMap<Object, Number>();

	public LinkedHashMap<Object, Number> linkedHashMapByte = new LinkedHashMap<Object, Number>();

	public CounterView() {
		linkedHashMapPacket.put(1, 450);
		linkedHashMapByte.put(1, 450);
	}
	
	public void increment() {
		MyWrapper wrapper = personFacade.getMap();
		linkedHashMapPacket = wrapper.getLinkedHashMapPacket();
		linkedHashMapByte = wrapper.getLinkedHashMapByte();
	}
}
