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
        if (head != null && head.next != null) {
            Node<T> currentNode = head.next;
            head.next = null;
            while (currentNode != null) {
                Node<T> nextNode = currentNode.next;
                currentNode.next = head;
                head = currentNode;
                currentNode = nextNode;
            }
            return true;
        }
        return false;
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
