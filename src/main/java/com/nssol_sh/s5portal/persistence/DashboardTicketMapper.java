package com.nssol_sh.s5portal.persistence;

import java.util.List;
import java.util.Map;

import com.nssol_sh.s5portal.domain.TicketDataForCount;
import com.nssol_sh.s5portal.domain.TicketDataForCountFinish;

public interface DashboardTicketMapper {

	List<TicketDataForCount> getTicketData(Map<String, Object> params);
	
	TicketDataForCount getTicketDataFinish(Map<String, Object> params);
	
	List<TicketDataForCount> getTicketDataPieChart(Map<String, Object> params);
	
	TicketDataForCount getTicketDataBarChart(Map<String, Object> params);
	
	TicketDataForCountFinish getFinishTicketData(Map<String, Object> params);
}
