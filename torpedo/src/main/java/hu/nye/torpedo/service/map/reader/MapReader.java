package hu.nye.torpedo.service.map.reader;

import hu.nye.torpedo.service.exception.MapReadException;

import java.util.List;

public interface MapReader {

    List<String> readMap() throws MapReadException;

}
