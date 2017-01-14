import com.tran.area.Area;
import com.tran.area.AreaKey;
import com.tran.map.Data;
import com.tran.map.SkiMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class SkiMapTest {
    private Data dataMap;
    private SkiMap map;

    @Before
    public void setUp() {
        map = SkiMap.getInstance();
        dataMap = new Data("/data/test.txt");
    }

    @After
    public void cleanUp() {
        map.cleanMap();
    }

    @Test
    public void testSingleton() {
        SkiMap skiMap = SkiMap.getInstance();
        assertEquals(map, skiMap);
    }

    @Test
    public void testHashMap() {
        map.put(new Area(1, 2, dataMap));
        assertTrue(map.containsKey(new AreaKey(1, 2)));

        assertTrue(map.containsKey(new AreaKey(2, 3)));
        assertFalse(map.containsKey(new AreaKey(3, 2)));
    }

    @Test
    public void testPopulateAndComputeBestPath() {
        map.populateMap(dataMap);
        Area startArea = map.getStartAreaOfBestPath();
        System.out.println("Best Area: " + startArea.toString());
        assertEquals(9, startArea.getHeight());
        assertEquals(5, startArea.getTotalPath());
        assertEquals(8, startArea.getTotalDrop());
        assertEquals(new AreaKey(2, 1), startArea.getKey());
    }

    @Test
    public void testPopulateActualData() {
        Data actualData = new Data("/data/map.txt");
        map.populateMap(actualData);
        Area startArea = map.getStartAreaOfBestPath();
        System.out.println("Best Area: " + startArea.toString());
        System.out.println("Print path from Best Area to the end");
        map.printPath(startArea);
    }


}
