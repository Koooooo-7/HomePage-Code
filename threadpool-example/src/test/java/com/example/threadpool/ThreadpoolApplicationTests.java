package com.example.threadpool;

import com.example.threadpool.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ThreadPoolApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    DemoService demoService;

    @Test
    void threadPoolTest() {
//        List mockedList = Mockito.mock(List.class);

        List<String> numbers = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
           numbers.add("0000x"+i);
        }
        demoService.sendMessage(numbers);
    }
}
