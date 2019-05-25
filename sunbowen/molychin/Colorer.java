/*
 * Created on 2005-1-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author RockCarry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fractal.sunbowen.molychin;

import java.awt.*;

public class Colorer {
	private int colorNumber;
	private Color[] colors;

	public Colorer() {
		colorNumber=Constants.IMAGE_Y;
		colors = new Color[colorNumber];
		init();
	}

	public Colorer(int num) {
		colorNumber = num;
		colors = new Color[num];
		init();
	}

	public void init() {
		// 设置颜色生成器
		int tempColor = (int) (colorNumber / 10);
		this.setChangingColor(0, tempColor, Color.green, Color.blue);
		this.setChangingColor(tempColor + 1,5 * tempColor, Color.blue,
				Color.red);
		this.setChangingColor(5 * tempColor + 1,
				colorNumber - 4, Color.red, Color.yellow);
		this.setChangingColor(colorNumber - 3,
				colorNumber-1, Color.yellow, Color.black);
	}

	public int getColorNumber() {
		return colorNumber;
	}

	public void setColorNumber(int n) {
		this.colorNumber = n;
	}

	public Color getColors(int i) {
		return colors[i];
	}

	public void setColors(int i, Color c) {
		this.colors[i] = c;
	}

	public Color[] getColors() {
		return colors;
	}

	// 基于RGB的颜色配置
	public void setChangingColor(int si, int ei, Color sc, Color ec) {
		if (si < 0 || si >= colorNumber) {
			si = 0;
		}else{}
		if (ei < 0 || ei >= colorNumber) {
			ei = colorNumber - 1;
		}else{}

		float rd = ((float) ec.getRed() - sc.getRed()) / (ei - si);
		float gd = ((float) ec.getGreen() - sc.getGreen()) / (ei - si);
		float bd = ((float) ec.getBlue() - sc.getBlue()) / (ei - si);

		float tred = sc.getRed();
		float tgreen = sc.getGreen();
		float tblue = sc.getBlue();

		for (int i = si; i <= ei; i++) {
			colors[i] = new Color((int) tred, (int) tgreen, (int) tblue);
			tred += rd;
			tgreen += gd;
			tblue += bd;
		}
	}

	/*
	 * //基于HSB的颜色配置 public void setChangingColor(int){
	 *  }
	 */
}
