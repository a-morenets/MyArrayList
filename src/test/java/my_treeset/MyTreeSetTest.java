package my_treeset;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by a-morenets on 26.11.2016.
 */
public class MyTreeSetTest {
    private Set<Integer> setEmpty;
    private Set<Integer> set10elements;
    private Set<Integer> set10elementsSameElements;

    @Before
    public void setUp() throws Exception {
        setEmpty = new MyTreeSet<>();

        set10elements = new MyTreeSet<>();
        set10elements.add(5);
        set10elements.add(-9);
        set10elements.add(34);
        set10elements.add(100500);
        set10elements.add(0);
        set10elements.add(2);
        set10elements.add(-17);
        set10elements.add(-3);
        set10elements.add(-4);
        set10elements.add(7);
        // add same elements in other order
        set10elementsSameElements = new MyTreeSet<>();
        set10elementsSameElements.add(7);
        set10elementsSameElements.add(5);
        set10elementsSameElements.add(-4);
        set10elementsSameElements.add(-9);
        set10elementsSameElements.add(-3);
        set10elementsSameElements.add(34);
        set10elementsSameElements.add(-17);
        set10elementsSameElements.add(100500);
        set10elementsSameElements.add(2);
        set10elementsSameElements.add(0);
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
        assertFalse(setEmpty.add(5));                 // again
        assertEquals(1, setEmpty.size());   // size not changed
        assertTrue(setEmpty.contains(5));

        assertFalse(set10elements.add(5)); // duplicate
        assertTrue(set10elements.add(-5));
        assertEquals(11, set10elements.size());
    }

    @Test(expected = NullPointerException.class)
    public void remove_null_NPE() throws Exception {
        setEmpty.remove(null);
    }

    @Test
    public void remove() throws Exception {
        assertFalse(setEmpty.remove(0));

        assertTrue(set10elements.remove(5));
        assertFalse(set10elements.remove(5)); // 5 again
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
        //TODO
    }

    @Test
    public void toArray1() throws Exception {
        //TODO
    }

    @Test(expected = NullPointerException.class)
    public void addAll_null_NPE() throws Exception {
        setEmpty.addAll(null);
    }

    @Test
    public void addAll() throws Exception {
        //TODO
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
        //TODO
    }

    @Test(expected = NullPointerException.class)
    public void removeAll_null_NPE() throws Exception {
        set10elements.removeAll(null);
    }

    @Test
    public void removeAll() throws Exception {
        List<Integer> list = Arrays.asList(34, -4, 555, null, 34);

        assertFalse(setEmpty.removeAll(list));

        assertTrue(set10elements.removeAll(list));
        assertFalse(set10elements.removeAll(list)); // again
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
        //TODO
    }

    @Test
    public void equals() throws Exception {
        assertTrue(set10elements.equals(set10elementsSameElements));

        set10elements.remove(100500);
        assertFalse(set10elements.equals(set10elementsSameElements));
    }
}