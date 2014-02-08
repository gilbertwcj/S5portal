package com.nssol_sh.s5portal.domain;

import java.util.List;

public class TicketDataInner {
	private String class2sts;

	private List<TicketDataForCount> data01;

	private TicketDataForCount data02;

	public TicketDataInner(String class2sts, List<TicketDataForCount> data01,
			TicketDataForCount data02) {
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

	public List<TicketDataForCount> getData01() {
		return data01;
	}

	public void setData01(List<TicketDataForCount> data01) {
		this.data01 = data01;
	}

	public TicketDataForCount getData02() {
		return data02;
	}

	public void setData02(TicketDataForCount data02) {
		this.data02 = data02;
	}
}
