/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

/**
 *
 * @author Red King
 */
import java.io.Serializable;

public class Point implements Serializable {
    private double x;
    private double y;

    public Point(double x, double y) {
            this.x = x;
            this.y = y;
    }

    public Point() {
            this(0.0, 0.0);
    }

    public double getX() {
            return x;
    }

    public double getY() {
            return y;
    }

    public void setX(double x) {
            this.x = x;
    }

    public void setY(double y) {
            this.y = y;
    }

    @Override
    public String toString() {
            return "(" + x + ", " + y + ")";
    }
}