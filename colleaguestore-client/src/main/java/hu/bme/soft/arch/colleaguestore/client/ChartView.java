package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@ViewScoped
public class ChartView implements Serializable {

	private static final long serialVersionUID = 11L;

	private LineChartModel zoomModel;

	@PostConstruct
	public void init() {
		createZoomModel();
	}

	public LineChartModel getZoomModel() {
		return zoomModel;
	}

	private void createZoomModel() {
		zoomModel = initLinearModel();
		zoomModel.setTitle("PacketCounter");
		zoomModel.setZoom(true);
		zoomModel.setLegendPosition("e");
		Axis yAxis = zoomModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		model.addSeries(series1);
		model.addSeries(series2);

		return model;
	}

	private int number;

	public int getNumber() {
		return number;
	}

	public void increment() {
		number++;
		if (number % 2 == 0) {
			createZoomModel();
		} else {
			zoomModel.getSeries().clear();
			LineChartSeries series1 = new LineChartSeries();
			series1.setLabel("Series 1");

			series1.set(1, 2);
			series1.set(2, 1);
			series1.set(3, 3);
			series1.set(4, 6);
			series1.set(5, 8);
			zoomModel.getSeries().add(series1);
		}
	}

}
