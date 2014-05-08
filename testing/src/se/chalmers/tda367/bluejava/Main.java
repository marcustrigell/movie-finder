package se.chalmers.tda367.bluejava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by axelniklasson on 2014-05-08.
 */
public class Main {

    public static void main(String[] main) {
        String correct = "", unsorted = "";
        try {
            Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/title_unsorted.txt"));
            while(sc.hasNextLine()) {
                unsorted += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Scanner sc = new Scanner(new FileReader("/Users/axelniklasson/dev/blue-java/testing/resources/title_sorted.txt"));
            while(sc.hasNextLine()) {
                correct += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Movie> list = Movie.jsonToListOfMovies(correct);
        System.out.println(list.size());
    }

}
