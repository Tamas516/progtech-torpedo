package hu.nye.torpedo.service.map.parser;

import java.util.Arrays;
import java.util.List;

import hu.nye.torpedo.model.MapVO;

public class MapParser {

    private  int numberOfRows;
    private int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }


    public MapVO parseMap(List<String> rawMap)
    {
        String[][] map = getMap(rawMap);
        boolean[][] fixed = getFixed(map);


        return new MapVO(numberOfRows, numberOfColumns, map, fixed);
    }

    private String[][] getMap(List<String> rawMap)
    {
        String[][] result = new String[numberOfRows][];
        for(int i=0; i<numberOfRows; i++) {
            result[i] = new String[numberOfColumns];
            String line = rawMap.get(i);
            String[] parts = line.split("");
            for (int j = 0; j < numberOfColumns; j++) {
                result[i][j]=parts[j];
            }
        }
        return result;
    }

    private boolean[][] getFixed(String[][] map)
    {
        boolean[][] result = new boolean[numberOfRows][];

        for(int i=0; i<numberOfRows; i++)
        {
            result[i]=new boolean[numberOfColumns];
            for(int j=0; j<numberOfColumns; j++)
            {
                result[i][j]=map[i][j].equals("X");
            }
        }
        return result;
    }

}
