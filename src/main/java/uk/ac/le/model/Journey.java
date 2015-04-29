package uk.ac.le.model;

import javax.persistence.*;
import java.sql.Time;

@Entity(name = "journeys")
public class Journey extends BaseModel {

    @Column
    private String source;

    @Column
    private String sink;

    @Column
    private boolean isDriver;

    @Column
    private Time departure;

    @Column
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Column
    private String purpose;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Journey() {
    }

    public Journey(String source, String sink) {
        super();
        this.setSource(source);
        this.setSink(sink);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source.toUpperCase().replace(" ", "");
    }

    public String getSink() {
        return sink;
    }

    public void setSink(String sink) {
        this.sink = sink.toUpperCase().replace(" ", "");
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }

    public Time getDeparture() {
        return departure;
    }

    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getComments() {
        return comments;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String mode = isDriver ? "driver" : "rider";
        return super.toString() + " source = " + source + " sink = " + sink
                + " " + mode + " id = " + getId();
    }

    public enum Frequency {
        DAILY("Daily"),
        WEEKLY("Weekly"),
        SHIFT("Shift"),
        ONE_OFF("One-off"),
        OCCASIONAL("Occasional");

        private final String value;

        Frequency(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
