package uk.ac.le.utils;

import uk.ac.le.model.Journey;

import java.util.ArrayList;
import java.util.List;

public class JourneyPeers {
    List<Journey> journeys;
    List<Combination> combinations;

    public JourneyPeers() {
        super();
        combinations = new ArrayList<Combination>();
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }

    public List<Combination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Combination> combinations) {
        this.combinations = combinations;
    }
}