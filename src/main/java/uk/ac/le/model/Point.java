package uk.ac.le.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "points")
public class Point extends BaseModel {

    @Column(nullable = false)
    private double x;

    @Column(nullable = false)
    private double y;

    public Point() {
        super();
    }

    public Point(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public final boolean equals(Point b) {
        return this.x == b.x && this.y == y;
    }
}
