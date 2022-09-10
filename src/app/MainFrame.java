package app;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class MainFrame {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Face Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		CardLayout cards = new CardLayout();
		
		JPanel p = new JPanel();
		
		p.setLayout(cards);
		
		JPanel wait = new JPanel();
	
		JPanel menu = new JPanel();
		menu.setLayout(new BorderLayout());
		menu.add(new FacesRatio(), BorderLayout.CENTER);
		menu.add(new Menu(), BorderLayout.EAST);
		
		JButton b_menu = new JButton("return");
		b_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(p, "menu");
			}
		}
				);
		
		
		JButton b_display = new JButton("display");
		b_display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorderLayout layout = (BorderLayout)menu.getLayout();
				FacesRatio ratioMenu = (FacesRatio)layout.getLayoutComponent(BorderLayout.CENTER);
				Menu referenceMenu = (Menu)layout.getLayoutComponent(BorderLayout.EAST);
				double[] ratios = ratioMenu.getRatios();
				int[] references = referenceMenu.getReferences();
				Drawer drawer = new Drawer(ratios,references);
				
				JPanel dp = new JPanel(new BorderLayout());
				dp.add(drawer, BorderLayout.CENTER);
				dp.add(b_menu, BorderLayout.SOUTH);
				
				p.add("drawer",dp);
				cards.show(p, "drawer");
			}
		}
				);
		
		menu.add(b_display,BorderLayout.SOUTH);
		
		
		p.add("menu",menu);
		p.add("wait",wait);
		
		frame.add(p);
		frame.setVisible(true);

	}
}