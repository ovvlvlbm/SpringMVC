package com.example.spring02.controller.pdf;

import com.example.spring02.service.pdf.PdfService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
@RequestMapping("/pdf/*")
public class PdfController {

    @Inject
    PdfService pdfService;

    @RequestMapping("list.do")
    public ModelAndView list() throws Exception {
        String result=pdfService.createPdf();
        return new ModelAndView("pdf/result","message", result);
    }
}
