package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils;

@FunctionalInterface
public interface TaskWithException {
	void run() throws Exception;
}
