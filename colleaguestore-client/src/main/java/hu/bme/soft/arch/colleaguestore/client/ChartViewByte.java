package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@ViewScoped
public class ChartViewByte implements Serializable {

	private static final long serialVersionUID = 12L;

	private static LineChartModel zoomModelByte;

	public LineChartModel getZoomModelByte() {
		return zoomModelByte;
	}

	@PostConstruct
	public void init() {
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");
		LinkedHashMap<Object, Number> linkedHashMap = new LinkedHashMap<Object, Number>();
		linkedHashMap.put(1, 450);
		linkedHashMap.put(2, 462);
		linkedHashMap.put(3, 500);
		linkedHashMap.put(4, 530);
		createZoomModelByte(linkedHashMap);
	}

	public void increment(LinkedHashMap<Object, Number> linkedHashMapByte) {
		createZoomModelByte(linkedHashMapByte);
	}

	private void createZoomModelByte(LinkedHashMap<Object, Number> linkedHashMap) {
		LineChartModel model = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("ByteCounter");
		series1.setData(linkedHashMap);
		model.addSeries(series1);

		zoomModelByte = model;
		zoomModelByte.setTitle("ByteCounter");
		zoomModelByte.setZoom(true);
		zoomModelByte.setLegendPosition("e");
		Axis yAxis = zoomModelByte.getAxis(AxisType.Y);
		yAxis.setLabel("ByteCounter");
		Axis xAxis = zoomModelByte.getAxis(AxisType.X);
		xAxis.setLabel("Time");
	}

}
