package com.nssol_sh.s5portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nssol_sh.s5portal.domain.DashboardChart01;
import com.nssol_sh.s5portal.domain.DashboardGrid01;
import com.nssol_sh.s5portal.persistence.DashboardChartMapper;

@Service
public class DashboardChartService {

	@Autowired
	private DashboardChartMapper dashboardChartMapper;

	public Object[] getDashboardChart01Main(String projectId, String fromDate,
			String toDate) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("projectId", projectId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		List<DashboardChart01> resultList = dashboardChartMapper
				.getDashboardChart01Main(params);
		Object[] data = new Object[resultList.size()];
		for (int i = 0; i < resultList.size(); i++) {
			Object[] element = new Object[2];
			element[0] = resultList.get(i).getName();
			element[1] = resultList.get(i).getCount();
			data[i] = element;
		}

		return data;
	}

	public Object[] getDashboardChart01Sub(String projectId, String classValue,
			String fromDate, String toDate) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("projectId", projectId);
		params.put("classValue", classValue);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		List<DashboardChart01> resultList = dashboardChartMapper
				.getDashboardChart01Sub(params);
		Object[] data = new Object[resultList.size()];
		for (int i = 0; i < resultList.size(); i++) {
			Object[] element = new Object[2];
			element[0] = resultList.get(i).getName();
			element[1] = resultList.get(i).getCount();
			data[i] = element;
		}
		return data;
	}

	public List<DashboardGrid01> getDashboardGrid01Main(String projectId,
			String fromDate, String toDate) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("projectId", projectId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		return dashboardChartMapper.getDashboardGrid01Main(params);
	}

	public List<DashboardGrid01> getDashboardGrid01Sub(String projectId,
			String classValue, String fromDate, String toDate) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("projectId", projectId);
		params.put("classValue", classValue);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		return dashboardChartMapper.getDashboardGrid01Sub(params);
	}
	
	public List<DashboardGrid01> getDashboardGrid01Last(String projectId,
			String classValue, String classValue2, String fromDate, String toDate) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("projectId", projectId);
		params.put("classValue", classValue);
		params.put("classValue2", classValue2);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		return dashboardChartMapper.getDashboardGrid01Last(params);
	}
}
