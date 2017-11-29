package hu.bme.soft.arch.colleaguestore.client;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import hu.bme.soft.arch.colleaguestore.domain.dto.TeamDTO;

public class LazySorter implements Comparator<TeamDTO> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(TeamDTO car1, TeamDTO car2) {
		try {
			Object value1 = TeamDTO.class.getField(this.sortField).get(car1);
			Object value2 = TeamDTO.class.getField(this.sortField).get(car2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}