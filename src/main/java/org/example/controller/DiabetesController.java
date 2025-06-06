package org.example.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiabetesController {

  public ResponseEntity<String> hasDiabetes() {
    return new ResponseEntity<>("Dont have diabetes", HttpStatus.OK);
  }

}
