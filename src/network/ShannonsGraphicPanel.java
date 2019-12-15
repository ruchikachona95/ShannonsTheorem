package network;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Program Description:
 * 
 * Program will display a G.U.I for the user to input the <i>bandwidth</i>, and <i>signalToNoise</i> ratio.
 * User has the option of either inputing the amounts manually through the keyboard, or using the sliders to adjust the values.
 * When the user interacts with the text box of the sliders, this will adjust the graph at the bottom of the window, relative to their values.
 * 
 * @author    Ruchika Chona
 * Assignment: Lab Assignment 3 - Shannons Theorem (MVC) 
 * 
 *	<i>ShannonsGraphicsPanel</i> class is part of the view for the (MVC).
 *	<i>ShannonsGraphicsPanel</i> will outline the graphics view that controls the values of bandwidth, signal to noise, and max data rate graph.
 *	<i>ShannonsGraphicsPanel</i> extends JPanel to implement panel methods and attributes.
 *	<i>ShannonsGraphicsPanel</i> implements Observer to update the graph values.
 */
@SuppressWarnings("serial")
public class ShannonsGraphicPanel extends JPanel implements Observer{
	//Copy of model for accessing the set and get methods for graph adjustment.
	private DefaultShannonsModel mdl;
	
	//Decimal formating for the display of Max data value.
	//Round to two decimal places.
	private DecimalFormat decFormt;

	
	/* CONSTRUCTOR	--------------------------------------------------	*/
	/**
	 *	Constructor initializes <i>ShannonsGraphicsPanel</i>, object of type <i>ShannonsModel</i>, and DecimalFormat object.
	 */
	public ShannonsGraphicPanel(){
		mdl = new DefaultShannonsModel();
		decFormt = new DecimalFormat("#.00");
	}
	
	/**
	 *	This method will repaint the graph determined by the values in <i>ShannonsModel</i>.
	 *	@param observable is the notification from the observer.
	 *	@param arg needed for object notification.
	 */
	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof DefaultShannonsModel) {
			this.mdl = (DefaultShannonsModel)observable;//To access the variables in ShannonsModel.
			this.repaint();//Calls to repaint the JPanel.
		}
	}//End update()
	
	/**
	 *	This method will determine when a change occurs in one of the text fields.
	 *	When a user enters a value and hits enter, the value is passed to <i>ShannonsController</i> for value storage.
	 */
	@Override
	public void paintComponent(Graphics g){
		//Font metrics determines the size of the drawn string.
		FontMetrics fontMetrics = g.getFontMetrics();
		
		//Maximum values for determining the graph width.
		double maxBandwidth = 3000;
		double maxSignalToNoise = 30;
		double maxMDR = 29901.68;
		
		//Returns a copy of bandwidth, signal to noise, and max data rate into a double variable.
		double bandwidth = mdl.getBandwidth();
		double signalToNoise = mdl.getSignalToNoise();
		double maxDataRate = mdl.getMaximumDataRate();
		
		//Calculates the string width from the graphics display.
		int stringSizeBandwidth = fontMetrics.stringWidth("bw (hertz):" + mdl.getBandwidth());
		int stringSizeSignalToNoise = fontMetrics.stringWidth("snr (db):" + mdl.getSignalToNoise());
		int stringSizeMDR = fontMetrics.stringWidth("mdr (bps):" + decFormt.format(mdl.getMaximumDataRate()));
		
		//Calculates the available width on screen to graph the bar charts.
		int widthAvailableBandwidth = this.getWidth() - stringSizeBandwidth;
		int widthAvailableSTN = this.getWidth() - stringSizeSignalToNoise;
		int widthAvailableMDR = this.getWidth() - stringSizeMDR;
		
		//Calculates the width of the bar graph to be painted onto the panel.
		int bandwidthBarWidth = (int)(bandwidth / maxBandwidth * widthAvailableBandwidth);
		int signalToNoiseBarWidth = (int)(signalToNoise / maxSignalToNoise * widthAvailableSTN);
		int maxDataRateBarWidth = (int)(maxDataRate / maxMDR * widthAvailableMDR);
	
		//Sets the font and font size of string that will be drawn.
		g.setFont(new Font("SansSerif", Font.PLAIN, 12));

		//Main rectangle is colored white and is the background of the panel
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		//Creates the title and bar graph for bandwidth.
		g.setColor(Color.black);
		g.drawString("bw (hertz):" + mdl.getBandwidth(), 0, 15);
		g.setColor(Color.yellow);
		g.drawRect(stringSizeMDR, 0, bandwidthBarWidth, this.getHeight()/3);
		g.fillRect(stringSizeMDR, 0, bandwidthBarWidth, this.getHeight()/3);

		//Creates the title and bar graph for the signal to noise.
		g.setColor(Color.black);
		g.drawString("snr (db):" + mdl.getSignalToNoise(), 0, this.getHeight()/2+5);
		g.setColor(Color.cyan);
		g.drawRect(stringSizeMDR, this.getHeight()/3, signalToNoiseBarWidth, this.getHeight()/3);
		g.fillRect(stringSizeMDR, this.getHeight()/3, signalToNoiseBarWidth, this.getHeight()/3);
		
		//Creates the title and bar graph for the max data rate.
		g.setColor(Color.black);
		g.drawString("mdr (bps):" + decFormt.format(mdl.getMaximumDataRate()), 0, this.getHeight()-5);
		g.setColor(Color.green);
		g.drawRect(stringSizeMDR, this.getHeight()-this.getHeight()/3-1, maxDataRateBarWidth, this.getHeight()/3);
		g.fillRect(stringSizeMDR, this.getHeight()-this.getHeight()/3-1, maxDataRateBarWidth, this.getHeight()/3);
	}//paintComponent()
}//End shnnonsGraphicPanel
