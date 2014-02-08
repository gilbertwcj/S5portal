package com.nssol_sh.s5portal.domain;

import java.util.List;

public class TicketData {

	private String class1stsName;

	private List<TicketDataInner> ticketDataInnerList;
	
	public TicketData(String class1stsName,
			List<TicketDataInner> ticketDataInnerList) {
		super();
		this.class1stsName = class1stsName;
		this.ticketDataInnerList = ticketDataInnerList;
	}

	public String getClass1stsName() {
		return class1stsName;
	}

	public void setClass1stsName(String class1stsName) {
		this.class1stsName = class1stsName;
	}

	public List<TicketDataInner> getTicketDataInnerList() {
		return ticketDataInnerList;
	}

	public void setTicketDataInnerList(
			List<TicketDataInner> ticketDataInnerList) {
		this.ticketDataInnerList = ticketDataInnerList;
	}
}
