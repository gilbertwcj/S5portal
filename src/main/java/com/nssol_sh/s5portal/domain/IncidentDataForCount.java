package com.nssol_sh.s5portal.domain;

public class IncidentDataForCount {

	private String name;

	private int counts;
	
	public IncidentDataForCount() {
	}

	public IncidentDataForCount(String name, int counts) {
		super();
		this.name = name;
		this.counts = counts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

}
