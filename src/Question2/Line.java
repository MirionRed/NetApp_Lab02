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

public class Line implements Serializable {
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
    }

    public Line() {
            p1 = new Point();
            p2 = new Point();
    }

    public Point getP1() {
            return p1;
    }

    public Point getP2() {
            return p2;
    }

    public void setP1(Point p1) {
            this.p1 = p1;
    }

    public void setP2(Point p2) {
            this.p2 = p2;
    }

    public Point getMidPoint() {
            Point midPoint = new Point((p1.getX() + p2.getX())/2, (p1.getY() + p2.getY())/2);
            return midPoint;
    }

    @Override
    public String toString() {
            return "p1 " + p1.toString() + ", " + "p2 " + p2.toString();
    }
}