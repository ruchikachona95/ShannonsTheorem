package network;
import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JFrame;


/***
 * The class implements Shannon's Theorem GUI
 * 
 * It will create the frame window of the application.
 * It creates three panels added to the frame window.
 * The first panel uses JTextField class.
 * The second panel uses JSlider class.
 * The third panel uses JSpinner class.
 * 
 * @author Ruchika Chona
 */
public class ShannonsTheorem implements ShannonsController{
	
	/** The frame which instance the JFrame */
	private JFrame appFrame = new JFrame(); // the application frame
	/** Setting the window height */
	private int height = 650; // set window's height
	/** Setting the window width */
	private int width = 450; // set window's width
	/** The model which instances the ShannonsModel */
	private ShannonsPanel[] showPanel = new ShannonsPanel[3]; // declare a panel array
	private DefaultShannonsModel model = new DefaultShannonsModel(); // call the default model
	
	/***
	 * Default constructor without argument.
	 * initial three panel and add observer for each panel
	 */
	public ShannonsTheorem(){
		
		showPanel[0] = new ShannonsPanel1(this);
		showPanel[1] = new ShannonsPanel2(this);
		showPanel[2] = new ShannonsPanel3(this);
		
		for(int i=0; i<showPanel.length; i++){
			addObserver(showPanel[i]);
		}
		
	} // end constructor ShannonsTheorem
	
	@Override
	public void setBandwidth(double bw){
		model.setBandwidth(bw);
	} // end method setBandwidth
	
	@Override
	public void setSignalToNoise(double snr){
		model.setSignalToNoise(snr);
	} // end method setSignalToNoise
	
	@Override
	public void addObserver(Observer o){
		model.addObserver(o);
	} // end method addObserver 
	
	/***
	 * Initial the GUI window
	 * It is 3x1 grid layout, size is from width and height
	 * add 3 panels and set the window visible 
	 */
	public void initGUI(){
		appFrame.setLayout(new GridLayout(3,1));
		appFrame.setSize(width, height);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i=0; i < showPanel.length; i++){
			appFrame.add(showPanel[i]);
		}
		
		appFrame.setVisible(true);
		
	} // end method initGUI

} // end class ShannonsTheorem
