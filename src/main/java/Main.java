import my_linkedlist.MyLinkedList;
import my_treeset.MyTreeSet;

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

        System.out.println("=====================================");
        Set<Integer> intSet = new TreeSet<>();
        System.out.println("Removed from empty set? " + intSet.remove(0));
        intSet.add(99);
//        intSet.add(null); // not allowed - NPE
        intSet.add(0);
//        intSet.remove(null); // not allowed - NPE
//        intSet.contains(null); // not allowed - NPE

        MyTreeSet<Integer> myTreeSet = new MyTreeSet<>();
        myTreeSet.add(5);
        myTreeSet.add(0);
        myTreeSet.add(-3);
        myTreeSet.add(17);
        myTreeSet.add(34);
        myTreeSet.add(-4);
        myTreeSet.add(7);
        myTreeSet.add(9);
        myTreeSet.add(-3); // again
        myTreeSet.add(2);
        myTreeSet.add(0); // again
        System.out.println("size = " + myTreeSet.size());

        myTreeSet.clear();
        System.out.println("size = " + myTreeSet.size());

        myTreeSet.add(5);
        myTreeSet.add(0);
        myTreeSet.add(-3);
        myTreeSet.add(-17);
        myTreeSet.add(34);
        myTreeSet.add(-4);
        myTreeSet.add(7);
        myTreeSet.add(-9);
        myTreeSet.add(-3); // again
        myTreeSet.add(2);
        myTreeSet.add(0); // again

        MyTreeSet<Integer> myTreeSet1 = new MyTreeSet<>();
        myTreeSet1.add(5);
        myTreeSet1.add(0);
        myTreeSet1.add(-3);
        myTreeSet1.add(-17);
        myTreeSet1.add(34);
        myTreeSet1.add(-4);
        myTreeSet1.add(7);
        myTreeSet1.add(-9);
        myTreeSet1.add(-3); // again
        myTreeSet1.add(2);
        myTreeSet1.add(0); // again
        System.out.println("set1 and set2 are " + (myTreeSet.equals(myTreeSet1) ? "" : "NOT ") + "equal");

        for (Integer aMyTreeSet : myTreeSet) {
            System.out.println(aMyTreeSet);
        }

        System.out.println("remove -17: " + myTreeSet.remove(-17));
        System.out.println("remove -17: " + myTreeSet.remove(-17)); // again
        System.out.println("remove 0: " + myTreeSet.remove(0));
        System.out.println("remove -4: " + myTreeSet.remove(-4));
        System.out.println("remove 5: " + myTreeSet.remove(5));
        System.out.println("size = " + myTreeSet.size());
        System.out.println("Set is " + (myTreeSet.isEmpty() ? "" : "NOT ") + "empty.");
        System.out.println("set1 and set2 are " + (myTreeSet.equals(myTreeSet1) ? "" : "NOT ") + "equal");

        for (Iterator<Integer> itr = myTreeSet.iterator(); itr.hasNext();) {
            itr.remove();
        }

        System.out.println("size = " + myTreeSet.size());
        System.out.println("Set is " + (myTreeSet.isEmpty() ? "" : "NOT ") + "empty.");
    }

}
