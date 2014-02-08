package com.nssol_sh.spring.foundation;

public class DataSourceContextHolder {

	private static final String DEFAULT_DS = "dataSourcePortal";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSourceType(String dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	public static String getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}

	public static void setDefaultDataSource() {
		contextHolder.set(DEFAULT_DS);
	}

}
