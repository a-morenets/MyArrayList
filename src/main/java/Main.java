import my_linkedlist.MyLinkedList;

import java.util.*;

/**
 * Created by a-morenets on 22.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(5, null, 0));
        ListIterator<Integer> li = a.listIterator(1);
        System.out.println("hasPrev: " + li.hasPrevious());
        System.out.println("hasNext: " + li.hasNext());
        System.out.println("prevInd = " + li.previousIndex());
        System.out.println("nextInd = " + li.nextIndex());

        System.out.println("next = " + li.next());
        System.out.println("hasPrev: " + li.hasPrevious());
        System.out.println("hasNext: " + li.hasNext());
        System.out.println("prevInd = " + li.previousIndex());
        System.out.println("nextInd = " + li.nextIndex());

        System.out.println("next = " + li.next());
        System.out.println("hasPrev: " + li.hasPrevious());
        System.out.println("hasNext: " + li.hasNext());
        System.out.println("prevInd = " + li.previousIndex());
        System.out.println("nextInd = " + li.nextIndex());

        li.set(999);
        System.out.println("prev = " + li.previous());
        li.add(33);

        System.out.println(a);

        // my_linkedlist.MyLinkedList
        List<String> myLinkedList = new MyLinkedList<>();
        System.out.println("myLinkedList.size() = " + myLinkedList.size());
        System.out.println(myLinkedList);

        myLinkedList.add("5");
        myLinkedList.add(null);
        myLinkedList.add("null");
        myLinkedList.add("ZERO");
        System.out.println("myLinkedList.size() = " + myLinkedList.size());
        System.out.println(myLinkedList);

        Set<Integer> intSet = new TreeSet<>();
        intSet.add(99);
        intSet.add(null);
        intSet.add(0);
    }

}
