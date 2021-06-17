package com.example.spring02.controller.shop;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping(value="/shop/product/*")
public class ProductController {

    @Inject
    ProductService productService;

    @RequestMapping("list.do")
    public ModelAndView list(ModelAndView mav){
        mav.setViewName("/shop/product_list");
        mav.addObject("list", productService.listProduct());
        return mav;
    }

    @RequestMapping("delete.do")
    public String delete(@RequestParam int product_id, HttpServletRequest request){
        String filename = productService.fileInfo(product_id);
        if(filename!=null && !filename.equals("-")){
            ServletContext application = request.getSession().getServletContext();
            String path = application.getRealPath("/WEB-INF/views/images/");
            File f = new File(path+filename);
            if(f.exists()){
                f.delete();
            }
        }
        productService.deleteProduct(product_id);
        return "redirect:/shop/product/list.do";
    }

    @RequestMapping("write.do")
    public String write(){
        return "shop/product_write";
    }

    @RequestMapping("edit/{product_id}")
    public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav){
        mav.setViewName("/shop/product_edit");
        mav.addObject("dto", productService.detailProduct(product_id));
        return mav;
    }

    @RequestMapping("insert.do")
    public String insert(@ModelAttribute ProductDTO dto, HttpServletRequest request){
        String filename="-";
        if(!dto.getFile1().isEmpty()){
            filename = dto.getFile1().getOriginalFilename();
            try{
                ServletContext application = request.getSession().getServletContext();
                String path = application.getRealPath("/WEB-INF/views/images/");
                new File(path).mkdir();
                dto.getFile1().transferTo(new File(path+filename));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        dto.setPicture_url(filename);
        productService.insertProduct(dto);
        return "redirect:/shop/product/list.do";
    }

    @RequestMapping("update.do")
    public String update(@ModelAttribute ProductDTO dto, HttpServletRequest request){
        String filename="-";
        if(!dto.getFile1().isEmpty()){
            filename = dto.getFile1().getOriginalFilename();
            try {
                ServletContext application = request.getSession().getServletContext();
                String path = application.getRealPath("/WEB-INF/views/images/");
                new File(path).mkdir();
                dto.getFile1().transferTo(new File(path+filename));
            } catch(Exception e) {
                e.printStackTrace();
            }
            dto.setPicture_url(filename);
        }else{
            ProductDTO dto2 = productService.detailProduct(dto.getProduct_id());
            dto.setPicture_url(dto2.getPicture_url());
        }
        productService.updateProduct(dto);
        return "redirect:/shop/product/list.do";
    }

    @RequestMapping("detail/{product_id}")
    public ModelAndView detail(@PathVariable int product_id, ModelAndView mav){
        mav.setViewName("/shop/product_detail");
        mav.addObject("dto", productService.detailProduct(product_id));
        return mav;
    }
}
