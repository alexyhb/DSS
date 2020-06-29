package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.pojo.caculate;
import com.pojo.company;
import com.service.impl.caculateServiceImpl;
import com.service.impl.companyServiceImpl;
import com.service.impl.customerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class companyController {
    @Autowired
    public companyServiceImpl companyService;
    @Autowired
    public customerServiceImpl customerService;
    @Autowired
    public caculateServiceImpl caculateService;

    @ResponseBody
    @RequestMapping(value = "/Login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) throws Exception{
        JSONObject json =new JSONObject();
        try{
            JSONObject loginInfo= getCompanyService().Login(username,password);
            json.put("info",loginInfo);
            return json.toJSONString();
        }catch (Exception e){
            throw new IOException(e);
        }
    }
    @ResponseBody
    @RequestMapping(value="/print",method = RequestMethod.POST)
    public String print(@RequestParam(value = "result") String result){

        JSONObject json=new JSONObject();

        List<company> list=companyService.getCompanyList("education");
        json.put("result",list);
        return json.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/register")
    public String register(@RequestParam(value = "industry")String industry, @RequestParam(value = "nationality")String nationality,@RequestParam( "quantity")int quantity,@RequestParam ("username")String username,@RequestParam( "password")String password, @RequestParam("name")String name,@RequestParam("scale")int scale){
        JSONObject json =new JSONObject();
        boolean flag= getCompanyService().addCompany(industry, nationality, quantity, username, password, name,scale);

//            int companyId= getCompanyService().idOfCompany(username);
//            List<company> list1= getCompanyService().getCompanyList(industry);
//            for (int i=0;i<list1.size();i++){
//                int customerId=list1.get(i).getId();
//                int tempScale=Math.abs(scale-list1.get(i).getScale());
//                int sacleScore=0;
//                if(tempScale<2000){
//                    sacleScore=10;
//                }else if(tempScale>=2000&&tempScale<4000){
//                    sacleScore=8;
//                }else if(tempScale>=4000&&tempScale<8000){
//                    sacleScore=6;
//                }else if(tempScale>=8000&&tempScale<16000){
//                    sacleScore=4;
//                }else if(tempScale>=16000){
//                    sacleScore=2;
//                }
//                int tempQuantity=Math.abs(quantity-list1.get(i).getQuantity());
//                int quantityScore=0;
//                if(tempQuantity<20){
//                    quantityScore=10;
//                }else if(tempQuantity>=20&&tempQuantity<50){
//                    quantityScore=8;
//                }else if(tempQuantity>=50&&tempQuantity<100){
//                    quantityScore=6;
//                }else if(tempQuantity>=100&&tempQuantity<500){
//                    quantityScore=4;
//                }else if(tempQuantity>=500){
//                    quantityScore=2;
//                }
//                int nationScore=0;
//                if (nationality==list1.get(i).getNationality()){
//                    nationScore=5;
//                }
//                int totolScore=sacleScore+quantityScore+nationScore;
//
//                caculateService.addCaculate(companyId,customerId,sacleScore,nationScore,quantityScore,totolScore);
//                caculateService.addCaculate(customerId,companyId,sacleScore,nationScore,quantityScore,totolScore);
//            }

        json.put("flag",flag);
        return json.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value="/show",method = RequestMethod.POST)
    public String show(@RequestParam(value = "username")String username){
        JSONObject json=new JSONObject();
        int companyId=companyService.idOfCompany(username);
        List<caculate> list=caculateService.getCaculate(companyId);
        int maxTS=0;
        int tempId=0;
        for(int i=0;i<list.size();i++){
            if(maxTS<list.get(i).getTotalScore()){
                maxTS=list.get(i).getTotalScore();
                tempId=list.get(i).getCustomerId();
            }
        }

        String companyName=companyService.getCompanyByid(tempId).getName();

        List list1=customerService.customerList(companyName);
        json.put("list",JSONObject.toJSON(list1));
        return json.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(){

        caculateService.updateAll();
        return "111";

    }


    public companyServiceImpl getCompanyService() {
        return companyService;
    }

    public void setCompanyService(companyServiceImpl companyService) {
        this.companyService = companyService;
    }
}
