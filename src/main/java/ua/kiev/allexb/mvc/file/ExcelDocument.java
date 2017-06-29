package ua.kiev.allexb.mvc.file;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import ua.kiev.allexb.mvc.model.Cat;
import ua.kiev.allexb.mvc.model.CatData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelDocument extends AbstractXlsView {

    private static final String DEFAULT_SHEET_NAME = "Simple excel example";
    private static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    private static final String CONTENT_DISPOSITION_VALUE = "attachment; filename=excelDocument.xls";

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //New Excel sheet
        Sheet excelSheet = workbook.createSheet(DEFAULT_SHEET_NAME);
        //Excel file name change
        response.setHeader(CONTENT_DISPOSITION_HEADER, CONTENT_DISPOSITION_VALUE);

        Font font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());

        //Create Style for header
        CellStyle styleHeader = workbook.createCellStyle();
        styleHeader.setFillForegroundColor(IndexedColors.BLUE.index);
        styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleHeader.setFont(font);

        //Set excel header
        setExcelHeader(excelSheet, styleHeader);

        //Get data from model
        List<Cat> cats = (List<Cat>) model.get(CatData.MODEL_OBJECT);
        addDataFromModel(excelSheet, cats);
    }

    private void addDataFromModel(Sheet excelSheet, List<Cat> cats) {
        int rowCount = 1;
        for (Cat cat : cats) {
            Row row = excelSheet.createRow(rowCount++);
            row.createCell(0).setCellValue(cat.getName());
            row.createCell(1).setCellValue(cat.getWeight());
            row.createCell(2).setCellValue(cat.getColor());
        }
    }

    private void setExcelHeader(Sheet excelSheet, CellStyle styleHeader) {
        //set Excel Header names
        Row header = excelSheet.createRow(0);
        for (int i = 0; i < CatData.COLUMN_NAMES.size(); i++) {
            header.createCell(i).setCellValue(CatData.COLUMN_NAMES.get(i));
            header.getCell(i).setCellStyle(styleHeader);
        }
    }

}
