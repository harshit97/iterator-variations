package hello.iterators;

import java.util.Iterator;
import java.util.Objects;

public class PeekingIterator<E> implements Iterator<E> {

    private final Iterator<E> iterator;
    private boolean hasPeaked;
    private E peekedElement;

    PeekingIterator(Iterator<E> iterator) {
        Objects.requireNonNull(iterator, "Cannot initialize PeekingIterator with null iterator");
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return hasPeaked || iterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            return null; // throw new NoSuchElementException()
        }
        if (hasPeaked) {
            E elementToReturn = peekedElement;
            peekedElement = null;
            hasPeaked = false;
            return elementToReturn;
        }
        return iterator.next();
    }

    public E peek() {
        if (hasPeaked) {
            return peekedElement;
        }
        if (!hasNext()) {
            return null; // throw new NoSuchElementException()
        }
        peekedElement = iterator.next();
        hasPeaked = true;
        return peekedElement;
    }

}
