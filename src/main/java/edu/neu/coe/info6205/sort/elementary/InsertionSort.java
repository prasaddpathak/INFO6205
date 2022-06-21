/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();

        // FIXME
        for (int i = from; i < to; i++) {
            for (int j = i; j > from && helper.less(xs[j], xs[j - 1]); j--)
                helper.swap(xs, j -1, j);
        }
        return;
        // END 
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }


    public static void main(String[] args) {
        Random rand = new Random();
        InsertionSort insSort = new InsertionSort();

        for (int n = 100; n < 13000; n = n * 2) {

            //RANDOM Array
            ArrayList<Integer> list_random = new ArrayList<>();
            for (int i = 0; i < n; i++)
                list_random.add(rand.nextInt(n));

            //ORDERED Array
            ArrayList<Integer> list_arranged = new ArrayList<>();
            for (int i = 0; i < n; i++)
                list_arranged.add(i + 1);

            //REVERSE Array
            ArrayList<Integer> list_reverse = new ArrayList<>();
            for (int i = 0; i < n; i++)
                list_reverse.add(n - i);

            //PARTIALLY ORDERED Array
            List<Integer> list_partial = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (i > n / 2) {
                    list_partial.add(rand.nextInt(n));
                } else {
                    list_partial.add(i);
                }
            }

            Integer[] randArray = list_random.toArray(new Integer[0]);
            Integer[] srtdArray = list_arranged.toArray(new Integer[0]);
            Integer[] revArray = list_reverse.toArray(new Integer[0]);
            Integer[] parArray = list_partial.toArray(new Integer[0]);

            Benchmark<Boolean> bmRand = new Benchmark_Timer<>(
                    "randomSort", b -> {
                insSort.sort(randArray.clone(), 0, randArray.length);
            });
            double resultRand = bmRand.run(true, 100);

            Benchmark<Boolean> bmArenged = new Benchmark_Timer<>(
                    "arrangedSort", b -> {
                insSort.sort(srtdArray.clone(), 0, srtdArray.length);
            });
            double resultOrganised = bmArenged.run(true, 100);

            Benchmark<Boolean> bmRev = new Benchmark_Timer<>(
                    "reverseSort", b -> {
                insSort.sort(revArray.clone(), 0, revArray.length);
            });
            double resultRev = bmRev.run(true, 100);

            Benchmark<Boolean> bmPartial = new Benchmark_Timer<>(
                    "partialSort", b -> {
                insSort.sort(parArray.clone(), 0, parArray.length);
            });
            double resultPartial = bmPartial.run(true, 100);

            System.out.println(n);
            System.out.println("Random\t" + "Ordered\t" + "Reverse\t" + "Partial\t");
            System.out.println(resultRand + "\t" + resultOrganised + "\t" + resultRev + "\t" + resultPartial);

        }
    }
}
