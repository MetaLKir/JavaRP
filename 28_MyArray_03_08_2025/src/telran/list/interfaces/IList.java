package telran.list.interfaces;

public interface IList<E> extends Iterable<E> {

    default boolean add(E element) {
        return add(size(), element);
    }

    boolean add(int index, E element);

    E get(int index);

    int size();

    int indexOf(E element);

    int lastIndexOf(E element);

    E remove(int index);

    default boolean remove(E element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    default boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    void clear();

    default boolean isEmpty() {
        return size() == 0;
    }

    E set(int index, E element);
}
