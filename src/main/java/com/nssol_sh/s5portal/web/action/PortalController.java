package com.nssol_sh.s5portal.web.action;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nssol_sh.s5portal.domain.DashboardGrid01;
import com.nssol_sh.s5portal.domain.RedmineInfo;
import com.nssol_sh.s5portal.service.DashboardChartService;
import com.nssol_sh.s5portal.service.DashboardIncidentService;
import com.nssol_sh.s5portal.service.DashboardTicketService;
import com.nssol_sh.s5portal.service.RedmineInfoService;
import com.nssol_sh.spring.foundation.DataSourceContextHolder;

@Controller
public class PortalController {

	@Autowired
	private RedmineInfoService redmineInfoService;

	@Autowired
	private DashboardChartService dashboardChartService;

	@Autowired
	private DashboardIncidentService dashboardIncidentService;

	@Autowired
	private DashboardTicketService dashboardTicketService;

	@RequestMapping(value = "/portal")
	public String portal(HttpServletRequest request, Map<String, Object> model) {
		DataSourceContextHolder.setDefaultDataSource();
		List<RedmineInfo> list = redmineInfoService
				.getRedmineInfoByUserId(request.getUserPrincipal().getName());

		model.put("projects", list);

		return "portal";
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/ticket/grid", method = RequestMethod.GET)
	public @ResponseBody
	Object getTicketDatas(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardTicketService.getTicketDatas(projectId, fromDate,
				toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/ticket/gridFinish", method = RequestMethod.GET)
	public @ResponseBody
	Object getFinishTicketDatas(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardTicketService.getFinishTicketDatas(projectId, fromDate,
				toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/ticket/piechart", method = RequestMethod.GET)
	public @ResponseBody
	Object getTicketDataPieChart(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardTicketService.getTicketDataPieChart(projectId,
				fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/ticket/barchart", method = RequestMethod.GET)
	public @ResponseBody
	Object getTicketDataBarChart(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model)
			throws ParseException {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardTicketService.getTicketDataBarChart(projectId,
				fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/incident/grid", method = RequestMethod.GET)
	public @ResponseBody
	Object getIncidentDatas(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardIncidentService.getIncidentDatas(projectId, fromDate,
				toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/incident/gridFinish", method = RequestMethod.GET)
	public @ResponseBody
	Object getFinishIncidentDatas(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardIncidentService.getFinsishIncidentDatas(projectId,
				fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/incident/piechart", method = RequestMethod.GET)
	public @ResponseBody
	Object getIncidentDataPieChart(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardIncidentService.getIncidentDataPieChart(projectId,
				fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/incident/barchart", method = RequestMethod.GET)
	public @ResponseBody
	Object getIncidentDataBarChart(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model)
			throws ParseException {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardIncidentService.getIncidentDataBarChart(projectId,
				fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/classvalue/{value}/chart/01sub", method = RequestMethod.GET)
	public @ResponseBody
	Object getDashboardChart01Sub(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate,
			@PathVariable("value") String classValue, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardChartService.getDashboardChart01Sub(projectId,
				classValue, fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/classvalue/{value}/grid/01sub", method = RequestMethod.GET)
	public @ResponseBody
	List<DashboardGrid01> getDashboardGrid01Sub(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate,
			@PathVariable("value") String classValue, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardChartService.getDashboardGrid01Sub(projectId,
				classValue, fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/classvalue/{value}/{value2}/grid/01sub", method = RequestMethod.GET)
	public @ResponseBody
	List<DashboardGrid01> getDashboardGrid01Last(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate,
			@PathVariable("value") String classValue,
			@PathVariable("value2") String classValue2,
			Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardChartService.getDashboardGrid01Last(projectId,
				classValue, classValue2, fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/chart/01main", method = RequestMethod.GET)
	public @ResponseBody
	Object getDashboardChart01Main(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardChartService.getDashboardChart01Main(projectId,
				fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}/time/{fromDate}/{toDate}/grid/01main", method = RequestMethod.GET)
	public @ResponseBody
	List<DashboardGrid01> getDashboardGrid01Main(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		return dashboardChartService.getDashboardGrid01Main(projectId,
				fromDate, toDate);
	}

	@RequestMapping(value = "/dashboard/ds/{ds}/project/{projectId}")
	public String dashboard(@PathVariable("ds") String ds,
			@PathVariable("projectId") String projectId,
			Map<String, Object> model) {
		DataSourceContextHolder.setDataSourceType(ds);
		model.put("projectId", projectId);
		return "dashboard";
	}

}
