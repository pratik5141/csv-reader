package com.poc.crud;
import com.poc.crud.dao.ItemDao;
import com.poc.crud.entity.Item;
import com.poc.crud.service.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UploadCsvTests {

    @Mock
    private ItemDao itemDao;

    @Test
    public void testSaveItem(){
        Item dummyItem = new Item(10L, "MacBook", "MacBook Air", "Laptop", 85000.00, new Date(), new Date(), 25L);
        itemDao.saveItem(dummyItem);
        assertThat(dummyItem.getItemId()).isGreaterThan(0);
    }


    @Test
    public void saveAndProcessData() throws IOException {
        ItemServiceImpl itemService = new ItemServiceImpl();
        ItemDao mockItemDao = mock(ItemDao.class);
        itemService.setItemDao(mockItemDao);
        MockMultipartFile multipartFile = createMockFile("TestData.csv");
        itemService.processAndSaveData(multipartFile);
        verify(mockItemDao, times(5)).saveItem(any(Item.class));
    }

    private MockMultipartFile createMockFile(String name) throws IOException {
        Path path = Paths.get("src/test/resources", name);
        byte[] content = Files.readAllBytes(path);
        return new MockMultipartFile("file", name, "text/csv", content);
    }
}
