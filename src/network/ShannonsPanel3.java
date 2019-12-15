package network;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShannonsPanel3 extends ShannonsPanel {
	
	private JSpinner bandwidthSpinner;
	private SpinnerNumberModel bandwidthModel;
	private JSpinner.NumberEditor bandwidthEditor;
	private JSpinner snrSpinner;
	private SpinnerNumberModel snrModel;
	private JSpinner.NumberEditor snrEditor;
	private JLabel mdrValue;
	
	public ShannonsPanel3(ShannonsController ctl){
		super(ctl);
	}

	@Override
	public void update(Observable o, Object arg) {
		String[] object = (String[])arg;
		mdrValue.setText(object[0]);
		bandwidthModel.setValue((int)Double.parseDouble(object[1]));
		snrSpinner.getModel().setValue((int)Double.parseDouble(object[2]));
	}

	@Override
	public void initGUI() {
		mdrLBL = new JLabel("Maximum Data Rate via Shannons Theorem = ");
		
		mdrValue = new JLabel("0.00");
		mdrValue.setForeground(Color.blue);
		mdrValue.setBorder(new LineBorder(new Color(0, 0, 255)));
		mdrValue.setOpaque(true);
		
		bandwidthModel = new SpinnerNumberModel(0, 0, 10000, 1);
		bandwidthSpinner = new JSpinner(bandwidthModel);
		bandwidthEditor = new JSpinner.NumberEditor(bandwidthSpinner);
		
		snrModel = new SpinnerNumberModel(0, 0, 1000, 1);
		snrSpinner = new JSpinner(snrModel);
		snrEditor = new JSpinner.NumberEditor(snrSpinner);
		
		GridBagLayout l = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(l);
		
		//adding mdrLBL
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		l.setConstraints(mdrLBL, c);
		this.add(mdrLBL);
		
		//adding mdrValue
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		l.setConstraints(mdrValue, c);
		this.add(mdrValue);
		
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
		l.setConstraints(bandwidthSpinner, c);
		this.add(bandwidthSpinner);
		
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
		l.setConstraints(snrSpinner, c);
		this.add(snrSpinner);
		
		bandwidthSpinner.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent e){
				try{
					double bw = bandwidthModel.getNumber().doubleValue();
					controller.setBandwidth((double)bw);
				} catch(Exception ex){
					controller.setBandwidth(0);
				}
			}
		});
			
		snrSpinner.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent e){
				try{
					controller.setSignalToNoise(snrModel.getNumber().doubleValue());
				} catch(Exception ex){
					controller.setSignalToNoise(0);
				}
			}
		});
	}
			
}
