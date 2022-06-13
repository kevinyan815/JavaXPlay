package com.learncollection;

import java.util.ArrayList;
import java.util.List;

public class StreamReduceGetSum {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(9);
        intList.add(8);
        intList.add(7);
        Integer sum = intList.stream().reduce(0, Integer::sum);
        System.out.printf("List 求和，总和为%s\n", sum);
    }
}
