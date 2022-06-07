package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param i the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int i) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : implement getTriples
        int[] a = this.a;
        int l = i - 1;
        int h = i + 1;

        while(l < h && l >= 0 && h < a.length){
            if (a[i] + a[l] + a[h] < 0) {
                h++;
            }
            else if (a[i] + a[l] + a[h] > 0) {
                l--;
            }
            else {
                triples.add(new Triple(a[i], a[l], a[h]));
                System.out.println("Triple Found" + a[i] + a[l] + a[h] );
                l--;
                h++;
            }
        }
        // END 
        return triples;
    }

    private final int[] a;
    private final int length;

//    public static void main (String[] args) {
////        System.out.println("Hello");
//        int[] a = [-100, -90, -79, -68, -62, -51, -44, -37, -27, -17, -11, -4, 2, 9, 16, 32, 42, 49, 57, 67];
//        ThreeSumQuadratic tsq = new ThreeSumQuadratic(a);
//        Triple[] tri = tsq.getTriples();
//        System.out.println(tri.length);
//    }
}
