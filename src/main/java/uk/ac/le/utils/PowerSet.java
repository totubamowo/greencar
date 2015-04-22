package uk.ac.le.utils;
/*************************************************************************
*  http://rosettacode.org/wiki/Power_set#Iterative
*
*  Content is available under GNU Free Documentation License 1.2.
*
*************************************************************************/

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PowerSet {
    public static <T> List<List<T>> powerSet(Collection<T> list) {
        List<List<T>> ps = new ArrayList<List<T>>();
        ps.add(new ArrayList<T>()); // add the empty set

        // for every item in the original list
        for (T item : list) {
            List<List<T>> newPs = new ArrayList<List<T>>();

            for (List<T> subset : ps) {
                // copy all of the current power set's subsets
                newPs.add(subset);

                // plus the subsets appended with the current item
                List<T> newSubset = new ArrayList<T>(subset);
                newSubset.add(item);
                newPs.add(newSubset);
            }

            // power set is now power set of list.subList(0, list.indexOf(item)+1)
            ps = newPs;
        }
        return ps;
    }
}