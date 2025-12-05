package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static String filepath = "C:/Users/Shiva/MAvenProjects/NumpyNinja/SDET229-DSAlgo/src/test/resources/TestData/DSAlgo.xlsx";

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;

	public synchronized Object[][] getData(String testsheet) throws Exception {
		FileInputStream filelocation = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(filelocation);
		// sheet = workbook.getSheet("Queue");
		sheet = workbook.getSheet(testsheet);

		System.out.println("I am inside Excel class");
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();

		List<Map<String, String>> data = new ArrayList<>();

		for (int i = 1; i < rows; i++) {
			Map<String, String> testData = new HashMap<>();
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < cols; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = (row.getCell(j) == null)
						? ""
						: row.getCell(j).toString();
				testData.put(key, value);
			}
			data.add(testData);
		}

		workbook.close();

		// Convert List<Map> → Object[][]
		Object[][] finalTestData = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			finalTestData[i][0] = data.get(i);
			System.out.println("final test data is " + finalTestData[i][0]);
		}

		return finalTestData;
	}

	public static List<Map<String, String>> getTestData(String sheetName)
			throws IOException {
		List<Map<String, String>> testData = new ArrayList<>();

		workbook = new XSSFWorkbook(filepath);
		sheet = workbook.getSheet(sheetName);

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();

		// List<Map<String, String>> data = new ArrayList<>();

		for (int i = 1; i < rows; i++) {
			Map<String, String> map = new HashMap<>();
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < cols; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = (row.getCell(j) == null)
						? ""
						: row.getCell(j).toString();
				map.put(key, value);
			}
			testData.add(map);
		}
		// lock.unlock();
		System.out.println(testData.getLast());
		workbook.close();
		return testData;

	}

	public static Map<String, String> getTestRow(String sheetName,
			String scenarioName) {

		Map<String, String> getRow = null;
		try {
			List<Map<String, String>> code = ExcelUtil.getTestData(sheetName);
			System.out.println("code is " + code.get(0));

			getRow = code.stream().filter(
					row -> scenarioName.contains(row.get("ScenarioName")))
					.findFirst().orElse(null);
		} catch (Exception e) {

		}
		return getRow;
	}

}
