package com.poc.crud.controller;
import com.poc.crud.helper.ResponseMessage;
import com.poc.crud.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value ="/welcome")
    public String welcome() throws Exception {
        throw new Exception();
        //return "Welcome";
    }

    @PostMapping(value = "/upload-and-save")
    public ResponseEntity<ResponseMessage> uploadAndSave(@RequestParam("file") MultipartFile file) throws Exception{
        if(itemService.hasCsvFormat(file)){
            itemService.processAndSaveData(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Data saved success"));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("Provide csv format file"));
    }

    @ExceptionHandler(value = {Exception.class, IOException.class, UnsupportedEncodingException.class})
    public ResponseEntity<ResponseMessage> exceptionHandler() {
        System.out.println(exceptionHandler().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage(exceptionHandler().toString()));
    }
}
