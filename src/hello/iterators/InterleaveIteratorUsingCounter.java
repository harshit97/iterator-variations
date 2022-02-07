package hello.iterators;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InterleaveIteratorUsingCounter<E> implements Iterator<E> {

    private final List<Iterator<E>> iteratorList;
    private Iterator<E> currentIterator;
    private int iteratorIndex;

    InterleaveIteratorUsingCounter(List<Iterator<E>> iteratorList) {
        this.iteratorList = iteratorList.stream().filter(Iterator::hasNext).collect(Collectors.toList());
        iteratorIndex = 0;
        currentIterator = this.iteratorList.get(iteratorIndex);
    }

    @Override
    public boolean hasNext() {
        return currentIterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E elementToReturn = currentIterator.next();
        int currentIteratorIndex = iteratorIndex;
        Iterator<E> nextIterator = iteratorList.get(incrementAndGetIteratorIndex());
        while (!nextIterator.hasNext() && currentIteratorIndex != iteratorIndex) {
            nextIterator = iteratorList.get(incrementAndGetIteratorIndex());
        }
        currentIterator = nextIterator;
        return elementToReturn;
    }

    private int incrementAndGetIteratorIndex() {
        iteratorIndex = (iteratorIndex + 1) % iteratorList.size();
        return iteratorIndex;
    }
}
