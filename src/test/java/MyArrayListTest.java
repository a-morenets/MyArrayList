import org.junit.BeforeClass;

/**
 * Created by a-morenets on 20.11.2016.
 */
public class MyArrayListTest extends MyListAbstractTest {

    @BeforeClass
    public static void init() throws Exception {
        listEmpty = new MyArrayList<>();
        list3Elements = new MyArrayList<>();
    }
}