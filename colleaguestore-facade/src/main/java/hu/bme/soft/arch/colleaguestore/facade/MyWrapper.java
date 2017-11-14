package hu.bme.soft.arch.colleaguestore.facade;

import java.util.LinkedHashMap;

public class MyWrapper {

	LinkedHashMap<Object, Number> linkedHashMapPacket = new LinkedHashMap<Object, Number>();

	LinkedHashMap<Object, Number> linkedHashMapByte = new LinkedHashMap<Object, Number>();

	public MyWrapper(LinkedHashMap<Object, Number> linkedHashMapPacket,
			LinkedHashMap<Object, Number> linkedHashMapByte) {
		this.linkedHashMapPacket = linkedHashMapPacket;
		this.linkedHashMapByte = linkedHashMapByte;
	}

	public LinkedHashMap<Object, Number> getLinkedHashMapPacket() {
		return linkedHashMapPacket;
	}

	public void setLinkedHashMapPacket(LinkedHashMap<Object, Number> linkedHashMapPacket) {
		this.linkedHashMapPacket = linkedHashMapPacket;
	}

	public LinkedHashMap<Object, Number> getLinkedHashMapByte() {
		return linkedHashMapByte;
	}

	public void setLinkedHashMapByte(LinkedHashMap<Object, Number> linkedHashMapByte) {
		this.linkedHashMapByte = linkedHashMapByte;
	}

}
