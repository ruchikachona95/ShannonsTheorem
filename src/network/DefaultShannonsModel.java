package network;
import java.text.DecimalFormat;
import java.util.Observable;

/***
 * This class extends the Observerable class indicating that it supports observers.
 * When changes are detected to snr or bw, this class invokes the setChanges() method 
 * inherited from the parent class Observable indicating that data has changed 
 * followed by the notifyObservers() methods (this method also is inherited 
 * from its parent class Observable)
 * notifyObservers() method invokes the update() method on all registered observers.
 * @author Ruchika Chona
 * 
 */
public class DefaultShannonsModel extends Observable {
	
	private double bandwidth;
	private double signalToNoise;
	
	public DefaultShannonsModel(){
		
	} // end constructor DefaultShannonsModel
	
	/***
	 * This method gets the bandwidth and returns it
	 * @return bandwidth
	 */
	public double getBandwidth(){
		return bandwidth;
	} // end method getBandwidth
	
	/***
	 * This method gets the signalToNoise and returns it.
	 * @return signalToNoise
	 */
	public double getSignalToNoise(){
		return signalToNoise;
	} // end method getSignalToNoise
	
	/***
	 * This method sets bw and calculates MDR
	 * @param bw
	 */
	public void setBandwidth(double bw){
		bandwidth = bw;
		calculateMDR();
	} // end method setBandwidth
	
	/***
	 * This method sets signalToNoise and calculates MDR
	 * @param snr
	 */
	public void setSignalToNoise(double snr){
		signalToNoise = snr;
		calculateMDR();
	} // end method setSignalToNoise
	
	/***
	 * The method calculateMDR
	 * invokes the setChanged() method inherited from the parent class Observable
	 * indicating that data content has changed
	 * setChanged() method followed by the notifyObservers() method which is inherited from Observable
	 * notifyObservers() method will invoke all the Observers' update() method
	 * @return MDR maximum data rate
	 */
	public double calculateMDR(){
		double MDR = maximumDataRate(bandwidth, signalToNoise);
		DecimalFormat df = new DecimalFormat("#0.00");
		String MDRinf = df.format(MDR);
		setChanged();
		notifyObservers(new String[]{ MDRinf, Double.toString(bandwidth),
				Double.toString(signalToNoise) });
		
		return MDR;
	} // end method calculateMDR
	
	/***
	 * This method applies hertz and signalToNoise to the fomular to calculates MDR
	 * @param hertz
	 * @param signalToNoise
	 * @return Maximum data rate
	 */
	public double maximumDataRate(double hertz, double signalToNoise){
		return hertz* (Math.log(1 + Math.pow(10, signalToNoise/10)) / Math.log(2));
	} // end method maximumDataRate

	public double getMaximumDataRate() {
		// TODO Auto-generated method stub
		return 0;
	}

} // end class DefaultShannonsModel
