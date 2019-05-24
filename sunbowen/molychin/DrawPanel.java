package fractal.sunbowen.molychin;

import java.awt.*;
import java.awt.image.MemoryImageSource;

import javax.swing.*;

import fractal.sunbowen.molychin.type.FractalObject;

public class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static int iNumber = 0;
	FractalObject fractalObject;
	FractalFrame frame;
	Image image;

	public DrawPanel(FractalFrame frame) {
		this.frame = frame;
		this.setBackground(Constants.BACKGROUND_COLOR);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Constants.DRAW_COLOR);
		this.fractalObject=frame.fractalObject;
		int fractalType = fractalObject.getFractalType();
		if (fractalType == Constants.CANTORTYPE) {
			drawCantor(g, Constants.CANTOR_AX, Constants.CANTOR_AY,
					Constants.CANTOR_BX, Constants.CANTOR_BY);
		} else if (fractalType == Constants.KOCHTYPE01) {
			drawKoch(g, Constants.KOCH_AX, Constants.KOCH_AY,
					Constants.KOCH_BX, Constants.KOCH_BY); // 标准的Koch曲线
		} else if (fractalType == Constants.KOCHTYPE02) {
			drawKoch(g, Constants.KOCH_AX, Constants.KOCH_AY,
					Constants.KOCH_BX, Constants.KOCH_BY,
					(Double)fractalObject.getParameter(0)); // 变长的Koch曲线
		} else if (fractalType == Constants.KOCHTYPE03) {
			drawKoch(g, Constants.KOCH_AX, Constants.KOCH_AY,
					Constants.KOCH_BX, Constants.KOCH_BY,
					(Double)fractalObject.getParameter(0), true); // 有迭代深度颜色表示的Koch曲线
		} else if (fractalType == Constants.KOCHTYPE04) {
			// 增加对应不同迭代深度的颜色表示；
			// 增加分割线段的百分比例，scale1和scale2分别在（1,100）区间，并且scale1+scale2在(1,100)之间；
			drawKoch(g, Constants.KOCH_AX, Constants.KOCH_AY,
					Constants.KOCH_BX, Constants.KOCH_BY,
					Constants.KOCHTYPE04_SCALE1, Constants.KOCHTYPE04_SCALE2,
					Constants.KOCH_SCALE);
		} else if (fractalType == Constants.ARBORESENTTYPE) {
			drawArboresent(g);
		} else if (fractalType == Constants.SIERPINSKITYPE) {
			drawSierpinski(g);
		} else if (fractalType == Constants.LEAFTYPE) {
			// 绘制Leaf分形图；
			drawLeaf(g, Constants.LEAF_AX, Constants.LEAF_AY, Constants.LEAF_L,
					Constants.LEAF_ANGLE);
		} else if (fractalType == Constants.LSTYPE01) {
			drawLS01(g);
		} else if (fractalType == Constants.LSTYPE02) {
			drawLS02(g);
		} else if (fractalType == Constants.LSTYPE03) {
			drawLS03(g);
		} else if (fractalType == Constants.LSTYPE04) {
			drawLS04(g);
		} else if (fractalType == Constants.IFS01) {
			this.draw(g);
		} else if (fractalType == Constants.IFSJulia) {
			this.draw(g);
		} else if (fractalType == Constants.JULIATYPE01
				|| fractalType == Constants.JULIATYPE02
				|| fractalType == Constants.JULIATYPE03
				|| fractalType == Constants.JULIATYPE04
				|| fractalType == Constants.JULIATYPE05) {
			//System.out.println("fractalObject="+fractalObject);
			this.draw(g);
		} else if(fractalType==Constants.NOTHING){
			this.draw(g);
		}
	}

	private void draw(Graphics g){
		fractalObject.calculatePixels();
		image=this.createImage(new MemoryImageSource(Constants.IMAGE_X,Constants.IMAGE_Y,
				fractalObject.getPixels(),0,Constants.IMAGE_X));
		g.drawImage(image,5,5,null);
	}

	public void drawNothing(Graphics g) {
		g.drawString("Nothing is drawing", 400, 200);
	}

	public void drawCantor(Graphics g, double ax, double ay, double bx,
			double by) {
		if ((bx - ax) < Constants.CANTOR_MIN_STEP) {
			//以画线段的方式呈现Cantor集
			//g.drawLine((int) ax, (int) ay, (int) bx, (int) by);
			//以画短实心线段（矩形）的方式呈现Cantor集
			g.fillRect((int) ax,(int) ay,(int)(bx-ax),Constants.CANTOR_WIDTH);
		} else {
			double cx = 0, cy = 0, dx = 0, dy = 0;
			//g.drawLine((int) ax, (int) ay, (int) bx, (int) by);
			g.fillRect((int) ax,(int) ay,(int)(bx-ax),Constants.CANTOR_WIDTH);
			cx = ax + (bx - ax) / 3;
			cy = ay + Constants.CANTOR_HIGHT;
			dx = bx - (bx - ax) / 3;
			dy = by + Constants.CANTOR_HIGHT;
			ay = ay + Constants.CANTOR_HIGHT;
			by = by + Constants.CANTOR_HIGHT;

			drawCantor(g, ax, ay, cx, cy);
			drawCantor(g, dx, dy, bx, by);
		}
	}

	// 标准的Koch曲线
	public void drawKoch(Graphics g, double aX, double aY, double bX, double bY) {
		double cX = 0, cY = 0, dX = 0, dY = 0, eX = 0, eY = 0, l = 0, alpha = 0;
		if ((bX - aX) * (bX - aX) + (bY - aY) * (bY - aY) < Constants.KOCH_MIN_STEP) {
			g.drawLine((int) aX, Constants.COORDINATE_BUTTOM_Y - (int) aY,
					(int) bX, Constants.COORDINATE_BUTTOM_Y - (int) bY);
		} else {
			cX = aX + (bX - aX) / 3;
			cY = aY + (bY - aY) / 3;
			eX = bX - (bX - aX) / 3;
			eY = bY - (bY - aY) / 3;
			l = Math.sqrt((eX - cX) * (eX - cX) + (eY - cY) * (eY - cY));
			alpha = Math.atan((eY - cY) / (eX - cX));
			if ((alpha >= 0) && ((eX - cX) < 0) || (alpha <= 0)
					&& ((eX - cX) < 0)) {
				alpha = alpha + Math.PI;
			}

			dY = cY + Math.sin(alpha + Math.PI / 3) * l;
			dX = cX + Math.cos(alpha + Math.PI / 3) * l;

			drawKoch(g, aX, aY, cX, cY);
			drawKoch(g, eX, eY, bX, bY);
			drawKoch(g, cX, cY, dX, dY);
			drawKoch(g, dX, dY, eX, eY);
		}
	}

	// 可变长的Koch曲线
	public void drawKoch(Graphics g, double aX, double aY, double bX,
			double bY, double scaleL) {
		double cX = 0, cY = 0, dX = 0, dY = 0, eX = 0, eY = 0, l = 0, alpha = 0;

		if ((bX - aX) * (bX - aX) + (bY - aY) * (bY - aY) < Constants.KOCH_MIN_STEP
				* Constants.KOCH_MIN_STEP) {
			g.drawLine((int) aX, Constants.COORDINATE_BUTTOM_Y - (int) aY,
					(int) bX, Constants.COORDINATE_BUTTOM_Y - (int) bY);
		} else {
			cX = aX + (bX - aX) / 3;
			cY = aY + (bY - aY) / 3;
			eX = bX - (bX - aX) / 3;
			eY = bY - (bY - aY) / 3;
			l = scaleL
					* Math.sqrt((eX - cX) * (eX - cX) + (eY - cY) * (eY - cY));
			alpha = Math.atan((eY - cY) / (eX - cX));
			if ((alpha >= 0) && ((eX - cX) < 0) || (alpha <= 0)
					&& ((eX - cX) < 0)) {
				alpha = alpha + Math.PI;
			}

			dY = cY + Math.sin(alpha + Math.PI / 3) * l;
			dX = cX + Math.cos(alpha + Math.PI / 3) * l;

			drawKoch(g, aX, aY, cX, cY, scaleL);
			drawKoch(g, eX, eY, bX, bY, scaleL);
			drawKoch(g, cX, cY, dX, dY, scaleL);
			drawKoch(g, dX, dY, eX, eY, scaleL);
		}
	}

	// 增加对应不同迭代深度的颜色表示；
	public void drawKoch(Graphics g, double aX, double aY, double bX,
			double bY, double scaleL, boolean deepOpen) {
		double cX = 0, cY = 0, dX = 0, dY = 0, eX = 0, eY = 0, l = 0, alpha = 0;
		double deep = (bX - aX) * (bX - aX) + (bY - aY) * (bY - aY);

		if (deep < Constants.KOCH_MIN_STEP * Constants.KOCH_MIN_STEP) {
			++iNumber;
			Color color = new Color(iNumber % 255, Math
					.abs((256 - iNumber) % 255), Math
					.abs((iNumber - 150) % 255));
			g.setColor(color);
			g.drawLine((int) aX, Constants.COORDINATE_BUTTOM_Y - (int) aY,
					(int) bX, Constants.COORDINATE_BUTTOM_Y - (int) bY);
		} else {
			cX = aX + (bX - aX) / 3;
			cY = aY + (bY - aY) / 3;
			eX = bX - (bX - aX) / 3;
			eY = bY - (bY - aY) / 3;
			l = scaleL
					* Math.sqrt((eX - cX) * (eX - cX) + (eY - cY) * (eY - cY));
			alpha = Math.atan((eY - cY) / (eX - cX));
			if ((alpha >= 0) && ((eX - cX) < 0) || (alpha <= 0)
					&& ((eX - cX) < 0)) {
				alpha = alpha + Math.PI;
			}

			dY = cY + Math.sin(alpha + Math.PI / 3) * l;
			dX = cX + Math.cos(alpha + Math.PI / 3) * l;

			drawKoch(g, aX, aY, cX, cY, scaleL, true);
			drawKoch(g, eX, eY, bX, bY, scaleL, true);
			drawKoch(g, cX, cY, dX, dY, scaleL, true);
			drawKoch(g, dX, dY, eX, eY, scaleL, true);
		}
	}

	// 增加对应不同迭代深度的颜色表示；
	// 增加分割线段的百分比例，scale1和scale2分别在（1,100）区间，并且scale1+scale2在(1,100)之间；
	public void drawKoch(Graphics g, double aX, double aY, double bX,
			double bY, double scale1, double scale2, double scaleL) {
		double cX = 0, cY = 0, dX = 0, dY = 0, eX = 0, eY = 0, l = 0, alpha = 0;
		double deep = (bX - aX) * (bX - aX) + (bY - aY) * (bY - aY);

		if (deep < Constants.KOCH_MIN_STEP * Constants.KOCH_MIN_STEP) {
			++iNumber;
			Color color = new Color(iNumber % 255, Math
					.abs((256 - iNumber) % 255), Math
					.abs((iNumber - 150) % 255));
			g.setColor(color);
			g.drawLine((int) aX, Constants.COORDINATE_BUTTOM_Y - (int) aY,
					(int) bX, Constants.COORDINATE_BUTTOM_Y - (int) bY);
		} else {
			cX = aX + (bX - aX) * scale1 / 100;
			cY = aY + (bY - aY) * scale1 / 100;
			eX = bX - (bX - aX) * scale2 / 100;
			eY = bY - (bY - aY) * scale2 / 100;
			l = scaleL
					* Math.sqrt((eX - cX) * (eX - cX) + (eY - cY) * (eY - cY));
			alpha = Math.atan((eY - cY) / (eX - cX));
			if ((alpha >= 0) && ((eX - cX) < 0) || (alpha <= 0)
					&& ((eX - cX) < 0)) {
				alpha = alpha + Math.PI;
			}

			dY = cY + Math.sin(alpha + Math.PI / 3) * l;
			dX = cX + Math.cos(alpha + Math.PI / 3) * l;

			drawKoch(g, aX, aY, cX, cY, scale1, scale2, scaleL);
			drawKoch(g, eX, eY, bX, bY, scale1, scale2, scaleL);
			drawKoch(g, cX, cY, dX, dY, scale1, scale2, scaleL);
			drawKoch(g, dX, dY, eX, eY, scale1, scale2, scaleL);
		}
	}

	public void drawArboresent(Graphics g) {
		double prate = 1.5;
		double theta = Math.atan(Math.sqrt(4 / prate / prate - 1));
		double alfa = 55 * Math.PI / 180;
		double t = Math.PI - 4 * theta;
		double x = 200.0;
		double y = 300.0;
		double size = 300.0;
		int level = 9;

		arboresentAction(g, x, y, size, prate, alfa, theta, t, level);
		arboresentAction(g, x + size * prate * Math.cos(alfa - theta - t), y
				+ size * prate * Math.sin(alfa - theta - t), size, prate, alfa
				+ 2 * theta - t, -theta, -t, level);
	}

	private void arboresentAction(Graphics g, double x, double y, double size,
			double prate, double alfa, double theta, double t, int level) {
		double xe, ye, xa, ya, xb, yb, xc, yc, xd, yd, l;
		l = size / prate;
		xe = x;
		ye = y;
		xa = x + size * Math.cos(alfa);
		ya = y + size * Math.sin(alfa);
		xb = x + l * Math.cos(alfa - theta);
		yb = y + l * Math.sin(alfa - theta);
		xc = x + l * Math.cos(alfa - theta - t);
		yc = y + l * Math.sin(alfa - theta - t);
		xd = x + size * Math.cos(alfa - theta * 2 - t);
		yd = y + size * Math.sin(alfa - theta * 2 - t);
		if (level <= 1) {
			g.drawLine((int) xa, (int) ya, (int) xe, (int) ye);
			g.drawLine((int) xe, (int) ye, (int) xd, (int) yd);
			g.drawLine((int) xd, (int) yd, (int) xc, (int) yc);
			g.drawLine((int) xc, (int) yc, (int) xe, (int) ye);
			g.drawLine((int) xe, (int) ye, (int) xb, (int) yb);
			g.drawLine((int) xb, (int) yb, (int) xa, (int) ya);
		} else {
			arboresentAction(g, xb, yb, l, prate, alfa - theta + Math.PI,
					theta, t, level - 1);
			arboresentAction(g, xc, yc, l, prate, alfa - theta - t + Math.PI,
					-theta, -t, level - 1);
		}
	}

	public void drawSierpinski(Graphics g) {
		int level = 7;
		int xCoords[] = { 210, 590, 400 };
		int yCoords[] = { 390, 390, 10 };
		g.drawPolygon(xCoords, yCoords, 3);

		sierpinskiAction(g, new Point(210, 390), new Point(590, 390),
				new Point(400, 10), level);
	}

	private void sierpinskiAction(Graphics g, Point a, Point b, Point c,
			int level) {
		Point a1, b1, c1, a2, b2, c2, a3, b3, c3;

		if (level == 0)
			return;
		level -= 1;

		int xCoords[] = { c.x, (c.x + b.x) / 2, (a.x + c.x) / 2 };
		int yCoords[] = { b.y, (c.y + a.y) / 2, (c.y + a.y) / 2 };

		g.drawPolygon(xCoords, yCoords, 3);

		// 3个递归三角形
		a1 = a;
		b1 = new Point(c.x, b.y);
		c1 = new Point((a.x + c.x) / 2, (c.y + a.y) / 2);
		sierpinskiAction(g, a1, b1, c1, level);

		a2 = new Point(c.x, b.y);
		b2 = b;
		c2 = new Point((c.x + b.x) / 2, (c.y + a.y) / 2);
		sierpinskiAction(g, a2, b2, c2, level);

		a3 = new Point((a.x + c.x) / 2, (c.y + a.y) / 2);
		b3 = new Point((c.x + b.x) / 2, (c.y + a.y) / 2);
		c3 = c;
		sierpinskiAction(g, a3, b3, c3, level);
	}

	// LEAF分形图
	public void drawLeaf(Graphics g, double x, double y, double longer,
			double angle) {
		double x1, x2, x1L, x2L, x2R, x1R, y1, y2, y1L, y2L, y2R, y1R;
		int leafB = (Integer) fractalObject.getParameter(0);
		int leafC = (Integer) fractalObject.getParameter(1);
		double s2 = (Double) fractalObject.getParameter(2);
		double s3 = (Double) fractalObject.getParameter(3);
		double spi = Math.PI / 180;
		
		//System.out.println("fractalObject2="+fractalObject);
		//System.out.println("fractalObjectType="+fractalObject.getFractalType());

		if (longer > Constants.LEAF_MIN_STEP) {
			x2 = x + longer * Math.cos(angle * spi);
			y2 = y + longer * Math.sin(angle * spi);
			x2R = x2 + longer / s2 * Math.cos((angle + leafB) * spi);
			y2R = y2 + longer / s2 * Math.sin((angle + leafB) * spi);
			x2L = x2 + longer / s2 * Math.cos((angle - leafB) * spi);
			y2L = y2 + longer / s2 * Math.sin((angle - leafB) * spi);

			x1 = x + longer / s2 * Math.cos(angle * spi);
			y1 = y + longer / s2 * Math.sin(angle * spi);
			x1L = x1 + longer / s2 * Math.cos((angle - leafB) * spi);
			y1L = y1 + longer / s2 * Math.sin((angle - leafB) * spi);
			x1R = x1 + longer / s2 * Math.cos((angle + leafB) * spi);
			y1R = y1 + longer / s2 * Math.sin((angle + leafB) * spi);

			int temp = (int) (longer - Constants.LEAF_MIN_STEP) * 255 * 30
					/ (Constants.LEAF_L - Constants.LEAF_MIN_STEP);
			// int red=(int)(longer*20)%255;
			int red = 20;
			int green = temp % 255;
			// int blue=Math.abs((int)(255-longer*50)%255);
			int blue = 20;
			g.setColor(new Color(red, green, blue));

			g.drawLine((int) x, (int) y, (int) x2, (int) y2);
			g.drawLine((int) x2, (int) y2, (int) x2R, (int) y2R);
			g.drawLine((int) x2, (int) y2, (int) x2L, (int) y2L);
			g.drawLine((int) x1, (int) y1, (int) x1L, (int) y1L);
			g.drawLine((int) x1, (int) y1, (int) x1R, (int) y1R);

			drawLeaf(g, x2, y2, longer / s3, angle + leafC);
			drawLeaf(g, x2R, y2R, longer / s2, angle + leafB);
			drawLeaf(g, x2L, y2L, longer / s2, angle - leafB);
			drawLeaf(g, x1L, y1L, longer / s2, angle - leafB);
			drawLeaf(g, x1R, y1R, longer / s2, angle + leafB);
		}
	}

	public void drawLS01(Graphics g) {
		int depth = (Integer) fractalObject.getParameter(4);
		StringBuffer seed = new StringBuffer((String) fractalObject
				.getParameter(0));
		String regulation = (String) fractalObject.getParameter(1);
		Turtle turtle = new Turtle();
		LLStack stack = new LLStack();

		turtle.setXpos(Constants.LS_AX);
		turtle.setYpos(Constants.LS_AY);
		turtle.setAlpha(Constants.LS_PRI_ANGLE);
		turtle.pendown();

		for (int z = 0; z < depth; z++) {
			for (int i = 0; i < seed.length(); i++) {
				if (seed.charAt(i) == 'F') {
					seed = seed.deleteCharAt(i);
					seed = seed.insert(i, regulation);
					i = i + regulation.length();
				}
			}
		}

		for (int z = 0; z < seed.length(); z++) {
			lsAction01(g, seed.charAt(z), turtle, stack, null);
		}
	}

	public void drawLS02(Graphics g) {
		int depth = (Integer) fractalObject.getParameter(5);
		StringBuffer seed = new StringBuffer((String) fractalObject
				.getParameter(0));
		String regulation1 = (String) fractalObject.getParameter(1);
		String regulation2 = (String) fractalObject.getParameter(2);
		Turtle turtle = new Turtle();
		LLStack stack = new LLStack();

		turtle.setXpos(Constants.LS_AX);
		turtle.setYpos(Constants.LS_AY);
		turtle.setAlpha(Constants.LS_PRI_ANGLE);
		turtle.pendown();

		for (int z = 0; z < depth; z++) {
			for (int i = 0; i < seed.length(); i++) {
				if (seed.charAt(i) == 'X') {
					seed = seed.deleteCharAt(i);
					seed = seed.insert(i, regulation1);
					i = i + regulation1.length();
				}
			}
			for (int i = 0; i < seed.length(); i++) {
				if (seed.charAt(i) == 'Y') {
					seed = seed.deleteCharAt(i);
					seed = seed.insert(i, regulation2);
					i = i + regulation2.length();
				}
			}
		}

		for (int z = 0; z < seed.length(); z++) {
			lsAction02(g, seed.charAt(z), turtle, stack, null);
		}
	}

	public void drawLS03(Graphics g) {
		int depth = (Integer) fractalObject.getParameter(5);
		StringBuffer seed = new StringBuffer((String) fractalObject
				.getParameter(0));
		String regulation1 = (String) fractalObject.getParameter(1);
		String regulation2 = (String) fractalObject.getParameter(2);
		Turtle turtle = new Turtle();
		LLStack stack = new LLStack();

		turtle.setXpos(Constants.LS_AX);
		turtle.setYpos(Constants.LS_AY);
		turtle.setAlpha(Constants.LS_PRI_ANGLE);
		turtle.pendown();

		for (int z = 0; z < depth; z++) {
			for (int i = 0; i < seed.length(); i++) {
				if (seed.charAt(i) == 'L') {
					seed = seed.deleteCharAt(i);
					seed = seed.insert(i, regulation1);
					i = i + regulation1.length();
				}
			}
			for (int i = 0; i < seed.length(); i++) {
				if (seed.charAt(i) == 'R') {
					seed = seed.deleteCharAt(i);
					seed = seed.insert(i, regulation2);
					i = i + regulation2.length();
				}
			}
		}

		for (int z = 0; z < seed.length(); z++) {
			lsAction03(g, seed.charAt(z), turtle, stack, null);
		}
	}

	private void drawLS04(Graphics g) {
		int depth = (Integer) fractalObject.getParameter(6);
		StringBuffer seed = new StringBuffer((String) fractalObject
				.getParameter(0));
		// String regulation1 = (String) fractalObject.getParameter(1);
		// String regulation2 = (String) fractalObject.getParameter(2);
		// String regulation3 = (String) fractalObject.getParameter(3);
		// String regulation=null;
		Turtle turtle = new Turtle();
		LLStack stack = new LLStack();

		turtle.setXpos(Constants.LS_AX);
		turtle.setYpos(Constants.LS_AY);
		turtle.setAlpha(Constants.LS_PRI_ANGLE);
		turtle.pendown();

		// System.out.println("seed1="+seed);
		for (int z = 0; z < depth; z++) {
			for (int i = 0; i < seed.length(); i++) {
				if (seed.charAt(i) == 'F') {
					seed = seed.deleteCharAt(i); // 删除一个字符，返回该字符的函数
					String regul = selectRegulation(Math.random());
					seed = seed.insert(i, regul);
					i = i + regul.length();
				}
			}
		}
		// System.out.println("seed2="+seed);
		// System.out.println("p4="+fractalObject.getParameter(4));
		// System.out.println("p5="+fractalObject.getParameter(5));

		for (int z = 0; z < seed.length(); z++) {
			lsAction04(g, seed.charAt(z), turtle, stack, null);
		}
	}

	private String selectRegulation(double random) {
		String regulation = null;
		if (random <= 0.3) {
			regulation = (String) fractalObject.getParameter(1);
		} else if (random > 0.3 && random <= 0.6) {
			regulation = (String) fractalObject.getParameter(2);
		} else {
			regulation = (String) fractalObject.getParameter(3);
		}
		return regulation;
	}

	private void lsAction01(Graphics g, char ch, Turtle turtle, LLStack stack,
			Note note) {
		switch (ch) {
		case 'F':
			turtle.pendown();
			turtle.move((Integer) fractalObject.getParameter(3), g);
			break;
		case 'f':
			turtle.penup();
			turtle.move((Integer) fractalObject.getParameter(3), g);
			break;
		case '+':
			turtle.turn((Double) fractalObject.getParameter(2));
			break;
		case '-':
			turtle.turn(-(Double) fractalObject.getParameter(2));
			break;
		case '[':
			// push
			stack.push(new Note(turtle.getXpos(), turtle.getYpos(), turtle
					.getAlpha()));
			break;
		case ']':
			// pop
			note = (Note) stack.pop();
			turtle.setXpos(note.getX());
			turtle.setYpos(note.getY());
			turtle.setAlpha(note.getAngle());
			break;
		default: {
		}
		}
	}

	private void lsAction02(Graphics g, char ch, Turtle turtle, LLStack stack,
			Note note) {
		switch (ch) {
		case 'F':
			turtle.pendown();
			turtle.move((Integer) fractalObject.getParameter(4), g);
			break;
		case 'f':
			turtle.penup();
			turtle.move((Integer) fractalObject.getParameter(4), g);
			break;
		case '+':
			turtle.turn((Double) fractalObject.getParameter(3));
			break;
		case '-':
			turtle.turn(-(Double) fractalObject.getParameter(3));
			break;
		case '[':
			// push
			stack.push(new Note(turtle.getXpos(), turtle.getYpos(), turtle
					.getAlpha()));
			break;
		case ']':
			// pop
			note = (Note) stack.pop();
			turtle.setXpos(note.getX());
			turtle.setYpos(note.getY());
			turtle.setAlpha(note.getAngle());
			break;
		default: {
		}
		}
	}

	private void lsAction03(Graphics g, char ch, Turtle turtle, LLStack stack,
			Note note) {
		switch (ch) {
		case 'L':
			turtle.pendown();
			turtle.move((Integer) fractalObject.getParameter(4), g);
			break;
		case 'R':
			turtle.pendown();
			turtle.move((Integer) fractalObject.getParameter(4), g);
			break;
		case 'f':
			turtle.penup();
			turtle.move((Integer) fractalObject.getParameter(4), g);
			break;
		case '+':
			turtle.turn((Double) fractalObject.getParameter(3));
			break;
		case '-':
			turtle.turn(-(Double) fractalObject.getParameter(3));
			break;
		case '[':
			// push
			stack.push(new Note(turtle.getXpos(), turtle.getYpos(), turtle
					.getAlpha()));
			break;
		case ']':
			// pop
			note = (Note) stack.pop();
			turtle.setXpos(note.getX());
			turtle.setYpos(note.getY());
			turtle.setAlpha(note.getAngle());
			break;
		default: {
		}
		}
	}

	private void lsAction04(Graphics g, char ch, Turtle turtle, LLStack stack,
			Note note) {
		switch (ch) {
		case 'F':
			turtle.pendown();
			turtle.move((Integer) fractalObject.getParameter(5), g);
			break;
		case 'f':
			turtle.penup();
			turtle.move((Integer) fractalObject.getParameter(5), g);
			break;
		case '+':
			turtle.turn((Double) fractalObject.getParameter(4));
			break;
		case '-':
			turtle.turn(-(Double) fractalObject.getParameter(4));
			break;
		case '[':
			// push
			stack.push(new Note(turtle.getXpos(), turtle.getYpos(), turtle
					.getAlpha()));
			break;
		case ']':
			// pop
			note = (Note) stack.pop();
			turtle.setXpos(note.getX());
			turtle.setYpos(note.getY());
			turtle.setAlpha(note.getAngle());
			break;
		default: {
		}
		}
	}

}
