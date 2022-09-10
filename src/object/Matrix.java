package object;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	private ArrayList<ArrayList<Double>> rows;
	
	public Matrix() {
		this.rows = new ArrayList<ArrayList<Double>>();
	}
	
	public Matrix(Point p) {
		this.rows = new ArrayList<ArrayList<Double>>();
		this.rows.add(new ArrayList<Double>(List.of(p.x)));
		this.rows.add(new ArrayList<Double>(List.of(p.y)));
		this.rows.add(new ArrayList<Double>(List.of(p.z)));
	}
	
	public ArrayList<ArrayList<Double>> getMatrix(){
		return this.rows;
	}
	
	
	
	public void addRow(ArrayList<Double> row) {
		this.rows.add(row);
	}
	
	
}
