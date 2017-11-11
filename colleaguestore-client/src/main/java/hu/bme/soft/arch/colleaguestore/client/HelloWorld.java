package hu.bme.soft.arch.colleaguestore.client;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "helloWorld", eager = true)
@RequestScoped
public class HelloWorld {
	// @ManagedProperty(value = "#{message}")
	// private Message message;

	public String getMessage() {
		return "Hello World!";
	}
}
