package fractal.sunbowen.molychin;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuFrame extends MenuBar{
	private static final long serialVersionUID = 1023880977634770485L;
	private Menu fileMenu;
	private MenuItem saveMenu;
	private MenuItem exitMenu;
	private Menu setMenu;
	private MenuItem colorSetMenu;
	private Menu helpMenu;
	private MenuItem helpDocumentMenu;
	private MenuItem aboutUsMenu;

	MenuFrame() {
		this.fileMenu = new Menu("�ļ�");
		this.saveMenu = new MenuItem("�������ͼ");
		this.exitMenu = new MenuItem("�˳�");
		this.fileMenu.add(this.saveMenu);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.exitMenu);
		this.add(this.fileMenu);
		this.saveMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Saveing file...");
			}
		});
		this.exitMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
				//System.out.println("Exiting...");
			}
		});

		this.setMenu = new Menu("����");
		this.colorSetMenu = new MenuItem("��ɫ������");
		this.setMenu.add(this.colorSetMenu);
		this.add(this.setMenu);
		this.colorSetMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//......
			}
		});
		
		this.helpMenu = new Menu("����");
		this.helpDocumentMenu = new MenuItem("�����ĵ�");
		this.aboutUsMenu = new MenuItem("��������");
		this.helpMenu.add(this.helpDocumentMenu);
		this.helpMenu.add(this.aboutUsMenu);
		this.add(this.helpMenu);
		this.helpDocumentMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Help Document...");
			}
		});
		this.aboutUsMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("About Us...");
			}
		});
	}
}
