package telran.list.model;

import telran.list.interfaces.IList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements IList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;


    @Override
    public boolean add(int index, E element) {
        if (index == size) {
            Node<E> newNode = new Node<>(element, last, null);
            if (last != null)
                last.next = newNode;
            else
                first = newNode;
            last = newNode;
        } else {
            Node<E> node = getNodeIndex(index);
            Node<E> newNode = new Node<>(element, node.prev, node);
            node.prev = newNode;
            if (index != 0)
                newNode.prev.next = newNode;
            else
                first = newNode;
        }
        size++;
        return true;
    }

    private Node<E> getNodeIndex(int index) {
        checkIndex(index);
        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++)
                node = node.next;
        } else {
            node = last;
            for (int i = size - 1; i > index; i--)
                node = node.prev;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
    }


    @Override
    public E get(int index) {
        return getNodeIndex(index).data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        if (element != null) {
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (element.equals(node.data))
                    return index;
            }
        } else { // if searching for null
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (element == node.data)
                    return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        int index = size - 1;
        if (element != null) {
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (element.equals(node.data))
                    return index;
            }
        } else { // if searching for null
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (element == node.data)
                    return index;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> node = getNodeIndex(index);
        E oldValue = node.data;
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        if (prevNode != null)
            prevNode.next = nextNode;
        else
            first = nextNode;
        if (nextNode != null)
            nextNode.prev = prevNode;
        else
            last = prevNode;

        node.data = null;
        node.prev = null;
        node.next = null;
        size--;

        return oldValue;
    }

    @Override
    public void clear() {
        Node<E> current = first;
        while (current != null) {
            Node<E> next = current.next;
            current.prev = null;
            current.next = null;
            current.data = null;
            current = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node = getNodeIndex(index);
        E oldValue = node.data;
        node.data = element;

        return oldValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
