package fractal.sunbowen.molychin.type;

import java.awt.Color;
import fractal.sunbowen.molychin.Constants;

public class FractalIFSJulia extends FractalObject {
	public static int count=0;

	@Override
	public void calculatePixels() {
		drawIFSJulia(Constants.IFS_X0, Constants.IFS_Y0);
	}

	private void drawIFSJulia(double x, double y) {

		int depth = (Integer) getParameter(2);
		byte[][] maps = (byte[][]) getParameter(3);
		double r1, newx, newy;
		int sgn;

		int xtmp = (int) ((x - Constants.IFS_CX_MIN)
				/ (Constants.IFS_CX_MAX - Constants.IFS_CX_MIN) * Constants.IFS_W);
		int ytmp = (int) ((y - Constants.IFS_CY_MIN)
				/ (Constants.IFS_CY_MAX - Constants.IFS_CY_MIN) * Constants.IFS_H);
		if (++count > Constants.IFS_COUNT_MAX
				|| xtmp >= Constants.IFS_W || xtmp < 0
				|| ytmp > Constants.IFS_H || ytmp < 0) {
			return;
		} else if (++maps[xtmp][ytmp] >= depth) {
			pixels[ytmp * Constants.IMAGE_X + xtmp] = Color.red.getRGB();
			return;
		} else {
			pixels[ytmp * Constants.IMAGE_X + xtmp] = Color.blue.getRGB();
			if (maps[xtmp][ytmp] < depth) {
				x -= (Double) getParameter(0);
				y -= (Double) getParameter(1);
				r1 = Math.sqrt(x * x + y * y);
				sgn = (y > 0) ? 1 : (y < 0) ? -1 : 0;
				newx = Math.sqrt((r1 + x) / 2);
				newy = Math.sqrt((r1 - x) / 2) * sgn;

				drawIFSJulia(newx, newy);
				drawIFSJulia(-newx, -newy);
			}
		}
	}
}
