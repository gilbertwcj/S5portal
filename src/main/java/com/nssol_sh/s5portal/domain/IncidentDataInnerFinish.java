package com.nssol_sh.s5portal.domain;

public class IncidentDataInnerFinish {
	private String class2sts;

	private IncidentDataForCountFinish data;

	public IncidentDataInnerFinish() {
		super();
	}

	public IncidentDataInnerFinish(String class2sts,
			IncidentDataForCountFinish data) {
		super();
		this.class2sts = class2sts;
		this.data = data;
	}

	public String getClass2sts() {
		return class2sts;
	}

	public void setClass2sts(String class2sts) {
		this.class2sts = class2sts;
	}

	public IncidentDataForCountFinish getData() {
		return data;
	}

	public void setData(IncidentDataForCountFinish data) {
		this.data = data;
	}

}
