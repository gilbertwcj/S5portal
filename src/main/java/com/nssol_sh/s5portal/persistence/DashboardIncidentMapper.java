package com.nssol_sh.s5portal.persistence;

import java.util.List;
import java.util.Map;

import com.nssol_sh.s5portal.domain.IncidentDataForCount;
import com.nssol_sh.s5portal.domain.IncidentDataForCountFinish;

public interface DashboardIncidentMapper {

	List<IncidentDataForCount> getIncidentData(Map<String, Object> params);
	
	IncidentDataForCount getIncidentDataFinish(Map<String, Object> params);
	
	List<IncidentDataForCount> getIncidentDataPieChart(Map<String, Object> params);
	
	IncidentDataForCount getIncidentDataBarChart(Map<String, Object> params);
	
	IncidentDataForCountFinish getFinishIncidentData(Map<String, Object> params);
}
