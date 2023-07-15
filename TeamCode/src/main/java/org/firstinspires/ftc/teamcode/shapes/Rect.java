package org.firstinspires.ftc.teamcode.shapes;

public class Rect extends Shape {
    public Point[] points;
    public Rect(Point tl, Point tr, Point bl, Point br) {
        super(tl);
        points = new Point[]{tl, tr, bl, br};
    }
}
