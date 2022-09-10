package app;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel implements ItemListener, ActionListener{
	private int reference1;
	private int reference2;
	private int reference3;
	
	static JComboBox<String> c1, c2, c3;
	
	public Menu(){
		this.setName("Select the reference face");
		
		this.reference1 = 0;
		this.reference2 = 0;
		this.reference3 = 0;
		
		CardLayout cards = new CardLayout();
		this.setLayout(cards);
		
		String[] options = new String[200];
		for(int i = 0 ; i < 200; i++) {
			options[i] = String.valueOf(i);
		}
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		JButton b_c1 = new JButton("Preview");
		b_c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f1 = new JFrame("Face Example");
				f1.setSize(700,700);
				f1.setLocation(5,5);
				f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f1.add(new Drawer(reference1));
				
				f1.setVisible(true);
			}
		}
				);
		
		JButton b_c2 = new JButton("Preview");
		b_c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f1 = new JFrame("Face Example");
				f1.setSize(700,700);
				f1.setLocation(5,5);
				f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f1.add(new Drawer(reference2));
				
				f1.setVisible(true);
			}
		}
				);
		
		JButton b_c3 = new JButton("Preview");
		b_c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f1 = new JFrame("Face Example");
				f1.setSize(700,700);
				f1.setLocation(5,5);
				f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f1.add(new Drawer(reference3));
				
				f1.setVisible(true);
			}
		}
				);
		
		c1 = new JComboBox<String>(options);
		c2 = new JComboBox<String>(options);
		c3 = new JComboBox<String>(options);
		
		JPanel select1 = new JPanel(new FlowLayout());
		JPanel select2 = new JPanel(new FlowLayout());
		JPanel select3 = new JPanel(new FlowLayout());
		
		select1.add(new JLabel("Face 1"));
		select1.add(c1);
		select1.add(b_c1);
		
		select2.add(new JLabel("Face 2"));
		select2.add(c2);
		select2.add(b_c2);
		
		select3.add(new JLabel("Face 3"));
		select3.add(c3);
		select3.add(b_c3);
		
		main.add(select1);
		main.add(select2);
		main.add(select3);
		
		
		
		this.add("main",main);
		
	}
	
	public int getFirst() {
		return this.reference1;
	}
	
	public int getSecond() {
		return this.reference2;
	}
	
	public int getThird() {
		return this.reference3;
	}
	
	public int[] getReferences() {
		return new int[] {this.reference1,this.reference2,this.reference3};
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource() == c1) {
			this.reference1 = Integer.valueOf(c1.getSelectedItem().toString());
		}
		
		if(e.getSource() == c2) {
			this.reference2 = Integer.valueOf(c2.getSelectedItem().toString());
		}
		
		if(e.getSource() == c3) {
			this.reference3 = Integer.valueOf(c3.getSelectedItem().toString());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
