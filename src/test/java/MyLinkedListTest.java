import org.junit.BeforeClass;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyLinkedListTest extends MyListAbstractTest {

    @BeforeClass
    public static void init() throws Exception {
        listEmpty = new MyLinkedList<>();
        list3Elements = new MyLinkedList<>();
    }
}