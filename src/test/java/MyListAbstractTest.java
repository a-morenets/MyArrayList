import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by a-morenets on 24.11.2016.
 */
public abstract class MyListAbstractTest {

    /** Empty list */
    protected static List<Integer> listEmpty;

    /** List with 3 elements */
    protected static List<Integer> list3Elements;

    /** Empty array */
    private Integer[] arrayEmpty = new Integer[] {};

    /** Array with 3 elements */
    protected Integer[] arrayWith3Elements = new Integer[] {5, null, 0};

    @Before
    public void setUp() throws Exception {
        listEmpty.clear();
        list3Elements.clear();
        // fill list with same array elements
        for (Integer arrayWith3Element : arrayWith3Elements) {
            if (!list3Elements.add(arrayWith3Element))
                fail("List was not initialized!");
        }
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, listEmpty.size());
        assertEquals(3, list3Elements.size());
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(listEmpty.isEmpty());
        assertFalse(list3Elements.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        assertFalse(listEmpty.contains(null));
        assertFalse(listEmpty.contains(5));

        assertTrue(list3Elements.contains(null));
        assertFalse(list3Elements.contains(999));
    }

    @Test
    public void iterator() throws Exception {
        Iterator<Integer> itrEmptyList = listEmpty.iterator();
        assertFalse(itrEmptyList.hasNext());

        Iterator<Integer> itr = list3Elements.iterator();
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(5), itr.next());
        assertTrue(itr.hasNext());
        assertNull(itr.next());
        itr.remove();
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(0), itr.next());
        assertFalse(itr.hasNext());
        assertEquals(2, list3Elements.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_NoSuchElementException() throws Exception {
        Iterator<Integer> itrEmptyList = listEmpty.iterator();
        itrEmptyList.next();
    }

    @Test
    public void toArray() throws Exception {
        assertArrayEquals(arrayEmpty, listEmpty.toArray());
        assertArrayEquals(arrayWith3Elements, list3Elements.toArray());
    }

    @Test
    public void toArray_Existing() throws Exception {
        Integer[] existingArray = {};
        assertArrayEquals(arrayEmpty, listEmpty.toArray(existingArray));
        existingArray = new Integer[] {null, null, null};
        assertArrayEquals(arrayWith3Elements, list3Elements.toArray(existingArray));
        existingArray = new Integer[] {null, null, null, 7};
        assertNotEquals(arrayWith3Elements.length, list3Elements.toArray(existingArray));
    }

    @Test
    public void add_Object() throws Exception {
        assertTrue(listEmpty.add(null));
        assertEquals(1, listEmpty.size());

        assertTrue(list3Elements.add(null));
        assertTrue(list3Elements.add(999));
        assertEquals(5, list3Elements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_atIndexOutOfRangeLessThanZero() throws Exception {
        listEmpty.add(-1, 777);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_atIndexOutOfRangeGreaterThanListSize() throws Exception {
        list3Elements.add(3, 777); // allowed!
        list3Elements.add(5, 555); // exception
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_atIndexEmptyList() throws Exception {
        listEmpty.add(0, 777); // allowed!
        listEmpty.add(2, 555); // exception
    }

    @Test
    public void add_atIndex() throws Exception {
        list3Elements.add(2, 999);
        assertEquals(Integer.valueOf(999), list3Elements.get(2));
    }

    @Test
    public void remove_Object() throws Exception {
        assertFalse(listEmpty.remove(Integer.valueOf(999)));
        assertEquals(0, listEmpty.size());

        assertFalse(list3Elements.remove(Integer.valueOf(999)));
        assertEquals(3, list3Elements.size());
        assertTrue(list3Elements.remove(null));
        assertEquals(2, list3Elements.size());
        assertTrue(list3Elements.remove(Integer.valueOf(5)));
        assertEquals(1, list3Elements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_atIndexLessThanZero() throws Exception {
        list3Elements.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_atIndexGreaterThanListSize() throws Exception {
        listEmpty.remove(100500);
    }

    @Test
    public void remove_atIndex() throws Exception {
        assertEquals(Integer.valueOf(5), list3Elements.remove(0));
        assertEquals(2, list3Elements.size());
        assertEquals(Integer.valueOf(0), list3Elements.remove(1));
        assertEquals(1, list3Elements.size());
        assertEquals(null, list3Elements.remove(0));
        assertEquals(0, list3Elements.size());
    }

    @Test(expected = NullPointerException.class)
    public void containsAll_nullCollection() throws Exception {
        list3Elements.containsAll(null);
    }

    @Test
    public void containsAll() throws Exception {
        assertFalse(listEmpty.containsAll(Arrays.asList(null, 0)));
        assertFalse(listEmpty.containsAll(Arrays.asList(5, null, 0, 999)));

        assertTrue(list3Elements.containsAll(Arrays.asList(null, 0)));
        assertFalse(list3Elements.containsAll(Arrays.asList(5, null, 0, 999)));
    }

    @Test
    public void addAll() throws Exception {
        assertTrue(listEmpty.addAll(Arrays.asList(null, 0, 999)));
        assertEquals(Arrays.asList(null, 0, 999), listEmpty);

        assertTrue(list3Elements.addAll(Arrays.asList(null, 0, 999)));
        assertEquals(6, list3Elements.size());
        assertEquals(null, list3Elements.get(3));
        assertEquals(Integer.valueOf(0), list3Elements.get(4));
        assertEquals(Integer.valueOf(999), list3Elements.get(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAll_atIndexIndexOutOfBounds() throws Exception {
        list3Elements.addAll(100500, Arrays.asList(null, 0, 999));
    }

    @Test
    public void addAll_atIndex() throws Exception {
        assertTrue(listEmpty.addAll(0, Arrays.asList(null, 0, 999)));
        assertEquals(Arrays.asList(null, 0, 999), listEmpty);

        assertTrue(list3Elements.addAll(2, Arrays.asList(null, 0, 999)));
        assertEquals(6, list3Elements.size());
        assertEquals(null, list3Elements.get(2));
        assertEquals(Integer.valueOf(0), list3Elements.get(3));
        assertEquals(Integer.valueOf(999), list3Elements.get(4));
    }

    @Test(expected = NullPointerException.class)
    public void removeAll_nullCollection() throws Exception {
        list3Elements.removeAll(null);
    }

    @Test
    public void removeAll() throws Exception {
        // TODO check test
        assertFalse(listEmpty.removeAll(Arrays.asList(null, 0)));

        assertTrue(list3Elements.removeAll(Arrays.asList(null, 0)));
        assertEquals(1, list3Elements.size());
        assertEquals(Integer.valueOf(5), list3Elements.get(0));
    }

    @Test(expected = NullPointerException.class)
    public void retainAll_nullCollection() throws Exception {
        list3Elements.retainAll(null);
    }

    @Test
    public void retainAll() throws Exception {
        // TODO check test
        assertFalse(listEmpty.retainAll(Arrays.asList(null, 0)));

        assertTrue(list3Elements.retainAll(Arrays.asList(null, 0)));
        assertEquals(2, list3Elements.size());
        assertEquals(null, list3Elements.get(0));
        assertEquals(Integer.valueOf(0), list3Elements.get(1));
    }

    @Test
    public void clear() throws Exception {
        list3Elements.clear();
        assertEquals(0, list3Elements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_OutOfBounds() throws Exception {
        listEmpty.get(0);
    }

    @Test
    public void get() throws Exception {
        assertEquals(null, list3Elements.get(1));
        assertEquals(Integer.valueOf(0), list3Elements.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_OutOfBounds() throws Exception {
        list3Elements.set(100500, 999);
    }

    @Test
    public void set() throws Exception {
        list3Elements.set(2, 222);
        assertEquals(Integer.valueOf(222), list3Elements.get(2));
    }

    @Test
    public void indexOf() throws Exception {
        assertEquals(-1, listEmpty.indexOf(0));

        assertEquals(1, list3Elements.indexOf(null));
        assertEquals(2, list3Elements.indexOf(0));
        assertEquals(-1, list3Elements.indexOf(100500));
    }

    @Test
    public void lastIndexOf() throws Exception {
        assertEquals(-1, listEmpty.lastIndexOf(0));

        assertEquals(2, list3Elements.lastIndexOf(0));
        assertEquals(0, list3Elements.lastIndexOf(5));
        assertEquals(-1, list3Elements.lastIndexOf(100500));
    }

    @Test
    public void listIterator() throws Exception {
        // empty list - no previous, no next
        ListIterator<Integer> listIterEmpty = listEmpty.listIterator();
        assertFalse(listIterEmpty.hasPrevious());
        assertFalse(listIterEmpty.hasNext());

        // non-empty list
        ListIterator<Integer> listIter = list3Elements.listIterator();
        assertFalse(listIter.hasPrevious());
        assertTrue(listIter.hasNext());
        // next()
        assertEquals(Integer.valueOf(5), listIter.next());
        assertTrue(listIter.hasPrevious());
        assertTrue(listIter.hasNext());
        // next()
        assertEquals(null, listIter.next());
        assertTrue(listIter.hasPrevious());
        assertTrue(listIter.hasNext());
        // next()
        assertEquals(Integer.valueOf(0), listIter.next());
        assertTrue(listIter.hasPrevious());
        assertFalse(listIter.hasNext());
        // previous()
        assertEquals(Integer.valueOf(0), listIter.previous());
        assertTrue(listIter.hasPrevious());
        assertTrue(listIter.hasNext());
        // next()
        assertEquals(Integer.valueOf(0), listIter.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void listIterator_NoSuchElementException() throws Exception {
        ListIterator<Integer> listItrEmptyList = list3Elements.listIterator();
        listItrEmptyList.previous();
    }

    @Test
    public void listIterator_fromIndex() throws Exception {
        ListIterator<Integer> listIter = list3Elements.listIterator(1);
        // cursor == 1
        assertTrue(listIter.hasPrevious());
        assertTrue(listIter.hasNext());
        // next()
        assertEquals(null, listIter.next());
        assertTrue(listIter.hasPrevious());
        assertTrue(listIter.hasNext());
        // next()
        assertEquals(Integer.valueOf(0), listIter.next());
        assertTrue(listIter.hasPrevious());
        assertFalse(listIter.hasNext());
        // previous()
        assertEquals(Integer.valueOf(0), listIter.previous());
        assertTrue(listIter.hasPrevious());
        assertTrue(listIter.hasNext());
        // next()
        assertEquals(Integer.valueOf(0), listIter.next());
        assertFalse(listIter.hasNext());

        // previousIndex()
        assertEquals(2, listIter.previousIndex());
        // nextIndex()
        assertEquals(3, listIter.nextIndex());

        // set()
        listIter.set(999);
        // previous()
        assertEquals(Integer.valueOf(999), listIter.previous());

        // add()
        listIter.add(33);
        assertEquals(4, list3Elements.size());
        assertEquals(Integer.valueOf(33), list3Elements.get(2));
        assertEquals(Integer.valueOf(999), list3Elements.get(3));
    }

    @Ignore
    @Test
    public void subList() throws Exception {
        // TODO
        fail("Test not implemented.");
    }

    @Test
    public void equals() throws Exception {
        assertEquals(Arrays.asList(arrayWith3Elements), list3Elements);
    }
}
