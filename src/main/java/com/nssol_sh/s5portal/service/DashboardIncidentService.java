package com.nssol_sh.s5portal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nssol_sh.s5portal.domain.Class1sts;
import com.nssol_sh.s5portal.domain.Class2sts;
import com.nssol_sh.s5portal.domain.IncidentBarChartData;
import com.nssol_sh.s5portal.domain.IncidentData;
import com.nssol_sh.s5portal.domain.IncidentDataFinish;
import com.nssol_sh.s5portal.domain.IncidentDataForCount;
import com.nssol_sh.s5portal.domain.IncidentDataForCountFinish;
import com.nssol_sh.s5portal.domain.IncidentDataInner;
import com.nssol_sh.s5portal.domain.IncidentDataInnerFinish;
import com.nssol_sh.s5portal.domain.Status;
import com.nssol_sh.s5portal.persistence.CommonMapper;
import com.nssol_sh.s5portal.persistence.DashboardIncidentMapper;
import com.nssol_sh.spring.foundation.DataSourceContextHolder;

@Service
public class DashboardIncidentService {

	@Autowired
	private CommonMapper commonMapper;

	@Autowired
	private DashboardIncidentMapper dashboardIncidentMapper;

	private static final SimpleDateFormat fmt = new SimpleDateFormat(
			"yyyy-MM-dd");
		

	public List<IncidentData> getIncidentDatas(String projectId,
			String fromDate, String toDate) {

		Status finish = commonMapper.getStatusByName("完成");
		
		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier("supportdesk");

		List<IncidentData> incidentDataList = new ArrayList<IncidentData>(
				class1stsList.size());

		for (Class1sts class1sts : class1stsList) {

			List<Class2sts> class2stsList = commonMapper
					.getClass2stsByParentId(class1sts.getId());
			List<IncidentDataInner> incidentDataInnerList = new ArrayList<IncidentDataInner>(
					class2stsList.size());

			for (Class2sts class2sts : class2stsList) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("projectId", projectId);
				params.put("fromDate", fromDate);
				params.put("toDate", toDate);
				params.put("class1stsVal", class1sts.getName());
				params.put("class2stsVal", class2sts.getName());

				List<IncidentDataForCount> data01 = dashboardIncidentMapper
						.getIncidentData(params);

				params.put("statusId", finish.getId());

				IncidentDataForCount data02 = dashboardIncidentMapper
						.getIncidentDataFinish(params);
				if (data02 == null) {
					data02 = new IncidentDataForCount("完成", 0);
				}

				IncidentDataInner incidentDataInner = new IncidentDataInner(
						class2sts.getName(), data01, data02);
				incidentDataInnerList.add(incidentDataInner);
			}

			IncidentData incidentData = new IncidentData(class1sts.getName(),
					incidentDataInnerList);
			incidentDataList.add(incidentData);
		}

