package com.nssol_sh.s5portal.domain;

import java.util.List;

public class IncidentData {

	private String class1stsName;

	private List<IncidentDataInner> incidentDataInnerList;
	
	public IncidentData(String class1stsName,
			List<IncidentDataInner> incidentDataInnerList) {
		super();
		this.class1stsName = class1stsName;
		this.incidentDataInnerList = incidentDataInnerList;
	}

	public String getClass1stsName() {
		return class1stsName;
	}

	public void setClass1stsName(String class1stsName) {
		this.class1stsName = class1stsName;
	}

	public List<IncidentDataInner> getIncidentDataInnerList() {
		return incidentDataInnerList;
	}

	public void setIncidentDataInnerList(
			List<IncidentDataInner> incidentDataInnerList) {
		this.incidentDataInnerList = incidentDataInnerList;
	}
}
