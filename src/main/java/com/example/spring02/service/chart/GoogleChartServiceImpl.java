package com.example.spring02.service.chart;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class GoogleChartServiceImpl implements GoogleChartService{
    @Inject
    CartService cartService;

    @Override
    public JSONObject getChartData() {
        List<CartDTO> items = cartService.cartMoney();

        JSONObject data=new JSONObject();
        JSONObject col1=new JSONObject();
        JSONObject col2=new JSONObject();
        JSONArray title=new JSONArray();
        col1.put("label","상품명");
        col1.put("type","string");
        col2.put("label","금액");
        col2.put("type","number");

        title.add(col1);
        title.add(col2);

        data.put("cols", title);

        JSONArray body = new JSONArray();
        for(CartDTO dto:items){
            JSONObject name=new JSONObject();
            name.put("v", dto.getProduct_name());
            JSONObject money=new JSONObject();
            money.put("v", dto.getMoney());
            JSONArray row=new JSONArray();
            row.add(name);
            row.add(money);
            JSONObject cell=new JSONObject();
            cell.put("c",row);
            body.add(cell);
        }
        data.put("rows", body);

        return data;
    }
}
