import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyArrayListTest {
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

        listEmpty = new MyArrayList<>();
        listWith3Elements = new MyArrayList<>();

        // fill list with same array elements
        for (int i = 0; i < arrayWith3Elements.length; i++) {
            listWith3Elements.add(arrayWith3Elements[i]);
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
        listEmpty.add(null);
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
        listWith3Elements.add(3, 777);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_atIndexEmptyList() throws Exception {
        listEmpty.add(0, 777);
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
        listWith3Elements.remove(0);
        assertEquals(2, listWith3Elements.size());
        listWith3Elements.remove(1);
        assertEquals(1, listWith3Elements.size());
        assertEquals(null, listWith3Elements.remove(0));
    }

    @Test
    public void containsAll() throws Exception {

    }

    @Test
    public void addAll() throws Exception {

    }

    @Test
    public void addAll_atIndex() throws Exception {

    }

    @Test
    public void removeAll() throws Exception {

    }

    @Test
    public void retainAll() throws Exception {

    }

    @Test
    public void clear() throws Exception {
        listWith3Elements.clear();
        assertEquals(0, listWith3Elements.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_OutOfBounds() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_OutOfBounds() throws Exception {

    }

    @Test
    public void set() throws Exception {

    }

    @Test
    public void trimToSize() throws Exception {

    }

    @Test
    public void ensureCapacity() throws Exception {

    }

    @Test
    public void indexOf() throws Exception {

    }

    @Test
    public void lastIndexOf() throws Exception {

    }

    @Test
    public void listIterator() throws Exception {

    }

    @Test
    public void listIterator_fromIndex() throws Exception {

    }

    @Test
    public void subList() throws Exception {

    }

    @Test
    public void equals() throws Exception {

    }

    @Test
    public void hashCodeTest() throws Exception {

    }

    @Test
    public void toStringTest() throws Exception {

    }

}