package GUI;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.Box;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

public class MenuComp extends JPanel
{
	private JMenuBar menuBar;
	private JButton btn1, btn2, btn3, btn4;
	
	public MenuComp()
	{
	
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		menuBar = new JMenuBar();
		add(menuBar);

		
		btn1 = new JButton("Rozpocznij");
		btn1.setRolloverEnabled(false);
		btn1.setBorder(UIManager.getBorder("Button.border"));
		btn1.setBackground(SystemColor.control);
		btn1.setFocusable(false);
		btn1.setOpaque(false);
		btn1.setActionCommand("Rozpocznij");
		menuBar.add(btn1);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalGlue_2.setPreferredSize(new Dimension(2, 0));
		menuBar.add(horizontalGlue_2);
		
		btn2 = new JButton("Pauza/Wzn\u00F3w");
		btn2.setRolloverEnabled(false);
		btn2.setBackground(SystemColor.control);
		btn2.setFocusable(false);
		btn2.setOpaque(false);
		btn2.setActionCommand("Pauza/Wzn\u00F3w");
		menuBar.add(btn2);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setPreferredSize(new Dimension(2, 0));
		menuBar.add(horizontalGlue);
		
		btn3 = new JButton("Tabela wynik\u00F3w");
		btn3.setRolloverEnabled(false);
		btn3.setBackground(SystemColor.control);
		btn3.setFocusable(false);
		btn3.setOpaque(false);
		menuBar.add(btn3);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalGlue_1.setPreferredSize(new Dimension(2, 0));
		menuBar.add(horizontalGlue_1);
		
		btn4 = new JButton("Zako\u0144cz");
		btn4.setRolloverEnabled(false);
		btn4.setBackground(SystemColor.control);
		btn4.setFocusable(false);
		btn4.setOpaque(false);
		menuBar.add(btn4);
	}
	
	public void addActionListener(ActionListener listener)
	{
		btn1.addActionListener(listener);
		btn2.addActionListener(listener);
		btn3.addActionListener(listener);
		btn4.addActionListener(listener);
	}
	
}
