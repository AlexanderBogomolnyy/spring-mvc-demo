package ua.kiev.allexb.mvc.file;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import ua.kiev.allexb.mvc.model.Cat;
import ua.kiev.allexb.mvc.model.CatData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PDFDocument extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PdfPTable table = new PdfPTable(3);
        for (int i = 0; i < CatData.COLUMN_NAMES.size(); i++) {
            PdfPCell header = new PdfPCell(new Phrase(CatData.COLUMN_NAMES.get(i)));
            header.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(header);
        }
        //Get data from model
        List<Cat> cats = (List<Cat>) model.get(CatData.MODEL_OBJECT);
        addRowsDataModel(table, cats);
        document.add(table);
    }

    private void addRowsDataModel(PdfPTable table, List<Cat> cats) {
        for (Cat cat : cats) {
            table.addCell(cat.getName());
            table.addCell(String.valueOf(cat.getWeight()));
            table.addCell(cat.getColor());
        }
    }

}
