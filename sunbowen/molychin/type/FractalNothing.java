package fractal.sunbowen.molychin.type;

//import fractal.chenkai.molychin.Colorer;
import fractal.sunbowen.molychin.Colorer;
import fractal.sunbowen.molychin.Constants;

public class FractalNothing extends FractalObject {

	@Override
	public void calculatePixels() {
		int index = 0;
		Colorer colorer=new Colorer(Constants.IMAGE_Y);
		
		for (int y = 0; y < Constants.IMAGE_Y; y++) {
			//int red = (y * 255) / (Constants.IMAGE_Y - 1);
			for (int x = 0; x < Constants.IMAGE_X; x++) {
				//int blue = (x * 255) / (Constants.IMAGE_X - 1);
				//pixels[index++] = (255 << 24) | (red << 16) | blue;
				pixels[index++]=colorer.getColors(y).getRGB();
			}
		}
	}

}
