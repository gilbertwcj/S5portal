package com.nssol_sh.s5portal.domain;

import java.util.List;

public class IncidentDataFinish {

	private String class1stsName;

	private List<IncidentDataInnerFinish> incidentDataInnerFinishList;

	public IncidentDataFinish(String class1stsName,
			List<IncidentDataInnerFinish> incidentDataInnerFinishList) {
		super();
		this.class1stsName = class1stsName;
		this.incidentDataInnerFinishList = incidentDataInnerFinishList;
	}

	public String getClass1stsName() {
		return class1stsName;
	}

	public void setClass1stsName(String class1stsName) {
		this.class1stsName = class1stsName;
	}

	public List<IncidentDataInnerFinish> getIncidentDataInnerFinishList() {
		return incidentDataInnerFinishList;
	}

	public void setIncidentDataInnerFinishList(
			List<IncidentDataInnerFinish> incidentDataInnerFinishList) {
		this.incidentDataInnerFinishList = incidentDataInnerFinishList;
	}
}
