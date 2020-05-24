package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/map")
public class MapController {

    @RequestMapping("/map")
    public String map() {
        return "map";
    }

    @RequestMapping("/getindex")
    public String index() {
        System.out.println("--------------------------------ii");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/updateExcel")
    public String updateExcel() {
        System.out.println("--------------------------------12i");
        return "index";
    }


}
