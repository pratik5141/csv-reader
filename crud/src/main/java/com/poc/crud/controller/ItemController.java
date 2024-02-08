package com.poc.crud.controller;
import com.poc.crud.helper.ResponseMessage;
import com.poc.crud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value ="/welcome")
    public String welcome()  {
        throw new RuntimeException("Testing global exception handling");
        //return "Welcome";
    }

    @PostMapping(value = "/upload-and-save")
    public ResponseEntity<ResponseMessage> uploadAndSave(@RequestParam("file") MultipartFile file){
        if(itemService.hasCsvFormat(file) && !ObjectUtils.isEmpty(file)){
            itemService.processAndSaveData(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Data saved success"));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("Provide csv format file"));
    }

    @ExceptionHandler(value = {RuntimeException.class, IOException.class, UnsupportedEncodingException.class})
    public ResponseEntity<ResponseMessage> exceptionHandler(Exception exception) {
        String errorMsg = exception.getMessage();
        if(ObjectUtils.isEmpty(errorMsg)){
            errorMsg = "Internal Server Error";
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage(errorMsg));
    }
}
