package com.example.threadpool.pojo.dto;

import java.util.List;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/03/22
 */
public class NumberDTO {

    private List<String> numbers;

    @Override
    public String toString() {
        return "NumberDTO{" +
                "numbers=" + numbers +
                '}';
    }

    public List<String> getNumbers() {
        return numbers;
    }

}
