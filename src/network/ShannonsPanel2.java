package network;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShannonsPanel2 extends ShannonsPanel {
	
	private JSlider bandwidthSlider;
	private JSlider snrSlider;
	
	public ShannonsPanel2(ShannonsController ctl){
		super(ctl);
	}

	@Override
	public void update(Observable o, Object arg) {
		String[] object = (String[])arg;
		mdrLBL.setText("Maximum Data Rate Via Shannons Theorem = " + object[0]);
		bandwidthSlider.setValue((int)Double.parseDouble(object[1]));
		snrSlider.setValue((int)Double.parseDouble(object[2]));
	}

	@Override
	public void initGUI() {
		mdrLBL = new JLabel("Maximum Data Rate via Shannons Theorem = 0.00");
		
		bandwidthSlider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 0);
		snrSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);
		
		bandwidthSlider.setMajorTickSpacing(10000);
		bandwidthSlider.setMinorTickSpacing(10000);
		bandwidthSlider.setPaintLabels(true);
		
		snrSlider.setMajorTickSpacing(1000);
		snrSlider.setMinorTickSpacing(1000);
		snrSlider.setPaintLabels(true);
		
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
		
		//adding bwText
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		l.setConstraints(bandwidthSlider, c);
		this.add(bandwidthSlider);
		
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
		l.setConstraints(snrSlider, c);
		this.add(snrSlider);
		
		bandwidthSlider.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent e){
				try{
					controller.setBandwidth((double)bandwidthSlider.getValue());
				} catch(Exception ex){
					controller.setBandwidth(0);
				}
			}
		});
			
		snrSlider.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent e){
				try{
					controller.setSignalToNoise((double)snrSlider.getValue());
				} catch(Exception ex){
					controller.setSignalToNoise(0);
				}
			}
		});
	}
			
}
