package hu.nye.torpedo;

import hu.nye.torpedo.model.MapVO;
import hu.nye.torpedo.service.exception.MapReadException;
import hu.nye.torpedo.service.map.parser.MapParser;
import hu.nye.torpedo.service.map.reader.MapReader;
import hu.nye.torpedo.service.map.reader.impl.BufferedReaderMapReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/beginner.txt");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        try {
            List<String> strings = mapReader.readMap();
            System.out.println(strings);

            MapParser mapParser= new MapParser(5,5);
            MapVO mapVO1 = mapParser.parseMap(strings);
            System.out.println(mapVO1);
        } catch (MapReadException e) {
            e.printStackTrace();
        }
    }

}
