package com.nssol_sh.s5portal.domain;

import java.util.List;

public class IncidentDataInner {
	private String class2sts;

	private List<IncidentDataForCount> data01;

	private IncidentDataForCount data02;

	public IncidentDataInner(String class2sts, List<IncidentDataForCount> data01,
			IncidentDataForCount data02) {
		super();
		this.class2sts = class2sts;
		this.data01 = data01;
		this.data02 = data02;
	}

	public String getClass2sts() {
		return class2sts;
	}

	public void setClass2sts(String class2sts) {
		this.class2sts = class2sts;
	}

	public List<IncidentDataForCount> getData01() {
		return data01;
	}

	public void setData01(List<IncidentDataForCount> data01) {
		this.data01 = data01;
	}

	public IncidentDataForCount getData02() {
		return data02;
	}

	public void setData02(IncidentDataForCount data02) {
		this.data02 = data02;
	}
}
