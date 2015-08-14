package com.sap.nic.traffic.osm.reader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author  steve.hu@sap.com
 * @version created at：Aug 14, 2015 10:55:55 AM
 * 
 */
public class OSMReader {

	private File osmFile;
	
	private final ArrayList<OSMNode> nodeList = new ArrayList<OSMNode>();
	private final ArrayList<OSMWay> wayList = new ArrayList<OSMWay>();
	
	public ArrayList<OSMNode> getNodeList() {
		return nodeList;
	}

	public ArrayList<OSMWay> getWayList() {
		return wayList;
	}

	public OSMReader(File osmFile) {
		this.osmFile = osmFile;
	}

	
	public OSMReader(String filePath) {
		File file = new File(filePath);
		this.osmFile = file;
	}
	
	public void read() {
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(osmFile), 50000);
			XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
			XMLStreamReader parser = xmlFactory.createXMLStreamReader(is);
			while (parser.hasNext()) {
				int point = parser.next();
				switch (point) {
					case XMLStreamConstants.START_ELEMENT : {
						String eleName = parser.getName().toString();
						String idStr = parser.getAttributeValue(null, "id");
						long id;
						if (idStr != null) {
							id = Long.parseLong(idStr);
							switch (eleName.charAt(0)) {
								case 'n' : {
									double lat = Double.parseDouble(parser.getAttributeValue(null, "lat"));
									double lon = Double.parseDouble(parser.getAttributeValue(null, "lon"));
									OSMNode node = new OSMNode(id, lat, lon);
									
									int event = parser.nextTag();
									while (event != XMLStreamReader.END_DOCUMENT && parser.getLocalName().equals("tag"))
									{
										if (event == XMLStreamConstants.START_ELEMENT)
										{
											String k = parser.getAttributeValue(null, "k");
											String v = parser.getAttributeValue(null, "v");
											node.setTag(k, v);
										}
										event = parser.nextTag();
									}
									nodeList.add(node);
									break;
								}
								case 'w': {
									OSMWay way = new OSMWay(id);
									int event = parser.nextTag();
									String localName = parser.getLocalName();
									while (event != XMLStreamReader.END_DOCUMENT && (localName.equals("nd") || localName.equals("tag")))
									{
							            if (event == XMLStreamConstants.START_ELEMENT)
							            {
							            	if (localName.equals("nd"))
							            	{
								                // read node reference
								                String ref = parser.getAttributeValue(null, "ref");
								                way.appendNode(Long.parseLong(ref));
							            	}
							            	else if (localName.equals("tag"))
							            	{
							            		String k = parser.getAttributeValue(null, "k");
												String v = parser.getAttributeValue(null, "v");
												way.setTag(k, v);
							            	}

							            }

							            event = parser.nextTag();
									}
									wayList.add(way);
									break;
								}
							}
						}
					}
				}
			}
			
			is.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OSMReader reader = new OSMReader("/Users/i075903/Downloads/map.osm");
		reader.read();
		System.out.println(reader.getNodeList().size());
		for (int i = 0; i < reader.getWayList().size(); i++) {
			OSMWay way = reader.getWayList().get(i);
			System.out.println(way.toString());
		}
	}

}
