package my_treeset;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests for TreeSet<E>
 * Created by a-morenets on 26.11.2016.
 */
public class MyTreeSetTest {
    private Set<Integer> setEmpty;
    private Set<Integer> set10elements;
    private Set<Integer> set10sameElements;
    private Integer[] arrayEmpty;
    private Integer[] array10elements;
    private Integer[] array10sameElements;

    @Before
    public void setUp() throws Exception {
        arrayEmpty = new Integer[] {};
        array10elements = new Integer[] {5, -9, 34, 100500, 0, 2, -17, -3, -4, 7};
        array10sameElements = new Integer[] {7, 5, -4, -9, -3, 34, -17, 100500, 2, 0};

        setEmpty = new MyTreeSet<>();

        set10elements = new MyTreeSet<>();
        set10elements.addAll(Arrays.asList(array10elements));

        // add same elements added in other order
        set10sameElements = new MyTreeSet<>();
        set10sameElements.addAll(Arrays.asList(array10sameElements));
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, setEmpty.size());
        assertEquals(10, set10elements.size());
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(setEmpty.isEmpty());
        assertFalse(set10elements.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void add_null_NPE() throws Exception {
        setEmpty.add(null);
    }

    @Test
    public void add() throws Exception {
        assertTrue(setEmpty.add(5));
        assertEquals(1, setEmpty.size());
        assertFalse(setEmpty.add(5));                 // duplicate
        assertEquals(1, setEmpty.size());   // size not changed
        assertTrue(setEmpty.contains(5));

        assertFalse(set10elements.add(5));          // duplicate
        assertTrue(set10elements.add(-5));
        assertEquals(11, set10elements.size());
        assertTrue(set10elements.contains(-5));
    }

    @Test(expected = NullPointerException.class)
    public void remove_null_NPE() throws Exception {
        setEmpty.remove(null);
    }

    @Test
    public void remove() throws Exception {
        assertFalse(setEmpty.remove(0));

        // remove root
        setEmpty.add(3);
        assertTrue(setEmpty.remove(3));
        assertFalse(setEmpty.contains(3));
        assertTrue(setEmpty.isEmpty());
        assertEquals(0, setEmpty.size());

        assertTrue(set10elements.remove(5));
        assertFalse(set10elements.remove(5));   // 5 again
        assertFalse(set10elements.contains(5));
        assertTrue(set10elements.remove(2));
        assertFalse(set10elements.contains(2));
        assertEquals(8, set10elements.size());
    }

    @Test(expected = NullPointerException.class)
    public void contains_null_NPE() throws Exception {
        set10elements.contains(null);
    }

    @Test
    public void contains() throws Exception {
        assertFalse(setEmpty.contains(5));

        assertTrue(set10elements.contains(100500));
        assertFalse(set10elements.contains(-100500));
    }

    @Test
    public void toArray() throws Exception {
        assertArrayEquals(arrayEmpty, setEmpty.toArray());

        Arrays.sort(array10elements);
        assertArrayEquals(array10elements, set10elements.toArray());
    }

    @Test(expected = NullPointerException.class)
    public void toArray_null_NPE() throws Exception {
        set10elements.toArray(null);
    }

    @Test
    public void toArray_existing() throws Exception {
        Integer[] arr = {};
        assertArrayEquals(arrayEmpty, setEmpty.toArray(arr));

        Arrays.sort(array10elements);
        assertArrayEquals(array10elements, set10elements.toArray(arr));

        arr = new Integer[100];
        Integer[] resArr = set10elements.toArray(arr);
        assertEquals(100, resArr.length);
        for (int i = 0; i < resArr.length; i++) {
            if (i < 10)                                     // only first 10 elements are not null
                assertEquals(array10elements[i], resArr[i]);
            else
                assertNull(resArr[i]);
        }
    }

    @Test(expected = NullPointerException.class)
    public void addAll_null_NPE() throws Exception {
        setEmpty.addAll(null);
    }

    @Test
    public void addAll() throws Exception {
        assertTrue(setEmpty.addAll(Arrays.asList(arrayEmpty)));
        assertTrue(setEmpty.isEmpty());
        assertTrue(setEmpty.addAll(set10elements));
        assertEquals(10, setEmpty.size());

        // add all duplicate elements
        assertFalse(set10elements.addAll(set10sameElements));
        assertEquals(10, set10elements.size());         // no changes
    }

    @Test(expected = NullPointerException.class)
    public void containsAll_null_NPE() throws Exception {
        set10elements.containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void containsAll_null_element_NPE() throws Exception {
        List<Integer> listContainsNull = Arrays.asList(34, null, -4, -4);
        set10elements.containsAll(listContainsNull);
    }

    @Test
    public void containsAll() throws Exception {
        List<Integer> listContainsAll = Arrays.asList(34, -4, -4);
        List<Integer> listNotContainsAll = Arrays.asList(34, -4, 555, 34);

        assertFalse(setEmpty.containsAll(listContainsAll));

        assertTrue(set10elements.containsAll(listContainsAll));
        assertFalse(set10elements.containsAll(listNotContainsAll));
    }

    @Test(expected = NullPointerException.class)
    public void retainAll_null_NPE() throws Exception {
        setEmpty.retainAll(null);
    }

    @Test
    public void retainAll() throws Exception {
        List<Integer> list = Arrays.asList(34, -4, 555, null, 34);      // to retain: 34, -4

        assertFalse(setEmpty.retainAll(list));

        assertTrue(set10elements.retainAll(list));
        assertEquals(2, set10elements.size());
        assertFalse(set10elements.retainAll(list));         // again
        assertEquals(2, set10elements.size());
    }

    @Test(expected = NullPointerException.class)
    public void removeAll_null_NPE() throws Exception {
        set10elements.removeAll(null);
    }

    @Test
    public void removeAll() throws Exception {
        List<Integer> list = Arrays.asList(34, -4, 555, null, 34);      // to remove: 34, -4

        assertFalse(setEmpty.removeAll(list));

        assertTrue(set10elements.removeAll(list));
        assertEquals(8, set10elements.size());
        assertFalse(set10elements.removeAll(list));         // again
        assertEquals(8, set10elements.size());
    }

    @Test
    public void clear() throws Exception {
        setEmpty.clear();
        assertEquals(0, setEmpty.size());

        set10elements.clear();
        assertEquals(0, set10elements.size());
    }

    @Test
    public void iterator() throws Exception {
        Iterator<Integer> itrEmptyList = setEmpty.iterator();
        assertFalse(itrEmptyList.hasNext());

        Iterator<Integer> itr = set10elements.iterator();
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(-17), itr.next());
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(-9), itr.next());
        itr.remove();
        assertTrue(itr.hasNext());
        assertEquals(Integer.valueOf(-4), itr.next());
        assertTrue(itr.hasNext());
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        assertFalse(itr.hasNext());
        assertFalse(set10elements.contains(-9));
        assertEquals(9, set10elements.size());
    }

    @Test
    public void equals() throws Exception {
        assertTrue(set10elements.equals(set10sameElements));

        set10elements.remove(100500);
        assertFalse(set10elements.equals(set10sameElements));
    }
}