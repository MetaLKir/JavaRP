package telran.point.dao;

import java.util.Arrays;
import java.util.Comparator;
import telran.point.model.Point;

public class PointsSpace {
	Point relPoint;
	Point[] points;
	Comparator<Point> comp;

	public PointsSpace(Point relPoint, Point[] points) {
		this.relPoint = relPoint;
		this.points = Arrays.copyOf(points, points.length);
        comp = (p1, p2) -> {
            double vecLen1 = vectorLength(relPoint, p1);
            double vecLen2 = vectorLength(relPoint, p2);
            return Double.compare(vecLen1, vecLen2);
        };
		Arrays.sort(this.points, comp);
    }

	public Point[] getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		// Find the index of where the new point should be.
		// Copy array before that index.
		// Insert the point after copied part (in the index).
		// Copy the rest array after the point.
		Point[] pointsNew = new Point[points.length + 1];
		int index = Arrays.binarySearch(points, point, comp);
		if (index < 0) index = -index - 1;
		System.arraycopy(points, 0, pointsNew, 0, index);
		pointsNew[index] = point;
		System.arraycopy(points, index, pointsNew, index + 1, points.length - index);
		points = pointsNew;
	}

	private double vectorLength(Point p1, Point p2){
		return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
	}
}
