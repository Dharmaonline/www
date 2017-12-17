package org.eprophet.jsonservice.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.eprophet.jsonservice.model.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
public class JSONFormatter {
  
  @Autowired
  private ObjectMapper objectMapper; //reuse the pre-configured mapper


  @PostConstruct
  public void setup() {
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      //whatever else you need
  }
  
  @GetMapping("/JSONFormatter")
    String loadPage(Model model) {
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

      model.addAttribute("JSONFormatter", new JsonFormat());
      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return "JSONFormatter";
    }
  
  @PostMapping(value ="/JSONFormatter", consumes="application/x-www-form-urlencoded;charset=UTF-8")
  String convertJSON( HttpServletRequest request,Model model) {
    try {
      String outputJson = objectMapper.writeValueAsString(request.getParameter("inputJson"));
      outputJson =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request.getParameter("inputJson"));
      model.addAttribute("outputJson", outputJson);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    model.addAttribute("inputJson", request.getParameter("inputJson"));
   
      return "JSONFormatter";
  }
}
