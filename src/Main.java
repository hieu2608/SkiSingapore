import com.tran.area.Area;
import com.tran.map.Data;
import com.tran.map.SkiMap;

public class Main {

    public static void main(String[] args) {
        SkiMap skiMap = SkiMap.getInstance();
        Data actualData = new Data("/data/map.txt");

        skiMap.populateMap(actualData);
        Area startArea = skiMap.getStartAreaOfBestPath();
        System.out.println("Best Area: " + startArea.toString());
        System.out.println("Print path from Best Area to the end");
        skiMap.printPath(startArea);
    }
}
