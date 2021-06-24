package com.example.spring02.controller.chart;

import com.example.spring02.service.chart.GoogleChartService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@RestController
@RequestMapping("/chart/*")
public class GoogleChartController {
    @Inject
    GoogleChartService googleChartService;

    @RequestMapping("chart1.do")
    public ModelAndView chart1(){
        return new ModelAndView("chart/chart01");
    }

    @RequestMapping("chart2.do")
    public ModelAndView chart2(){
        return new ModelAndView("chart/chart02");
    }

    @RequestMapping("cart_money_list.do")
    public JSONObject cart_money_list(){
        return googleChartService.getChartData();
    }

}
