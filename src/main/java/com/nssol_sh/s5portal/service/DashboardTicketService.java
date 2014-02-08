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
import com.nssol_sh.s5portal.domain.TicketDataFinish;
import com.nssol_sh.s5portal.domain.TicketDataInnerFinish;
import com.nssol_sh.s5portal.domain.TicketBarChartData;
import com.nssol_sh.s5portal.domain.TicketData;
import com.nssol_sh.s5portal.domain.TicketDataForCount;
import com.nssol_sh.s5portal.domain.TicketDataForCountFinish;
import com.nssol_sh.s5portal.domain.TicketDataInner;
import com.nssol_sh.s5portal.domain.Status;
import com.nssol_sh.s5portal.persistence.CommonMapper;
import com.nssol_sh.s5portal.persistence.DashboardTicketMapper;

@Service
public class DashboardTicketService {

	@Autowired
	private CommonMapper commonMapper;

	@Autowired
	private DashboardTicketMapper dashboardTicketMapper;

	private static final SimpleDateFormat fmt = new SimpleDateFormat(
			"yyyy-MM-dd");
	

	public List<TicketData> getTicketDatas(String projectId, String fromDate,
			String toDate) {
		
		Status finish = commonMapper.getStatusByName("完成");
		
		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier(projectId);

		List<TicketData> ticketDataList = new ArrayList<TicketData>(
				class1stsList.size());

		for (Class1sts class1sts : class1stsList) {

			List<Class2sts> class2stsList = commonMapper
					.getClass2stsByParentId(class1sts.getId());
			List<TicketDataInner> ticketDataInnerList = new ArrayList<TicketDataInner>(
					class2stsList.size());

			for (Class2sts class2sts : class2stsList) {

				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("projectId", projectId);
				params.put("fromDate", fromDate);
				params.put("toDate", toDate);
				params.put("class1stsVal", class1sts.getName());
				params.put("class2stsVal", class2sts.getName());

				List<TicketDataForCount> data01 = dashboardTicketMapper
						.getTicketData(params);
				
				params.put("statusId", finish.getId());

				TicketDataForCount data02 = dashboardTicketMapper
						.getTicketDataFinish(params);
				if (data02 == null) {
					data02 = new TicketDataForCount("完成", 0);
				}

				TicketDataInner ticketDataInner = new TicketDataInner(
						class2sts.getName(), data01, data02);
				ticketDataInnerList.add(ticketDataInner);
			}

			TicketData ticketData = new TicketData(class1sts.getName(),
					ticketDataInnerList);
			ticketDataList.add(ticketData);
		}

		return ticketDataList;
	}

	public Object[] getTicketDataPieChart(String projectId, String fromDate,
			String toDate) {		
		
		Status finish = commonMapper.getStatusByName("完成");
		
		List<Object[]> tmpList = new ArrayList<Object[]>();

		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier(projectId);

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

				List<TicketDataForCount> data01 = dashboardTicketMapper
						.getTicketDataPieChart(params);
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

	public TicketBarChartData getTicketDataBarChart(String projectId,
			String fromDate, String toDate) throws ParseException {		
		
		Status finish = commonMapper.getStatusByName("完成");
		
		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier(projectId);

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

				TicketDataForCount ticketDataForCount = dashboardTicketMapper
						.getTicketDataBarChart(params);

				if (ticketDataForCount == null) {
					dataList[class1stsCount][i] = 0;
				} else {
					dataList[class1stsCount][i] = ticketDataForCount
							.getCounts();
				}
			}

			class1stsCount++;
		}

		ticks[2] = "现在";
		String title = "Project "
				+ commonMapper.getProjectByIdentifier(projectId).getName()
				+ ": Tickets";
		return new TicketBarChartData(messages, dataList, ticks, title);
	}

	public List<TicketDataFinish> getFinishTicketDatas(String projectId,
			String fromDate, String toDate) {		
		
		Status finish = commonMapper.getStatusByName("完成");
		
		List<Class1sts> class1stsList = commonMapper
				.getClass1stsByProjectIdentifier(projectId);

		List<TicketDataFinish> ticketDataFinishList = new ArrayList<TicketDataFinish>(
				class1stsList.size());

		for (Class1sts class1sts : class1stsList) {

			List<Class2sts> class2stsList = commonMapper
					.getClass2stsByParentId(class1sts.getId());
			List<TicketDataInnerFinish> ticketDataInnerFinishList = new ArrayList<TicketDataInnerFinish>(
					class2stsList.size());

			for (Class2sts class2sts : class2stsList) {

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("class1stsVal", class1sts.getName());
				params.put("class2stsVal", class2sts.getName());
				params.put("fromDate", fromDate);
				params.put("toDate", toDate);
				params.put("projectId", projectId);
				params.put("statusId", finish.getId());

				TicketDataForCountFinish data = dashboardTicketMapper
						.getFinishTicketData(params);

				TicketDataInnerFinish ticketDataInnerFinish = new TicketDataInnerFinish(
						class2sts.getName(), data);
				ticketDataInnerFinishList.add(ticketDataInnerFinish);
			}

			TicketDataFinish incidentData = new TicketDataFinish(
					class1sts.getName(), ticketDataInnerFinishList);
			ticketDataFinishList.add(incidentData);
		}

		return ticketDataFinishList;
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
