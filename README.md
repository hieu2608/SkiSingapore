# SkiSingapore
Ski Singapore is an interesting challenge

# Algorithm
This is a basic challenge for recursive algorithm. Each Area will compute its best path by comparing their eligible neighbor (downhill from North/East/West/South). If there is no eligible neighbor, it will be the end of a path.
```
if (this.downhillNeighbor.isEmpty()) {
    nextArea = null;
    totalPath = 1;
    totalDrop = 0;
} else {
    nextArea = computeBestNeighbor();
    totalPath = nextArea.totalPath + 1;
    totalDrop = height - nextArea.getHeight() + nextArea.getTotalDrop();
}	
```

# Implementation
Recursive algorithm is computing extensive if it's not implemented properly. That's why using an immutable object will reduce computing time largely. Each Area is an immutable object, i.e. once all the attributes is calculated, we don't need to construct this object again and can simply pull the object from the HashMap:
```
public class Area {
    final private AreaKey key;
    final private int posX;
    final private int posY;
    final private int height;
    final private int totalPath;
    final private int totalDrop;
    final private Area nextArea;
    final private Data mapData;
    final private List<AreaKey> downhillNeighbor;
```

