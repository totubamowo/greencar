package uk.ac.le.model;

import javax.persistence.*;

@Entity(name = "journeys")
public class Journey extends BaseModel {

    @Column
    private String source;

    @Column
    private String sink;

    @Column
    private boolean isDriver;

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
}
