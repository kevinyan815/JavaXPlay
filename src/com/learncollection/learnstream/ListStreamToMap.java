package com.learncollection.learnstream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListStreamToMap {


    public static void main(String[] args) {
        List<Animal> aList = new ArrayList<>();
        aList.add(new Animal(1, "Elephant"));
        aList.add(new Animal(2, "Bear"));

        Map<Integer, Animal> map = aList.stream()
                .collect(Collectors.toMap(Animal::getId, Function.identity()));

        map.forEach((integer, animal) -> {
            System.out.println(animal.getName());
        });

    }
}
