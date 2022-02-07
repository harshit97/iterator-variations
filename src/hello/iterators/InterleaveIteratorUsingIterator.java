package hello.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class InterleaveIteratorUsingIterator<E> implements Iterator<E> {

    private Iterator<Iterator<E>> iterators;
    private final List<Iterator<E>> iteratorList;
    private Iterator<E> currentIterator;

    InterleaveIteratorUsingIterator(List<Iterator<E>> iteratorList) {
        this.iteratorList = iteratorList;
        this.iterators = new ArrayList<>(iteratorList).iterator();
    }

    @Override
    public boolean hasNext() {
        if (currentIterator != null) {
            return true;
        }
        int count = 0;
        while (iterators.hasNext() && count <= iteratorList.size()) {
            count++;
            Iterator<E> childIterator = iterators.next();
            if (!iterators.hasNext()) {
                iterators = iteratorList.iterator();
            }
            if (childIterator.hasNext()) {
                currentIterator = childIterator;
                return true;
            }
        }
        return false;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E elementToReturn = currentIterator.next();
        currentIterator = null;
        return elementToReturn;
    }
}
