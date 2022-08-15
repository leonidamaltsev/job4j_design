package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tmpNode = head;
        T tmpValue = head.value;
        head = head.next;
        tmpNode.next = null;
        tmpNode.value = null;
        return tmpValue;
    }

    public boolean revert() {

        boolean rsl = !(isEmpty() || head.next == null);
        if (rsl) {
            Node<T> next;
            Node<T> current = head;
            Node<T> previous = null;
            while (current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
                Node<T> node = head;

                @Override
                public boolean hasNext() {
                    return node != null;
                }
                @Override
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    T value = node.value;
                    node = node.next;
                    return value;
                }
            };
        }

    public boolean isEmpty() {
        return head == null;
    }

    private static class Node<T> {
            T value;
            Node<T> next;

            public Node(T value, Node<T> next) {
                this.next = next;
                this.value = value;

            }
        }
    }
