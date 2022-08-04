package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private E[] container;

    private int size;

    private int modCount;

    @Override
    public void add(E value) {
        modCount++;
        if (size == container.length) {
            growContainer();
        }
        container[size] = value;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    private void growContainer() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size > index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}