package hello.iterators;

import com.google.common.collect.Iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        List<Integer> integers = Arrays.asList(2, 3, 5, 6, 5, 7, 5, -1, 5, 10);
        SkipIterator<Integer> integerSkipIterator = new SkipIterator<>(integers.iterator());
        System.out.println();
        System.out.println(integerSkipIterator.hasNext()); // true
        System.out.println(integerSkipIterator.next()); // returns 2
        integerSkipIterator.skip(5);
        System.out.println(integerSkipIterator.next()); // returns 3
        System.out.println(integerSkipIterator.next()); // returns 6 because 5 should be skipped
        System.out.println(integerSkipIterator.next()); // returns 5
        integerSkipIterator.skip(5);
        integerSkipIterator.skip(5);
        System.out.println(integerSkipIterator.next()); // returns 7
        System.out.println(integerSkipIterator.next()); // returns -1
        System.out.println(integerSkipIterator.next()); // returns 10
        System.out.println(integerSkipIterator.hasNext()); // false
        System.out.println(integerSkipIterator.next()); // error
    }
}
