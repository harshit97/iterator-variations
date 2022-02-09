package hello.iterators;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CustomListIterator {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        ListIterator<Integer> listIterator = integerList.listIterator();
    }

}
