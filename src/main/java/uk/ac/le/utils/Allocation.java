package uk.ac.le.utils;

import uk.ac.le.model.Journey;

import java.util.ArrayList;
import java.util.List;

public class Allocation {
    List<Journey> journeys;
    List<Combo> combos;

    public Allocation() {
        super();
        combos = new ArrayList<Combo>();
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
    }
}