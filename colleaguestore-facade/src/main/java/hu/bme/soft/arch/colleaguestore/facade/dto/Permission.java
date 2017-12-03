package hu.bme.soft.arch.colleaguestore.facade.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permission {

	public static final String LOGIN = "LOGIN";
	public static final String ADMIN = "ADMIN";
	public static final String VIEW = "VIEW";

	private static final List<String> VALUES = initValues(LOGIN, ADMIN, VIEW);

	public static final List<String> getValues() {
		return VALUES;
	}

	private static List<String> initValues(String... permissions) {
		return Collections.unmodifiableList(Arrays.asList(permissions));
	}

	private Permission() {
	}
}