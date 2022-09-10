package object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Mesh {
	
	private ArrayList<Triangle> triangles;
	private double maxX;
	private double minX;
	private double maxY;
	private double minY;
	private double maxZ;
	private double minZ;
	
	public Mesh() {
		this.triangles = new ArrayList<Triangle>();
		this.maxX = Constant.LOWEST;
		this.minX = Constant.HIGHEST;
		this.maxY = Constant.LOWEST;
		this.minY = Constant.HIGHEST;
		this.maxZ = Constant.LOWEST;
		this.minZ = Constant.HIGHEST;		
	}
	
	public Mesh(ArrayList<double[]> mesh, ArrayList<double[]> shapes, ArrayList<double[]> colours, Point ray) {
		this.triangles = new ArrayList<Triangle>();
		this.maxX = shapes.get(0)[Constant.X];
		this.minX = shapes.get(0)[Constant.X];
		this.maxY = shapes.get(0)[Constant.Y]; 
		this.minY = shapes.get(0)[Constant.Y];
		this.maxZ = shapes.get(0)[Constant.Z]; 
		this.minZ = shapes.get(0)[Constant.Z];
		
		for(int i = 0 ; i < mesh.size(); i++) {
			double[] ts = mesh.get(i);
			Point[] vertices = new Point[3];
			for(int j = 0 ; j < ts.length; j ++) {
				int coordinateindex = (int)ts[j] - 1;
				vertices[j] = new Point(shapes.get(coordinateindex),colours.get(coordinateindex));
				this.checkBoundaries(vertices[j].x, vertices[j].y, vertices[j].z);
			}
			Triangle t = new Triangle(vertices,ray);
			this.triangles.add(t);
		}
		
		
		
		this.painterSort();
	}
	
	public Mesh(ArrayList<Triangle> ts, double maxX, double minX, double maxY, double minY, double maxZ, double minZ) {
		this.triangles = ts;
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
		this.maxZ = maxZ;
		this.minZ = minZ;
	}
	
	public Mesh Scaling(double width, double height) {
		if(this.maxX < width && this.maxY < height) {
			return this;
		}
		
		double xrate = this.maxX / width;
		double yrate = this.maxY / height;
		
		double scale = xrate>yrate ? xrate : yrate;
		scale = 1/scale;
		
		double[][] t = new double[3][3];
		t[0][0] = scale;
		t[1][1] = scale;
		t[2][2] = Constant.HOMOGENOUSCOORDINATE;
		
		ArrayList<Triangle> res = new ArrayList<Triangle>();
		for(Triangle tri:this.triangles) {
			res.add(tri.tranform2d(t));
		}
		
		Mesh ans = new Mesh(res, this.maxX*scale, this.minX*scale, this.maxY*scale, this.minY*scale, this.maxZ,this.minZ);
		return ans;
	}
	
	public Mesh rotate180_2D() {
		 double[][] trans = new double[3][3];
		 
		 trans[0][0] = -1;
		 trans[1][1] = -1;
		 trans[2][2] = 1;
		 
		 ArrayList<Triangle> res = new ArrayList<Triangle>();
		 for(Triangle tri:this.triangles) {
				res.add(tri.tranform2d(trans));
		}
		 
		 return new Mesh(res,-1*this.minX, -1*this.maxX, -1*this.minY, -1*this.maxY,this.maxZ,this.minZ);
	}
	
	public Mesh translation2D() {
		if(this.minX > 0 && this.minY >0) {
			return this;
		}
		
		
		double[][] trans = new double[3][3];
		trans[0][0] = 1.;
		trans[1][1] = 1.;
		trans[2][2] = 1.;
		
		double maxX_new = this.maxX;
		double minX_new = this.minX;
		double maxY_new = this.maxY;
		double minY_new = this.minY;
		
		if(this.minX < 0) {
			trans[0][2] = Math.abs(this.minX);
			minX_new = 0;
			maxX_new += Math.abs(this.minX);
		}
		
		if(this.minY < 0) {
			trans[1][2] = Math.abs(this.minY);
			minY_new = 0;
			maxY_new += Math.abs(this.minY);;
		}
		
		ArrayList<Triangle> ts = new ArrayList<Triangle>();
		for(Triangle t:this.triangles) {
			ts.add(t.tranform2d(trans));
		}
		
		return new Mesh(ts, maxX_new, minX_new, maxY_new, minY_new, this.maxZ, this.minZ);
	}
	
	public ArrayList<Triangle> getMesh() {
		return this.triangles;
	}
	
	private void painterSort() {
		TreeMap<Double,ArrayList<Triangle>> sorted = new TreeMap<Double,ArrayList<Triangle>>();
		for(Triangle t: triangles) {
			if(sorted.containsKey(t.getCentroid().z)) {
				ArrayList<Triangle> ts = sorted.get(t.getCentroid().z);
				ts.add(t);
				sorted.put(t.getCentroid().z, ts);
			}else {
				ArrayList<Triangle> ts = new ArrayList<Triangle>();
				ts.add(t);
				sorted.put(t.getCentroid().z, ts);
			}
		}
		ArrayList<Triangle> res = new ArrayList<Triangle>();
		
		for(ArrayList<Triangle> ts: sorted.values()) {
			for(Triangle t: ts) {
				res.add(t);
			}
		}
		
		this.triangles = res;
	}
	
	
	
	private void checkBoundaries(double x, double y, double z) {
		if(this.maxX < x) {
			this.maxX = x;
		}
		if(this.minX > x) {
			this.minX = x;
		}
		if(this.maxY < y) {
			this.maxY = y;
		}
		if(this.minY > y) {
			this.minY = y;
		}
		if(this.maxZ < z) {
			this.maxZ = z;
		}
		if(this.minZ > z) {
			this.minZ = z;
		}
	}
	
	
	
}
