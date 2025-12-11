package br.upe.finance.controllers.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
