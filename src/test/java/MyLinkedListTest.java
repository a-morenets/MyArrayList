import my_linkedlist.MyLinkedList;
import org.junit.BeforeClass;

/**
 * Tests for LinkedList<E>
 * Created by a-morenets on 20.11.2016.
 */
public class MyLinkedListTest extends MyListTest {

    @BeforeClass
    public static void init() throws Exception {
        listEmpty = new MyLinkedList<>();
        list3Elements = new MyLinkedList<>();
    }
}