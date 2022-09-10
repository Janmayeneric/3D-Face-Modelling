package object;

public class Triangle {
	private Point[] vertices;
	private double[] rgb;
	
	public Triangle(Point[] vertices, Point ray) {
		this.vertices = vertices;
		this.setColour(ray);
	}
	
	public Triangle() {
	}
	
	public void setVertices(Point[] vertices) {
		this.vertices = vertices;
	}
	
	public Point[] getVertices() {
		return this.vertices;
	}
	
	public Triangle tranform2d(double[][] t) {
		Triangle res = new Triangle();
		res.setColour(this.rgb);
		Point[] vs = new Point[3];
		vs[0] = this.vertices[0].transform2D(t);
		vs[1] = this.vertices[1].transform2D(t);
		vs[2] = this.vertices[2].transform2D(t);
		
		res.setVertices(vs);
		return res;
	}
	
	public Triangle tranform3d(double[][] t) {
		Triangle res = new Triangle();
		res.setColour(this.rgb);
		Point[] vs = new Point[3];
		vs[0] = this.vertices[0].transform3D(t);
		vs[1] = this.vertices[1].transform3D(t);
		vs[2] = this.vertices[2].transform3D(t);
		
		res.setVertices(vs);
		return res;
	}
	
	/**
	 * this is based on the z coordinate of the centroid
	 * @return
	 */
	public Point getCentroid() {
		Point centroid = new Point();
		centroid.x = (vertices[0].x + vertices[1].x + vertices[2].x)/3.;
		centroid.y = (vertices[0].y + vertices[1].y + vertices[2].y)/3.;
		centroid.z = (vertices[0].z + vertices[1].z + vertices[2].z)/3.;
		return centroid;
	}
	
	public double getRed() {
		return this.rgb[Constant.RED];
	}
	
	public double getGreen() {
		return this.rgb[Constant.GREEN];
	}
	
	public double getBlue() {
		return this.rgb[Constant.BLUE];
	}
	
	private void setColour(Point ray) {
		this.rgb = new double[3];
		this.rgb[Constant.RED] = (vertices[0].red + vertices[1].red + vertices[2].red)/3.;
		this.rgb[Constant.GREEN] = (vertices[0].green + vertices[1].green + vertices[2].green)/3.;
		this.rgb[Constant.BLUE] = (vertices[0].blue + vertices[1].blue + vertices[2].blue)/3.;
		
		Point normal = this.getNormal();
		double coefficient = ray.dotProduct(normal) * (1/normal.getNorm());
		
		this.rgb[Constant.RED] *= Math.abs(coefficient);
		this.rgb[Constant.BLUE] *= Math.abs(coefficient);
		this.rgb[Constant.GREEN] *= Math.abs(coefficient);
	}
	
	public void setColour(double[] rgb) {
		this.rgb = rgb;
	}
	
	public double[] getRGB() {
		return this.rgb;
	}
	
	public Point getNormal() {
		Point vector1 = new Point();
		vector1.x = vertices[0].x - vertices[1].x;
		vector1.y = vertices[0].y - vertices[1].y;
		vector1.z = vertices[0].z - vertices[1].z;
		
		Point vector2 = new Point();
		vector2.x = vertices[0].x - vertices[2].x;
		vector2.y = vertices[0].y - vertices[2].y;
		vector2.z = vertices[0].z - vertices[2].z;
		
		return vector1.crossProduct(vector2);
		
	}
	
		
}
