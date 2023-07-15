package org.firstinspires.ftc.teamcode.shapes;

public class Shape {
    //Top Left Point - Determines location
    Point point;
    public Shape(long x, long y) {
        this.point = new Point(x, y);
    }

    public Shape(Point _p) {
        this.point = _p;
    }
}
