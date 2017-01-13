import com.tran.area.Area;
import com.tran.area.AreaKey;
import com.tran.map.SkiMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class SkiMapTest {
    @Test
    public void testSingleton() {
        SkiMap map = SkiMap.getInstance();
        assertEquals(map, SkiMap.getInstance());
    }

    @Test
    public void testHashMap() {
        SkiMap map = SkiMap.getInstance();

        map.put(new Area(1, 2));
        assertTrue(map.containsKey(new AreaKey(1, 2)));

        Area area = new Area(2, 4);
        assertFalse(map.containsKey(area.getKey()));
        map.put(area);
        assertTrue(map.containsKey(new AreaKey(2, 4)));
        assertFalse(map.containsKey(new AreaKey(4, 2)));
        assertEquals(area, map.getArea(new AreaKey(2, 4)));
    }
}
