package Utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dataprovider {

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static String filepath = "C:/Users/Shiva/MAvenProjects/NumpyNinja/SDET229-DSAlgo/src/test/resources/TestData/DSAlgo.xlsx";
	// "src/test/resources/TestData/DS-Algo.xlsx";

	public synchronized Object[][] getData() throws Exception {
		FileInputStream filelocation = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(filelocation);
		sheet = workbook.getSheet("Login");

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
		}

		System.out.println(finalTestData);

		return finalTestData;
	}
}
