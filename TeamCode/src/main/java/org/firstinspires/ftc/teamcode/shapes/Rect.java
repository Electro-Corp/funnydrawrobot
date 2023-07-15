package org.firstinspires.ftc.teamcode.shapes;

public class Rect extends Shape {
    public Point[] points;
    public Rect(Point tl, Point tr, Point bl, Point br) {
        super(tl);
        Point[] tmppoitns = {tl, tr, bl, br};
        points = tmppoitns;
    }
}
