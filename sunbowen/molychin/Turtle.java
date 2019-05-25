package fractal.sunbowen.molychin;

import java.awt.Graphics;

public class Turtle {
	double xpos;
	double ypos;
	double alpha;
	boolean penState;

	void penup() {
		penState = false;
	}

	void pendown() {
		penState = true;
	}

	void turn(double degree) {
		alpha = (alpha + degree)%360;
	}

	void move(int laenge, Graphics g) {
		double newXpos;
		double newYpos;
		newXpos = xpos + (laenge * Math.cos(Math.toRadians(alpha)));
		newYpos = ypos + (laenge * Math.sin(Math.toRadians(alpha)));
		if (penState) {
			g.drawLine((int) xpos, (int) ypos, (int) newXpos, (int) newYpos);
		}
		setXpos(newXpos);
		setYpos(newYpos);
	}
	
	public double getXpos() {
		return xpos;
	}

	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

	public double getYpos() {
		return ypos;
	}

	public void setYpos(double ypos) {
		this.ypos = ypos;
	}

	//����ת��Ƕ�
	public double getAlpha() {
		return alpha;
	}

	//��ȡת��Ƕ�
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
}
