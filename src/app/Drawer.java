package app;

import javax.swing.JPanel;

import object.Mesh;
import object.Point;
import object.Triangle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.geom.Path2D;
public class Drawer extends JPanel{
	private Mesh mesh;
	
	public Drawer(int index) {
		FileLoader fl = new FileLoader();
		this.mesh = fl.getMesh(index);
	}
	
	public Drawer(double[] ratios, int[] indexs) {
		FileLoader fl = new FileLoader();
		this.mesh = fl.getMesh(ratios,indexs);
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Mesh face_tranlated = this.mesh.translation2D();
		Mesh face_scaled = face_tranlated.Scaling(500., 500.);
		Mesh face_rotated = face_scaled.rotate180_2D().translation2D();
		
		
		ArrayList<Triangle> triangles = face_rotated.getMesh();
		
		
		for(Triangle triangle: triangles) {
			Path2D path = new Path2D.Double();
			Point[] vertices = triangle.getVertices();
			path.moveTo(vertices[0].x, vertices[0].y);
			path.lineTo(vertices[1].x, vertices[1].y);
			path.lineTo(vertices[2].x, vertices[2].y);
			path.closePath();
			g2.setColor(new Color((int)triangle.getRed(),(int)triangle.getGreen(),(int)triangle.getBlue()));
			g2.fill(path);
			g2.draw(path);
		}
		
		
	}
}