		return incidentDataList;
	}

	public Object[] getIncidentDataPieChart(String projectId, String fromDate,
			String toDate) {

		Status finish = commonMapper.getStatusByName("完成");
		
		List<Object[]> tmpList = new ArrayList<Object[]>();

		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier("supportdesk");

		for (Class1sts class1sts : class1stsList) {

			List<Class2sts> class2stsList = commonMapper
					.getClass2stsByParentId(class1sts.getId());

			for (Class2sts class2sts : class2stsList) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("projectId", projectId);
				params.put("fromDate", fromDate);
				params.put("toDate", toDate);
				params.put("class1stsVal", class1sts.getName());
				params.put("class2stsVal", class2sts.getName());
				params.put("statusId", finish.getId());

				List<IncidentDataForCount> data01 = dashboardIncidentMapper
						.getIncidentDataPieChart(params);
				for (int i = 0; i < data01.size(); i++) {
					Object[] element = new Object[2];
					element[0] = data01.get(i).getName();
					element[1] = data01.get(i).getCounts();
					tmpList.add(element);
				}
			}
		}

		return tmpList.toArray();
	}

	public IncidentBarChartData getIncidentDataBarChart(String projectId,
			String fromDate, String toDate) throws ParseException {
		Status finish = commonMapper.getStatusByName("完成");
		System.out.println(DataSourceContextHolder.getDataSourceType());
		System.out.println(commonMapper.getProjectByIdentifier(projectId));
		
		
		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier("supportdesk");

		Date fromD = fmt.parse(fromDate);
		Calendar fromC = Calendar.getInstance();
		fromC.setTime(fromD);

		Date toD = fmt.parse(toDate);
		Calendar toC = Calendar.getInstance();
		toC.setTime(toD);

		long diff = toD.getTime() - fromD.getTime();
		long days = diff / (24 * 60 * 60 * 1000);

		if (days > 7) {
			fromC.add(Calendar.MONTH, -2);
			toC.add(Calendar.MONTH, -2);
		} else {
			fromC.add(Calendar.WEEK_OF_YEAR, -2);
			toC.add(Calendar.WEEK_OF_YEAR, -2);
		}

		Object[] messages = new Object[class1stsList.size()];

		Object[][] dataList = new Object[class1stsList.size()][3];

		Object[] ticks = new Object[3];

		int class1stsCount = 0;
		for (Class1sts class1sts : class1stsList) {
			messages[class1stsCount] = new Message(class1sts.getName());

			for (int i = 0; i < 3; i++) {

				Calendar fromCClone = (Calendar) fromC.clone();
				Calendar toCClone = (Calendar) toC.clone();

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("projectId", projectId);

				if (days > 7) {
					fromCClone.add(Calendar.MONTH, i);
					toCClone.add(Calendar.MONTH, i);
					toCClone.set(Calendar.DAY_OF_MONTH,
							toCClone.getActualMaximum(Calendar.DAY_OF_MONTH));
					ticks[i] = fromCClone.get(Calendar.YEAR) + "年"
							+ (fromCClone.get(Calendar.MONTH) + 1) + "月";
				} else {
					fromCClone.add(Calendar.WEEK_OF_YEAR, i);
					toCClone.add(Calendar.WEEK_OF_YEAR, i);
					ticks[i] = fromCClone.get(Calendar.YEAR) + "年"
							+ (fromCClone.get(Calendar.MONTH) + 1) + "月第"
							+ (fromCClone.get(Calendar.WEEK_OF_MONTH)) + "周";
				}

				params.put("fromDate", fmt.format(fromCClone.getTime()));
				params.put("toDate", fmt.format(toCClone.getTime()));

				params.put("class1stsVal", class1sts.getName());
				
				params.put("statusId", finish.getId());

				IncidentDataForCount incidentDataForCount = dashboardIncidentMapper
						.getIncidentDataBarChart(params);

				if (incidentDataForCount == null) {
					dataList[class1stsCount][i] = 0;
				} else {
					dataList[class1stsCount][i] = incidentDataForCount
							.getCounts();
				}
			}

			class1stsCount++;
		}

		ticks[2] = "现在";
		String title = "Project "
				+ commonMapper.getProjectByIdentifier(projectId).getName()
				+ ": Incidents";
		return new IncidentBarChartData(messages, dataList, ticks, title);
	}

	public List<IncidentDataFinish> getFinsishIncidentDatas(String projectId,
			String fromDate, String toDate) {
		
		Status finish = commonMapper.getStatusByName("完成");
		
		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier("supportdesk");

		List<IncidentDataFinish> incidentDataFinishList = new ArrayList<IncidentDataFinish>(
				class1stsList.size());

		for (Class1sts class1sts : class1stsList) {

			List<Class2sts> class2stsList = commonMapper
					.getClass2stsByParentId(class1sts.getId());
			List<IncidentDataInnerFinish> incidentDataInnerFinishList = new ArrayList<IncidentDataInnerFinish>(
					class2stsList.size());

			for (Class2sts class2sts : class2stsList) {

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("class1stsVal", class1sts.getName());
				params.put("class2stsVal", class2sts.getName());
				params.put("fromDate", fromDate);
				params.put("toDate", toDate);
				params.put("projectId", projectId);
				params.put("statusId", finish.getId());

				IncidentDataForCountFinish data = dashboardIncidentMapper
						.getFinishIncidentData(params);

				IncidentDataInnerFinish incidentDataInnerFinish = new IncidentDataInnerFinish(
						class2sts.getName(), data);
				incidentDataInnerFinishList.add(incidentDataInnerFinish);
			}

			IncidentDataFinish incidentData = new IncidentDataFinish(
					class1sts.getName(), incidentDataInnerFinishList);
			incidentDataFinishList.add(incidentData);
		}

		return incidentDataFinishList;
	}

	class Message {

		private String label;

		public Message() {
		}

		public Message(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}
}
