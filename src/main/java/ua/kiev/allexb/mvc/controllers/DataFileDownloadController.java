package ua.kiev.allexb.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.allexb.mvc.model.Cat;
import ua.kiev.allexb.mvc.model.CatData;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataFileDownloadController {

    private static final String EXCEL_DOCUMENT_VIEW = "excelDocument";
    private static final String PDF_DOCUMENT_VIEW = "pdfDocument";

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public ModelAndView excel() {
        System.out.println("ExcelPDFController excel is called");

        List<Cat> cats = createCats();
        //excelDocument - look excel-pdf-config.xml
        return new ModelAndView(EXCEL_DOCUMENT_VIEW, CatData.MODEL_OBJECT, cats);
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public ModelAndView pdf() {
        System.out.println("ExcelPDFController pdf is called");

        List<Cat> cats = createCats();
        //pdfDocument - look excel-pdf-config.xml
        return new ModelAndView(PDF_DOCUMENT_VIEW, CatData.MODEL_OBJECT, cats);
    }

    private List<Cat> createCats() {
        List<Cat> cats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Cat cat = new Cat("cat" + i, "color" + i, i);
            cats.add(cat);
        }
        return cats;
    }

}
