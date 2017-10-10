package hu.bme.soft.arch.colleaguestore.domain.dto;

import java.io.Serializable;

public class TeamFilterDTO implements Serializable {

	private int offset;

	private int limit;

	@SuppressWarnings("unused")
	private TeamFilterDTO() {
	}

	public TeamFilterDTO(Integer offset, Integer limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}
}
