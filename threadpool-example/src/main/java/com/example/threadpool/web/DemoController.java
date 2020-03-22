package com.example.threadpool.web;

import com.example.threadpool.pojo.dto.NumberDTO;
import com.example.threadpool.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/03/22
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/send")
    public ResponseEntity send(@RequestBody NumberDTO numbers) {
        demoService.sendMessage(numbers.getNumbers());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
