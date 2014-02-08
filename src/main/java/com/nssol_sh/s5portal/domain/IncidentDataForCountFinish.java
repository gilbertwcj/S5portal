package com.nssol_sh.s5portal.domain;

public class IncidentDataForCountFinish {

	private int counts;

	private double eHours;

	private double aHours;

	public IncidentDataForCountFinish() {
	}

	public IncidentDataForCountFinish(int counts, double eHours, double aHours) {
		super();
		this.counts = counts;
		this.eHours = eHours;
		this.aHours = aHours;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public double geteHours() {
		return eHours;
	}

	public void seteHours(double eHours) {
		this.eHours = eHours;
	}

	public double getaHours() {
		return aHours;
	}

	public void setaHours(double aHours) {
		this.aHours = aHours;
	}

}
