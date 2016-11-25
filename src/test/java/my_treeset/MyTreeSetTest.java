package my_treeset;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by a-morenets on 26.11.2016.
 */
public class MyTreeSetTest {
    private Set<Integer> setEmpty;
    private Set<Integer> set10elements;

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
    public void add_NPE() throws Exception {
        setEmpty.add(null);
    }

    @Test
    public void add() throws Exception {
        assertTrue(setEmpty.add(5));
        assertEquals(1, setEmpty.size());
        assertTrue(setEmpty.contains(5));
    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void contains() throws Exception {

    }

    @Test
    public void toArray() throws Exception {

    }

    @Test
    public void toArray1() throws Exception {

    }

    @Test
    public void addAll() throws Exception {

    }

    @Test
    public void containsAll() throws Exception {

    }

    @Test
    public void retainAll() throws Exception {

    }

    @Test
    public void removeAll() throws Exception {

    }

    @Test
    public void clear() throws Exception {

    }

    @Test
    public void iterator() throws Exception {

    }

    @Test
    public void equals() throws Exception {

    }

    @Test
    public void hashCodeTest() throws Exception {

    }

}