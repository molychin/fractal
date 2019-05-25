package fractal.sunbowen.molychin.type;

import java.util.ArrayList;

import fractal.sunbowen.molychin.CaculatePixelable;
import fractal.sunbowen.molychin.Constants;

public class FractalObject implements CaculatePixelable{
	protected int imageX,imageY;	//绘制图像的宽度和高度；
	protected int[] pixels;			//保存绘制图像的数组；
	ArrayList<Object> parameters;
	int fractalType;

	public FractalObject(){
		this(Constants.NOTHING);
	}
	
	public FractalObject(int fractalType){
		this.imageX=Constants.IMAGE_X;
		this.imageY=Constants.IMAGE_Y;
		this.pixels=new int[imageX*imageY];
		this.initPixels();
		this.fractalType=fractalType;
		parameters=new ArrayList<Object>();
	}
	
	public int[] getPixels(){
		return pixels;
	}
	
	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}	
	
	public int getPixel(int index){
		return this.pixels[index];
	}
	
	public void setPixel(int index,int rgb){
		this.pixels[index]=rgb;
	}
	
	public void calculatePixels(){};
	
	public void initPixels(){
		for(int i=0;i<pixels.length;i++){
			pixels[i]=Constants.BACKGROUND_COLOR.getRGB();
		}
	}
	
	public int getImageX() {
		return imageX;
	}

	public void setImageX(int imageX) {
		this.imageX = imageX;
	}

	public int getImageY() {
		return imageY;
	}

	public void setImageY(int imageY) {
		this.imageY = imageY;
	}

	public int getFractalType() {
		return fractalType;
	}

	public void setFractalType(int fractalType) {
		this.fractalType = fractalType;
	}
	
	public void addParameter(Object parameter){
		this.parameters.add(parameter);
	}
	
	public Object getParameter(int index){
		if (index>=0 && index<this.parameters.size()){
			return this.parameters.get(index);	
		}else{
			return null;		
		}
	}
	
	public void setParameter(int index,Object parameter){
		if (index>=0 && index<this.parameters.size()){
			this.parameters.set(index,parameter);
		}else{
			System.out.println("Order out of limit.");
		}
	}
	
	public void clearParameters(){
		this.parameters.clear();
	}
	
	public int getParametersSize(){
		return this.parameters.size();
	}
	
	// 生成迭代数
	//public ComplexNumber createIterateNumber(int x, int y) {
	//	return new ComplexNumber(xPositionStart + x / scale, yPositionStart + y
	//			/ scale);
	//}
}
