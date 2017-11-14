package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;

@ManagedBean
@ViewScoped
public class ChartView implements Serializable {

	private static final long serialVersionUID = 11L;

	private LineChartModel zoomModel;

	@Default
	@SuppressWarnings({ "unused", "cdi-ambiguous-dependency" })
	@Inject
	private PersonFacade personFacade;

	@PostConstruct
	public void init() {
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");
		LinkedHashMap<Object, Number> linkedHashMap = new LinkedHashMap<Object, Number>();
		linkedHashMap.put(1, 450);
		linkedHashMap.put(2, 462);
		linkedHashMap.put(3, 500);
		linkedHashMap.put(4, 530);
		createZoomModel(linkedHashMap);
	}

	public LineChartModel getZoomModel() {
		return zoomModel;
	}

	public void increment() {
		number++;
		LinkedHashMap<Object, Number> linkedHashMap = personFacade.getMap();
		// LinkedHashMap<Object, Number> linkedHashMap2 = new LinkedHashMap<Object,
		// Number>();
		//
		// for (int i = 0; i < linkedHashMap.size(); i++) {
		// System.out.println("i" + i + " :" + linkedHashMap.get(i));
		// linkedHashMap2.put(i, linkedHashMap.get(i));
		//
		// }
		System.out.println(linkedHashMap.size() + " is size");
		createZoomModel(linkedHashMap);
	}

	private void createZoomModel(LinkedHashMap<Object, Number> linkedHashMap) {
		zoomModel = initLinearModel2(linkedHashMap);
		zoomModel.setTitle("PacketCounter");
		zoomModel.setZoom(true);
		zoomModel.setLegendPosition("e");
		Axis yAxis = zoomModel.getAxis(AxisType.Y);
		yAxis.setLabel("PacketCounter");
		Axis xAxis = zoomModel.getAxis(AxisType.X);
		xAxis.setLabel("Time");
		// yAxis.setMax(550);
	}

	private LineChartModel initLinearModel2(LinkedHashMap<Object, Number> linkedHashMap) {
		LineChartModel model = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("PacketCounter");
		series1.setData(linkedHashMap);
		model.addSeries(series1);
		return model;
	}

	private int number = 0;

	public int getNumber() {
		return number;
	}
}
