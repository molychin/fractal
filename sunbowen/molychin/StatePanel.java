package fractal.sunbowen.molychin;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import fractal.sunbowen.molychin.type.FractalIFS;
import fractal.sunbowen.molychin.type.FractalIFSJulia;
import fractal.sunbowen.molychin.type.FractalJulia;
import fractal.sunbowen.molychin.type.FractalNothing;
import fractal.sunbowen.molychin.type.FractalObject;

public class StatePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private FractalObject fractalObject;
	FractalFrame frame;
	private JPanel panelParameter, panelState;
	private JLabel labFractalType;
	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> textFields;
	private ArrayList<JSlider> sliderParameters;
	JComboBox comboBoxFractalType;
	private JTextArea textAreaStateMessage;
	private int fractalType;
	private JPanel panel1, panel2;

	public StatePanel(FractalFrame frame) {
		this.fractalObject = frame.fractalObject;
		this.frame = frame;
		this.setLayout(new BorderLayout());

		labels = new ArrayList<JLabel>();
		textFields = new ArrayList<JTextField>();
		sliderParameters = new ArrayList<JSlider>();

		this.panelParameter = new JPanel();		//参数面板
		this.panelState = new JPanel();		//状态面板
		this.panelState.setLayout(new GridLayout(0, 1));
		this.labFractalType = new JLabel("Fractal Type : ");
		this.comboBoxFractalType = new JComboBox();
		this.comboBoxFractalType.addItem("Nothing");
		this.comboBoxFractalType.addItem("ContorType");
		this.comboBoxFractalType.addItem("KochType01");
		this.comboBoxFractalType.addItem("KochType02");
		this.comboBoxFractalType.addItem("KochType03");
		this.comboBoxFractalType.addItem("KochType04");
		this.comboBoxFractalType.addItem("ArboresentType");
		this.comboBoxFractalType.addItem("SierpinskiType");	
		this.comboBoxFractalType.addItem("LeafType");
		this.comboBoxFractalType.addItem("LSType01");
		this.comboBoxFractalType.addItem("LSType02");
		this.comboBoxFractalType.addItem("LSType03");
		this.comboBoxFractalType.addItem("LSType04");
		this.comboBoxFractalType.addItem("IFS01");
		this.comboBoxFractalType.addItem("IFSJulia");
		this.comboBoxFractalType.addItem("JuliaType01");
		this.comboBoxFractalType.addItem("JuliaType02");
		this.comboBoxFractalType.addItem("JuliaType03");
		this.comboBoxFractalType.addItem("JuliaType04");
		this.comboBoxFractalType.addItem("JuliaType05");
		this.comboBoxFractalType.addItem("LSScaleType01");   //依据迭代深度变步长；		
		this.comboBoxFractalType.setSelectedIndex(-1);
		this.comboBoxFractalType.addItemListener(new myItemListener());

		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0, 2, 0, 2));
		panel1.add(labFractalType);
		panel1.add(comboBoxFractalType);
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 1, 0, 5));

		this.panelParameter.setLayout(new GridLayout(0, 1));
		this.panelParameter.add(panel1);
		this.panelParameter.add(panel2);

		this.textAreaStateMessage = new JTextArea(
				"There is stateMessage ......", 8, 5);
		this.textAreaStateMessage.setBackground(Color.pink);
		this.panelState.add(textAreaStateMessage);
		this.add(panelParameter, BorderLayout.NORTH);
		this.add(panelState, BorderLayout.SOUTH);
	}

	private void init() {
		/* 初始化所有面板内容和farctalObject参数; */
		labels.clear();
		textFields.clear();
		sliderParameters.clear();
		fractalObject.clearParameters();
		for (; panel1.getComponentCount() > 2;) {
			panel1.remove(2);
		}
		for (; panel2.getComponentCount() > 0;) {
			panel2.remove(0);
		}
		/* 初始化所有面板内容和farctalObject参数;end */
	}

	private void initNothing(){
		this.fractalObject=new FractalNothing();
		frame.fractalObject=this.fractalObject;
	}
	
	private void initKoch() {
		if (fractalType == Constants.KOCHTYPE01) {
		} else if (fractalType == Constants.KOCHTYPE02
				|| fractalType == Constants.KOCHTYPE03
				|| fractalType == Constants.KOCHTYPE04) {
			JLabel label1 = new JLabel();
			label1.setText("KOCH_SCALE:");
			label1.setVisible(true);
			labels.add(label1);

			JTextField textField1 = new JTextField("", 6);
			textField1.setText(Double.valueOf(Constants.KOCH_SCALE).toString());
			textField1.setName("KOCH_SCALE");
			textField1.setMinimumSize(new Dimension(100, 100));
			textField1.setVisible(true);
			textField1.addActionListener(new MyTextFieldListener());
			textFields.add(textField1);

			JSlider sliderParameter1 = new JSlider();
			sliderParameter1.setMinimum(1);
			sliderParameter1.setMaximum(100);
			sliderParameter1.setMinorTickSpacing(1);
			sliderParameter1.setPaintLabels(true);
			sliderParameter1.setName("KOCH_SCALE");
			sliderParameter1.setValue((int) (Constants.KOCH_SCALE
					/ Constants.KOCHTYPE02_MAX_SCALE * 100));
			sliderParameter1.setVisible(true);
			sliderParameter1.addChangeListener(new SliderListener());
			sliderParameters.add(sliderParameter1);

			panel1.add(label1);
			panel1.add(textField1);
			panel2.add(sliderParameter1);
			this.updateUI();// 更新panel1的控件

			fractalObject.addParameter(Double.valueOf(textField1.getText()));
		}
	}

	private void intiLeaf() {
		JLabel label1 = new JLabel();
		label1.setText("LEAF_ANGLE1 : ");
		label1.setVisible(true);
		JLabel label2 = new JLabel();
		label2.setText("LEAF_ANGLE2 : ");
		label2.setVisible(true);
		JLabel label3 = new JLabel();
		label3.setText("LEAF_SCALE : ");
		label3.setVisible(true);
		JLabel label4 = new JLabel();
		label4.setText("LEAF_SCALE2 : ");
		label4.setVisible(true);
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);
		labels.add(label4);

		JTextField textField1 = new JTextField();
		textField1.setText(Integer.valueOf(Constants.LEAF_ANGLE1).toString());
		textField1.setName("LEAF_ANGLE1");
		textField1.setVisible(true);
		JTextField textField2 = new JTextField();
		textField2.setText(Integer.valueOf(Constants.LEAF_ANGLE2).toString());
		textField2.setName("LEAF_ANGLE2");
		textField2.setVisible(true);
		JTextField textField3 = new JTextField();
		textField3.setText(Double.valueOf(Constants.LEAF_SCALE1).toString());
		textField3.setName("LEAF_SCALE1");
		textField3.setVisible(true);
		JTextField textField4 = new JTextField();
		textField4.setText(Double.valueOf(Constants.LEAF_SCALE2).toString());
		textField4.setName("LEAF_SCALE2");
		textField4.setVisible(true);
		textField1.addActionListener(new MyTextFieldListener());
		textField2.addActionListener(new MyTextFieldListener());
		textField3.addActionListener(new MyTextFieldListener());
		textField4.addActionListener(new MyTextFieldListener());

		textFields.add(textField1);
		textFields.add(textField2);
		textFields.add(textField3);
		textFields.add(textField4);

		JSlider sliderParameter1 = new JSlider();
		sliderParameter1.setMinimum(1);
		sliderParameter1.setMaximum(360);
		sliderParameter1.setMinorTickSpacing(1);
		sliderParameter1.setValue(Constants.LEAF_ANGLE1);
		sliderParameter1.setPaintLabels(true);
		sliderParameter1.setName("LEAF_ANGLE1");
		sliderParameter1.setVisible(true);
		JSlider sliderParameter2 = new JSlider();
		sliderParameter2.setMinimum(1);
		sliderParameter2.setMaximum(360);
		sliderParameter2.setMinorTickSpacing(1);
		sliderParameter2.setValue(Constants.LEAF_ANGLE2);
		sliderParameter2.setPaintLabels(true);
		sliderParameter2.setName("LEAF_ANGLE2");
		sliderParameter2.setVisible(true);
		JSlider sliderParameter3 = new JSlider();
		sliderParameter3.setMinimum(16);
		sliderParameter3.setMaximum(100);
		sliderParameter3.setMinorTickSpacing(1);
		sliderParameter3.setValue((int) (Constants.LEAF_SCALE1 * 10));
		sliderParameter3.setPaintLabels(true);
		sliderParameter3.setName("LEAF_SCALE1");
		sliderParameter3.setVisible(true);
		JSlider sliderParameter4 = new JSlider();
		sliderParameter4.setMinimum(11);
		sliderParameter4.setMaximum(30);
		sliderParameter4.setMinorTickSpacing(1);
		sliderParameter4.setValue((int) (Constants.LEAF_SCALE2 * 10));
		sliderParameter4.setPaintLabels(true);
		sliderParameter4.setName("LEAF_SCALE2");
		sliderParameter4.setVisible(true);
		sliderParameter1.addChangeListener(new SliderListener());
		sliderParameter2.addChangeListener(new SliderListener());
		sliderParameter3.addChangeListener(new SliderListener());
		sliderParameter4.addChangeListener(new SliderListener());

		sliderParameters.add(sliderParameter1);
		sliderParameters.add(sliderParameter2);
		sliderParameters.add(sliderParameter3);
		sliderParameters.add(sliderParameter4);

		panel1.add(label1);
		panel1.add(textField1);
		panel1.add(label2);
		panel1.add(textField2);
		panel1.add(label3);
		panel1.add(textField3);
		panel1.add(label4);
		panel1.add(textField4);

		panel2.add(sliderParameter1);
		panel2.add(sliderParameter2);
		panel2.add(sliderParameter3);
		panel2.add(sliderParameter4);
		this.updateUI();// 更新panel1的控件

		fractalObject.addParameter(Constants.LEAF_ANGLE1);
		fractalObject.addParameter(Constants.LEAF_ANGLE2);
		fractalObject.addParameter(Constants.LEAF_SCALE1);
		fractalObject.addParameter(Constants.LEAF_SCALE2);
	}

	private void initLS() {
		if ((fractalType == Constants.LSTYPE01)) {
			JLabel label1 = new JLabel();
			label1.setText("LS_SEED : ");
			label1.setVisible(true);
			JLabel label2 = new JLabel();
			label2.setText("LS_REGULATION:");
			label2.setVisible(true);
			JLabel label3 = new JLabel();
			label3.setText("LS_ANGLE : ");
			label3.setVisible(true);
			JLabel label4 = new JLabel();
			label4.setText("LS_STEP : ");
			label4.setVisible(true);
			JLabel label5 = new JLabel();
			label5.setText("LS_DEPTH : ");
			label5.setVisible(true);
			labels.add(label1);
			labels.add(label2);
			labels.add(label3);
			labels.add(label4);
			labels.add(label5);

			JTextField textField1 = new JTextField("", 6);
			textField1.setText(Constants.LS_SEED1);
			textField1.setName("LS_SEED");
			textField1.setVisible(true);
			JTextField textField2 = new JTextField("", 6);
			textField2.setText(Constants.LS_REGULATION);
			textField2.setName("LS_REGULATION");
			textField2.setVisible(true);
			JTextField textField3 = new JTextField("", 6);
			textField3.setText(Double.valueOf(Constants.LS_ANGLE).toString());
			textField3.setName("LS_ANGLE");
			textField3.setVisible(true);
			JTextField textField4 = new JTextField("", 6);
			textField4.setText(Integer.valueOf(Constants.LS_STEP).toString());
			textField4.setName("LS_STEP");
			textField4.setVisible(true);
			JTextField textField5 = new JTextField("", 6);
			textField5.setText(Integer.valueOf(Constants.LS_DEPTH).toString());
			textField5.setName("LS_DEPTH");
			textField5.setVisible(true);
			textField1.addActionListener(new MyTextFieldListener());
			textField2.addActionListener(new MyTextFieldListener());
			textField3.addActionListener(new MyTextFieldListener());
			textField4.addActionListener(new MyTextFieldListener());
			textField5.addActionListener(new MyTextFieldListener());
			textFields.add(textField1);
			textFields.add(textField2);
			textFields.add(textField3);
			textFields.add(textField4);
			textFields.add(textField5);

			JSlider sliderParameter1 = new JSlider();
			sliderParameter1.setMinimum(1);
			sliderParameter1.setMaximum(100);
			sliderParameter1.setMinorTickSpacing(1);
			sliderParameter1.setValue((int) Constants.LS_ANGLE);
			sliderParameter1.setPaintLabels(true);
			sliderParameter1.setName("LS_ANGLE");
			sliderParameter1.setVisible(true);
			sliderParameter1.addChangeListener(new SliderListener());
			sliderParameters.add(sliderParameter1);

			panel1.add(label1);
			panel1.add(textField1);
			panel1.add(label2);
			panel1.add(textField2);
			panel1.add(label3);
			panel1.add(textField3);
			panel1.add(label4);
			panel1.add(textField4);
			panel1.add(label5);
			panel1.add(textField5);
			panel2.add(sliderParameter1);

			this.updateUI();// 更新panel1的控件

			fractalObject.addParameter(Constants.LS_SEED1);
			fractalObject.addParameter(Constants.LS_REGULATION);
			fractalObject.addParameter(Constants.LS_ANGLE);
			fractalObject.addParameter(Constants.LS_STEP);
			fractalObject.addParameter(Constants.LS_DEPTH);
		} else if (fractalType == Constants.LSTYPE02) {
			JLabel label1 = new JLabel();
			label1.setText("LS_SEED : ");
			label1.setVisible(true);
			JLabel label2 = new JLabel();
			label2.setText("LS_REGULATION1:");
			label2.setVisible(true);
			JLabel label3 = new JLabel();
			label3.setText("LS_REGULATION2:");
			label3.setVisible(true);
			JLabel label4 = new JLabel();
			label4.setText("LS_ANGLE : ");
			label4.setVisible(true);
			JLabel label5 = new JLabel();
			label5.setText("LS_STEP : ");
			label5.setVisible(true);
			JLabel label6 = new JLabel();
			label6.setText("LS_DEPTH : ");
			label6.setVisible(true);
			labels.add(label1);
			labels.add(label2);
			labels.add(label3);
			labels.add(label4);
			labels.add(label5);
			labels.add(label6);

			JTextField textField1 = new JTextField("", 6);
			textField1.setText(Constants.LS_SEED2);
			textField1.setName("LS_SEED");
			textField1.setVisible(true);
			JTextField textField2 = new JTextField("", 6);
			textField2.setText(Constants.LS02_REGULATION1);
			textField2.setName("LS_REGULATION1");
			textField2.setVisible(true);
			JTextField textField3 = new JTextField("", 6);
			textField3.setText(Constants.LS02_REGULATION2);
			textField3.setName("LS_REGULATION2");
			textField3.setVisible(true);
			JTextField textField4 = new JTextField("", 6);
			textField4.setText(Double.valueOf(Constants.LS_ANGLE).toString());
			textField4.setName("LS_ANGLE");
			textField4.setVisible(true);
			JTextField textField5 = new JTextField("", 6);
			textField5.setText(Integer.valueOf(Constants.LS_STEP).toString());
			textField5.setName("LS_STEP");
			textField5.setVisible(true);
			JTextField textField6 = new JTextField("", 6);
			textField6.setText(Integer.valueOf(Constants.LS_DEPTH).toString());
			textField6.setName("LS_DEPTH");
			textField6.setVisible(true);
			textField1.addActionListener(new MyTextFieldListener());
			textField2.addActionListener(new MyTextFieldListener());
			textField3.addActionListener(new MyTextFieldListener());
			textField4.addActionListener(new MyTextFieldListener());
			textField5.addActionListener(new MyTextFieldListener());
			textField6.addActionListener(new MyTextFieldListener());
			textFields.add(textField1);
			textFields.add(textField2);
			textFields.add(textField3);
			textFields.add(textField4);
			textFields.add(textField5);
			textFields.add(textField6);

			JSlider sliderParameter1 = new JSlider();
			sliderParameter1.setMinimum(1);
			sliderParameter1.setMaximum(100);
			sliderParameter1.setMinorTickSpacing(1);
			sliderParameter1.setValue((int) Constants.LS_ANGLE);
			sliderParameter1.setPaintLabels(true);
			sliderParameter1.setName("LS_ANGLE");
			sliderParameter1.setVisible(true);
			sliderParameter1.addChangeListener(new SliderListener());
			sliderParameters.add(sliderParameter1);

			panel1.add(label1);
			panel1.add(textField1);
			panel1.add(label2);
			panel1.add(textField2);
			panel1.add(label3);
			panel1.add(textField3);
			panel1.add(label4);
			panel1.add(textField4);
			panel1.add(label5);
			panel1.add(textField5);
			panel1.add(label6);
			panel1.add(textField6);
			panel2.add(sliderParameter1);

			this.updateUI();// 更新panel1的控件

			fractalObject.addParameter(Constants.LS_SEED2);
			fractalObject.addParameter(Constants.LS02_REGULATION1);
			fractalObject.addParameter(Constants.LS02_REGULATION2);
			fractalObject.addParameter(Constants.LS_ANGLE);
			fractalObject.addParameter(Constants.LS_STEP);
			fractalObject.addParameter(Constants.LS_DEPTH);
		} else if (fractalType == Constants.LSTYPE03) {
			JLabel label1 = new JLabel();
			label1.setText("LS_SEED : ");
			label1.setVisible(true);
			JLabel label2 = new JLabel();
			label2.setText("LS_REGULATION1:");
			label2.setVisible(true);
			JLabel label3 = new JLabel();
			label3.setText("LS_REGULATION2:");
			label3.setVisible(true);
			JLabel label4 = new JLabel();
			label4.setText("LS_ANGLE : ");
			label4.setVisible(true);
			JLabel label5 = new JLabel();
			label5.setText("LS_STEP : ");
			label5.setVisible(true);
			JLabel label6 = new JLabel();
			label6.setText("LS_DEPTH : ");
			label6.setVisible(true);
			labels.add(label1);
			labels.add(label2);
			labels.add(label3);
			labels.add(label4);
			labels.add(label5);
			labels.add(label6);

			JTextField textField1 = new JTextField("", 6);
			textField1.setText(Constants.LS_SEED3);
			textField1.setName("LS_SEED");
			textField1.setVisible(true);
			JTextField textField2 = new JTextField("", 6);
			textField2.setText(Constants.LS03_REGULATION1);
			textField2.setName("LS_REGULATION1");
			textField2.setVisible(true);
			JTextField textField3 = new JTextField("", 6);
			textField3.setText(Constants.LS03_REGULATION2);
			textField3.setName("LS_REGULATION2");
			textField3.setVisible(true);
			JTextField textField4 = new JTextField("", 6);
			textField4.setText(Double.valueOf(Constants.LS_ANGLE).toString());
			textField4.setName("LS_ANGLE");
			textField4.setVisible(true);
			JTextField textField5 = new JTextField("", 6);
			textField5.setText(Integer.valueOf(Constants.LS_STEP).toString());
			textField5.setName("LS_STEP");
			textField5.setVisible(true);
			JTextField textField6 = new JTextField("", 6);
			textField6.setText(Integer.valueOf(Constants.LS_DEPTH).toString());
			textField6.setName("LS_DEPTH");
			textField6.setVisible(true);
			textField1.addActionListener(new MyTextFieldListener());
			textField2.addActionListener(new MyTextFieldListener());
			textField3.addActionListener(new MyTextFieldListener());
			textField4.addActionListener(new MyTextFieldListener());
			textField5.addActionListener(new MyTextFieldListener());
			textField6.addActionListener(new MyTextFieldListener());
			textFields.add(textField1);
			textFields.add(textField2);
			textFields.add(textField3);
			textFields.add(textField4);
			textFields.add(textField5);
			textFields.add(textField6);

			JSlider sliderParameter1 = new JSlider();
			sliderParameter1.setMinimum(1);
			sliderParameter1.setMaximum(100);
			sliderParameter1.setMinorTickSpacing(1);
			sliderParameter1.setValue((int) Constants.LS_ANGLE);
			sliderParameter1.setPaintLabels(true);
			sliderParameter1.setName("LS_ANGLE");
			sliderParameter1.setVisible(true);
			sliderParameter1.addChangeListener(new SliderListener());
			sliderParameters.add(sliderParameter1);

			panel1.add(label1);
			panel1.add(textField1);
			panel1.add(label2);
			panel1.add(textField2);
			panel1.add(label3);
			panel1.add(textField3);
			panel1.add(label4);
			panel1.add(textField4);
			panel1.add(label5);
			panel1.add(textField5);
			panel1.add(label6);
			panel1.add(textField6);
			panel2.add(sliderParameter1);

			this.updateUI();// 更新panel1的控件

			fractalObject.addParameter(Constants.LS_SEED3);
			fractalObject.addParameter(Constants.LS03_REGULATION1);
			fractalObject.addParameter(Constants.LS03_REGULATION2);
			fractalObject.addParameter(Constants.LS_ANGLE);
			fractalObject.addParameter(Constants.LS_STEP);
			fractalObject.addParameter(Constants.LS_DEPTH);
		} else if (fractalType == Constants.LSTYPE04) {
			JLabel label1 = new JLabel();
			label1.setText("LS_SEED : ");
			label1.setVisible(true);
			JLabel label2 = new JLabel();
			label2.setText("LS_REGULATION1:");
			label2.setVisible(true);
			JLabel label3 = new JLabel();
			label3.setText("LS_REGULATION2:");
			label3.setVisible(true);
			JLabel label4 = new JLabel();
			label4.setText("LS_REGULATION3:");
			label4.setVisible(true);
			JLabel label5 = new JLabel();
			label5.setText("LS_ANGLE : ");
			label5.setVisible(true);
			JLabel label6 = new JLabel();
			label6.setText("LS_STEP : ");
			label6.setVisible(true);
			JLabel label7 = new JLabel();
			label7.setText("LS_DEPTH : ");
			label7.setVisible(true);
			labels.add(label1);
			labels.add(label2);
			labels.add(label3);
			labels.add(label4);
			labels.add(label5);
			labels.add(label6);
			labels.add(label7);

			JTextField textField1 = new JTextField("", 6);
			textField1.setText(Constants.LS_SEED4);
			textField1.setName("LS_SEED");
			textField1.setVisible(true);
			JTextField textField2 = new JTextField("", 6);
			textField2.setText(Constants.LS04_REGULATION1);
			textField2.setName("LS_REGULATION1");
			textField2.setVisible(true);
			JTextField textField3 = new JTextField("", 6);
			textField3.setText(Constants.LS04_REGULATION2);
			textField3.setName("LS_REGULATION2");
			textField3.setVisible(true);
			JTextField textField4 = new JTextField("", 6);
			textField4.setText(Constants.LS04_REGULATION3);
			textField4.setName("LS_REGULATION3");
			textField4.setVisible(true);
			JTextField textField5 = new JTextField("", 6);
			textField5.setText(Double.valueOf(Constants.LS_ANGLE).toString());
			textField5.setName("LS_ANGLE");
			textField5.setVisible(true);
			JTextField textField6 = new JTextField("", 6);
			textField6.setText(Integer.valueOf(Constants.LS_STEP).toString());
			textField6.setName("LS_STEP");
			textField6.setVisible(true);
			JTextField textField7 = new JTextField("", 6);
			textField7.setText(Integer.valueOf(Constants.LS_DEPTH).toString());
			textField7.setName("LS_DEPTH");
			textField7.setVisible(true);
			textField1.addActionListener(new MyTextFieldListener());
			textField2.addActionListener(new MyTextFieldListener());
			textField3.addActionListener(new MyTextFieldListener());
			textField4.addActionListener(new MyTextFieldListener());
			textField5.addActionListener(new MyTextFieldListener());
			textField6.addActionListener(new MyTextFieldListener());
			textField7.addActionListener(new MyTextFieldListener());
			textFields.add(textField1);
			textFields.add(textField2);
			textFields.add(textField3);
			textFields.add(textField4);
			textFields.add(textField5);
			textFields.add(textField6);
			textFields.add(textField7);

			JSlider sliderParameter1 = new JSlider();
			sliderParameter1.setMinimum(1);
			sliderParameter1.setMaximum(100);
			sliderParameter1.setMinorTickSpacing(1);
			sliderParameter1.setValue((int) Constants.LS_ANGLE);
			sliderParameter1.setPaintLabels(true);
			sliderParameter1.setName("LS_ANGLE");
			sliderParameter1.setVisible(true);
			sliderParameter1.addChangeListener(new SliderListener());
			sliderParameters.add(sliderParameter1);

			panel1.add(label1);
			panel1.add(textField1);
			panel1.add(label2);
			panel1.add(textField2);
			panel1.add(label3);
			panel1.add(textField3);
			panel1.add(label4);
			panel1.add(textField4);
			panel1.add(label5);
			panel1.add(textField5);
			panel1.add(label6);
			panel1.add(textField6);
			panel1.add(label7);
			panel1.add(textField7);
			panel2.add(sliderParameter1);

			this.updateUI();// 更新panel1的控件

			fractalObject.addParameter(Constants.LS_SEED4);
			fractalObject.addParameter(Constants.LS04_REGULATION1);
			fractalObject.addParameter(Constants.LS04_REGULATION2);
			fractalObject.addParameter(Constants.LS04_REGULATION3);
			fractalObject.addParameter(Constants.LS_ANGLE);
			fractalObject.addParameter(Constants.LS_STEP);
			fractalObject.addParameter(Constants.LS_DEPTH);
		}else if (fractalType == Constants.LSSCALE01){
			JLabel label1 = new JLabel();
			label1.setText("LSSCALE_SEED : ");
			label1.setVisible(true);
			JLabel label2 = new JLabel();
			label2.setText("LSSCALE_REGULATION:");
			label2.setVisible(true);
			JLabel label3 = new JLabel();
			label3.setText("LSSCALE_ANGLE : ");
			label3.setVisible(true);
			JLabel label4 = new JLabel();
			label4.setText("LSSCALE_STEP : ");
			label4.setVisible(true);
			JLabel label5 = new JLabel();
			label5.setText("LSSCALE_DEPTH : ");
			label5.setVisible(true);
			labels.add(label1);
			labels.add(label2);
			labels.add(label3);
			labels.add(label4);
			labels.add(label5);

			JTextField textField1 = new JTextField("", 6);
			textField1.setText(Constants.LSSCALE_SEED1);
			textField1.setName("LSSCALE_SEED");
			textField1.setVisible(true);
			JTextField textField2 = new JTextField("", 6);
			textField2.setText(Constants.LSSCALE_REGULATION);
			textField2.setName("LSSCALE_REGULATION");
			textField2.setVisible(true);
			JTextField textField3 = new JTextField("", 6);
			textField3.setText(Double.valueOf(Constants.LSSCALE_ANGLE).toString());
			textField3.setName("LSSCALE_ANGLE");
			textField3.setVisible(true);
			JTextField textField4 = new JTextField("", 6);
			textField4.setText(Integer.valueOf(Constants.LSSCALE_STEP).toString());
			textField4.setName("LSSCALE_STEP");
			textField4.setVisible(true);
			JTextField textField5 = new JTextField("", 6);
			textField5.setText(Integer.valueOf(Constants.LSSCALE_DEPTH).toString());
			textField5.setName("LSSCALE_DEPTH");
			textField5.setVisible(true);
			textField1.addActionListener(new MyTextFieldListener());
			textField2.addActionListener(new MyTextFieldListener());
			textField3.addActionListener(new MyTextFieldListener());
			textField4.addActionListener(new MyTextFieldListener());
			textField5.addActionListener(new MyTextFieldListener());
			textFields.add(textField1);
			textFields.add(textField2);
			textFields.add(textField3);
			textFields.add(textField4);
			textFields.add(textField5);

			JSlider sliderParameter1 = new JSlider();
			sliderParameter1.setMinimum(1);
			sliderParameter1.setMaximum(100);
			sliderParameter1.setMinorTickSpacing(1);
			sliderParameter1.setValue((int) Constants.LSSCALE_ANGLE);
			sliderParameter1.setPaintLabels(true);
			sliderParameter1.setName("LSSCALE_ANGLE");
			sliderParameter1.setVisible(true);
			sliderParameter1.addChangeListener(new SliderListener());
			sliderParameters.add(sliderParameter1);

			panel1.add(label1);
			panel1.add(textField1);
			panel1.add(label2);
			panel1.add(textField2);
			panel1.add(label3);
			panel1.add(textField3);
			panel1.add(label4);
			panel1.add(textField4);
			panel1.add(label5);
			panel1.add(textField5);
			panel2.add(sliderParameter1);

			this.updateUI();// 更新panel1的控件

			fractalObject.addParameter(Constants.LSSCALE_SEED1);
			fractalObject.addParameter(Constants.LSSCALE_REGULATION);
			fractalObject.addParameter(Constants.LSSCALE_ANGLE);
			fractalObject.addParameter(Constants.LSSCALE_STEP);
			fractalObject.addParameter(Constants.LSSCALE_DEPTH);			
		}
		else{
			//do something.
		}
	}

	private void initIFSJulia() {
		JLabel label1 = new JLabel();
		label1.setText("Julia cx : ");
		label1.setVisible(true);
		JLabel label2 = new JLabel();
		label2.setText("Julia cy : ");
		label2.setVisible(true);
		JLabel label3 = new JLabel();
		label3.setText("Depth : ");
		label3.setVisible(true);
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);

		JTextField textField1 = new JTextField("", 6);
		textField1.setText(Double.valueOf(Constants.IFS_CX).toString());
		textField1.setName("IFS_CX");
		textField1.setVisible(true);
		JTextField textField2 = new JTextField("", 6);
		textField2.setText(Double.valueOf(Constants.IFS_CY).toString());
		textField2.setName("IFS_CY");
		textField2.setVisible(true);
		JTextField textField3 = new JTextField("", 6);
		textField3.setText(Integer.valueOf(Constants.IFS_DEPTH).toString());
		textField3.setName("IFS_DEPTH");
		textField3.setVisible(true);
		textField1.addActionListener(new MyTextFieldListener());
		textField2.addActionListener(new MyTextFieldListener());
		textField3.addActionListener(new MyTextFieldListener());
		textFields.add(textField1);
		textFields.add(textField2);
		textFields.add(textField3);

		JSlider sliderParameter1 = new JSlider();
		sliderParameter1.setMinimum(1);
		sliderParameter1.setMaximum(100);
		sliderParameter1.setMinorTickSpacing(1);
		sliderParameter1
				.setValue((int) (100 * (Constants.IFS_CX - Constants.IFS_CX_MIN) / (Constants.IFS_CX_MAX - Constants.IFS_CX_MIN)));
		sliderParameter1.setPaintLabels(true);
		sliderParameter1.setName("IFS_CX");
		sliderParameter1.setVisible(true);
		sliderParameter1.addChangeListener(new SliderListener());
		JSlider sliderParameter2 = new JSlider();
		sliderParameter2.setMinimum(1);
		sliderParameter2.setMaximum(100);
		sliderParameter2.setMinorTickSpacing(1);
		sliderParameter2
				.setValue((int) (100 * (Constants.IFS_CY - Constants.IFS_CY_MIN) / (Constants.IFS_CY_MAX - Constants.IFS_CY_MIN)));
		sliderParameter2.setPaintLabels(true);
		sliderParameter2.setName("IFS_CY");
		sliderParameter2.setVisible(true);
		sliderParameter2.addChangeListener(new SliderListener());
		sliderParameters.add(sliderParameter1);
		sliderParameters.add(sliderParameter2);

		panel1.add(label1);
		panel1.add(textField1);
		panel1.add(label2);
		panel1.add(textField2);
		panel1.add(label3);
		panel1.add(textField3);
		panel2.add(sliderParameter1);
		panel2.add(sliderParameter2);

		this.updateUI();// 更新panel1的控件

		this.fractalObject=new FractalIFSJulia();
		frame.fractalObject=fractalObject;
		
		fractalObject.addParameter(Constants.IFS_CX);
		fractalObject.addParameter(Constants.IFS_CY);
		fractalObject.addParameter(Constants.IFS_DEPTH);
		fractalObject.addParameter(new byte[Constants.IFS_W][Constants.IFS_H]);
	}

	private void initJulia() {
		JLabel label1 = new JLabel();
		label1.setText("Julia cx : ");
		label1.setVisible(true);
		JLabel label2 = new JLabel();
		label2.setText("Julia cy : ");
		label2.setVisible(true);
		JLabel label3 = new JLabel();
		label3.setText("Step : ");
		label3.setVisible(true);
		JLabel label4 = new JLabel();
		label4.setText("Step Speed:");
		label4.setVisible(true);
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);
		labels.add(label4);

		JTextField textField1 = new JTextField("", 6);
		textField1.setText(Double.valueOf(Constants.JULIA_CP).toString());
		textField1.setName("JULIA_CP");
		textField1.setVisible(true);
		JTextField textField2 = new JTextField("", 6);
		textField2.setText(Double.valueOf(Constants.JULIA_CQ).toString());
		textField2.setName("JULIA_CQ");
		textField2.setVisible(true);
		JTextField textField3 = new JTextField("", 6);
		textField3.setText(Double.valueOf(Constants.JULIA_STEP).toString());
		textField3.setName("JULIA_STEP");
		textField3.setVisible(true);
		JTextField textField4 = new JTextField("", 6);
		textField4.setText(Integer.valueOf(Constants.JULIA_STEP_SPEED)
				.toString());
		textField4.setName("JULIA_STEP_SPEED");
		textField4.setVisible(true);
		textField1.addActionListener(new MyTextFieldListener());
		textField2.addActionListener(new MyTextFieldListener());
		textField3.addActionListener(new MyTextFieldListener());
		textField4.addActionListener(new MyTextFieldListener());
		textFields.add(textField1);
		textFields.add(textField2);
		textFields.add(textField3);
		textFields.add(textField4);

		JSlider sliderParameter1 = new JSlider();
		sliderParameter1.setMinimum(1);
		sliderParameter1.setMaximum(Constants.JULIA_STEP_SPEED);
		sliderParameter1.setMinorTickSpacing(1);
		sliderParameter1
				.setValue((int) (Constants.JULIA_STEP_SPEED
						* (Constants.JULIA_CP - Constants.JULIA_CP_MIN) / (Constants.JULIA_CP_MAX - Constants.JULIA_CP_MIN)));
		sliderParameter1.setPaintLabels(true);
		sliderParameter1.setName("JULIA_CP");
		sliderParameter1.setVisible(true);
		sliderParameter1.addChangeListener(new SliderListener());

		JSlider sliderParameter2 = new JSlider();
		sliderParameter2.setMinimum(1);
		sliderParameter2.setMaximum(Constants.JULIA_STEP_SPEED);
		sliderParameter2.setMinorTickSpacing(1);
		sliderParameter2
				.setValue((int) (Constants.JULIA_STEP_SPEED
						* (Constants.JULIA_CQ - Constants.JULIA_CQ_MIN) / (Constants.JULIA_CQ_MAX - Constants.JULIA_CQ_MIN)));
		sliderParameter2.setPaintLabels(true);
		sliderParameter2.setName("JULIA_CQ");
		sliderParameter2.setVisible(true);
		sliderParameter2.addChangeListener(new SliderListener());

		JSlider sliderParameter3 = new JSlider();
		sliderParameter3.setMinimum(1);
		sliderParameter3.setMaximum(Constants.JULIA_STEP_SPEED);
		sliderParameter3.setMinorTickSpacing(1);
		sliderParameter3
				.setValue((int) (Constants.JULIA_STEP_SPEED
						* (Constants.JULIA_STEP - Constants.JULIA_STEP_MIN) / (Constants.JULIA_STEP_MAX - Constants.JULIA_STEP_MIN)));
		sliderParameter3.setPaintLabels(true);
		sliderParameter3.setName("JULIA_STEP");
		sliderParameter3.setVisible(true);
		sliderParameter3.addChangeListener(new SliderListener());
		sliderParameters.add(sliderParameter1);
		sliderParameters.add(sliderParameter2);
		sliderParameters.add(sliderParameter3);

		panel1.add(label1);
		panel1.add(textField1);
		panel1.add(label2);
		panel1.add(textField2);
		panel1.add(label3);
		panel1.add(textField3);
		panel1.add(label4);
		panel1.add(textField4);
		panel2.add(sliderParameter1);
		panel2.add(sliderParameter2);
		panel2.add(sliderParameter3);
		this.updateUI();// 更新panel1的控件

		fractalObject=new FractalJulia();
		fractalObject.addParameter(Constants.JULIA_CP);
		fractalObject.addParameter(Constants.JULIA_CQ);
		fractalObject.addParameter(Constants.JULIA_STEP);
		fractalObject.addParameter(Constants.JULIA_STEP_SPEED);
		frame.fractalObject=this.fractalObject;

	}

	private void initIFS(){
		this.fractalObject=new FractalIFS();
		frame.fractalObject=this.fractalObject;
	}
	
	class myItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				init();

				fractalType = comboBoxFractalType.getSelectedIndex();
				//System.out.println("StatePanel 917 = "+fractalType);
				if (fractalType == Constants.NOTHING) {
					textAreaStateMessage.setText("NOTHING Set");
					initNothing();
				} else if (fractalType == Constants.CANTORTYPE) {
					textAreaStateMessage.setText("Cantor Set");
				} else if (fractalType == Constants.KOCHTYPE01
						|| fractalType == Constants.KOCHTYPE02
						|| fractalType == Constants.KOCHTYPE03
						|| fractalType == Constants.KOCHTYPE04) {
					textAreaStateMessage.setText("Koch Set");
					initKoch();
				} else if (fractalType == Constants.ARBORESENTTYPE) {
					textAreaStateMessage.setText("ARBORESENTTYPE Set");
				} else if (fractalType == Constants.SIERPINSKITYPE) {
					textAreaStateMessage.setText("SIERPINSKITYPE Set");
				} else if (fractalType == Constants.LEAFTYPE) {
					textAreaStateMessage.setText("LEAFTYPE Set");
					intiLeaf();
				} else if (fractalType == Constants.LSTYPE01
						|| fractalType == Constants.LSTYPE02
						|| fractalType == Constants.LSTYPE03
						|| fractalType == Constants.LSTYPE04) {
					textAreaStateMessage.setText("LSTYPE Set");
					initLS();
				} else if (fractalType == Constants.IFS01) {
					textAreaStateMessage.setText("IFS01 Set");
					initIFS();
				} else if (fractalType == Constants.IFSJulia) {
					textAreaStateMessage.setText("IFSJulia Set");
					initIFSJulia();
				} else if (fractalType == Constants.JULIATYPE01
						|| fractalType == Constants.JULIATYPE02
						|| fractalType == Constants.JULIATYPE03
						|| fractalType == Constants.JULIATYPE04
						|| fractalType == Constants.JULIATYPE05) {
					textAreaStateMessage.setText("SIERPINSKITYPE Set");
					initJulia();
				}else if(fractalType==Constants.LSSCALE01){
					textAreaStateMessage.setText("LSSCALE01 Set");
					initLS();
				}else{
					//do something.
				}
				fractalObject.setFractalType(fractalType);
				frame.repaint();
			}
		}
	}

	class MyTextFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField source = (JTextField) e.getSource();
			if (fractalType == Constants.KOCHTYPE02
					|| fractalType == Constants.KOCHTYPE03
					|| fractalType == Constants.KOCHTYPE04) {
				if (source.getName().equals("KOCH_SCALE")) {
					double para01 = Double.valueOf(source.getText());
					fractalObject.setParameter(0, para01);
					sliderParameters
							.get(0)
							.setValue(
									(int) (para01 * 100 / Constants.KOCHTYPE02_MAX_SCALE));
				}else{}
			} else if (fractalType == Constants.LEAFTYPE) {
				if (source.getName().equals("LEAF_ANGLE1")) {
					int leafB = Integer.valueOf(source.getText());
					fractalObject.setParameter(0, leafB);
					sliderParameters.get(0).setValue(leafB);
				} else if (source.getName().equals("LEAF_ANGLE2")) {
					int leafC = Integer.valueOf(source.getText());
					fractalObject.setParameter(1, leafC);
					sliderParameters.get(1).setValue(leafC);
				} else if (source.getName().equals("LEAF_SCALE1")) {
					double leafScale1 = Double.valueOf(source.getText());
					fractalObject.setParameter(2, leafScale1);
					sliderParameters.get(2).setValue((int) leafScale1 * 10);
				} else if (source.getName().equals("LEAF_SCALE2")) {
					double leafScale2 = Double.valueOf(source.getText());
					fractalObject.setParameter(3, leafScale2);
					sliderParameters.get(3).setValue((int) leafScale2 * 10);
				}else{}
			} else if ((fractalType == Constants.LSTYPE01)) {
				if (source.getName().equals("LS_SEED")) {
					String seed = source.getText();
					fractalObject.setParameter(0, seed);
				} else if (source.getName().equals("LS_REGULATION")) {
					String regulation = source.getText();
					fractalObject.setParameter(1, regulation);
				} else if (source.getName().equals("LS_ANGLE")) {
					double angle = Double.valueOf(source.getText());
					sliderParameters.get(0).setValue((int) (angle));
					fractalObject.setParameter(2, angle);
				} else if (source.getName().equals("LS_STEP")) {
					int step = Integer.valueOf(source.getText());
					fractalObject.setParameter(3, step);
				} else if (source.getName().equals("LS_DEPTH")) {
					int depth = Integer.valueOf(source.getText());
					fractalObject.setParameter(4, depth);
				}else{}
			} else if (fractalType == Constants.LSTYPE02
					|| fractalType == Constants.LSTYPE03) {
				if (source.getName().equals("LS_SEED")) {
					String seed = source.getText();
					fractalObject.setParameter(0, seed);
				} else if (source.getName().equals("LS_REGULATION1")) {
					String regulation = source.getText();
					fractalObject.setParameter(1, regulation);
				} else if (source.getName().equals("LS_REGULATION2")) {
					String regulation = source.getText();
					fractalObject.setParameter(2, regulation);
				} else if (source.getName().equals("LS_ANGLE")) {
					double angle = Double.valueOf(source.getText());
					sliderParameters.get(0).setValue((int) (angle));
					fractalObject.setParameter(3, angle);
				} else if (source.getName().equals("LS_STEP")) {
					int step = Integer.valueOf(source.getText());
					fractalObject.setParameter(4, step);
				} else if (source.getName().equals("LS_DEPTH")) {
					int depth = Integer.valueOf(source.getText());
					fractalObject.setParameter(5, depth);
				}
			} else if (fractalType == Constants.LSTYPE04) {
				if (source.getName().equals("LS_SEED")) {
					String seed = source.getText();
					fractalObject.setParameter(0, seed);
				} else if (source.getName().equals("LS_REGULATION1")) {
					String regulation = source.getText();
					fractalObject.setParameter(1, regulation);
				} else if (source.getName().equals("LS_REGULATION2")) {
					String regulation = source.getText();
					fractalObject.setParameter(2, regulation);
				} else if (source.getName().equals("LS_REGULATION3")) {
					String regulation = source.getText();
					fractalObject.setParameter(3, regulation);
				} else if (source.getName().equals("LS_ANGLE")) {
					double angle = Double.valueOf(source.getText());
					sliderParameters.get(0).setValue((int) (angle));
					fractalObject.setParameter(4, angle);
				} else if (source.getName().equals("LS_STEP")) {
					int step = Integer.valueOf(source.getText());
					fractalObject.setParameter(5, step);
				} else if (source.getName().equals("LS_DEPTH")) {
					int depth = Integer.valueOf(source.getText());
					fractalObject.setParameter(6, depth);
				}
			} else if (fractalType == Constants.IFSJulia) {
				FractalIFSJulia.count=0;
				fractalObject.setParameter(3,
						new byte[Constants.IFS_W + 1][Constants.IFS_H + 1]);
				fractalObject.initPixels();
				if (source.getName().equals("IFS_CX")) {
					double ifsCx = Double.valueOf(source.getText());
					sliderParameters
							.get(0)
							.setValue(
									(int) (100 * (ifsCx - Constants.IFS_CX_MIN) / (Constants.IFS_CX_MAX - Constants.IFS_CX_MIN)));
					fractalObject.setParameter(0, ifsCx);
				} else if (source.getName().equals("IFS_CY")) {
					double ifsCy = Double.valueOf(source.getText());
					sliderParameters
							.get(1)
							.setValue(
									(int) (100 * (ifsCy - Constants.IFS_CY_MIN) / (Constants.IFS_CY_MAX - Constants.IFS_CY_MIN)));
					fractalObject.setParameter(1, ifsCy);
				} else if (source.getName().equals("IFS_DEPTH")) {
					int depth = Integer.valueOf(source.getText());
					fractalObject.setParameter(2, depth);
				}
			} else if (fractalType == Constants.JULIATYPE01
					|| fractalType == Constants.JULIATYPE02
					|| fractalType == Constants.JULIATYPE03
					|| fractalType == Constants.JULIATYPE04
					|| fractalType == Constants.JULIATYPE05) {
				if (source.getName().equals("JULIA_CP")) {
					double cp = Double.valueOf(source.getText());
					int stepSpeed = (Integer) fractalObject.getParameter(3);
					sliderParameters
							.get(0)
							.setValue(
									(int) (stepSpeed
											* (cp - Constants.JULIA_CP_MIN) / (Constants.JULIA_CP_MAX - Constants.JULIA_CP_MIN)));
					fractalObject.setParameter(0, cp);
				} else if (source.getName().equals("JULIA_CQ")) {
					double cq = Double.valueOf(source.getText());
					int stepSpeed = (Integer) fractalObject.getParameter(3);
					sliderParameters
							.get(1)
							.setValue(
									(int) (stepSpeed
											* (cq - Constants.JULIA_CQ_MIN) / (Constants.JULIA_CQ_MAX - Constants.JULIA_CQ_MIN)));

					fractalObject.setParameter(1, cq);
				} else if (source.getName().equals("JULIA_STEP")) {
					double step = Double.valueOf(source.getText());
					int stepSpeed = (Integer) fractalObject.getParameter(3);
					sliderParameters
							.get(2)
							.setValue(
									(int) (stepSpeed
											* (step - Constants.JULIA_STEP_MIN) / (Constants.JULIA_STEP_MAX - Constants.JULIA_STEP_MIN)));
					fractalObject.setParameter(2, step);
				} else if (source.getName().equals("JULIA_STEP_SPEED")) {
					int stepSpeed = Integer.valueOf(source.getText());
					sliderParameters.get(0).setMaximum(stepSpeed);
					sliderParameters.get(1).setMaximum(stepSpeed);
					sliderParameters.get(2).setMaximum(stepSpeed);
					fractalObject.setParameter(3, stepSpeed);
					double cp = (Double) fractalObject.getParameter(0);
					sliderParameters
							.get(0)
							.setValue(
									(int) (stepSpeed
											* (cp - Constants.JULIA_CP_MIN) / (Constants.JULIA_CP_MAX - Constants.JULIA_CP_MIN)));
					double cq = (Double) fractalObject.getParameter(1);
					sliderParameters
							.get(1)
							.setValue(
									(int) (stepSpeed
											* (cq - Constants.JULIA_CQ_MIN) / (Constants.JULIA_CQ_MAX - Constants.JULIA_CQ_MIN)));
					double step = (Double) fractalObject.getParameter(2);
					sliderParameters
							.get(2)
							.setValue(
									(int) (stepSpeed
											* (step - Constants.JULIA_STEP_MIN) / (Constants.JULIA_STEP_MAX - Constants.JULIA_STEP_MIN)));
				}else if( (fractalType == Constants.LSSCALE01)){
					if (source.getName().equals("LSSCALE_SEED")) {
						String seed = source.getText();
						fractalObject.setParameter(0, seed);
					} else if (source.getName().equals("LSSCALE_REGULATION")) {
						String regulation = source.getText();
						fractalObject.setParameter(1, regulation);
					} else if (source.getName().equals("LSSCALE_ANGLE")) {
						double angle = Double.valueOf(source.getText());
						sliderParameters.get(0).setValue((int) (angle));
						fractalObject.setParameter(2, angle);
					} else if (source.getName().equals("LSSCALE_STEP")) {
						int step = Integer.valueOf(source.getText());
						fractalObject.setParameter(3, step);
					} else if (source.getName().equals("LSSCALE_DEPTH")) {
						int depth = Integer.valueOf(source.getText());
						fractalObject.setParameter(4, depth);
					}else{}					
				}else{
					//do something.
				}
			}
			frame.repaint();
		}
	}

	class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			if (fractalType == Constants.KOCHTYPE02
					|| fractalType == Constants.KOCHTYPE03
					|| fractalType == Constants.KOCHTYPE04) {
				if (source.getName().equals("KOCH_SCALE")) {
					double para01 = source.getValue()
							* Constants.KOCHTYPE02_MAX_SCALE / 100;
					fractalObject.setParameter(0, para01);
					textFields.get(0)
							.setText(Double.valueOf(para01).toString());
				}
			} else if (fractalType == Constants.LEAFTYPE) {
				if (source.getName().equals("LEAF_ANGLE1")) {
					int leafB = source.getValue();
					fractalObject.setParameter(0, leafB);
					textFields.get(0)
							.setText(Integer.valueOf(leafB).toString());
				} else if (source.getName().equals("LEAF_ANGLE2")) {
					int leafC = source.getValue();
					fractalObject.setParameter(1, leafC);
					textFields.get(1)
							.setText(Integer.valueOf(leafC).toString());
				} else if (source.getName().equals("LEAF_SCALE1")) {
					int leafScale1 = source.getValue();
					fractalObject.setParameter(2, leafScale1 / 10.0);
					textFields.get(2).setText(
							Double.valueOf(leafScale1 / 10.0).toString());
				} else if (source.getName().equals("LEAF_SCALE2")) {
					int leafScale2 = source.getValue();
					fractalObject.setParameter(3, leafScale2 / 10.0);
					textFields.get(3).setText(
							Double.valueOf(leafScale2 / 10.0).toString());
				}
			} else if ((fractalType == Constants.LSTYPE01)) {
				if (source.getName().equals("LS_ANGLE")) {
					double angle = Double.valueOf(source.getValue());
					fractalObject.setParameter(2, angle);
					textFields.get(2).setText(Double.valueOf(angle).toString());
				}
			} else if (fractalType == Constants.LSTYPE02
					|| fractalType == Constants.LSTYPE03) {
				if (source.getName().equals("LS_ANGLE")) {
					double angle = Double.valueOf(source.getValue());
					fractalObject.setParameter(3, angle);
					textFields.get(3).setText(Double.valueOf(angle).toString());
				}
			} else if (fractalType == Constants.LSTYPE04) {
				if (source.getName().equals("LS_ANGLE")) {
					double angle = Double.valueOf(source.getValue());
					fractalObject.setParameter(4, angle);
					textFields.get(4).setText(Double.valueOf(angle).toString());
				}
			} else if (fractalType == Constants.IFSJulia) {
				String string;
				FractalIFSJulia.count=0;
				fractalObject.setParameter(3,
						new byte[Constants.IFS_W][Constants.IFS_H]);
				fractalObject.initPixels();	//这里如果不清图像数组时，会保留所有图像痕迹，也是一种效果；
				if (source.getName().equals("IFS_CX")) {
					double ifsCx = Double.valueOf(source.getValue())
							* (Constants.IFS_CX_MAX - Constants.IFS_CX_MIN)
							/ 100 + Constants.IFS_CX_MIN;
					fractalObject.setParameter(0, ifsCx);
					string = Double.valueOf(ifsCx).toString();
					if (string.length() > 8) {
						string = string.substring(0, 8);
					}
					textFields.get(0).setText(string);
				} else if (source.getName().equals("IFS_CY")) {
					double ifsCy = Double.valueOf(source.getValue())
							* (Constants.IFS_CY_MAX - Constants.IFS_CY_MIN)
							/ 100 + Constants.IFS_CY_MIN;
					fractalObject.setParameter(1, ifsCy);
					string = Double.valueOf(ifsCy).toString();
					if (string.length() > 8) {
						string = string.substring(0, 8);
					}
					textFields.get(1).setText(string);
				}
			} else if (fractalType == Constants.JULIATYPE01
					|| fractalType == Constants.JULIATYPE02
					|| fractalType == Constants.JULIATYPE03
					|| fractalType == Constants.JULIATYPE04
					|| fractalType == Constants.JULIATYPE05) {
				String string;
				if (source.getName().equals("JULIA_CP")) {
					int stepSpeed = (Integer) fractalObject.getParameter(3);
					double cp = Double.valueOf(source.getValue())
							* (Constants.JULIA_CP_MAX - Constants.JULIA_CP_MIN)
							/ stepSpeed + Constants.JULIA_CP_MIN;
					fractalObject.setParameter(0, cp);
					string = Double.valueOf(cp).toString();
					textFields.get(0).setText(
							(string.length() > 10) ? string.substring(0, 10)
									: string);
				} else if (source.getName().equals("JULIA_CQ")) {
					int stepSpeed = (Integer) fractalObject.getParameter(3);
					double cq = Double.valueOf(source.getValue())
							* (Constants.JULIA_CQ_MAX - Constants.JULIA_CQ_MIN)
							/ stepSpeed + Constants.JULIA_CQ_MIN;
					fractalObject.setParameter(1, cq);
					string = Double.valueOf(cq).toString();
					textFields.get(1).setText(
							(string.length() > 10) ? string.substring(0, 10)
									: string);
				} else if (source.getName().equals("JULIA_STEP")) {
					int stepSpeed = (Integer) fractalObject.getParameter(3);
					double step = Double.valueOf(source.getValue())
							* (Constants.JULIA_STEP_MAX - Constants.JULIA_STEP_MIN)
							/ stepSpeed + Constants.JULIA_STEP_MIN;
					fractalObject.setParameter(2, step);
					string = Double.valueOf(step).toString();
					textFields.get(2).setText(
							(string.length() > 10) ? string.substring(0, 10)
									: string);
				}
			}else if((fractalType == Constants.LSSCALE01)){
				if (source.getName().equals("LSSCALE_ANGLE")) {
					double angle = Double.valueOf(source.getValue());
					fractalObject.setParameter(2, angle);
					textFields.get(2).setText(Double.valueOf(angle).toString());
				}else{}				
			}else{}
			frame.repaint();
		}
	}

	public ArrayList<JTextField> getTextFields() {
		return textFields;
	}

	public void setTextFields(ArrayList<JTextField> textFields) {
		this.textFields = textFields;
	}

	public JTextField getTextField(int index) {
		return textFields.get(index);
	}
}
