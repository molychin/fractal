package fractal.sunbowen.molychin.type;

import fractal.sunbowen.molychin.Colorer;
import fractal.sunbowen.molychin.Constants;

public class FractalIFS extends FractalObject{

	public FractalIFS(){
		super(Constants.IFS01);
	}
	
	@Override
	public void calculatePixels() {
		this.initPixels();

		double u = 0.0, v = 0.0, newu, newv;
		int xPos = 300;
		int yPos = 400;
		long dots=Constants.IMAGE_X*Constants.IMAGE_Y;
		Colorer colorer=new Colorer();
		int scale = 200;
		
		double[][] maps = { { 0.05, 0, 0, 0.6, 0, 0, 0.1 },
				{ 0.05, 0, 0, -0.5, 0, 1.0, 0.1 },
				{ 0.46, 0.32, -0.386, 0.383, 0, 0.6, 0.2 },
				{ 0.47, -0.154, 0.171, 0.423, 0, 1.0, 0.2 },
				{ 0.43, 0.275, -0.26, 0.476, 0, 1.0, 0.2 },
				{ 0.421, -0.357, 0.354, 0.307, 0, 0.7, 0.2 } };

		for (long i = 1; i <= dots; i++) {
			double rnd = Math.random();
			double sum = 0.0;
			int index = 0;
			sum = maps[index][6];
			while ((rnd > sum) && (index < maps.length)) {
				sum += maps[index++][6];
			}
			if (index < maps.length) {
				newu = maps[index][0] * u + maps[index][1] * v + maps[index][4];
				newv = maps[index][2] * u + maps[index][3] * v + maps[index][5];
				u = newu;
				v = newv;
			}

			int pixelX=(int) (xPos + u * scale);
			int pixelY=(int) (yPos - v * scale);
			pixels[pixelY*Constants.IMAGE_X+pixelX]=colorer.getColors(pixelY).getRGB();
		}	
	}
}

// SierpinskiµæÆ¬
// int scale = 250;
// double[][] maps = { { 0.5, 0.0, 0.0, 0.5, 0.0, 0.0, 0.333 },
// { 0.5,0.0, 0.0, 0.5, 0.5, 0.0, 0.333 },
// { 0.5, 0.0, 0.0, 0.5, 0.25, 0.5,0.333 } };
// int scale = 50;
// double[][]
// maps={{0.787879,-0.424242,0.242424,0.859848,1.758647,1.408065,0.99},
// {-0.121212,0.257576,0.05303,0.05303,-6.721654,1.377236,0.005},
// {0.181818,-0.136364,0.090909,0.181818,6.086107,1.568035,0.005}};

// int scale = 250;
// double[][] maps = { { 0.382, 0, 0, 0.382, 0.3072, 0.619, 0.2 },
// { 0.382, 0, 0, 0.382, 0.6033, 0.4044, 0.2 },
// { 0.382, 0, 0, 0.382, 0.0139, 0.4044, 0.2 },
// { 0.382, 0, 0, 0.382, 0.1253, 0.0595, 0.2 },
// { 0.382, 0, 0, 0.382, 0.492, 0.0595, 0.2 } };

// int scale = 30;
// double[][]
// maps={{0.824074,0.281482,-0.212346,0.864198,-1.882290,-0.110607,0.8},
// {0.088272,0.520988,-0.463889,-0.377778,0.785360,8.095795,0.2}
// };

// int scale = 450;
// double[][] maps={
// {0,0,0,0.5,0,0,0.05},
// {0.42,-0.42,0.42,0.42,0,0.2,0.4},
// {0.42,0.42,0.42,-0.42,0,0.2,0.4},
// {0.1,0,0,0.4,0,0.2,0.15}
// };

// int scale = 100;
// double[][] maps={
// {-0.2,0.1,-0.19,-0.47,-0.12,0.3,0.25},
// {0.65,0,0,0.56,0.06,1.56,0.25},
// {0.41,0.46,-0.39,0.61,0.46,0.4,0.25},
// {0.52,-0.35,0.25,0.74,-0.48,0.38,0.25}
// };

// int scale = 200;
// double[][] maps={
// {0,0,0,0.25,0,-0.14,0.02},
// {0.85,0.02,-0.02,0.83,0,1,0.84},
// {0.09,-0.28,0.3,0.11,0,0.6,0.07},
// {-0.09,0.28,0.3,0.09,0,0.7,0.07}
// };


// int scale = 100;
// double[][] maps={
// {0,0,0,0.16,0,0,0.01},
// {0.85,0.04,-0.04,0.85,0,1.6,0.85},
// {0.2,-0.26,0.23,0.22,0,1.6,0.07},
// {-0.15,0.28,0.26,0.24,0,0.44,0.07}
// };
