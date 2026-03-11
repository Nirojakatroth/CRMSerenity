package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ContactExcelReader {

    public static List<String[]> getExcelData(String sheetName) throws Exception {

        FileInputStream file = new FileInputStream(
                "src/test/resources/testdata/Contacts.xlsx");

        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        List<String[]> data = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            String[] rowData = new String[row.getLastCellNum()];

            for (int j = 0; j < row.getLastCellNum(); j++) {
                rowData[j] = row.getCell(j).toString();
            }

            data.add(rowData);
        }

        workbook.close();
        file.close();

        return data;
    }
}