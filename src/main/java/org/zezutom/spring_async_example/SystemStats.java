package org.zezutom.spring_async_example;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;


public class SystemStats {

	private OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
	
	public double getLoadAverage() {
		return osBean.getSystemLoadAverage();
	}
		
	public double getCpuUsage() {
		return osBean.getSystemCpuLoad();
	}
	
	public long getMemoryTotal() {
		return osBean.getTotalPhysicalMemorySize();
	}

	public long getMemoryFree() {
		return osBean.getFreePhysicalMemorySize();
	}	
			
}
