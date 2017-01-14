import com.tran.area.Area;
import com.tran.area.AreaKey;
import com.tran.map.Data;
import com.tran.map.SkiMap;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @author: HieuTranNgoc
 * @since: 13/1/17.
 */
public class AreaTest {
    private Data dataMap;
    @Before
    public void setUp() {
        dataMap = new Data("/data/test.txt");
    }
    @Test
    public void testCornerArea() {
        Area testArea = new Area(0, 0, dataMap);
        System.out.println(testArea.getDownhillNeighbor().size());
        assertEquals(testArea.getDownhillNeighbor().size(), 1);
        AreaKey areaKey = new AreaKey(0, 1);
        assertTrue(testArea.getDownhillNeighbor().stream().anyMatch(e -> e.equals(areaKey)));
    }

    @Test
    public void testPathArea() {
        Area testArea = new Area(2, 1, dataMap);
        SkiMap skiMap = SkiMap.getInstance();
        skiMap.put(testArea);
        System.out.println(testArea.getDownhillNeighbor().size());
        assertEquals(testArea.getDownhillNeighbor().size(), 4);
        assertEquals(testArea.getTotalPath(), 5);
        assertEquals(testArea.getTotalDrop(), 8);
    }
}
