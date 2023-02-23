package com.learncollection;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemoApp {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add("ccc");
        ts.add("aaa");
        ts.add("ddd");
        ts.add("bbb");

        System.out.println(ts); // [aaa, bbb, ccc, ddd]

        TreeSet<Person> ts2 = new TreeSet<>(new MyComparator());

        ts2.add(new Person("cc", 17, "男"));
        ts2.add(new Person("dd", 17, "女"));
        ts2.add(new Person("aa", 15, "女"));
        ts2.add(new Person("dd", 15, "女"));

        System.out.println(ts2);
    }
}


class Person {
    private String name;
    private int age;
    private String gender;

    public Person() {

    }

    public Person(String name, int age, String gender) {

        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age * 37;
    }

    public boolean equals(Object obj) {
        System.err.println(this + "equals :" + obj);
        if (!(obj instanceof Person)) {
            return false;
        }
        Person p = (Person) obj;
        return this.name.equals(p.name) && this.age == p.age;

    }

    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", gender=" + gender
                + "]";
    }
}

class MyComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
        if (p1.getAge() > p2.getAge()) {
            return 1;
        }
        if (p1.getAge() < p2.getAge()) {
            return -1;
        }
        return p1.getName().compareTo(p2.getName());
    }
}