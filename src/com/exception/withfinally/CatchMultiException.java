package com.exception.withfinally;

import java.io.IOException;

public class CatchMultiException {
    public static void main(String[] args) {
        catchMultiNew();
        catchMultiOld();
    }

    private static void catchMultiOld() {
        try {
            throwMultiException(2);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void catchMultiTypeMultiMatch() {
        try {
            throwMultiException(2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static void catchMultiNew() {
        try {
            throwMultiException(3);
            // >> TODO 如果捕获了不同类型的异常，但是处理方式一样，可以用简化模式
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void throwMultiException(int i) throws ClassNotFoundException, IOException {

        switch (i) {
            case 1:
                throw new NullPointerException("demo");
            case 2:
                throw new ClassNotFoundException("demo");
            case 3:
                throw new IOException("demo");
        }
    }

}
