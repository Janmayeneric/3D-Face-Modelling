package app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import object.Constant;
import object.Point;

public class FacesRatio extends JPanel implements MouseListener{
	
	private static final double POINT1_X = 250;
	private static final double POINT1_Y = 150;
	private static final double POINT2_X = 250 - 200 / Math.sqrt(3);
	private static final double POINT2_Y = 350;
	private static final double POINT3_X = 250 + 200 / Math.sqrt(3);
	private static final double POINT3_Y = 350;
	
	private double x;
	private double y;
	
	public FacesRatio() {
		this.x = 250;
		this.y = 250;
		this.addMouseListener(this);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		Path2D path = new Path2D.Double();
		path.moveTo(POINT1_X, POINT1_Y);
		path.lineTo(POINT2_X, POINT2_Y);
		path.lineTo(POINT3_X, POINT3_Y);
		path.closePath();
		g2.setColor(Color.RED);
		g2.fill(path);
		g2.draw(path);
		
		g2.drawString("Face 1", (int)POINT1_X, (int)this.POINT1_Y - 50);
		g2.drawString("Face 2", (int)POINT2_X - 50, (int)POINT2_Y);
		g2.drawString("Face 3", (int)POINT3_X + 50, (int)POINT3_Y);
		
		g2.setColor(Color.BLACK);
		g2.fillOval((int)(this.x - Constant.POINTSIZE/2), (int)(this.y - Constant.POINTSIZE/2), Constant.POINTSIZE, Constant.POINTSIZE);
	}
	
	public void mousePressed(MouseEvent e) {
		if(this.checkInTriangle(e.getX(), e.getY())) {
			this.x = e.getX();
			this.y = e.getY();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public double[] getRatios() {
		Point vector1 = new Point(POINT1_X - this.x, POINT1_Y - this.y, 0);
		Point vector2 = new Point(POINT2_X - this.x, POINT2_Y - this.y, 0);
		Point vector3 = new Point(POINT3_X - this.x, POINT3_Y - this.y, 0);
		
		double dis1 = vector1.getNorm();
		double dis2 = vector2.getNorm();
		double dis3 = vector3.getNorm();
		double total = dis1 + dis2 + dis3;
		
		double[] result = {dis1/total, dis2/total, dis3/total};
		return result;
	}
	
	private boolean checkInTriangle(double x, double y) {
		Point vector1 = new Point(POINT2_X - POINT1_X, POINT2_Y - POINT1_Y, 0);
		Point vector2 = new Point(POINT3_X - POINT1_X, POINT3_Y - POINT1_Y, 0);
		Point touched = new Point(x,y,0);
		Point point0 = new Point(this.POINT1_X,this.POINT2_X,0);
		
		double a = (this.det(touched, vector2) - this.det(point0, vector2))/ this.det(vector1, vector2);
		double b = - (this.det(touched,vector1)-this.det(point0, vector1))/ this.det(vector1, vector2);
		
		if(a >0 && b >0) {
			if(a + b < 1) {
				return true;
			}
		}
		return false;
	}
	
	private double det(Point p1, Point p2) {
		return p1.x * p2.y - p1.y * p2.x;
	}
}
