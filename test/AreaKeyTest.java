import com.tran.area.AreaKey;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class AreaKeyTest {
    @Test
    public void testEqual() {
        AreaKey keyA = new AreaKey(1, 2);
        AreaKey keyB = new AreaKey(2, 3);
        AreaKey keyC = new AreaKey(2, 1);
        AreaKey keyD = new AreaKey(1, 2);

        assertEquals(keyA, keyD);
        assertFalse(keyA.equals(keyB));
        assertFalse(keyA.equals(keyC));
        assertTrue(keyA.equals(keyD));
    }

    @Test
    public void testPosition() {
        AreaKey keyA = new AreaKey(3, 4);
        assertEquals(keyA.getPosX(), 3);
        assertEquals(keyA.getPosY(), 4);

        AreaKey keyB = new AreaKey(keyA.getPosX(), keyA.getPosY());
        assertEquals(keyA, keyB);
    }

    @Test
    public void testHashMap() {
        HashMap<AreaKey, String> map = new HashMap<AreaKey, String>();
        map.put(new AreaKey(1, 2), "Object A");
        assertTrue(map.containsKey(new AreaKey(1, 2)));
        assertFalse(map.containsKey(new AreaKey(2, 1)));

    }
}
