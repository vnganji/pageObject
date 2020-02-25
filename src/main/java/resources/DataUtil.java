package resources;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class DataUtil {

	public static boolean getRunnerMode(String mname) {
		ExcelReader excel = new ExcelReader("C:\\Users\\ADMIN\\pageObject\\src\\main\\java\\resources\\testdata.xlsx");
		String testdatasheetname = "TestRunner";
		String testcasename = mname;
		System.out.println(testcasename);
		String RunMode = null;
		// total rows
		int rows = excel.getRowCount(testdatasheetname);
		for (int r = 2; r <= rows; r++) {
			if (excel.getCellData(testdatasheetname, 0, r).equals(testcasename)) {
				if (excel.getCellData(testdatasheetname, 1, r).equalsIgnoreCase("Y")){
					return true;
				}
				break;
			}

		}
		return false;
	}

	@DataProvider(name = "datagen")
	public Object[][] getData(Method m) {
		ExcelReader excel = new ExcelReader("C:\\Users\\ADMIN\\pageObject\\src\\main\\java\\resources\\testdata.xlsx");
		String testcasename = m.getName();
		String testdatasheetname = "TestData";
		// total rows
		int rows = excel.getRowCount(testdatasheetname);
		int rownum;
		// Row where test case exists
		for (rownum = 2; rownum <= rows; rownum++) {
			if (excel.getCellData(testdatasheetname, 0, rownum).equals(testcasename))
				break;
		}
		int testcaserownum = rownum;
		// find no of rows of test data for the test case.
		int tempval = testcaserownum;
		int noofrows = 0;

		while (!(excel.getCellData(testdatasheetname, 0, tempval).trim()).equalsIgnoreCase("TestCaseName")) {
			tempval++;
			noofrows++;
		}
		int endrownum = tempval - 1;

		// find data end column
		int colnum = 1;
		boolean a = true;
		while (a) {
			if (excel.getCellData(testdatasheetname, colnum, testcaserownum - 1).equals(""))
				break;
			else
				colnum++;
		}
		int endcolnum = colnum - 1;
		int iii = 0;
		Object[][] data = new Object[noofrows][1];
		for (int r = testcaserownum; r <= endrownum; r++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

			for (int c = 1; c <= endcolnum; c++) {
				table.put(excel.getCellData(testdatasheetname, c, testcaserownum - 1),
						excel.getCellData(testdatasheetname, c, r));
			}
			if (!(table.isEmpty())) {
				data[iii][0] = table;
				iii++;
			}

		}

		return data;

	}
}
