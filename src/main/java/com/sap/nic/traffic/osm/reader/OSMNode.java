package com.sap.nic.traffic.osm.reader;
/**
 * @author  steve.hu@sap.com
 * @version created at：Aug 14, 2015 10:49:08 AM
 * 
 */
public class OSMNode extends OSMElement {

	private final double lat,lon; 
	
	
	public OSMNode(long id, double lat, double lon) {
		super(id);
		this.lat = lat;
		this.lon = lon;
	}
	public double getLat(){
		return this.lat;
	}
	public double getLon(){
		return this.lon;
	}

}
