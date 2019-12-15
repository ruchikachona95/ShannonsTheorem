package network;
import java.util.Observer;

/***
 * There are three methods in this interface
 * addObserver()
 * setBandwidth()
 * setSignalToNoise()
 * 
 * The ShannonsTheorem is a controller which implements this interface
 * 
 * @author Ruchika Chona
 *
 */
public interface ShannonsController {
	
	public void addObserver(Observer o);
	
	/***
	 * This method is used to set bw to the argument
	 * @param bandwidthInHertz the setting value to bw
	 */
	public void setBandwidth(double bandwidthInHertz);
	
	/***
	 * This method is used to set snr to this argument
	 * @param snrInDB the setting value to snr
	 */
	public void setSignalToNoise(double snrInDB);

} // class ShannonsController
