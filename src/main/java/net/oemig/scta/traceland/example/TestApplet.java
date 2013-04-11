package net.oemig.scta.traceland.example;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TestApplet extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2759714022201385873L;

	public void init() {
		// It is required but does not need anything.
	}

	// This method gets called when the applet is terminated
	// That's when the user goes to another page or exits the browser.
	public void stop() {
		// no actions needed here now.
	}

	// The standard method that you have to use to paint things on screen
	// This overrides the empty Applet method, you can't called it "display" for
	// example.

	public void paint(Graphics g) {
		// method to draw text on screen
		// String first, then x and y coordinate.
		// Create a simple XY chart
		XYSeries series = new XYSeries("XYGraph");
		series.add(1, 1);
		series.add(2, 2);
		series.add(3, 1);
		series.add(4, 9);
		series.add(5, 10);
		series.add(6, 60);
		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
				"x-axis", // x-axis Label
				"y-axis", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		chart.setAntiAlias(true);

		if (chart != null) {
			chart.draw((Graphics2D) g, getBounds()); // repaints the whole chart
		}

	}

}