package hu.bme.soft.arch.colleaguestore.domain.dto;

import java.io.Serializable;
import java.util.List;

public class PagingTeamDTO implements Serializable {

	private List<TeamDTO> data;

	private int totalLength;

	private int offset;

	@SuppressWarnings("unused")
	private PagingTeamDTO() {
	}

	public PagingTeamDTO(List<TeamDTO> data, int totalLength, int offset) {
		this.data = data;
		this.totalLength = totalLength;
		this.offset = offset;
	}

	public List<TeamDTO> getData() {
		return data;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public int getOffset() {
		return offset;
	}
}
