package com.example.spring02.controller.chart;

import com.example.spring02.service.chart.JFreeChartService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/jchart/*")
public class JFreeChartController {
    @Inject
    JFreeChartService chartService;

    @RequestMapping("chart1.do")
    public void createChart1(HttpServletResponse response){
        try {
            JFreeChart chart=chartService.createChart();
            ChartUtils.writeChartAsPNG(response.getOutputStream(),chart,900,550);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("chart2.do")
    public ModelAndView createChart2(HttpServletRequest response){
        String message="";
        try {
            JFreeChart chart=chartService.createChart();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("d:/DEV/upload/test.pdf"));
            document.open();
            Image png= Image.getInstance(ChartUtils.encodeAsPNG(chart.createBufferedImage(500,500)));
            document.add(png);
            document.close();
            message="pdf파일이 생성되었습니다.";
        } catch(Exception e) {
            e.printStackTrace();
            message="pdf 파일 생성 실패.";
        }
        return new ModelAndView("chart/jchart02","message",message);
    }
}
