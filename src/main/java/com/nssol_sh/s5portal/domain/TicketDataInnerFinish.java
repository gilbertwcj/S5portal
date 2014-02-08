package com.nssol_sh.s5portal.domain;

public class TicketDataInnerFinish {
	private String class2sts;

	private TicketDataForCountFinish data;

	public TicketDataInnerFinish() {
		super();
	}

	public TicketDataInnerFinish(String class2sts,
			TicketDataForCountFinish data) {
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

	public TicketDataForCountFinish getData() {
		return data;
	}

	public void setData(TicketDataForCountFinish data) {
		this.data = data;
	}

}
