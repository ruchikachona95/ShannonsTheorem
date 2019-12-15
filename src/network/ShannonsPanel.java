package network;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

/***
 * ShannonsPanel class
 * When instantiated the overloaded constructor accepts an object reference 
 * to the controller as an argument.
 * The view then adds itself as an Observer of model changes by 
 * invoking the controller addObserver() method.
 * This class also implements the Observer interface indicating 
 * that it is an observer of the model.
 * 
 * @author Ruchika Chona
 */
public abstract class ShannonsPanel extends JPanel implements Observer{
	
	protected ShannonsController controller; // each panel has a controller
	
	protected JLabel mdrLBL;//common mdr label
	protected JLabel bandwidthLabel = new JLabel("Bandwidth (in hertz):");//common bw label
	protected JLabel snrLabel = new JLabel("SignalToNoise (in DB):"); // common snr label
	
	/***
	 * constructor with a controller object as the argument.
	 * it will call initGUI to initial GUI.
	 * @param ctl
	 */
	public ShannonsPanel(ShannonsController ctl){
		this.controller = ctl;
		initGUI();
	} // end constructor ShannonsPanel
	
	/***
	 * method gets the minimum dimension and returns it
	 */
	public Dimension getMinimumSize(){
		Dimension di = null;
		return di;
	} // end method getMinimumSize
	
	/***
	 * method gets the preferred dimension and returns it
	 */
	public Dimension getPreferredSize(){
		Dimension di = null;
		return di;
	} // end method getPreferredSize
	
	/***
	 * method gets the maximum dimension and returns it
	 */
	public Dimension getMaximumSize(){
		Dimension di = null;
		return di;
	} // end method getMaximumSize
	
	/***
	 * abstract method update,update the arg to 0
	 */
	public abstract void update(Observable o, Object arg);
	
	/***
	 * abstract method initGUI to invoke the GUI
	 */
	public abstract void initGUI();
	
} // end class ShannonsPanel
