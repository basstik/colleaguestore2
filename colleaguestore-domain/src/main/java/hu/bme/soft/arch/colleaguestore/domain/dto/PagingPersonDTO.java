package hu.bme.soft.arch.colleaguestore.domain.dto;

import java.io.Serializable;
import java.util.List;

public class PagingPersonDTO implements Serializable {

	private List<PersonDTO> data;

	private int totalLength;

	private int offset;

	@SuppressWarnings("unused")
	private PagingPersonDTO() {
	}

	public PagingPersonDTO(List<PersonDTO> data, int totalLength, int offset) {
		this.data = data;
		this.totalLength = totalLength;
		this.offset = offset;
	}

	public List<PersonDTO> getData() {
		return data;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public int getOffset() {
		return offset;
	}
}
