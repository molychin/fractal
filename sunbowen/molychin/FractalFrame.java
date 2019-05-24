package fractal.sunbowen.molychin;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import fractal.sunbowen.molychin.type.FractalObject;

public class FractalFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	protected FractalObject fractalObject;
	DrawPanel drawPanel;
	StatePanel statePanel;
	ButtonPanel buttonPanel;
	MenuFrame menu;

	FractalFrame(){
		this.setTitle("Fractal-MolyChin Demo");
		this.setBounds(Constants.SCREEN_LEFT_TOP_X, Constants.SCREEN_LEFT_TOP_Y,Constants.SCREEN_WIDTH,
				Constants.SCREEN_HEIGHT);
		this.fractalObject=new FractalObject();	
		this.drawPanel=new DrawPanel(this);
		this.statePanel=new StatePanel(this);
		this.buttonPanel=new ButtonPanel(this,statePanel);
		this.menu=new MenuFrame();
		this.setMenuBar(menu);
		Container contentPane = this.getContentPane();
		contentPane.add(drawPanel,BorderLayout.CENTER);
		contentPane.add(buttonPanel,BorderLayout.SOUTH);
		contentPane.add(statePanel,BorderLayout.EAST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
}
