package hello.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CyclicIterator<E> implements Iterator<E> {

    private final Iterable<E> iterable;
    private Iterator<E> iterator;

    CyclicIterator(Iterable<E> iterable) {
        Objects.requireNonNull(iterable, "Input iterable cannot be null");
        this.iterable = iterable;
        iterator = iterable.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterable.iterator().hasNext();
    }

    @Override
    public E next() {
        if (!iterator.hasNext()) {
            iterator = iterable.iterator();
            if (!iterator.hasNext()) {
                throw new NoSuchElementException(); // return null
            }
        }
        return iterator.next();
    }
}
