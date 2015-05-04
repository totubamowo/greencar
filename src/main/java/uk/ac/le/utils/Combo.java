package uk.ac.le.utils;

import java.util.ArrayList;
import java.util.List;

public class Combo {
    List<Integer> subRiders = new ArrayList<Integer>();
    List<Double> distTo = new ArrayList<Double>();
    List<String> nextTo = new ArrayList<String>();
    String toString ="";

    public List<Integer> getSubRiders() {
        return subRiders;
    }

    public void setSubRiders(List<Integer> subRiders) {
        this.subRiders = subRiders;
    }

    public List<Double> getDistTo() {
        return distTo;
    }

    public void setDistTo(List<Double> distTo) {
        this.distTo = distTo;
    }

    public List<String> getNextTo() {
        return nextTo;
    }

    public void setNextTo(List<String> nextTo) {
        this.nextTo = nextTo;
    }

    public String getToString() {
        return getNextTo().toString();
    }

    public void setToString(String toString) {
        this.toString = toString;
    }
}
