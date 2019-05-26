package fractal.sunbowen.molychin;

import java.awt.Color;

public final class Constants {
	/*分形类型 fractalType
	 *NOTHING=0/CANTORTYPE=1/KOCHTYPE01=2/KOCHTYPE02=3/KOCHTYPE03=4/KOCHTYPE04=5/
	 *ARBORESENTTYPE=6/SIERPINSKITYPE=7/LEAFTYPE=8/LSTYPE01=9/LSTYPE02=10/LSTYPE03=11/
	 *LSTYPE04=12/IFSJulia=13/IFS01=14/JULIATYPE01=15/JULIATYPE02=16/
	 *JULIATYPE03=17/JULIATYPE04=18/JULIATYPE05=19/LSSCALE01=20/
	 */
	
	public static final boolean SAVE_PIC=true;		//true:保存图片
	//截屏的范围
	public static final int SAVE_LT_AX=100;
	public static final int	SAVE_LT_AY=100;
	public static final int SAVE_RB_BX=1000;
	public static final int SAVE_RB_BY=900;
	//-----------------------------------------
	public static final int SCREEN_LEFT_TOP_X=50;
	public static final int SCREEN_LEFT_TOP_Y=50;
	public static final int SCREEN_WIDTH=1200;
	public static final int SCREEN_HEIGHT=1000;
	public static final int IMAGE_X=800;
	public static final int IMAGE_Y=650;
	public static final Color DRAW_COLOR=Color.black;
	public static final Color BACKGROUND_COLOR=Color.white;		//Color.gray
	public static final int COORDINATE_BUTTOM_Y=650;
	//----------------------------	
	public static final int NOTHING=0;
	public static final int CANTORTYPE=1;
	public static final int CANTOR_MIN_STEP=1;
	
	public static final int CANTOR_AX=100;
	public static final int CANTOR_AY=100;
	public static final int CANTOR_BX=800;
	public static final int CANTOR_BY=100;
	public static final int CANTOR_WIDTH=20;
	public static final int CANTOR_HIGHT=80;
	//----------------------------		
	public static final int KOCHTYPE01=2;
	public static final int KOCHTYPE02=3;	
	public static final int KOCHTYPE03=4;	
	public static final int KOCHTYPE04=5;
	public static final int KOCH_AX=100;
	public static final int KOCH_AY=100;
	public static final int KOCH_BX=800;
	public static final int KOCH_BY=100;
	public static final int KOCH_MIN_STEP=10;
	public static final double KOCH_SCALE=1.0;
	public static final double KOCHTYPE02_MAX_SCALE=2.6;
	public static final double KOCHTYPE04_SCALE1=20;
	public static final double KOCHTYPE04_SCALE2=30;
	//------------------------------
	public static final int ARBORESENTTYPE=6;
	//----------------------------	
	public static final int SIERPINSKITYPE=7;
	//----------------------------	
	public static final int LEAFTYPE=8;
	public static final int LEAF_AX=400;
	public static final int LEAF_AY=500;
	public static final int LEAF_L=100;
	public static final int LEAF_ANGLE=270;
	public static final int LEAF_MIN_STEP=3;
	public static final int LEAF_ANGLE1=50;
	public static final int LEAF_ANGLE2=30;
	public static final double LEAF_SCALE1=3.0;
	public static final double LEAF_SCALE2=1.3;
	//----------------------------	
	public static final int LSTYPE01=9;
	public static final int LS_STEP=100;
	public static final double LS_ANGLE=22;
	public static final int LS_DEPTH=5;
	public static final String LS_SEED1="F";
	//public static final String LS_REGULATION= "F[-F]F[+F]F";
	public static final String LS_REGULATION= "F+F";
	public static final int LS_AX=400;
	public static final int LS_AY=400;
	public static final int LS_PRI_ANGLE=0;
	//----------------------------
	public static final int LSTYPE02=10;
	public static final String LS_SEED2="X";
	public static final String LS02_REGULATION1= "XFY[+++++XFX][-----XFX]";
	public static final String LS02_REGULATION2= "F-YF";	
	//----------------------------	
	public static final int LSTYPE03=11;
	public static final String LS_SEED3="L";
	public static final String LS03_REGULATION1="+R-L-R+";
	public static final String LS03_REGULATION2="-L+R+L-";
	//------------------------------
	public static final int LSTYPE04=12;
	public static final String LS_SEED4="F";
	public static final String LS04_REGULATION1="F[+F]F[-F]F";
	public static final String LS04_REGULATION2="F[+F]F[-F[+F]]";
	public static final String LS04_REGULATION3="FF-[-F+F+F]+[+F-F-F]";
	//----------选取一下参数，保证程序不会溢出--------------------
	public static final int IFSJulia=13;
	public static final int IFS_DEPTH=2;
	public static final double IFS_CX=-0.74543;
	public static final double IFS_CY=0.11301;
	public static final double IFS_X0=1.0;
	public static final double IFS_Y0=1.0;
	public static final int IFS_H=600;
	public static final int IFS_W=600;
	public static final double IFS_CX_MAX=2.0;
	public static final double IFS_CX_MIN=-2.0;
	public static final double IFS_CY_MAX=2.0;
	public static final double IFS_CY_MIN=-2.0;
	public static final int IFS_COUNT_MAX=25000;
	//------------------------------
	public static final int IFS01=14;		
	//-----------------------
	public static final int JULIATYPE01=15;
	public static final double JULIA_CP=-0.46;
	public static final double JULIA_CQ=-0.5984;
	public static final double JULIA_STEP=0.0045;	//0.0045
	public static final double JULIA_REAL_Z=-1.5;	//-1.5
	public static final double JULIA_IMAGE_Z=-1.5;	//-1.5
	public static final int JULIA_SCREEN_WIDTH=800;
	public static final int JULIA_SCREEN_HEIGHT=600;
	public static final double JULIA_CP_MIN=-1.8;	//-0.8
	public static final double JULIA_CP_MAX=1.8;	//0.8
	public static final double JULIA_CQ_MIN=-1.8;	//-0.8
	public static final double JULIA_CQ_MAX=1.8;	//0.8
	public static final double JULIA_STEP_MIN=0.0020;
	public static final double JULIA_STEP_MAX=0.0080;
	public static final int JULIA_DEEPTH=100;	//100
	public static final double JULIA_OUTOF=4;		//4
	public static final Color JULIA_COLOR=Color.black;
	public static final int JULIA_STEP_SPEED=500;
	//-----------------------
	public static final int JULIATYPE02=16;	
	//-----------------------
	public static final int JULIATYPE03=17;	
	//-----------------------
	public static final int JULIATYPE04=18;	
	//-----------------------	
	public static final int JULIATYPE05=19;	
	//-----------------------
	public static final int LSSCALE01=20;	
	public static final int LSSCALE_STEP=1;
	public static final double LSSCALE_ANGLE=1;
	public static final int LSSCALE_DEPTH=10;
	public static final String LSSCALE_SEED1="F";
	public static final String LSSCALE_REGULATION= "F+F";
	public static final int LSSCALE_AX=600;
	public static final int LSSCALE_AY=500;
	public static final int LSSCALE_PRI_ANGLE=0;
	public static final double LSSCALE_SCALE=5.5d;   //放大系数；
	public static final double LSSCALE_ANGLE_MIN=80.0;
	public static final double LSSCALE_ANGLE_MAX=90.0;
	
	
}
