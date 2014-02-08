package com.nssol_sh.s5portal.persistence;

import java.util.List;
import java.util.Map;

import com.nssol_sh.s5portal.domain.DashboardChart01;
import com.nssol_sh.s5portal.domain.DashboardGrid01;

public interface DashboardChartMapper {
	List<DashboardChart01> getDashboardChart01Main(Map<String, String> params);

	List<DashboardChart01> getDashboardChart01Sub(Map<String, String> params);
	
	List<DashboardGrid01> getDashboardGrid01Main(Map<String, String> params);
	
	List<DashboardGrid01> getDashboardGrid01Sub(Map<String, String> params);
	
	List<DashboardGrid01> getDashboardGrid01Last(Map<String, String> params);
}
