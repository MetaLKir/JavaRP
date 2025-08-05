package telran.list.model;

public class Node<E> {
    E data;
    Node<E> prev;
    Node<E> next;

    public Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
