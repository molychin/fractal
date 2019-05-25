package fractal.sunbowen.molychin.type;

import fractal.sunbowen.molychin.Colorer;
import fractal.sunbowen.molychin.Constants;

public class FractalJulia extends FractalObject{

	@Override
	public void calculatePixels() {
		double reZ, imZ, ze0;
		Colorer colorer=new Colorer(Constants.JULIA_DEEPTH);

		ze0 = (Double) getParameter(2);
		imZ = Constants.JULIA_IMAGE_Z;
		for (int y = 0; y < Constants.JULIA_SCREEN_HEIGHT; y++) {
			reZ = Constants.JULIA_REAL_Z;
			for (int x = 0; x < Constants.JULIA_SCREEN_WIDTH; x++) {
				int deepth = juliaZ(reZ, imZ);
				if (deepth == Constants.JULIA_DEEPTH) {
					setPixel(y*Constants.JULIA_SCREEN_WIDTH+x, Constants.JULIA_COLOR.getRGB());
				} else {
					setPixel(y*Constants.JULIA_SCREEN_WIDTH+x,colorer.getColors(deepth).getRGB());
				}
				reZ = reZ + ze0;
			}
			imZ = imZ + ze0;
		}
	}

	private int juliaZ(double x0, double y0) {
		double xk = 0.0, yk = 0.0;
		double cp = (Double) getParameter(0);
		double cq = (Double) getParameter(1);

		for (int i = 0; i < Constants.JULIA_DEEPTH; i++) {
			if (fractalType == Constants.JULIATYPE01) {
				// Zn+1 = Zn^2 + C, C = const
				xk = x0 * x0 - y0 * y0 + cp;
				yk = 2 * x0 * y0 + cq;
			}else if (fractalType == Constants.JULIATYPE02){
				// Zn+1 = Zn^2 + C, C = const,加入dltaC的扰动；
				double dltaCp=Math.random()/100;
				double dltaCq=Math.random()/100;
				xk = x0 * x0 - y0 * y0 + cp+dltaCp;
				yk = 2 * x0 * y0 + cq+dltaCq;				
			}else if (fractalType == Constants.JULIATYPE03) {
				// Zn+1=Zn^3+C;
				xk = x0 * x0 * x0 - 3 * x0 * y0 * y0 + cp;
				yk = 3 * x0 * x0 * y0 - y0 * y0 * y0 + cq;
			} else if (fractalType == Constants.JULIATYPE04) {
				// Zn+1=Zn^4+C;
				xk = x0 * x0 * x0 * x0 - 6 * x0 * x0 * y0 * y0 + y0 * y0 * y0
						* y0 + cp;
				yk = 3 * x0 * x0 * x0 * y0 + x0 * x0 * x0 * y0 - 4 * x0 * y0
						* y0 * y0 + cq;
			} else if (fractalType == Constants.JULIATYPE05) {
				// Zn+1=Zn^5+C;
				xk = x0 * x0 * x0 * x0 * x0 - 10 * x0 * x0 * x0 * y0 * y0
						+ 5 * x0 * y0 * y0 * y0 * y0 + cp;
				yk = y0 * y0 * y0 * y0 * y0 - 10 * x0 * x0 * y0 * y0 * y0
						+ 5 * x0 * x0 * x0 * x0 * y0 + cq;
			}
			if (xk * xk + yk * yk > Constants.JULIA_OUTOF) {
				return i;
			} else {
				x0 = xk;
				y0 = yk;
			}
		}
		return Constants.JULIA_DEEPTH;
	}
	
}
