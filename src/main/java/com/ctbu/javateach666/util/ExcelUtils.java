package com.ctbu.javateach666.util;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelUtils {
    /**
     * 定义把excel文档中内容存储为一个集合对象返回
     *
     * @param name
     * @param in
     * @return
     */
    public static List<Object> importFile(String name, InputStream in, Class<?> clazz) {
        List<Object> arrayList = new ArrayList<>();
        // 判断上传文件的模板是2003还是2007版本
        // "dxxx.xls"
        String filePath = name.replace("\"", "").toLowerCase();
        // 定义工作簿对象句柄
        Workbook wb = null;
        try {
            if (filePath.endsWith(".xls")) {
                wb = new HSSFWorkbook(in);
            } else if (filePath.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取工作簿中的第一个工作表
        Sheet sheet = wb.getSheetAt(0);
        // 获取总共行数
        int firstRowNumber = sheet.getFirstRowNum();
        int endRowNumber = sheet.getLastRowNum();
        // 获取第一行的值(表头)
        Row header = sheet.getRow(firstRowNumber);


        // 遍历excel中有效行
        for (int i = firstRowNumber + 1; i <= endRowNumber; i++) {
            // 获取当前遍历行
            Row row = sheet.getRow(i);
            // 把当前遍历的行转换为一个单元格的迭代器对象
            Iterator<Cell> cellIterator = row.cellIterator();


            try {
            	// 创建一个判断题对象
            	Object instance = clazz.newInstance();

                // 遍历迭代器
                while (cellIterator.hasNext()) {
                    // 获取每一个单元格对象
                    Cell cell = cellIterator.next();
                    String fieldName = (getCellValue(header.getCell(cell.getColumnIndex()))).toString();
                    PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);

                    Class<?> type = pd.getReadMethod().getReturnType();
                    Object value = getCellValue(cell);

                    if (type == int.class) {
                        pd.getWriteMethod().invoke(instance, (int) value);
                    }

                    if (type == float.class) {
                        pd.getWriteMethod().invoke(instance, (float) value);
                    }

                    if (type == double.class) {
                        pd.getWriteMethod().invoke(instance, (double) value);
                    }

                    if (type == String.class) {
                        pd.getWriteMethod().invoke(instance, String.valueOf(value));
                    }

                    if (type == Integer.class) {
                        pd.getWriteMethod().invoke(instance, Integer.valueOf(value.toString()));
                    }
                }

                
                arrayList.add(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return arrayList;
    }

    /**
     * 转换单元格格式的方法
     */
    private static Object getCellValue(Cell cell) {
        Object value = null;
        if (cell == null) {
            return value;
        }
        // 获取单元格中值得类型
        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
	            //如果为时间格式的内容
	            if (HSSFDateUtil.isCellDateFormatted(cell)) {      
	               //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
	               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	               value=sdf.format(HSSFDateUtil.getJavaDate(cell.
	               getNumericCellValue())).toString();                                 
	                 break;
	             } else {
	                 value = new DecimalFormat("0").format(cell.getNumericCellValue());
	             }
	            break;
	        case HSSFCell.CELL_TYPE_STRING: // 字符串
	            value = cell.getStringCellValue();
	            break;
            default:
                break;
        }

        return value;
    }
}
