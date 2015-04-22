package uk.ac.le.utils;

import java.util.ArrayList;
import java.util.List;

public class Combo{
    List<Integer> subRiders = new ArrayList<Integer>();
    List<Double> distTo = new ArrayList<Double>();
    List<Boolean> isSource = new ArrayList<Boolean>();
    List<Integer> subRiderId = new ArrayList<Integer>();

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

    public List<Boolean> getIsSource() {
        return isSource;
    }

    public void setIsSource(List<Boolean> isSource) {
        this.isSource = isSource;
    }

    public List<Integer> getSubRiderId() {
        return subRiderId;
    }

    public void setSubRiderId(List<Integer> subRiderId) {
        this.subRiderId = subRiderId;
    }
}
