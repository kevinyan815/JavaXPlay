package com.learnconcurrent.atomic;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Person person = new Person("SnailClimb", 22);
        // 创建 AtomicStampedReference 实例时，需提供初始引用值和版本号
        AtomicStampedReference<Person> asr = new AtomicStampedReference<>(person, 0);
        System.out.printf("初始对象为: %s, {Name: %s, Age: %s}\n", asr.getReference(),
                asr.getReference().getName(), asr.getReference().getAge());
        Person updatePerson = new Person("Daisy", 20);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(Math.abs((int) (Math.random() * 100)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final int stamp = asr.getStamp(); // 获取引用的当前版本号
                // 引用的预期值是 person， 且当前的版本号匹配的情况下才会进行 CAS，并且对版本号进行加一
                if (asr.compareAndSet(person, updatePerson, stamp, stamp + 1)) {
                    System.out.println(Thread.currentThread().getName() + " 修改了对象！");
                    System.out.printf("新的对象为: %s, {Name: %s, Age: %s}\n", asr.getReference(),
                            asr.getReference().getName(), asr.getReference().getAge());
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);
    }

}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

}