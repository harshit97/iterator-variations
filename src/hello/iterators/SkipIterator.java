package hello.iterators;

import java.util.*;

/*

Design a SkipIterator that supports a method skip(int val). When it is called the next element equals val in iterator sequence should be skipped.

SkipIterator itr = new SkipIterator([2, 3, 5, 6, 5, 7, 5, -1, 5, 10]);
itr.hasNext(); // true
itr.next(); // returns 2
itr.skip(5);
itr.next(); // returns 3
itr.next(); // returns 6 because 5 should be skipped
itr.next(); // returns 5
itr.skip(5);
itr.skip(5);
itr.next(); // returns 7
itr.next(); // returns -1
itr.next(); // returns 10
itr.hasNext(); // false
itr.next(); // error

https://leetcode.com/discuss/interview-question/341818/Google-or-Onsite-or-Skip-Iterator

 */
public class SkipIterator<E> {

    private final Iterator<E> iterator;
    private final Map<E, Integer> skipValueToFreqMap;
    private E nextElement;

    public SkipIterator(Iterator<E> iterator) {
        this.iterator = iterator;
        skipValueToFreqMap = new HashMap<>();
        this.nextElement = null;
    }

    public boolean hasNext() {
        if (skipValueToFreqMap.isEmpty()) {
            return iterator.hasNext() || Objects.nonNull(nextElement);
        }

        if (Objects.isNull(nextElement)) {
            if (!iterator.hasNext()) {
                return false;
            }
            nextElement = iterator.next();
        }

        while (skipValueToFreqMap.containsKey(nextElement)) {
            int currentFreq = skipValueToFreqMap.get(nextElement);
            if (currentFreq <= 1) {
                skipValueToFreqMap.remove(nextElement);
            } else {
                skipValueToFreqMap.put(nextElement, currentFreq - 1);
            }
            if (!iterator.hasNext()) {
                return false;
            }
            nextElement = iterator.next();
        }

        return true;
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (Objects.nonNull(nextElement)) {
            E elementToReturn = nextElement;
            nextElement = null;
            return elementToReturn;
        }
        return iterator.next();
    }

    /**
     * The input parameter is an int, indicating that the next element equals 'val' needs to be skipped.
     * This method can be called multiple times in a row. skip(5), skip(5) means that the next two 5s should be skipped.
     */
    public void skip(E value) {
        Objects.requireNonNull(value, "value cannot be null");
        int currentFreq = skipValueToFreqMap.getOrDefault(value, 0);
        skipValueToFreqMap.put(value, currentFreq + 1);
    }
}
