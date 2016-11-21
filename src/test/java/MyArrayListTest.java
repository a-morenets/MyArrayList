import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyArrayListTest {
    private List<Integer> emptyList;
    private List<Integer> listWith3Elements;

    @Before
    public void setUp() throws Exception {
        // Initially empty list
        emptyList = new MyArrayList<>();

        // List with 3 elements
        listWith3Elements = new MyArrayList<>();
        listWith3Elements.add(5);
        listWith3Elements.add(null);
        listWith3Elements.add(0);
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, emptyList.size());
        assertEquals(3, listWith3Elements.size());
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(emptyList.isEmpty());
        assertFalse(listWith3Elements.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        assertFalse(emptyList.contains(null));
        assertFalse(emptyList.contains(5));

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
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(0), itr.next());
        assertFalse(itr.hasNext());
    }

    @Test
    public void toArray() throws Exception {
        assertArrayEquals(new Integer[]{}, emptyList.toArray());
        assertArrayEquals(new Integer[]{5, null, 0}, listWith3Elements.toArray());
    }

    @Test
    public void toArrayExisting() throws Exception {

    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void add1() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void containsAll() throws Exception {

    }

    @Test
    public void addAll() throws Exception {

    }

    @Test
    public void addAll1() throws Exception {

    }

    @Test
    public void removeAll() throws Exception {

    }

    @Test
    public void retainAll() throws Exception {

    }

    @Test
    public void clear() throws Exception {

    }

    @Test
    public void get() throws Exception {

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
    public void remove1() throws Exception {

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
    public void listIterator1() throws Exception {

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