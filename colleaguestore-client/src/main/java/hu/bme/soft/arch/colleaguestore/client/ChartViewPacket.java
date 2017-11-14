package hu.bme.soft.arch.colleaguestore.client;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import hu.bme.soft.arch.colleaguestore.facade.PersonFacade;

@ManagedBean
@ViewScoped
public class ChartViewPacket implements Serializable {

	private static final long serialVersionUID = 11L;

	private LineChartModel zoomModelPacket;

	public LineChartModel getZoomModelPacket() {
		return zoomModelPacket;
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
		createZoomModelPacket(linkedHashMap);
	}

	public LineChartModel getZoomModel() {
		return zoomModelPacket;
	}

//    @ManagedProperty(value = "counter")
	@Inject
	CounterView counterview;

	public void increment() {
		if (counterview == null) {
			System.out.println("////////////////////////////////////////");
			return;
		}
		
		if (counterview.linkedHashMapPacket != null) {
			System.out.println(counterview.linkedHashMapPacket.size() + " is size!!!!!!!");
			createZoomModelPacket(counterview.linkedHashMapPacket);
		}

	}

	private void createZoomModelPacket(LinkedHashMap<Object, Number> linkedHashMap) {
		zoomModelPacket = initLinearModelPacket(linkedHashMap);
		zoomModelPacket.setTitle("PacketCounter");
		zoomModelPacket.setZoom(true);
		zoomModelPacket.setLegendPosition("e");
		Axis yAxis = zoomModelPacket.getAxis(AxisType.Y);
		yAxis.setLabel("PacketCounter");
		Axis xAxis = zoomModelPacket.getAxis(AxisType.X);
		xAxis.setLabel("Time");
	}

	private LineChartModel initLinearModelPacket(LinkedHashMap<Object, Number> linkedHashMap) {
		LineChartModel model = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("PacketCounter");
		series1.setData(linkedHashMap);
		model.addSeries(series1);
		return model;
	}
}
