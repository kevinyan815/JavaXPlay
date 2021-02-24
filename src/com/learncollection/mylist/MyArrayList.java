package com.learncollection.mylist;

import javax.management.ObjectName;
import java.util.*;

public class MyArrayList implements List {

    private Object[] elements;

    // Last index of ArrayList
    private int curr;

    public MyArrayList () {
        elements = new Object[16];
        curr = 0;
    }

    @Override
    public int size() {
        return curr;
    }

    @Override
    public boolean isEmpty() {
        return curr == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object el : elements) {
            if (Objects.equals(el, o)) {
                return true;
            }
        }

        return false;
    }
    @Override
    public void clear() {
        elements = null;
        curr = 0;
    }

    @Override
    public Object get(int index) {
        if (index > curr || index < 0) {
            throw new IndexOutOfBoundsException("out of bounds" +  curr + " for " + index);
        }

        return elements[index];
    }

    @Override
    public boolean add(Object o) {
        if (curr == elements.length -1) {
            // 不够存放新元素的时候， 对底层数组扩容
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }

        elements[curr] = o;
        curr++;

        return true;
    }



    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    private void arrayGrow() {
        Object[] newArray = new Object[elements.length * 2];
        int currIndex = 0;
        for (Object e : elements) {
            newArray[currIndex] = e;
            currIndex++;
        }
        elements = newArray;
    }

}
