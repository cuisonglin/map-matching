package com.sap.nic.traffic.osm.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author  steve.hu@sap.com
 * @version created at：Aug 14, 2015 9:39:12 AM
 * 
 */
public abstract class OSMElement {
	
    private final long id;
	private final Map<String,String> tags = new HashMap<String, String>();
	
	
	public OSMElement(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}


	public Map<String, String> getTags() {
		return tags;
	}
	
	
	
	public void setTag(String key, String value) {
		tags.put(key, value);
	}
	
	public void setTags(Map<String, String> newTags) {
		tags.clear();
        if (newTags != null)
            for (Entry<String, String> e : newTags.entrySet())
            {
                setTag(e.getKey(), e.getValue());
            }
	}


    protected String tagsToString()
    {
        if (tags.isEmpty())
            return "<empty>";

        StringBuilder tagTxt = new StringBuilder();
        for (Map.Entry<String, String> entry : tags.entrySet())
        {
            tagTxt.append(entry.getKey());
            tagTxt.append("=");
            tagTxt.append(entry.getValue());
            tagTxt.append("\n");
        }
        return tagTxt.toString();
    }
	
	
}
