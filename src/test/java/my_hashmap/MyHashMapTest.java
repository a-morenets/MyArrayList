package my_hashmap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests for MyHashMap<K, V>
 * Created by a-morenets on 30.11.2016.
 */
public class MyHashMapTest {

    private MyHashMap<Integer, String> mapEmpty;
    private MyHashMap<Integer, String> map3elements;

    private Map<Integer, String> mapStandardEmpty;
    private Map<Integer, String> mapStandard3elements;

    private Integer[] keys = {null, 2, 3};
    private String[] values = {"A", null, "C"};

    private Set<Integer> setStandardEmpty;
    private Set<Integer> setStandard3elements;

    private Set<String> setValuesStandardEmpty;
    private Set<String> setValuesStandard3elements;

    private Set<Map.Entry<Integer, String>> entrySetStandardEmpty;
    private Set<Map.Entry<Integer, String>> entrySetStandard3elements;

    @Before
    public void setUp() throws Exception {
        mapEmpty = new MyHashMap<>();
        map3elements = new MyHashMap<>(100, 0.25f);

        mapStandardEmpty = new HashMap<>();
        mapStandard3elements = new HashMap<>();

        entrySetStandardEmpty = mapStandardEmpty.entrySet();
        entrySetStandard3elements = mapStandard3elements.entrySet();

        setStandardEmpty = new HashSet<>();
        setStandard3elements = new HashSet<>(Arrays.asList(keys));

        setValuesStandardEmpty = new HashSet<>();
        setValuesStandard3elements = new HashSet<>(Arrays.asList(values));

        for (int i = 0; i < keys.length; i++) {
            map3elements.put(keys[i], values[i]);
            mapStandard3elements.put(keys[i], values[i]);
        }
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, mapEmpty.size());
        assertEquals(3, map3elements.size());
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(mapEmpty.isEmpty());
        assertFalse(map3elements.isEmpty());
    }

    @Test
    public void containsKey() throws Exception {
        assertFalse(mapEmpty.containsKey(2));

        assertTrue(map3elements.containsKey(null));
        assertTrue(map3elements.containsKey(2));
        assertFalse(map3elements.containsKey(100500));
    }

    @Test
    public void containsValue() throws Exception {
        assertFalse(mapEmpty.containsValue("A"));

        assertTrue(map3elements.containsValue(null));
        assertFalse(map3elements.containsValue("B"));
    }

    @Test
    public void get() throws Exception {
        assertNull(mapEmpty.get(0));

        assertEquals("C", map3elements.get(3));
        assertEquals(null, map3elements.get(2));
        assertNull(map3elements.get(100500));
    }

    @Test
    public void put() throws Exception {
        assertNull(mapEmpty.put(null, null));
        assertEquals(1, mapEmpty.size());
        assertNull(mapEmpty.put(null, "D")); // duplicate key, returns previous value (null)
        assertEquals(1, mapEmpty.size()); // size not changed
        assertEquals("D", mapEmpty.put(null, "E")); // duplicate key, returns previous value ("D")
        assertEquals(1, mapEmpty.size()); // size not changed

        assertEquals("A", map3elements.put(null, "F")); // duplicate key, returns previous value ("A")
        assertEquals(3, map3elements.size()); // size not changed
        assertNull(map3elements.put(100500, "ABCDE")); // new key, returns null
        assertEquals(4, map3elements.size()); // size == 4
    }

    @Test
    public void remove() throws Exception {
        assertNull(mapEmpty.remove(2));

        assertNull(map3elements.remove(2));
        assertEquals(2, map3elements.size());
        assertEquals("C", map3elements.remove(3));
        assertEquals(1, map3elements.size());
        assertNull(map3elements.remove(100500));
        assertEquals(1, map3elements.size()); // size not changed
    }

    @Test
    public void putAll() throws Exception {
        mapEmpty.putAll(mapStandard3elements);
        assertEquals(3, mapEmpty.size());

        mapStandardEmpty.put(100500, "ABCDE");
        map3elements.putAll(mapStandardEmpty); // 1 element only must be put
        assertEquals(4, map3elements.size());
    }

    @Test
    public void clear() throws Exception {
        mapEmpty.clear();
        assertTrue(mapEmpty.isEmpty());

        map3elements.clear();
        assertEquals(0, map3elements.size());
    }

    @Test
    public void keySet() throws Exception {
        assertEquals(setStandardEmpty, mapEmpty.keySet());
        assertEquals(setStandard3elements, map3elements.keySet());
    }

    @Test
    public void values() throws Exception {
        assertEquals(setValuesStandardEmpty, mapEmpty.values());
        assertEquals(setValuesStandard3elements, map3elements.values());
    }

    @Test
    public void entrySet() throws Exception {
        assertEquals(entrySetStandardEmpty, mapEmpty.entrySet());
        assertEquals(entrySetStandard3elements, map3elements.entrySet());
    }

    @Test
    public void equals() throws Exception {
        Map<Integer, String> map3sameElements = new MyHashMap<>();
        map3sameElements.put(3, "C");
        map3sameElements.put(null, "A");
        map3sameElements.put(2, null);

        assertTrue(map3elements.equals(map3sameElements));

        map3sameElements.remove(null);
        assertFalse(map3elements.equals(map3sameElements));
    }

}