import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyLinkedListTest {
    /** empty list */
    private List<Integer> listEmpty;

    /** List with 3 elements */
    private List<Integer> listWith3Elements;

    /** empty array */
    private Integer[] arrayEmpty;

    /** array with 3 elements */
    private Integer[] arrayWith3Elements;

    @Before
    public void setUp() throws Exception {
        arrayEmpty = new Integer[] {};
        arrayWith3Elements = new Integer[] {5, null, 0};

        listEmpty = new MyLinkedList<>();
        listWith3Elements = new MyLinkedList<>();

        // fill list with same array elements
        for (int i = 0; i < arrayWith3Elements.length; i++) {
            if (!listWith3Elements.add(arrayWith3Elements[i]))
                fail("List was not initialized!");
        }
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, listEmpty.size());
        assertEquals(3, listWith3Elements.size());
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(listEmpty.isEmpty());
        assertFalse(listWith3Elements.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        assertFalse(listEmpty.contains(null));
        assertFalse(listEmpty.contains(5));

        assertTrue(listWith3Elements.contains(null));
        assertFalse(listWith3Elements.contains(999));
    }

    @Test
    public void iterator() throws Exception {
        Iterator<Integer> itrEmptyList = listEmpty.iterator();
        assertFalse(itrEmptyList.hasNext());

        Iterator<Integer> itr = listWith3Elements.iterator();
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(5), itr.next());
        assertTrue(itr.hasNext());
        assertNull(itr.next());
        itr.remove();
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(0), itr.next());
        assertFalse(itr.hasNext());
        assertEquals(2, listWith3Elements.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_NoSuchElementException() throws Exception {
        Iterator<Integer> itrEmptyList = listEmpty.iterator();
        itrEmptyList.next();
    }

    @Test
    public void toArray() throws Exception {
        assertArrayEquals(arrayEmpty, listEmpty.toArray());
        assertArrayEquals(arrayWith3Elements, listWith3Elements.toArray());
    }

    @Test
    public void toArray_Existing() throws Exception {
        Integer[] existingArray = {};
        assertArrayEquals(arrayEmpty, listEmpty.toArray(existingArray));
        existingArray = new Integer[] {null, null, null};
        assertArrayEquals(arrayWith3Elements, listWith3Elements.toArray(existingArray));
        existingArray = new Integer[] {null, null, null, 7};
        assertNotEquals(arrayWith3Elements.length, listWith3Elements.toArray(existingArray));
    }

    @Test
    public void add_Object() throws Exception {
        assertTrue(listEmpty.add(null));
        assertEquals(1, listEmpty.size());

        assertTrue(listWith3Elements.add(null));
        assertTrue(listWith3Elements.add(999));
        assertEquals(5, listWith3Elements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_atIndexOutOfRangeLessThanZero() throws Exception {
        listEmpty.add(-1, 777);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_atIndexOutOfRangeGreaterThanListSize() throws Exception {
        listWith3Elements.add(3, 777); // allowed!
        listWith3Elements.add(5, 555); // exception
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_atIndexEmptyList() throws Exception {
        listEmpty.add(0, 777); // allowed!
        listEmpty.add(2, 555); // exception
    }

    @Test
    public void add_atIndex() throws Exception {
        listWith3Elements.add(2, 999);
        assertEquals(Integer.valueOf(999), listWith3Elements.get(2));
    }

    @Test
    public void remove_Object() throws Exception {
        assertFalse(listEmpty.remove(Integer.valueOf(999)));
        assertEquals(0, listEmpty.size());

        assertFalse(listWith3Elements.remove(Integer.valueOf(999)));
        assertEquals(3, listWith3Elements.size());
        assertTrue(listWith3Elements.remove(null));
        assertEquals(2, listWith3Elements.size());
        assertTrue(listWith3Elements.remove(Integer.valueOf(5)));
        assertEquals(1, listWith3Elements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_atIndexLessThanZero() throws Exception {
        listWith3Elements.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_atIndexGreaterThanListSize() throws Exception {
        listEmpty.remove(100500);
    }

    @Test
    public void remove_atIndex() throws Exception {
        assertEquals(Integer.valueOf(5), listWith3Elements.remove(0));
        assertEquals(2, listWith3Elements.size());
        assertEquals(Integer.valueOf(0), listWith3Elements.remove(1));
        assertEquals(1, listWith3Elements.size());
        assertEquals(null, listWith3Elements.remove(0));
        assertEquals(0, listWith3Elements.size());
    }

    @Test
    public void containsAll() throws Exception {
        // TODO
        fail("Not implemented.");
    }

    @Test
    public void addAll() throws Exception {
        // TODO
        fail("Not implemented.");
    }

    @Test
    public void addAll_atIndex() throws Exception {
        // TODO
        fail("Not implemented.");
    }

    @Ignore
    @Test
    public void removeAll() throws Exception {
        fail("Not implemented.");
    }

    @Ignore
    @Test
    public void retainAll() throws Exception {
        fail("Not implemented.");
    }

    @Test
    public void clear() throws Exception {
        listWith3Elements.clear();
        assertEquals(0, listWith3Elements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_OutOfBounds() throws Exception {
        listEmpty.get(0);
    }

    @Test
    public void get() throws Exception {
        assertEquals(null, listWith3Elements.get(1));
        assertEquals(Integer.valueOf(0), listWith3Elements.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_OutOfBounds() throws Exception {
        listWith3Elements.set(100500, 999);
    }

    @Test
    public void set() throws Exception {
        listWith3Elements.set(2, 222);
        assertEquals(Integer.valueOf(222), listWith3Elements.get(2));
    }

    @Test
    public void indexOf() throws Exception {
        assertEquals(-1, listEmpty.indexOf(0));

        assertEquals(1, listWith3Elements.indexOf(null));
        assertEquals(2, listWith3Elements.indexOf(0));
        assertEquals(-1, listWith3Elements.indexOf(100500));
    }

    @Test
    public void lastIndexOf() throws Exception {
        assertEquals(-1, listEmpty.lastIndexOf(0));

        assertEquals(2, listWith3Elements.lastIndexOf(0));
        assertEquals(0, listWith3Elements.lastIndexOf(5));
        assertEquals(-1, listWith3Elements.lastIndexOf(100500));
    }

    @Test
    public void listIterator() throws Exception {
        // empty list - no previous, no next
        ListIterator<Integer> listIterEmpty = listEmpty.listIterator();
        assertFalse(listIterEmpty.hasPrevious());
        assertFalse(listIterEmpty.hasNext());

        // non-empty list
        ListIterator<Integer> listIter = listWith3Elements.listIterator();
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
        ListIterator<Integer> listItrEmptyList = listWith3Elements.listIterator();
        listItrEmptyList.previous();
    }

    @Test
    public void listIterator_fromIndex() throws Exception {
        ListIterator<Integer> listIter = listWith3Elements.listIterator(1);
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
        assertEquals(4, listWith3Elements.size());
        assertEquals(Integer.valueOf(33), listWith3Elements.get(2));
        assertEquals(Integer.valueOf(999), listWith3Elements.get(3));
    }

    @Ignore
    @Test
    public void subList() throws Exception {
        fail("Not implemented.");
    }

    @Test
    public void equals() throws Exception {
        assertEquals(Arrays.asList(arrayWith3Elements), listWith3Elements);
    }

}