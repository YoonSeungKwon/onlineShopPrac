package yoon.project.onlineShop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping({"/", "/index", "/main"})
    public ResponseEntity<?> mainPage(){
       return ResponseEntity.ok("Main Page");
    }

}
