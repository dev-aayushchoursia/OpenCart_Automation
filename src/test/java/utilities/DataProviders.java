package utilities;
import org.testng.annotations.DataProvider;
import java.io.IOException;

public class DataProviders {

    //DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        //taking xl file from testData
        String path = ".\\testData\\Opencart_LoginData.xlsx";

        //creating an object for XLUtility
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        //created for two dimension array which can store the data user and password
        String[][] logindata = new String[totalrows][totalcols];

        //1   //read the data from xl storing in two-dimensional array
        for (int i = 1; i <= totalrows; i++) {
            //0    i is rows j is col
            for (int j = 0; j < totalcols; j++) {
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);  //1,0
            }
        }

        //returning two dimension array
        return logindata;

    }

    //DataProvider 2

    //DataProvider 3

    //DataProvider 4
}
