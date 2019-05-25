package fractal.sunbowen.molychin;

import java.awt.event.*;
import javax.swing.*;

import fractal.sunbowen.molychin.type.FractalObject;

public class ButtonPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton buttonDraw,buttonExit;
	private FractalFrame frame;
	//private JFrame frame;
	private FractalObject fractalObject;
	private int fractalType;
	private StatePanel statePanel;
	
	public ButtonPanel(){}
	
	//public ButtonPanel(FractalObject fraObj,JFrame fra,StatePanel sp){
	public ButtonPanel(FractalFrame fra,StatePanel sp){
		this.frame=fra;
		this.fractalObject=frame.fractalObject;
		this.statePanel=sp;
		this.buttonDraw=new JButton("Draw");
		this.buttonExit=new JButton("Exit");

		this.add(buttonDraw);
		this.add(buttonExit);
		
		buttonDraw.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent event){
				fractalType=fractalObject.getFractalType();
				if(fractalType==Constants.KOCHTYPE02||fractalType==Constants.KOCHTYPE03
						||fractalType==Constants.KOCHTYPE04){
					fractalObject.setParameter(0,Double.valueOf(statePanel.getTextField(0).getText()).doubleValue());
				}else if (fractalType==Constants.IFS01){
					//nothing;
				}else{}
				frame.repaint();		
			}
		});
		
		buttonDraw.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				fractalType=fractalObject.getFractalType();
				if(fractalType==Constants.KOCHTYPE02||fractalType==Constants.KOCHTYPE03
						||fractalType==Constants.KOCHTYPE04){
					fractalObject.setParameter(0,Double.valueOf(statePanel.getTextField(0).getText()).doubleValue());
				}else{}
				frame.repaint();		
			}
		});
		
		buttonExit.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				System.exit(0);
			}
		});

		buttonExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				System.exit(0);
			}
		});
	}

	public FractalObject getFractalObject() {
		return fractalObject;
	}

	public void setFractalObject(FractalObject fractalObject) {
		this.fractalObject = fractalObject;
	}
	
	
 }
