import com.tran.map.Data;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * @author: HieuTranNgoc
 * @since: 12/1/17.
 */
public class DataTest {
    @Test
    public void testData() {
        Data mapData = new Data(new File("").getAbsolutePath().concat("/data/test.txt"));
        assertEquals(mapData.getMaxRows(), 4);
        assertEquals(mapData.getMaxColumns(), 4);
        assertEquals(mapData.getHeight(0, 0), 4);
        assertEquals(mapData.getHeight(3, 3), 6);
        for (int i = 0; i < mapData.getMaxRows(); i++) {
            for (int j = 0; j < mapData.getMaxColumns(); j++) {
                int height = mapData.getHeight(i, j);
                System.out.println("i = " + i + ", j = " + j + ", height = " + height);
            }
        }
    }
}
