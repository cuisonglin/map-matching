package com.sap.nic.traffic.osm.reader;

import java.util.ArrayList;

/**
 * @author  steve.hu@sap.com
 * @version created at：Aug 14, 2015 11:29:10 AM
 * 
 */
public class OSMWay extends OSMElement {
	
	private final ArrayList<Long> nodeList = new ArrayList<Long>();
	
	
	public void appendNode(Long node) {
		nodeList.add(node);
	}
	
	public Long getNode(int index) {
		return nodeList.get(index);
	}
	
	
	public OSMWay(long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
   
	@Override
	public String toString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Way: ");
        txt.append(getId());
        txt.append(" ");
        if (!getTags().isEmpty())
        {
            txt.append("\n");
            txt.append(tagsToString());
        }
        return txt.toString();
	}

}
