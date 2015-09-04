package com.sap.nic.traffic.osm.storage;

import com.sap.nic.traffic.osm.reader.OSMNode;

public class Segment {
	private OSMNode from_node;
	private OSMNode to_node;
	
	public OSMNode getFrom_node() {
		return from_node;
	}

	public void setFrom_node(OSMNode from_node) {
		this.from_node = from_node;
	}

	public OSMNode getTo_node() {
		return to_node;
	}

	public void setTo_node(OSMNode to_node) {
		this.to_node = to_node;
	}

	public double distanceToNode(double lat, double lng){
		double distance = 0;
		double a,b,c;
		double x1 = from_node.getLat();
		double y1 = from_node.getLon();
		double x2 = to_node.getLat();
		double y2 = to_node.getLon();
		a = lineSpace(x1, y1, x2, y2);
		return distance;
	}
	private double pointToLine(int x1, int y1, int x2, int y2, int x0, 
            int y0) { 
        double space = 0; 
        double a, b, c; 
        a = lineSpace(x1, y1, x2, y2);// 线段的长度 
        b = lineSpace(x1, y1, x0, y0);// (x1,y1)到点的距离 
        c = lineSpace(x2, y2, x0, y0);// (x2,y2)到点的距离 
        if (c <= 0.000001 || b <= 0.000001) { 
            space = 0; 
            return space; 
        } 
        if (a <= 0.000001) { 
            space = b; 
            return space; 
        } 
        if (c * c >= a * a + b * b) { 
            space = b; 
            return space; 
        } 
        if (b * b >= a * a + c * c) { 
            space = c; 
            return space; 
        } 
        double p = (a + b + c) / 2;// 半周长 
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积 
        space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高） 
        return space; 
    } 

    // 计算两点之间的距离 
    private double lineSpace(double x1, double y1, double x2, double y2) { 
        double lineLength = 0; 
        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) 
                * (y1 - y2)); 
        return lineLength; 

    } 

}
