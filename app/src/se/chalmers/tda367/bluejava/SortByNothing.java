package se.chalmers.tda367.bluejava;

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
