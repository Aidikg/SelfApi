package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {
    private static XSSFWorkbook excelBook;
    private static XSSFSheet excelSheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    private static String excelFilePath;

    /**
     * This method accepts path of Excell and Sheet name, and opens Excell file.
     * @param path
     * @param sheetName
     */
    public static void openExcelFile(String path,String sheetName) {
        excelFilePath=path;
        try {
            File file=new File(path);
            FileInputStream input=new FileInputStream(file);
            excelBook=new XSSFWorkbook(input);
            excelSheet=excelBook.getSheet(sheetName);
        }catch(Exception e) {
            System.out.println("Excel file not found.");
        }
    }

    /**This method accepts row and cell number and return value of the cell.
     *
     * @param row
     * @param cell
     * @return Value of Cell
     */
    public static String getCellValue(int row,int cell) {
        String value=excelSheet.getRow(row).getCell(cell).toString();
        return value;
    }
    /**
     * This method changes the value of the cell. Should accept new value, row number
     * and cell number as parameters.
     * @param value
     * @param row
     * @param cell
     */
    public static void setCellValue(String value, int row,int cell) {
        excelSheet.getRow(row).getCell(cell).setCellValue(value);
    }

    /**Method will return physical number of rows in Excel Sheet.
     *  @return number of rows
     */
    public static int getNumberOfRows() {
        return excelSheet.getPhysicalNumberOfRows();
    }

}
