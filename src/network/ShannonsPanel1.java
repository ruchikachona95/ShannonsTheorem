package network;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class ShannonsPanel1 extends ShannonsPanel {
	
	protected JTextField bandwidthText; // panel1's bw textField
	protected JTextField snrText;//panel1's snr textField
	
	public ShannonsPanel1(ShannonsController ctl){
		super(ctl);
	}
	
	/***
	 * method initGUI to invoke the panel1
	 * the panel1 will show 2 textfields to get snr and bw
	 * and 1 label to show the MDR result
	 */
	public void initGUI() {
		mdrLBL = new JLabel("Maximum Data Rate via Shannons Theorem = 0.00");
		bandwidthText = new JTextField(20);
		snrText = new JTextField(20);
		
		GridBagLayout l = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(l);
		
		//adding mdrLBL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.WEST;
		l.setConstraints(mdrLBL, c);
		this.add(mdrLBL);
		
		//adding bwLabel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		l.setConstraints(bandwidthLabel, c);
		this.add(bandwidthLabel);
		
		//adding bwTextfield
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		l.setConstraints(bandwidthText, c);
		this.add(bandwidthText);
		
		//adding snrLabel
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		l.setConstraints(snrLabel, c);
		this.add(snrLabel);
		
				
		//adding snrText
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		l.setConstraints(snrText, c);
		this.add(snrText);
		
		bandwidthText.addFocusListener(new FocusListener(){
			
			@Override
			public void focusGained(FocusEvent arg0){
				if(bandwidthText.getCaretListeners().length == 0){
					bandwidthText.addCaretListener(new CaretListener(){
						
						@Override
						public void caretUpdate(CaretEvent arg0){
							try{
								controller.setBandwidth(Double.parseDouble(bandwidthText.getText()));
							} catch(Exception ex){
								controller.setBandwidth(0);
							}
						}
					});
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0){
				for(CaretListener al : bandwidthText.getCaretListeners()){
					bandwidthText.removeCaretListener(al);
				}
			}
		});		
		
		snrText.addFocusListener(new FocusListener(){
			
			@Override
			public void focusGained(FocusEvent arg0){
				if(snrText.getCaretListeners().length == 0){
					snrText.addCaretListener(new CaretListener(){
						
						@Override
						public void caretUpdate(CaretEvent arg0){
							try{
								controller.setSignalToNoise(Double.parseDouble(snrText.getText()));
							} catch(Exception ex){
								controller.setSignalToNoise(0);
							}
						}
					});
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0){
				for(CaretListener al : snrText.getCaretListeners()){
					snrText.removeCaretListener(al);
				}
			}
		});		

	}
	
	/***
	 * when the model - DefaultShannonsModel changes its bw or snr settings,
	 * it invokes notifyObservers() method.
	 * the notifyObserves() method will invoke update() implicitly
	 * update() updates MDR in the mdrLBL label,
	 * sets new bw in bw TextField, new snr in snr TextField
	 * it will update all new information to observer
	 */	
	public void update(Observable o, Object arg) {

		String[] object = (String[])arg;
		
		mdrLBL.setText("Maximum Data Rate Via Shannons Theorem = " + object[0]);
		if(bandwidthText.getCaretListeners().length == 0){
			bandwidthText.setText(object[1]);
		}
		if(snrText.getCaretListeners().length == 0){
			snrText.setText(object[2]);
		}
	}
	
} // end class ShannonsPanel1


