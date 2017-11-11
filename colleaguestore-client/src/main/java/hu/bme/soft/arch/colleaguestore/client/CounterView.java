package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

// https://www.primefaces.org/showcase/ui/ajax/poll.xhtml

@ManagedBean
@ViewScoped
public class CounterView implements Serializable {

	private static final long serialVersionUID = 12L;

	private int number;

	public int getNumber() {
		return number;
	}

	public void increment() {
		number++;
	}
}
