package Utils;

import java.util.Map;

import io.cucumber.testng.TestNGCucumberRunner;

public class Savedata {

	private static final ThreadLocal<Map<String, String>> testData = new ThreadLocal<>();
	private static final ThreadLocal<TestNGCucumberRunner> runnerData = new ThreadLocal<>();
	private static ThreadLocal<String> sheetData = new ThreadLocal<String>();
	private static ThreadLocal<String> executionType = new ThreadLocal<String>();

	// private static Map<String, String> testData =new LinkedHashMap<>();

	public static void setData(Map<String, String> data) {

		// Map<String, String> testData = data;
		testData.set(data);
		// testData.("logindata", data);
	}

	public static String get(String key) {
		return testData.get().get(key);
	}

	public static Map<String, String> getData() {
		return testData.get();
	}

	public static void clear() {
		testData.remove();

	}

	public static void setbrowserData(Map<String, String> data) {

		testData.set(data);
	}

	public static TestNGCucumberRunner getRunner() {
		return runnerData.get();

	}

	public static void setRunner(TestNGCucumberRunner runner) {
		runnerData.set(runner);
		// System.out.println("from set1 runner is "+runner);
	}

	public static void setSheet(String sheet) {
		// TODO Auto-generated method stub
		sheetData.set(sheet);

	}

	public static String getSheet() {
		// TODO Auto-generated method stub
		return sheetData.get();

	}

	public static void setexecutionType(String dd) {
		// TODO Auto-generated method stub
		executionType.set(dd);

	}

	public static String getexecutionType() {
		// TODO Auto-generated method stub
		return executionType.get();

	}
}