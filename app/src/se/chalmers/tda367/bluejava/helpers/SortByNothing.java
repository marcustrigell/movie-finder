package se.chalmers.tda367.bluejava.helpers;

import se.chalmers.tda367.bluejava.interfaces.SortMethod;
import se.chalmers.tda367.bluejava.models.Movie;

import java.util.List;

/**
 * Created by marcus on 2014-04-29.
 */
public class SortByNothing implements SortMethod {

    @Override
    public List<Movie> sort(List<Movie> list) {
        return list;
    }
}
