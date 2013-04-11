package net.oemig.scta.traceland.example;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import net.oemig.scta.jfreechart.SctaItemLabelGenerator;
import net.oemig.scta.jfreechart.SctaRenderer;
import net.oemig.scta.jfreechart.SctaToolTipGenerator;
import net.oemig.scta.jfreechart.data.SctaDataset;
import net.oemig.scta.jfreechart.data.SctaDatasetBuilder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;

public class SctaTestApplet extends Applet {

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
//		XYSeries series = new XYSeries("XYGraph");
//		series.add(1, 1);
//		series.add(2, 2);
//		series.add(3, 1);
//		series.add(4, 9);
//		series.add(5, 10);
//		series.add(6, 60);
//		// Add the series to your data set
//		XYSeriesCollection dataset = new XYSeriesCollection();
//		dataset.addSeries(series);
//		// Generate the graph
//		JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
//				"x-axis", // x-axis Label
//				"y-axis", // y-axis Label
//				dataset, // Dataset
//				PlotOrientation.VERTICAL, // Plot Orientation
//				true, // Show Legend
//				true, // Use tooltips
//				false // Configure chart to generate URLs?
//				);
		XYDataset dataset = createSCTADataset("tracy");
		JFreeChart chart = ChartFactory.createXYLineChart("", "Quickness [%]","Successrate [%]",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		XYLineAndShapeRenderer renderer = new SctaRenderer();

		// background image
		URL picURL = getClass().getResource("/background.gif");
		try {
			Image image = ImageIO.read(picURL);
			chart.getPlot().setBackgroundImage(image);

		} catch (IOException e) {
			e.printStackTrace();
		}

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.getDomainAxis().setAutoRange(false);
		plot.getDomainAxis().setRange(new Range(0, 100));
		plot.getRangeAxis().setAutoRange(false);
		plot.getRangeAxis().setRange(new Range(0, 100));

		renderer.setBaseItemLabelGenerator(new SctaItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseToolTipGenerator(new SctaToolTipGenerator());
		
		renderer.setSeriesVisibleInLegend(0, false);
		renderer.setSeriesVisibleInLegend(1, false);

		chart.getXYPlot().setRenderer(renderer);

		chart.setAntiAlias(true);

		if (chart != null) {
			chart.draw((Graphics2D) g, getBounds()); // repaints the whole chart
		}

	}
	
	private SctaDataset createSCTADataset(String traceName) {
		return SctaDatasetBuilder.of(traceName).
				addItemToSeries(traceName, "Test ideal(Oe) SEAA13", 78.0, 68.0, 0.5, 2.0).
				addItemToSeries(traceName, "Improved wording", 69.0, 62.0, 1.0, 1.0).
				addItemToSeries(traceName, "Init", 62.0, 56.0, 1.0, 1.0).
				addEmptySeries("illusive").
				addItemToSeries("illusive", "Test illusive(OE) SEAA13", 72.0, 10.0, 1.0, 1.0).
				addItemToSeries("illusive", "Worse", 64.0, 15.0, 1.0, 1.0).
				addItemToSeries("illusive", "Init", 58.0, 23.0, 1.0, 1.0).
				addEmptySeries("inefficient").
				addItemToSeries("inefficient", "Test inefficient(OE) SEAA13", 15.0, 90.0, 1.0, 1.0).
				addItemToSeries("inefficient", "Change", 21.0, 68.0, 1.0, 1.0).
				addItemToSeries("inefficient", "Init", 33.0, 55.0, 1.0, 1.0).
				addEmptySeries("ineffective").
				addItemToSeries("ineffective", "Test ineffective(OE) SEAA13", 15.0, 10.0, 1.0, 1.0).
				addItemToSeries("ineffective", "Change", 15.0, 22.0, 1.0, 1.0).
				addItemToSeries("ineffective", "Init", 27.0, 42.0, 1.0, 1.0).
				build();
		
			//illusive ineffective inefficient
	}


}