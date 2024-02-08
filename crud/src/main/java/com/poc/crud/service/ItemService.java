package com.poc.crud.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.poc.crud.entity.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ItemService {

    public boolean hasCsvFormat(MultipartFile file);

    public void processAndSaveData(MultipartFile file) ;

    public List<Item> csvTOJavaObject(InputStream inputStream);
}
