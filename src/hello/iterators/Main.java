package hello.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    List<String> aList = Arrays.asList("a1", "a2", "a3", "a4", "a5");
        List<String> bList = Arrays.asList("b1", "b2", "b3", "b4");
        List<String> cList = Arrays.asList("c1", "c2", "c3");

        List<Iterator<String>> iteratorList = new ArrayList<>();
        iteratorList.add(aList.iterator());
        iteratorList.add(bList.iterator());
        iteratorList.add(cList.iterator());

        InterleaveIteratorUsingCounter<String> interleaveIteratorUsingCounter = new InterleaveIteratorUsingCounter(iteratorList);
        while (interleaveIteratorUsingCounter.hasNext()) {
            System.out.print(interleaveIteratorUsingCounter.next() + ", ");
        }
    }
}
