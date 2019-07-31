package com.ambition.agile.modules.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ambition.agile.common.utils.DateUtils;
import com.ambition.agile.common.utils.excel.ExportExcel;

/**
 * poi读写Excle兼容xls、xlsx
 * @author hkzr
 *
 */
public class POICompatibilityUtil {
	
	public static void main(String[] args) throws Exception {
		//写
		excleWrite();
		//读
//		String file="d:\\test.xlsx";
//		String value = readCellValue(file, 0, 0);
//		System.out.println(value);
//		System.out.println(getCountRows(file));
		try {
            String fileName = "用户数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           
//    		new ExportExcel("用户数据").addCell(row, column, val);
//    		setDataList(page.getList()).write(response, fileName).dispose();
    		
		} catch (Exception e) {
			
		}
	}
	
	
	
	/**
	 * 读excle文件的操作
	 * @param file 文件路径
	 * @param isReadFirstRow 是否读取首行
	 * @param indexCell 所要读取的列
	 * @return 读取单元格的值
	 * @throws Exception
	 */
	public static String readCellValue(String file, int indexRow, int indexCell) throws Exception{
		//获取第一张单元表
		Sheet sheet = getSheet(file,0);
		//占存单元格的值
		String content="";
		//得到当前行
		Row row = sheet.getRow(indexRow);
		//获取当前行的总列数
		//int cells = row.getPhysicalNumberOfCells();//总列数
		//循环获取当前行的每一列数据,得到指定行指定列的单元格
		Cell cell = row.getCell(indexCell);
		//判断单元格的内容类型，调用对应的方法获取相应的单元格值
		content = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? cell.getNumericCellValue()+"": cell.getStringCellValue();
		//返回单元格值
		return content;
	}
	
	/**
	 * 获取工作表总行数
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static int getCountRows(String file) throws Exception{
		//获取总行数
		return getSheet(file,0).getPhysicalNumberOfRows();
	}
	
	/**
	 * 获取当前行的总列数
	 * @param row
	 * @param indexRow 
	 * @return
	 * @throws Exception 
	 */
	public static int getCountCells(String file, int indexRow) throws Exception{
		 Row row = getSheet(file,0).getRow(indexRow);
		//获取当前行的总列数
		return row.getPhysicalNumberOfCells();
	}
	
	/**
	 * sheet工作表获取工作表
	 * @param file
	 * @param indexSheet
	 * @return
	 * @throws Exception
	 */
	private static Sheet getSheet(String file, int indexSheet) throws Exception{
		//POIFSFileSystem fileSys = new POIFSFileSystem(new FileInputStream("students.xls"));
		//获取excle文件路径
		FileInputStream fileInputStream=new FileInputStream(file);
		//得到工作簿
		Workbook workBook  = file.endsWith("xls")?new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
		//获取第一张sheet工作表
		Sheet sheet = workBook.getSheetAt(indexSheet);
		return sheet;
	}
	
	//写excle文件的操作
	public static void excleWrite() throws Exception{
		//创建工作簿
		HSSFWorkbook workBook = new HSSFWorkbook();
		//创建名为sheetScores的工作表
		HSSFSheet sheetScores = workBook.createSheet("sheetScores");
		//设置excel每列宽度
		sheetScores.setColumnWidth(0, 4000);
		sheetScores.setColumnWidth(1, 3500);
		//创建字体样式
		HSSFFont font = workBook.createFont();
		font.setFontName("Verdana");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 300);
		font.setColor(HSSFColor.BLUE.index);
		//创建单元格样式
		HSSFCellStyle style = workBook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//设置边框
		style.setBottomBorderColor(HSSFColor.RED.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//设置字体
		style.setFont(font);
		//创建Excel的sheet的第一行
		HSSFRow row = sheetScores.createRow(0);
		row.setHeight((short) 500);// 设定行的高度
		//创建一个Excel的单元格
		HSSFCell cell = row.createCell(0);
		//合并单元格(startRow，endRow，startColumn，endColumn)
		sheetScores.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		// 给Excel的单元格设置样式和赋值
		cell.setCellStyle(style);
		cell.setCellValue("学生成绩表");
		
		//设置单元格内容格式
		HSSFCellStyle style1 = workBook.createCellStyle();
		style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm:ss"));
		//自动换行
		style1.setWrapText(true);

		row = sheetScores.createRow(1);

		//设置单元格的样式格式
		cell = row.createCell(0);
		cell.setCellStyle(style1);
		cell.setCellValue(new Date());

		//创建超链接
		HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		link.setAddress("http://www.baidu.com");
		cell = row.createCell(1);
		cell.setCellValue("数学");
		//设定单元格的链接
		cell.setHyperlink(link);

		FileOutputStream os = new FileOutputStream("D:\\workbook.xls");
		workBook.write(os);
		os.close();
	}
	
}

