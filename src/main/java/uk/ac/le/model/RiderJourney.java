package uk.ac.le.model;

public class RiderJourney extends Journey {
    private double cost;
    private double sourceToPickup;
    private double dropToSink;

    public RiderJourney(Journey journey) {
        this.setId(journey.getId());
        this.setSource(journey.getSource());
        this.setSink(journey.getSink());
    }

    public double getSourceToPickup() {
        return sourceToPickup;
    }

    public void setSourceToPickup(double sourceToPickup) {
        this.sourceToPickup = sourceToPickup;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDropToSink() {
        return dropToSink;
    }

    public void setDropToSink(double dropToSink) {
        this.dropToSink = dropToSink;
    }
}
