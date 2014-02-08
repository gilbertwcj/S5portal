package com.nssol_sh.s5portal.domain;

public class TicketBarChartData {

	private Object[] messages;

	private Object[] datas;

	private Object[] ticks;

	private String title;

	public TicketBarChartData() {
	}

	public TicketBarChartData(Object[] messages, Object[] datas,
			Object[] ticks, String title) {
		this.messages = messages;
		this.datas = datas;
		this.ticks = ticks;
		this.title = title;
	}

	public Object[] getMessages() {
		return messages;
	}

	public void setMessages(Object[] messages) {
		this.messages = messages;
	}

	public Object[] getDatas() {
		return datas;
	}

	public void setDatas(Object[] datas) {
		this.datas = datas;
	}

	public Object[] getTicks() {
		return ticks;
	}

	public void setTicks(Object[] ticks) {
		this.ticks = ticks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
