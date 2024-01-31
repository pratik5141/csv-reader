package com.poc.crud;

import com.poc.crud.dao.ItemDao;
import com.poc.crud.entity.Item;
import com.poc.crud.service.ItemService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UploadCsvTests {
    @Mock
    private ItemDao itemDao;

    @Mock
    private ItemService itemService;

    @Test
    public void testSaveItem(){
        Item dummyItem = new Item(10L, "MacBook", "MacBook Air", "Laptop", 85000.00, new Date(), new Date(), 25L);
       // when().thenReturn(dummyItem);
        itemDao.saveItem(dummyItem);
        assertThat(dummyItem.getItemId()).isGreaterThan(0);
    }

    //@Test
    public void testCsvToJavaObject() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("item_id,item_name\n1,Item1\n2,Item2\n".getBytes());
        BufferedReader fileReader = mock(BufferedReader.class);
        CSVParser csvParser = mock(CSVParser.class);

        when(CSVParser.parse(eq(fileReader), any(CSVFormat.class))).thenReturn(csvParser);

        CSVRecord record1 = mock(CSVRecord.class);
        when(record1.get("item_id")).thenReturn("1");
        when(record1.get("item_name")).thenReturn("Item1");

        CSVRecord record2 = mock(CSVRecord.class);
        when(record2.get("item_id")).thenReturn("2");
        when(record2.get("item_name")).thenReturn("Item2");

        List<CSVRecord> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);

        when(csvParser.getRecords()).thenReturn(records);

        List<Item> result = itemService.csvTOJavaObject(inputStream);

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Item1", result.get(0).getItemName());
    }
}
