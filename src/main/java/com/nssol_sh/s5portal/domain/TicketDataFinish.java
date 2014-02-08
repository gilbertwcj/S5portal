package com.nssol_sh.s5portal.domain;

import java.util.List;

public class TicketDataFinish {

	private String class1stsName;

	private List<TicketDataInnerFinish> ticketDataInnerFinishList;

	public TicketDataFinish(String class1stsName,
			List<TicketDataInnerFinish> ticketDataInnerFinishList) {
		super();
		this.class1stsName = class1stsName;
		this.ticketDataInnerFinishList = ticketDataInnerFinishList;
	}

	public String getClass1stsName() {
		return class1stsName;
	}

	public void setClass1stsName(String class1stsName) {
		this.class1stsName = class1stsName;
	}

	public List<TicketDataInnerFinish> getTicketDataInnerFinishList() {
		return ticketDataInnerFinishList;
	}

	public void setTicketDataInnerFinishList(
			List<TicketDataInnerFinish> ticketDataInnerFinishList) {
		this.ticketDataInnerFinishList = ticketDataInnerFinishList;
	}
}
