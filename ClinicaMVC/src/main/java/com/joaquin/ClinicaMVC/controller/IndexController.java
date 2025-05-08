package com.joaquin.ClinicaMVC.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import com.joaquin.ClinicaMVC.service.DentistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    DentistService dentistService;


    @GetMapping
    public String getDentists(Model model) {
        model.addAttribute("dentits", dentistService.findAll());
        
        return "index";
    }
    
}
