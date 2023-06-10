package company.wayfair.design;

import java.util.*;

/**
 * LeetCode 1912. Design Movie Rental System
 * https://leetcode-in-java.github.io/src/main/java/g1901_2000/s1912_design_movie_rental_system/
 * https://zxi.mytechroad.com/blog/data-structure/leetcode-1912-design-movie-rental-system/
 *
 * You have a movie renting company consisting of n shops. You want to implement a renting system that supports searching for, booking, and returning movies. The system should also support generating a report of the currently rented movies.
 *
 * Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates that there is a copy of movie moviei at shop shopi with a rental price of pricei. Each shop carries at most one copy of a movie moviei.
 *
 */

class Entry {
    public int movie;
    public int shop;
    public int price;

    public Entry(int movie, int shop, int price) {
        this.movie = movie;
        this.shop = shop;
        this.price = price;
    }
}

public class MovieRentalSystem {

    // {movie :  (movie shop, price)}
    private Map<Integer, Set<Entry>> unrentedMovies  = new HashMap<>();

    private Map<String, Integer> shopMovieToPrice = new HashMap<>();

    private Comparator<Entry> comparator = new Comparator<Entry>(){
        public int compare(Entry left, Entry right) {
            if (left.price != right.price){
                return Integer.compare(left.price, right.price);
            }
            if (left.shop != right.shop){
                return Integer.compare(left.shop, right.shop);
            }
            return Integer.compare(left.movie, right.movie);
        }
    };
    // same effect as comparator
    private Comparator<Entry> comparator_new = (left, right) -> {
            if (left.price != right.price){
                return Integer.compare(left.price, right.price);
            }
            if (left.shop != right.shop){
                return Integer.compare(left.shop, right.shop);
            }
            return Integer.compare(left.movie, right.movie);
    };

    // (price, shop, movie)
    private Set<Entry> rented = new TreeSet<>(comparator);

    public MovieRentalSystem(int n, int[][] entries) {
        for (int[] e : entries) {
             int shop = e[0];
             int movie = e[1];
             int price = e[2];
            unrentedMovies.putIfAbsent(movie, new TreeSet<>(comparator));
            unrentedMovies.get(movie).add(new Entry(movie, shop, price));
            shopMovieToPrice.put(shop + "+" + movie, price);
        }
    }

    public List<Integer> search(int movie) {
        if (!unrentedMovies.containsKey(movie)) {
            return new ArrayList<>();
        }
        List<Integer> resultShops = new ArrayList<>();
        for (Entry entry : unrentedMovies.get(movie)){
            if (resultShops.size() < 5){
                resultShops.add(entry.shop);
            }
        }
        return resultShops;
    }

    public void rent(int shop, int movie) {
        int price = shopMovieToPrice.get(shop + "+" + movie);
        rented.add(new Entry(movie, shop, price));
        unrentedMovies.get(movie).remove(new Entry(movie, shop, price));
    }

    public void drop(int shop, int movie) {
        int price = shopMovieToPrice.get(shop + "+" + movie);
        rented.remove(new Entry(movie, shop, price));
        unrentedMovies.get(movie).add(new Entry(movie, shop, price));
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();

        for (Entry entry : rented){
            if (result.size() < 5){
                result.add(Arrays.asList(entry.shop, entry.movie));
            }
        }
        return result;

    }

}
