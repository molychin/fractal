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
			for (int x = 0; x < Constants.IMAGE_X; x++) {
				pixels[index++]=colorer.getColors(y).getRGB();
			}
		}
	}
}
