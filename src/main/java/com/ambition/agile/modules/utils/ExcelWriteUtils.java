package com.ambition.agile.modules.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelWriteUtils {
	HttpServletResponse response;
	// 文件名
	private String fileName ;
	//文件保存路径
	
	private String fileDir;
	//sheet名
	private String sheetName;
	//表头字体
	private String titleFontType = "宋体";
	//表头背景色
	private String titleBackColor = "C1FBEE";
	//表头字号
	private short titleFontSize = 12;
	//添加自动筛选的列 如 A:M
	private String address = "";
	//正文字体
	private String contentFontType = "宋体";
	//正文字号
	private short contentFontSize = 15;
	//Float类型数据小数位
	private String floatDecimal = ".00";
	//Double类型数据小数位
	private String doubleDecimal = ".00";
	//设置列的公式
	private String colFormula[] = null;
	
	DecimalFormat floatDecimalFormat=new DecimalFormat(floatDecimal);
	DecimalFormat doubleDecimalFormat=new DecimalFormat(doubleDecimal);
	
	private HSSFWorkbook workbook = null;
	
	
	//xssf
	public ExcelWriteUtils(String fileDir){
	     this.fileDir = fileDir;
	     //this.sheetName = sheetName;
	     workbook = new HSSFWorkbook();
	    
	}
	public ExcelWriteUtils(){
		workbook = new HSSFWorkbook();
	}
	
	/*
	public ExcelWriteUtils(HttpServletResponse response,String fileName,String sheetName){
		 this.response = response;
		 this.sheetName = sheetName;
	     workbook = new HSSFWorkbook();
	}
	*/
	
    /**
     * 设置表头字体.
     * @param titleFontType
     */
	public void setTitleFontType(String titleFontType) {
		this.titleFontType = titleFontType;
	}
    /**
     * 设置表头背景色.
     * @param titleBackColor 十六进制
     */
	public void setTitleBackColor(String titleBackColor) {
		this.titleBackColor = titleBackColor;
	}
    /**
     * 设置表头字体大小.
     * @param titleFontSize
     */
	public void setTitleFontSize(short titleFontSize) {
		this.titleFontSize = titleFontSize;
	}
    /**
     * 设置表头自动筛选栏位,如A:AC.
     * @param address
     */
	public void setAddress(String address) {
		this.address = address;
	}
    /**
     * 设置正文字体.
     * @param contentFontType
     */
	public void setContentFontType(String contentFontType) {
		this.contentFontType = contentFontType;
	}
    /**
     * 设置正文字号.
     * @param contentFontSize
     */
	public void setContentFontSize(short contentFontSize) {
		this.contentFontSize = contentFontSize;
	}
	/**
	 * 设置float类型数据小数位 默认.00
	 * @param doubleDecimal 如 ".00"
	 */
    public void setDoubleDecimal(String doubleDecimal) {
		this.doubleDecimal = doubleDecimal;
	}
	/**
     * 设置doubel类型数据小数位 默认.00
     * @param floatDecimalFormat 如 ".00
     */
	public void setFloatDecimalFormat(DecimalFormat floatDecimalFormat) {
		this.floatDecimalFormat = floatDecimalFormat;
	}
	/**
	 * 设置列的公式 
	 * @param colFormula  存储i-1列的公式 涉及到的行号使用@替换 如A@+B@
	 */
	public void setColFormula(String[] colFormula) {
		this.colFormula = colFormula;
	}
	/**
     * 写excel.
     * @param titleColumn  对应bean的属性名
     * @param titleName   excel要导出的表名
     * @param titleSize   列宽
     * @param dataList  数据
     */
	public void wirteExcel(String sheetName, String titleColumn[], String titleName[], int titleSize[], List<?> dataList){
		this.sheetName = sheetName;
    	//添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
    	Sheet sheet = workbook.createSheet(this.sheetName);  
    	//新建文件
    	OutputStream out = null;
    	try {	 
    		if(fileDir!=null){
    			//有文件路径
    			out = new FileOutputStream(fileDir);
    		}else{
    			//否则，直接写到输出流中
    			out = response.getOutputStream();
    			fileName = fileName+".xls";
    			response.setContentType("application/x-msdownload");
    			response.setHeader("Content-Disposition", "attachment; filename="
    					+ URLEncoder.encode(fileName, "UTF-8"));
    		}
    		
    		//写入excel的表头
    		Row titleNameRow = workbook.getSheet(sheetName).createRow(0); 
    		//设置样式
    		HSSFCellStyle titleStyle = workbook.createCellStyle();  
    		titleStyle = (HSSFCellStyle) setFontAndBorder(titleStyle, titleFontType, (short) titleFontSize);
	    	titleStyle = (HSSFCellStyle) setColor(titleStyle, titleBackColor, (short)10);
    		
    		for(int i = 0;i < titleName.length;i++){
	    		sheet.setColumnWidth(i, titleSize[i]*256);    //设置宽度   		
	    		Cell cell = titleNameRow.createCell(i);
	    		cell.setCellStyle(titleStyle);
	    		cell.setCellValue(titleName[i].toString());
	    	}
	    	
	    	//为表头添加自动筛选
	    	if(!"".equals(address)){
				CellRangeAddress c = (CellRangeAddress) CellRangeAddress.valueOf(address);
		    	sheet.setAutoFilter(c);
			}
	    	
	    	//通过反射获取数据并写入到excel中
	    	if(dataList!=null&&dataList.size()>0){
	    		//设置样式
	    		HSSFCellStyle dataStyle = workbook.createCellStyle();  
	    		titleStyle = (HSSFCellStyle) setFontAndBorder(titleStyle, contentFontType, (short) contentFontSize);
	    		
	    		if(titleColumn.length>0){
	    	    	for(int rowIndex = 1;rowIndex<=dataList.size();rowIndex++){
	    	    		Object obj = dataList.get(rowIndex-1);     //获得该对象
	    	    		Class clsss = obj.getClass();     //获得该对对象的class实例
	    	    		Row dataRow = workbook.getSheet(sheetName).createRow(rowIndex);    
	    	    		for(int columnIndex = 0;columnIndex<titleColumn.length;columnIndex++){
	    	    			String title = titleColumn[columnIndex].toString().trim();
	    	    			if(!"".equals(title)){  //字段不为空
	    	    				//使首字母大写
								String UTitle = Character.toUpperCase(title.charAt(0))+ title.substring(1, title.length()); // 使其首字母大写;
								String methodName  = "get"+UTitle;
								
								// 设置要执行的方法
								Method method = clsss.getDeclaredMethod(methodName);
								
								//获取返回类型
								String returnType = method.getReturnType().getName();
								
								String data = method.invoke(obj)==null?"":method.invoke(obj).toString();
								Cell cell = dataRow.createCell(columnIndex);
								if(data!=null&&!"".equals(data)){
									if("int".equals(returnType)){
										cell.setCellValue(Integer.parseInt(data));
									}else if("long".equals(returnType)){
										cell.setCellValue(Long.parseLong(data));
									}else if("float".equals(returnType)){
										cell.setCellValue(floatDecimalFormat.format(Float.parseFloat(data)));
									}else if("double".equals(returnType)){
										cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
									}else{
										cell.setCellValue(data);
									}
								}
	    	    			}else{   //字段为空 检查该列是否是公式
	    	    				if(colFormula!=null){
	    	    					String sixBuf = colFormula[columnIndex].replace("@", (rowIndex+1)+"");
	    	    					Cell cell = dataRow.createCell(columnIndex);
	    	    					cell.setCellFormula(sixBuf.toString());
	    	    				}
	    	    			}
		    	    	}
	    	    	}
	    	    	
	    	    }
	    	}
	    	
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  
		    try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  
	}
	
    /**
     * 将16进制的颜色代码写入样式中来设置颜色
     * @param style  保证style统一
     * @param color 颜色：66FFDD
     * @param index 索引 8-64 使用时不可重复
     * @return
     */
    public CellStyle setColor(CellStyle style, String color, short index){
    	if(color!=""&&color!=null){
			//转为RGB码
    		int r = Integer.parseInt((color.substring(0,2)),16);   //转为16进制
    		int g = Integer.parseInt((color.substring(2,4)),16);
    		int b = Integer.parseInt((color.substring(4,6)),16);
    		//自定义cell颜色
    		HSSFPalette palette = workbook.getCustomPalette(); 
    		palette.setColorAtIndex((short)index, (byte) r, (byte) g, (byte) b);
    	
    		style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
    		style.setFillForegroundColor(index);
		}
        return style;	
    }
   
    /**
     * 设置字体并加外边框
     * @param style  样式
     * @param style  字体名
     * @param style  大小
     * @return
     */
    public CellStyle setFontAndBorder(CellStyle style, String fontName, short size){
    	HSSFFont font = workbook.createFont();  
        font.setFontHeightInPoints(size);    
        font.setFontName(fontName); 
       // font.setBold(true);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
        style.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
        style.setBorderTop(CellStyle.BORDER_THIN);//上边框    
        style.setBorderRight(CellStyle.BORDER_THIN);//右边框   
        return style;
    }
	/**
	 * 删除文件
	 * @param fileDir
	 * @return
	 */
    public boolean deleteExcel(){
    	boolean flag = false;
    	File file = new File(this.fileDir);
    	// 判断目录或文件是否存在  
        if (!file.exists()) {  // 不存在返回 false  
            return flag;  
        } else {  
            // 判断是否为文件  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                file.delete();
                flag = true;
            } 
        }
        return flag;
    }
    /**
	 * 删除文件
	 * @param fileDir
	 * @return
	 */
    public boolean deleteExcel(String path){
    	boolean flag = false;
    	File file = new File(path);
    	// 判断目录或文件是否存在  
        if (!file.exists()) {  // 不存在返回 false  
            return flag;  
        } else {  
            // 判断是否为文件  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                file.delete();
                flag = true;
            } 
        }
        return flag;
    }

    public List<List<Map<String, String>>> readExcelWithTitle(String sheetName) throws Exception {
    	String filepath = this.fileDir;
        String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
        InputStream is = null;
        Workbook wb = null;
        try {
            is = new FileInputStream(filepath);
             
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                throw new Exception("读取的不是excel文件");
            }
             
            List<List<Map<String, String>>> result = new ArrayList<List<Map<String,String>>>();//对应excel文件
             
            int sheetSize = wb.getNumberOfSheets();
            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
                Sheet sheet = wb.getSheetAt(i);
                
                //指定sheetname
                if(sheetName!=null&&!sheet.getSheetName().equals(sheetName)){
                	continue;
                }
                
                List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//对应sheet页
                 
                List<String> titles = new ArrayList<String>();//放置所有的标题
                 
                int rowSize = sheet.getLastRowNum() + 1;
                for (int j = 0; j < rowSize; j++) {//遍历行
                    Row row = sheet.getRow(j);
                    if (row == null) {//略过空行
                        continue;
                    }
                    int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
                    if (j == 0) {//第一行是标题行
                        for (int k = 0; k < cellSize; k++) {
                            Cell cell = row.getCell(k);
                            titles.add(cell.toString());
                        }
                    } else {//其他行是数据行
                        Map<String, String> rowMap = new HashMap<String, String>();//对应一个数据行
                        for (int k = 0; k < titles.size(); k++) {
                            Cell cell = row.getCell(k);
                            String key = titles.get(k);
                            String value = null;
                            if (cell != null) {
                            	if (0 == cell.getCellType()) {
                            	if(HSSFDateUtil.isCellDateFormatted(cell)){
                            		//用于转化为日期格式
                            		Date d = cell.getDateCellValue();
                            		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            		value = formater.format(d);
                            		}
                            	}
                            	else
                            	{
                            		value = cell.toString();
                            	}
                            }
                            rowMap.put(key, value);
                        }
                        sheetList.add(rowMap);
                    }
                }
                result.add(sheetList);
            }
             
            return result;
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (wb != null) {
               // wb.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }
    
    /**
    * 读取excel文件
    * @param startReadLine 开始读取的行:从0开始
    * @param tailLine 去除最后读取的行
     * @throws Exception
    */
	public List<List<Map<String, String>>> readExcelWithoutTitle(String sheetName, int startReadLine,
                                                                 int tailLine) throws Exception {
		String filepath = this.fileDir;
		String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
		InputStream is = null;
		Workbook wb = null;
		try {

			is = new FileInputStream(filepath);

			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (fileType.equals("xlsx")) {
				 wb = new XSSFWorkbook(is);

			} else {
				throw new Exception("读取的不是excel文件");
			}

			List<List<Map<String, String>>> result = new ArrayList<List<Map<String, String>>>();// 对应excel文件

                			int sheetSize = wb.getNumberOfSheets();
			for (int i = 0; i < sheetSize; i++) {// 遍历sheet页
				Sheet sheet = wb.getSheetAt(i);
				// 指定sheetname
				if (sheetName != null && !sheet.getSheetName().equals(sheetName)) {
					continue;
				}
				Row row = null;
				
				List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//对应sheet页

				for (int j = startReadLine; j < sheet.getLastRowNum() - tailLine + 1; j++) {
					row = sheet.getRow(j);
					Map<String, String> rowMap = new HashMap<String, String>();//对应一个数据行
					
					for (Cell c : row) {
						boolean isMerge = isMergedRegion(sheet, j, c.getColumnIndex());
						// 判断是否具有合并单元格
	                      String key = String.valueOf(CellReference.convertNumToColString(c.getColumnIndex()));
                          String value = null;
						if (isMerge) {
							value = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
						} else {
							value = this.getCellValue(c);//c.getRichStringCellValue() + "";
							
						}
						rowMap.put(key, value);
					}
					sheetList.add(rowMap);
				}
				result.add(sheetList);
			}
			return result;

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}finally {
			if (wb != null) {
				//wb.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * 读取excel文件
	 * @param is 文件流
	 * @param fileName 文件名称
	 * @param startReadLine 开始读取的行:从0开始
	 * @param tailLine 去除最后读取的行
	 * @throws Exception
	 */
	public List<List<Map<String, String>>> readExcelWithoutTitle(InputStream is, String fileName, int startReadLine,
                                                                 int tailLine) throws Exception {
		if(is == null){
			throw new Exception("获取excel文件错误!!!");
		}
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

		Workbook wb = null;
		try {

			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(is);

			} else {
				throw new Exception("读取的不是excel文件");
			}

			List<List<Map<String, String>>> result = new ArrayList<List<Map<String, String>>>();// 对应excel文件

			int sheetSize = wb.getNumberOfSheets();
			for (int i = 0; i < sheetSize; i++) {// 遍历sheet页
				Sheet sheet = wb.getSheetAt(i);

				Row row = null;

				List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//对应sheet页

				for (int j = startReadLine; j < sheet.getLastRowNum() - tailLine + 1; j++) {
					row = sheet.getRow(j);
					Map<String, String> rowMap = new HashMap<String, String>();//对应一个数据行
					if(row!=null) {
						for (Cell c : row) {
							boolean isMerge = isMergedRegion(sheet, j, c.getColumnIndex());
							// 判断是否具有合并单元格
							String key = String.valueOf(CellReference.convertNumToColString(c.getColumnIndex()));
							String value = null;
							if (isMerge) {
								value = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
							} else {
								value = this.getCellValue(c);//c.getRichStringCellValue() + "";
							}
							rowMap.put(key, value);
						}
						sheetList.add(rowMap);
					}
				}
				result.add(sheetList);
			}
			return result;

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}finally {
			if (wb != null) {
				//wb.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}

	/*读取excel文件
    * @param wb
    * @param startReadLine 开始读取的行:从0开始
    * @param tailLine 去除最后读取的行
     * @throws Exception
    */
	public List<List<Map<String, String>>> readExcelWithoutTitle(int startReadLine, int tailLine) throws Exception {
		String filepath = this.fileDir;
		String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
		InputStream is = null;
		Workbook wb = null;
		try {
			is = new FileInputStream(filepath);

			if (fileType.equals("xls")) {
				//  is = new FileInputStream(filepath);
				wb = new HSSFWorkbook(is);
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(is);

			} else {
				throw new Exception("读取的不是excel文件");
			}

			List<List<Map<String, String>>> result = new ArrayList<List<Map<String, String>>>();// 对应excel文件

			int sheetSize = wb.getNumberOfSheets();
			for (int i = 0; i < sheetSize; i++) {// 遍历sheet页
				Sheet sheet = wb.getSheetAt(i);
				Row row = null;

				List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//对应sheet页

				for (int j = startReadLine; j < sheet.getLastRowNum() - tailLine + 1; j++) {
					row = sheet.getRow(j);
					Map<String, String> rowMap = new HashMap<String, String>();//对应一个数据行

					for (Cell c : row) {
						boolean isMerge = isMergedRegion(sheet, j, c.getColumnIndex());
						// 判断是否具有合并单元格
						String key = String.valueOf(CellReference.convertNumToColString(c.getColumnIndex()));
						String value = null;
						if (isMerge) {
							value = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
						} else {
							//key = c.getColumnIndex().toString();
							value = this.getCellValue(c);//c.getRichStringCellValue() + "";

						}
						rowMap.put(key, value);
					}
					sheetList.add(rowMap);
				}
				result.add(sheetList);
			}
			return result;

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}finally {
			if (wb != null) {
				//wb.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * 判断合并了行
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean isMergedRow(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */
	private boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断sheet页中是否含有合并单元格
	 * 
	 * @param sheet
	 * @return
	 */
	private boolean hasMerged(Sheet sheet) {
		return sheet.getNumMergedRegions() > 0 ? true : false;
	}

	/**
	 * 合并单元格
	 * 
	 * @param sheet
	 * @param firstRow
	 *            开始行
	 * @param lastRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param lastCol
	 *            结束列
	 */
	private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell) {

		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double numeric = cell.getNumericCellValue();
			DecimalFormat df = new DecimalFormat("0");    
			return df.format(numeric);
		}
		return "";
	}

}