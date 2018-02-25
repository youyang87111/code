package com.pax.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pax.common.exception.BusinessException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by cqyd on 2017/2/9.
 */
public class ExcelUtils {
    /**
     * 导出为EXCEL
     *
     * @param list    结果集
     * @param headers 字段名
     * @return
     * @throws Exception
     */

    public static <T> SXSSFWorkbook toExcel(List<T> list, String[] headers) throws Exception {
        Locale locale=WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale");

        int len = list.size() + 1;

        SXSSFWorkbook wb = new SXSSFWorkbook();
        CellStyle headerStyle = wb.createCellStyle();                      //头部样式
        Font font = wb.createFont();                                       //字体样式
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);                  //左右居中
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);       //上下居中
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);                           //加粗
        headerStyle.setFont(font);


        SXSSFSheet sheet = null;
        int count = 1;
        for (int rownum = 0; rownum < len; rownum++) {
            int rowindex = rownum % 65535;
            if (rowindex == 0) {
                //重新创建sheet
                sheet = wb.createSheet();
            }


            SXSSFRow row = sheet.createRow(rowindex);
            if (rowindex == 0) {
                for (int colnum = 0; colnum < headers.length; colnum++) {
//                    sheet.setColumnWidth(colnum, (short) 4000);
                    String[] hd = headers[colnum].split("\\" + "|");
                    String hd_0=TranslationUtils.getInstance(locale).__(hd[0]);
                    SXSSFCell cell = row.createCell(colnum);
                    cell.setCellStyle(headerStyle);
                    cell.setCellValue(new XSSFRichTextString(hd_0));
                    sheet.setColumnWidth(colnum, hd_0.toString().length() * 512); //根据表格的值长度自适应列宽
                }
                continue;
            }
            Object o = list.get(rowindex - 1);
            for (int colnum = 0; colnum < headers.length; colnum++) {
//                sheet.setColumnWidth(colnum, (short) 4000);
                SXSSFCell cell = row.createCell(colnum);
                String[] hd = headers[colnum].split("\\" + "|");
                String fieldName = hd[1];
                if (fieldName.contains(".")) {
                    String[] fieldNames = fieldName.split("\\.");
                    Object temp = null;
                    for (String name : fieldNames) {
                        if (temp == null) {
                            Method m = o.getClass().getMethod("get" + StringUtils.capitalize(name));
                            Object val = m.invoke(o);
                            if (val != null) {
                                temp = val;
                            } else {
                                break;
                            }
                        } else {
                            Method m = temp.getClass().getMethod(
                                    "get" + StringUtils.capitalize(name));
                            Object val = m.invoke(temp);
                            if (val != null) {
                                temp = val;
                            }
                        }

                    }
                    if (temp != null) {
                        cell.setCellValue(new XSSFRichTextString(temp.toString()));
                    } else {
                        cell.setCellValue(new XSSFRichTextString(""));
                    }
                } else {
                    Method m = o.getClass().getMethod("get" + StringUtils.capitalize(fieldName));
                    Object val = m.invoke(o);
                    if (val != null) {
                        cell.setCellValue(new XSSFRichTextString(val.toString()));
                    } else {
                        cell.setCellValue(new XSSFRichTextString(""));
                    }
                }

            }
        }

        return wb;
    }
    
    
    
    /**
	 * 根据单元格的类型显示值
	 * 
	 * @param cell
	 *            单元格的实体
	 * @return 单元格的内容
	 */
	public static String getCell(Cell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue() + "";
			case Cell.CELL_TYPE_STRING:
				return cell.getRichStringCellValue().getString();
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() + "";
			case Cell.CELL_TYPE_ERROR:
				return cell.getErrorCellValue() + "";
			default:
				break;
		}
		return "";
	}
    
    /**
	 * 将excel（只读第一个sheet，第一行为头部的格式）中数据读出
	 * @throws Exception 
	 * */
	public static List<String[]> excel2List(File file) throws BusinessException, Exception {
		List<String[]> list = new ArrayList<String[]>();
		
		Workbook book = getWorkbook(file);
		Sheet firstSheet = book.getSheetAt(0);
		
		Row row0 = firstSheet.getRow(0);
		
		//第一行为空的情况
		if (row0 == null) {
			throw new BusinessException("第一行必须为标题行，不能为空");
		} else {
			// 每行第一列要是不为空的话意味着这行有数据
			if (StringUtils.isBlank(getCell(row0.getCell(0)))) {
				throw new BusinessException("第一行必须为标题行，不能为空");
			}
		}
		
		// 第一行为头，数据从第二行开始
		for (int i = 1; i < firstSheet.getPhysicalNumberOfRows(); i++) {
			
			Row row = firstSheet.getRow(i);
			
			if (row != null) {
				// 每行第一列要是不为空的话意味着这行有数据
				if (StringUtils.isBlank(getCell(row.getCell(0)))) {
					continue;
				}
				
				short cellLen = row.getLastCellNum();
				String[] ss = new String[cellLen];
				for (short j = 0; j < cellLen; j++) {
					ss[j] = getCell(row.getCell(j));
				}
				
				list.add(ss);
			} else {
				continue;
			}
			
		}
		return list;
	}
	
	public static List<String[]> excel2List(InputStream ins) throws BusinessException, Exception {
		List<String[]> list = new ArrayList<String[]>();
		
		Workbook book = getWorkbook(ins);
		Sheet firstSheet = book.getSheetAt(0);
		
		Row row0 = firstSheet.getRow(0);
		
		//第一行为空的情况
		if (row0 == null) {
			throw new BusinessException("第一行必须为标题行，不能为空");
		} else {
			// 每行第一列要是不为空的话意味着这行有数据
			if (StringUtils.isBlank(getCell(row0.getCell(0)))) {
				throw new BusinessException("第一行必须为标题行，不能为空");
			}
		}
		
		// 第一行为头，数据从第二行开始
		for (int i = 1; i < firstSheet.getPhysicalNumberOfRows(); i++) {
			
			Row row = firstSheet.getRow(i);
			
			if (row != null) {
				// 每行第一列要是不为空的话意味着这行有数据
				if (StringUtils.isBlank(getCell(row.getCell(0)))) {
					continue;
				}
				
				short cellLen = row.getLastCellNum();
				String[] ss = new String[cellLen];
				for (short j = 0; j < cellLen; j++) {
					ss[j] = getCell(row.getCell(j));
				}
				
				list.add(ss);
			} else {
				continue;
			}
			
		}
		return list;
	}
	
	/**
	 * 将excel（只读第一个sheet，第一行为头部的格式）中数据读出
	 * @param dataClass 装数据的实体class
	 * @param file excel文件对象
	 * @param fieldNames 实体中的字段名，顺序需与excel列的顺序对应
	 * @throws Exception 
	 * */
	public static <T> List<T> excel2List(Class<T> dataClass, File file, String[] fieldNames)
																							throws BusinessException,
																							Exception {
		List<T> list = new ArrayList<T>();
		if (fieldNames == null) {
			throw new Exception("字段为空");
		}
		Workbook book = getWorkbook(file);
		Sheet firstSheet = book.getSheetAt(0);
		// 第一行为头，数据从第二行开始
		for (int i = 1; i < firstSheet.getPhysicalNumberOfRows(); i++) {
			
			Row row = firstSheet.getRow(i);
			// 每行第一列要是不为空的话意味着这行有数据
			if (StringUtils.isEmpty(getCell(row.getCell(0)))) {
				break;
			}
			T t = dataClass.newInstance();
			// 获取的是需要截取的字段
			for (int j = 0; j < fieldNames.length; j++) {
				Field field = dataClass.getDeclaredField(fieldNames[j]);
				
				// 将可见性设置成真，方便设值，设值完毕后，恢复回false
				field.setAccessible(true);
				field.set(t, getCell(row.getCell(j)));
				field.setAccessible(false);
			}
			list.add(t);
		}
		return list;
	}
	
	
	private static Workbook getWorkbook(File file) {
		Workbook workbook = null;
		try {
			InputStream is = new FileInputStream(file);
			workbook = new HSSFWorkbook(is);
		} catch (Exception e) {
			try {
				workbook = new XSSFWorkbook(file);
			} catch (InvalidFormatException e1) {
				e1.printStackTrace();
				throw new BusinessException("请选择导入office的excel表格");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return workbook;
	}
	private static Workbook getWorkbook(InputStream ins) {
		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(ins);
		} catch (Exception e) {
			try {
				File file = new File("xx");
				FileOutputStream outs = new FileOutputStream(file);
				outs.write(ins.read());
				workbook = new XSSFWorkbook(file);
			} catch (InvalidFormatException e1) {
				e1.printStackTrace();
				throw new BusinessException("请选择导入office的excel表格");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return workbook;
	}

}
