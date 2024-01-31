package com.poc.crud.service;

import com.poc.crud.dao.ItemDao;
import com.poc.crud.entity.Item;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements  ItemService{

    @Autowired
    private ItemDao itemDao;

    @Override
    public boolean hasCsvFormat(MultipartFile file) {
        String type = "text/csv";
        if(!type.equals(file.getContentType()))
            return false;
        return  true;
    }

    @Override
    public void processAndSaveData(MultipartFile file) throws IOException {
        List<Item> listOfItems = csvTOJavaObject(file.getInputStream());
        for(Item item : listOfItems){
            itemDao.saveItem(item);
        }
    }

    @Override
    public List<Item> csvTOJavaObject(InputStream inputStream) {
        List<Item> list = new ArrayList<>();
        try(BufferedReader fileReader =
                    new BufferedReader(new InputStreamReader(inputStream, "UTF-8")
                    );
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){
            List<CSVRecord> records= csvParser.getRecords();

            for(CSVRecord record : records){
                Item item = new Item(Long.parseLong(record.get("item_id")),
                        record.get("item_name"),
                        record.get("item_description"),
                        record.get("item_type"),
                        Double.parseDouble(record.get("item_price")),
                        parseStringToDate(record.get("item_manufactured_date")),
                        parseStringToDate(record.get("item_expiry_date")),
                        Long.parseLong(record.get("item_qty"))
                        );
                list.add(item);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Date parseStringToDate(String inputDate) {
        Date parsedDate = new Date();
        try {
            parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
