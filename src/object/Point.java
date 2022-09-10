package object;

import java.util.ArrayList;

public class Point {
	public double x;
	public double y;
	public double z;
	
	public double red;
	public double blue;
	public double green;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point() {
	}
	
	public Point(Matrix m) {
		ArrayList<ArrayList<Double>> rows = m.getMatrix();
		this.x = rows.get(Constant.X).get(0);
		this.y = rows.get(Constant.Y).get(0);
		this.z = rows.get(Constant.Z).get(0);
	}
	
	public Point(double[] p, double[] c) {
		this.x = p[Constant.X];
		this.y = p[Constant.Y];
		this.z = p[Constant.Z];
		this.red = c[Constant.RED];
		this.green = c[Constant.GREEN];
		this.blue = c[Constant.BLUE];
	}
	
	public Point crossProduct(Point p) {
		Point res = new Point();
		res.x = this.y * p.z - this.z * p.y;
		res.y = this.z * p.x - this.x * p.z;
		res.z = this.x * p.y - this.y * p.x;
		return res;
	}
	
	public Point transform2D(double[][] t) {
		Point res = new Point();
		res.setColour(this.red, this.green, this.blue);
		res.x = t[0][0] * this.x + t[0][1] * this.y + t[0][2]*Constant.HOMOGENOUSCOORDINATE;
		res.y = t[1][0] * this.x + t[1][1] * this.y + t[1][2]*Constant.HOMOGENOUSCOORDINATE;
		return res;
	}
	
	public Point transform3D(double[][] t) {
		Point res = new Point();
		res.setColour(this.red, this.green, this.blue);
		res.x = t[0][0] * this.x + t[0][1] * this.y + t[0][2]*this.z + t[0][3] * Constant.HOMOGENOUSCOORDINATE;
		res.y = t[1][0] * this.x + t[1][1] * this.y + t[1][2]*this.z + t[1][3] * Constant.HOMOGENOUSCOORDINATE;
		res.z = t[2][0] * this.x + t[2][1] * this.y + t[1][2]*this.z + t[2][3] * Constant.HOMOGENOUSCOORDINATE;
		return res;
	}
	
	public double getNorm(){
		return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y, 2)+Math.pow(this.z, 2));
	}
	
	
	
	public double dotProduct(Point p) {
		return this.x * p.x + this.y * p.y + this.z * p.z;
	}
	
	public void setColour(double red, double green, double blue) {
		this.red = red;
		this.blue = blue;
		this.green = green;
	}
	
	
}
