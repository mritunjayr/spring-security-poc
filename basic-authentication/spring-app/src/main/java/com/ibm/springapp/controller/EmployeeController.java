package com.ibm.springapp.controller;

import com.ibm.springapp.dto.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class EmployeeController {

    @GetMapping(value = "/employee",produces = "application/json")
    public ResponseEntity<?> employee(){
        return new ResponseEntity<StatusDTO>(new StatusDTO("All employee details is accessed"), HttpStatus.OK);
    }

}
