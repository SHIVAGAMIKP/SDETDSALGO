package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static String filepath = "src/test/resources/test_data/DSAlgo.xlsx";
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;

	

	public static List<Map<String, String>> getTestData(String sheetName)
			throws IOException {
		List<Map<String, String>> testData = new ArrayList<>();

		workbook = new XSSFWorkbook(filepath);
		int total = workbook.getNumberOfSheets();
		for (int i = 0; i < total; i++) {
		    System.out.println("Sheet[" + i + "] = '" + workbook.getSheetName(i) + "'");
		}
		sheet = workbook.getSheet(sheetName);
		System.out.println(workbook);
		System.out.println("Sheetname"+sheetName);
		System.out.println("Sheetname"+sheet);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();
		System.out.print("rows:"+rows+"columns:"+cols);
		// List<Map<String, String>> data = new ArrayList<>();

		
		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < rows; i++) {

		    Map<String, String> map = new HashMap<>();
		    XSSFRow row = sheet.getRow(i);

		    for (int j = 0; j < cols; j++) {

		        String key = sheet.getRow(0).getCell(j).getStringCellValue();

		        String value = (row.getCell(j) == null)
		                ? ""
		                : formatter.formatCellValue(row.getCell(j));  

		        map.put(key, value);
		    }

		    testData.add(map);
		}
		
		
		System.out.println(testData.getLast());
		workbook.close();
		return testData;    

	}

	public static Map<String, String> getTestRow(String sheetName,
			String scenarioName) {

		Map<String, String> getRow = null;
		try {
			System.out.print(sheetName+scenarioName);
			
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
