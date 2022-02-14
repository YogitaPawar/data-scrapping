package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileOutputStream fo;
	public FileInputStream fi;
	String path;
	
	
	public ExcelUtils(String path)
	{
		this.path = path;
		
	}
	
	public ExcelUtils(String excelPath, String sheetName)
	{
		try
		{			
		 workbook = new XSSFWorkbook(excelPath); 
    	 sheet = workbook.getSheet(sheetName);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
 	public static Object getCellData(int rowNum,int colNum)
    {
	 	DataFormatter df = new DataFormatter();
	 	Object value = df.formatCellValue(sheet.getRow(rowNum).getCell(colNum)); 
	 	return value;
    } 	

	public int getRowCount()	 
	{
		int rowCount=sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		return rowCount;
	}
	  public void WriteHeader() throws IOException
	  {			
		 setCellData("Sheet1", 0, 0, "Title");
		 setCellData("Sheet1", 0, 1, "Category");
		 setCellData("Sheet1", 0, 2, "Ingredients");
		 setCellData("Sheet1", 0, 3, "Method/Recipe Steps");
		 setCellData("Sheet1", 0, 4, "Nutrient Values ");
		 setCellData("Sheet1", 0, 5, "Recipe image link");
		 setCellData("Sheet1", 0, 6, "Link to the recipe");		  
	  }
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File excelFile = new File(path);
		if(!excelFile.exists())
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
				
		if(workbook.getSheetIndex(sheetName)==-1)
			workbook.createSheet(sheetName);		
		sheet= workbook.getSheet(sheetName);
		
		if(sheet.getRow(rownum) == null)
				sheet.createRow(rownum);		
		row = sheet.getRow(rownum);
		
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		CellStyle cs= workbook.createCellStyle();
		cs.setWrapText(true);
		
		//row.setHeight((short) 2300);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}

	

}
