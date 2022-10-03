package com.learncollection.learnstream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 对 List 按照对象的字段进行排序
 * reference: https://stackoverflow.com/questions/40517977/sorting-a-list-with-stream-sorted-in-java
 */
public class UseSortedToSortObjectList {

    public static class Person {
        private Long personId;
        private String name;
        private Integer age;
        private Double salary;

        public Long getPersonId() {
            return personId;
        }

        public void setPersonId(Long personId) {
            this.personId = personId;
        }

        public Person(Long personId, String name, Integer age, Double salary) {
            this.personId = personId;
            this.name = name;
            this.age = age;

            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(new Person(1000L, "First", 25, 30000D),
                new Person(2000L, "Second", 30, 45000D),
                new Person(3000L, "Third", 35, 25000D));

        // 对 List 按照对象的字段进行排序

        // 使用字段包装类型的 compareTo 进行排序, 这个方式可以简化成下面的使用 Comparator.comparing 的例子
        personList.stream().sorted((p1, p2) -> (p1.getPersonId().compareTo(p2.getPersonId())))
                .forEach(person -> System.out.println(person.getName()));

        // 使用  Comparator.comparing() 该方法是一个泛型方法，会根据调用者的类型进行比较
        personList.stream().sorted(Comparator.comparing(Person::getPersonId)).forEach(person -> System.out.println(person.getName()));

        // 使用 按personId 倒序排序
        personList.stream().sorted(Comparator.comparing(Person::getPersonId).reversed()).forEach(person -> System.out.println(person.getName()));

        // 先按personId 排序，再按 age 排序
        personList.stream().sorted(Comparator.comparing(Person::getPersonId).thenComparing(Person::getAge)).forEach(person -> System.out.println(person.getName()));


    }
}
