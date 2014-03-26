package se.chalmers.tda367.bluejava;

import java.util.List;

/**
 * Created by marcus on 2014-03-25.
 *
 * Interface for sorting hits in the
 * DisplayResultsActivity-class.
 *
 * @author Marcus Trigell
 *
 */
public interface ISort {

    public List<Movie> sort(List<Movie> list);

}
