package set;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet<E> implements ISet<E> {
	private LinkedList<E>[] hashSet;
	private int size;
	private int capacity;
	private double loadFactor;
//	private static <T> LinkedList<T>[] newBucketArray(int capacity){
//		return (LinkedList<<T>[]) new LinkedList[capacity];
//	}

	public MyHashSet(int capacity, double loadFactor) {
		super();
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		this.hashSet = new LinkedList[capacity];
	}

	public MyHashSet(int capacity) {
		this(capacity, 0.75);
	}

	public MyHashSet() {
		this(16);
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<>() {
			int totalCounter; // counter for elements - where we'are now
			int i; // index of bucket (Array)
			int j; // index of element in bucket (LinkedList)

			@Override
			public boolean hasNext() {
				return totalCounter < size;
			}

			@Override
			public E next() {
				while (hashSet[i] == null || hashSet[i].isEmpty())
					i++;
				E res = hashSet[i].get(j);
				totalCounter++;
				if (j < hashSet[i].size() - 1)
					j++;
				else {
					j = 0;
					i++;
				}
				return res;
			}
		};
	}

	@Override
	public boolean add(E element) {
		if (size >= capacity * loadFactor)
			rebuildArray();
		int index = getIndex(element);
		if (hashSet[index] == null)
			hashSet[index] = new LinkedList<>();
		if (hashSet[index].contains(element))
			return false;
		hashSet[index].add(element);
		size++;
		return true;
	}

	private int getIndex(E element) {
		int hashCode = element.hashCode();
		hashCode = hashCode >= 0 ? hashCode : -hashCode;
		return hashCode % capacity;
	}

	private void rebuildArray() {
		capacity *= 2;
		LinkedList<E>[] newHashSet = new LinkedList[capacity];
		for (int i = 0; i < hashSet.length; i++) {
			if (hashSet[i] != null) {
				for (E e : hashSet[i]) {
					int index = getIndex(e);
					if (newHashSet[index] == null) {
						newHashSet[index] = new LinkedList<>();
					}
					newHashSet[index].add(e);
				}
			}
		}
		hashSet = newHashSet;
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(E element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
