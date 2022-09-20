package com.learncollection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ChangeListInIteration {

    static class ISBN {
        private String ISBNCode;
        public ISBN(String isbn) {
            this.ISBNCode = isbn;
        }

        public String getCode() {
            return this.ISBNCode;
        }
    }

    static class Book {
        private ISBN isbn;
        public Book(ISBN isbn) {
            this.isbn = isbn;
        }

        public ISBN getISBN() {
            return this.isbn;
        }
    }

    public static void main(String[] args) {
        // 直接在迭代的时候删除、添加 List 元素会造成异常 ConcurrentModificationException
        // Java有两种方法避免这个错误。

        // 第一种把要删除的元素暂存在一个新元素里，然后使用removeAll进行删除，避免在迭代中修改集合
        useListRemoveAll();
        // 第二种把使用 Collection 在Java 8 以后提供的 removeIf方法.
        useListRemoveIf();
    }

    public static void useListRemoveAll() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(new ISBN("0-201-63361-2")));
        books.add(new Book(new ISBN("0-201-63361-3")));
        books.add(new Book(new ISBN("0-201-63361-4")));

        ISBN isbn = new ISBN("0-201-63361-2");
        List<Book> found = new ArrayList<>();
        for(Book book : books){
            if(book.getISBN().getCode().equals(isbn.getCode())){
                found.add(book);
            }
        }
        books.removeAll(found);
        System.out.println(books);
    }

    public static void useListRemoveIf() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(new ISBN("0-201-63361-2")));
        books.add(new Book(new ISBN("0-201-63361-3")));
        books.add(new Book(new ISBN("0-201-63361-4")));
        ISBN isbn = new ISBN("0-201-63361-2");
        // 使用 Collection 在Java 8 以后提供的 removeIf方法也可以
        // 从集合里删除指定ISBN码的元素
        // 该方法与使用 Stream 的filter操作类似
        books.removeIf(book -> book.getISBN().getCode().equals(isbn.getCode()));

        System.out.println(books);
    }

}
