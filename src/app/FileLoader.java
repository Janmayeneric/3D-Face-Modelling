package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import object.Constant;
import object.Mesh;
import object.Point;

public class FileLoader {
	private ArrayList<double[]> average_shapes;
	private ArrayList<double[]> average_colours;
	private ArrayList<double[]> weight_shapes;
	private ArrayList<double[]> weight_colours;
	private ArrayList<double[]> mesh;
	private Point ray;
	
	private Mesh res;

	
	
	public FileLoader() {
		this.average_colours = new CSVReader(Constant.AVERAGECOLOURPATH).getList();
		this.average_shapes = new CSVReader(Constant.AVERAGESHAPEPATH).getList();
		this.weight_colours = new CSVReader(Constant.WEIGHTCOLOURPATH).getList();
		this.weight_shapes = new CSVReader(Constant.WEIGHTSHAPEPATH).getList();
		this.mesh = new CSVReader(Constant.MESHPATH).getList();
		this.ray = new Point(0,0,1.);
	}
	
	public Mesh getMesh(double[] ratios, int[] indexs) {
		ArrayList<double[]> res_shapes = new ArrayList<double[]>();
		ArrayList<double[]> res_colours = new ArrayList<double[]>();
		
		ArrayList<ArrayList<double[]>> shapes = new ArrayList<ArrayList<double[]>>();
		ArrayList<ArrayList<double[]>> colours = new ArrayList<ArrayList<double[]>>();
		
		for(int i = 0 ; i < 3; i++) {
			ArrayList<double[]> shape = indexs[i] > 0 && indexs[i] < 200 
					? this.weightCal(this.average_shapes, this.weight_shapes, new CSVReader(Constant.RESOURCESPREFIX + Constant.SHAPEPREFIX +  String.format("%03d",indexs[i]) + Constant.CSVSUFFIX).getList(), indexs[i])
							:this.average_shapes;
			ArrayList<double[]> colour= indexs[i] > 0 && indexs[i] < 200 
					? this.weightCal(this.average_colours, this.weight_colours, new CSVReader(Constant.RESOURCESPREFIX + Constant.COLOURPREFIX + String.format("%03d",indexs[i]) + Constant.CSVSUFFIX).getList(), indexs[i])
							:this.average_colours;
			
			shapes.add(shape);
			colours.add(colour);
		}
		
		for(int i = 0 ; i < shapes.get(0).size(); i ++) {
			double[] res_shape = new double[3];
			double[] res_colour = new double[3];
			for(int j = 0; j < 3 ; j++) {
				for(int k = 0; k < 3; k++) {
					res_shape[j] += shapes.get(k).get(i)[j] * ratios[k];
					res_colour[j] += colours.get(k).get(i)[j] * ratios[k];
				}
			}
			res_shapes.add(res_shape);
			res_colours.add(res_colour);
		}
		
		return new Mesh(this.mesh,res_shapes,res_colours,this.ray);
		
		
	}
	
	
	public Mesh getMesh(int index) {
		ArrayList<double[]> shapes= index > 0 && index < 200 
				? this.weightCal(this.average_shapes, this.weight_shapes, new CSVReader(Constant.RESOURCESPREFIX + Constant.SHAPEPREFIX +  String.format("%03d",index) + Constant.CSVSUFFIX).getList(),index)
						:this.average_shapes;
		ArrayList<double[]> colours= index > 0 && index < 200 
				? this.weightCal(this.average_colours, this.weight_colours, new CSVReader(Constant.RESOURCESPREFIX + Constant.COLOURPREFIX + String.format("%03d",index) + Constant.CSVSUFFIX).getList(), index)
						:this.average_colours;
		return new Mesh(this.mesh,shapes,colours,this.ray);
	}
	
	private ArrayList<double[]> weightCal(ArrayList<double[]> averages, ArrayList<double[]> weights, ArrayList<double[]> offsets,  int index) {
		ArrayList<double[]> res= new ArrayList<double[]>();
		
		for(int i= 0 ; i < averages.size(); i++) {
			double[] sum = new double[3];
			for(int j = 0; j < 3 ;j++) {
				sum[j] = averages.get(i)[j] + offsets.get(i)[j] * weights.get(index - 1)[0];
			}
			res.add(sum);
		}
		
		return res;
	}
	
	
	
	
}

class CSVReader {
	
	ArrayList<double[]> res;
	
	CSVReader (String url){
		
		this.res = new ArrayList<double[]>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(url))){
			String l = reader.readLine();
			while(l != null) {
				double[] values = new double[3];
				String[] values_str = l.split(",");
				for(int i = 0 ; i < values_str.length; i++) {
					values[i] = Double.valueOf(values_str[i]);
				}
				res.add(values);
				l = reader.readLine();
 			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
	public ArrayList<double[]> getList(){
		return this.res;
	}
}