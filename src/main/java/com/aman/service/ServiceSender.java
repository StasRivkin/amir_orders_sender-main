package com.aman.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.aman.dto.DtoOrder;
import com.aman.model.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ServiceSender implements IServiceSender {
    private final ModelMapper modelMapper;
    private final Gson gson;

    public ServiceSender() {
        this.modelMapper = new ModelMapper();
        this.gson = new Gson();
    }

    @Override
    public String obtainOrders() {
        String jsonData = sendJSON();
        Type orderListType = new TypeToken<List<DtoOrder>>() {
        }.getType();
        List<DtoOrder> dtoOrders = gson.fromJson(jsonData, orderListType);

        List<Order> orders = dtoOrders.stream().map(e -> modelMapper.map(e, Order.class)).toList();

        orders.forEach(e -> System.out.println(e));
        Order.createXMLFile("name", "./", orders);
        return "";
    }

    private String sendJSON() {
        return "[\n" +
                "    {\n" +
                "        \"SUPNAME\": \"62008300000\",\n" +
                "        \"CDES\": \"(1995) שיווק כבלים מסילות\",\n" +
                "        \"CURDATE\": \"2024-07-17T00:00:00+03:00\",\n" +
                "        \"ORDNAME\": \"PO24NET000010\",\n" +
                "        \"STATDES\": \"סגורה\",\n" +
                "        \"OWNERLOGIN\": \"hila\",\n" +
                "        \"CODE\": \"ש\\\"ח\",\n" +
                "        \"BRANCHNAME\": \"11\",\n" +
                "        \"QPRICE\": 6130.00,\n" +
                "        \"TOTPRICE\": 7172.10,\n" +
                "        \"TOTQUANT\": 16.000\n" +
                "    },\n" +
                "    {\n" +
                "        \"SUPNAME\": \"62008300000\",\n" +
                "        \"CDES\": \"(1995) שיווק כבלים מסילות\",\n" +
                "        \"CURDATE\": \"2024-07-17T00:00:00+03:00\",\n" +
                "        \"ORDNAME\": \"PO24NET000009\",\n" +
                "        \"STATDES\": \"מבוטלת\",\n" +
                "        \"OWNERLOGIN\": \"hila\",\n" +
                "        \"CODE\": \"ש\\\"ח\",\n" +
                "        \"BRANCHNAME\": \"11\",\n" +
                "        \"QPRICE\": 0.00,\n" +
                "        \"TOTPRICE\": 0.00,\n" +
                "        \"TOTQUANT\": 0.000\n" +
                "    }\n" +
                "]";
    }
}
