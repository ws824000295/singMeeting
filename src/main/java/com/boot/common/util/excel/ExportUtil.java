package com.boot.common.util.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;

public class ExportUtil {

    private HSSFWorkbook wb = null;

    private HSSFSheet sheet = null;
    @Autowired
HttpSession session;
    /**
     * @param wb
     * @param sheet
     */
    public ExportUtil(HSSFWorkbook wb, HSSFSheet sheet) {
        this.wb = wb;
        this.sheet = sheet;
    }

    public ExportUtil() {
    }

    /**
     * 合并单元格后给合并后的单元格加边框
     * 
     * @param region
     * @param cs
     */
    public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs) {
        int toprowNum = region.getFirstRow();
        for (int i = toprowNum; i <= region.getLastRow(); i++) {
            HSSFRow row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                HSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
                                                // (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    /**
     * 设置表头的单元格样式
     * 
     * @return
     */
    public HSSFCellStyle getHeadStyle(boolean isalert) {
        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        if (!isalert)
            cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        else
            cellStyle.setFillForegroundColor(HSSFColor.RED.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        HSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        if (isalert) {
            font.setColor(HSSFColor.WHITE.index);
        }
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    /**
     * 设置表体的单元格样式
     * 
     * @return
     */
    public HSSFCellStyle getBodyStyle() {
        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();

        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        HSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);

        return cellStyle;
    }

    // 将MultipartFile 转换为File
    public static void SaveFileFromInputStream(InputStream stream, String path,
            String savefile) throws IOException {
        FileOutputStream fs = new FileOutputStream(path + "/" + savefile);
        // System.out.println("------------"+path + "/"+ savefile);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    /**
     * 
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * 
     * @param file
     *            读取数据的源Excel
     * 
     * @param ignoreRows
     *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * 
     * @return 读出的Excel中数据的内容
     * 
     * @throws FileNotFoundException
     * 
     * @throws IOException
     */

    public static String[][] getData(File file, int ignoreRows)throws Exception {
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // 注意：一定要设成这个，否则可能会出现乱码
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0").format(cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            // 导入时如果为公式生成的数据则无值
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "Y"
                            : "N");
                            break;
                        default:
                            value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        continue;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }
                if (hasValue) {
                    result.add(values);
                }
            }
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * 
     * 去掉字符串右边的空格
     * 
     * @param str
     *            要处理的字符串
     * 
     * @return 处理后的字符串
     */

    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }
  

}