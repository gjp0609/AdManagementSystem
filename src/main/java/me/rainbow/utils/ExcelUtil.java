package me.rainbow.utils;

import me.rainbow.annotation.Excel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author guojinpeng
 * @date 18.1.4 14:55
 */
public class ExcelUtil {
    private static HSSFWorkbook sheets;
    private static Map<String, String> args;

    public static <Entity> HSSFWorkbook getExcel(List<Entity> list, Map<String, String> info) {
        args = info;
        if (args == null) args = new HashMap<>();
        if (list == null) list = new ArrayList<>();
        ArrayList<String> fieldList = new ArrayList<>();
        sheets = new HSSFWorkbook();
        String sheetName = args.get("sheetName");
        HSSFSheet sheet;
        if (StringUtils.isNotBlank(sheetName)) sheet = sheets.createSheet(sheetName);
        else sheet = sheets.createSheet();
        HSSFRow navRow = sheet.createRow(2);
        int colCount = 0;
        if (list.size() > 0) {
            Class<?> entityClass = list.get(0).getClass();
            Field[] declaredFields = entityClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Excel excel = field.getDeclaredAnnotation(Excel.class);
                if (excel != null) {
                    fieldList.add(field.getName());
                    HSSFCell cell = navRow.createCell(colCount);
                    cell.setCellValue(excel.name());
                    colCount++;
                }
            }
        }
        setTitle(colCount);
        int rowIndex = 3;
        for (Entity entity : list) {
            Class<?> xClass = entity.getClass();
            int cellIndex = 0;
            HSSFRow row = sheet.createRow(rowIndex);
            for (String filedName : fieldList) {
                try {
                    Method method = xClass.getMethod("get" + getFirstUpperName(filedName));
                    Object invoke = method.invoke(entity);
                    HSSFCell cell = row.createCell(cellIndex);
                    setValue(cell, invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cellIndex++;
            }
            rowIndex++;
        }
        for (int i = 0; i < colCount; i++) sheet.autoSizeColumn(i);
        return sheets;
    }

    private static void setTitle(int width) {
        String userName = args.get("userName");
        String title = args.get("title");
        HSSFSheet sheet = sheets.getSheetAt(0);
        CellRangeAddress cra0 = new CellRangeAddress(0, 0, 0, width - 1);
        CellRangeAddress cra1 = new CellRangeAddress(1, 1, 0, width - 1);
        sheet.addMergedRegion(cra0);
        sheet.addMergedRegion(cra1);
        HSSFCell titleRowCell0 = sheet.createRow(0).createCell(0);
        HSSFCell titleRowCell1 = sheet.createRow(1).createCell(0);
        titleRowCell0.setCellValue(title);
        HSSFCellStyle style = sheets.createCellStyle();
        HSSFFont font = sheets.createFont();
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        titleRowCell0.setCellStyle(style);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String info = "导出日期：" + date;
        if (StringUtils.isNotBlank(userName)) {
            String s = "导出人：" + userName + "，";
            info = s + info;
        }
        titleRowCell1.setCellValue(info);
    }

    /**
     * 向单元格填充数据
     */
    private static void setValue(HSSFCell cell, Object value) {
        if (value instanceof Integer) cell.setCellValue((Integer) value);
        else if (value instanceof Double) cell.setCellValue((Double) value);
        else if (value instanceof BigDecimal) cell.setCellValue(((BigDecimal) value).doubleValue());
        else if (value instanceof Date) cell.setCellValue((Date) value);
        else cell.setCellValue(String.valueOf(value));
    }

    private static String getFirstUpperName(String string) {
        if (StringUtils.isNotBlank(string)) {
            if (string.length() > 1) {
                String upperCase = string.substring(0, 1).toUpperCase();
                return upperCase + string.substring(1, string.length());
            } else return string.toUpperCase();
        }
        return "";
    }
}
