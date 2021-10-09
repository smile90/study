package org.duancq90.gongju.file.dowload;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/4/8 下午2:54
 */
@Slf4j
public class ExcelBuilder {

    private Workbook wb = new XSSFWorkbook();
    private ConcurrentHashMap<Integer, CurrentSheet> sheets = new ConcurrentHashMap<>();

    /**
     * 创建Sheet
     * @param sheetName
     * @return
     */
    public CurrentSheet createSheet(String sheetName) {
        CurrentSheet currentSheet = new CurrentSheet(
            wb.createSheet(sheetName != null ? sheetName : "sheet-" + sheets.size())
        );
        sheets.put(sheets.size(), currentSheet);
        return currentSheet;
    }

    /**
     * 切换sheet；从0开始
     * @param num
     * @return
     */
    public CurrentSheet sheet(int num) {
        return sheets.get(num);
    }

    public class CurrentSheet {
        private Sheet currentSheet = null;

        private Integer currentRowIndex = 0;

        public CurrentSheet(Sheet currentSheet) {
            this.currentSheet = currentSheet;
        }

        public CurrentSheet writeHeadTitle(OutputStream os, String[] headTitles) {
            Optional.ofNullable(headTitles).orElseThrow(() -> new RuntimeException("表头不能为空"));
            Optional.ofNullable(currentSheet).orElseThrow(() -> new RuntimeException("尚未创建sheet"));
            // 表头
            Row headRow = currentSheet.createRow(currentRowIndex++);
            for (int i = 0; i < headTitles.length; i++) {
                Cell cell = headRow.createCell(i);
                cell.setCellValue(headTitles[i]);
            }
            try {
                wb.write(os);
            } catch (Exception e) {
                try {
                    wb.close();
                } catch (Exception e1) {
                    log.error(null, e1);
                }
            }
            return this;
        }

        public CurrentSheet writeContent(OutputStream os, String[] contents) {
            Optional.ofNullable(contents).orElseThrow(() -> new RuntimeException("内容不能为空"));
            Optional.ofNullable(currentSheet).orElseThrow(() -> new RuntimeException("尚未创建sheet"));
            // 内容
            Row content = currentSheet.createRow(currentRowIndex++);
            for (int i = 0; i < contents.length; i++) {
                Cell cell = content.createCell(i);
                cell.setCellValue(contents[i]);
            }
            try {
                wb.write(os);
            } catch (Exception e) {
                try {
                    wb.close();
                } catch (Exception e1) {
                    log.error(null, e1);
                }
            }
            return this;
        }
    }
}
