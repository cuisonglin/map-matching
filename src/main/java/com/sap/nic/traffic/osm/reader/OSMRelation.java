package com.sap.nic.traffic.osm.reader;

import java.util.ArrayList;


/**
 * @author DengrongGuan
 *
 */
public class OSMRelation extends OSMElement{
	private ArrayList<Member> members = new ArrayList<Member>();
	public OSMRelation(long id){
		super(id);
	}
	public void addMember(char type, long ref, String role){
		members.add(new Member(type, ref, role));
	}
	
	public class Member{
		public static final char TYPE_WAY = 'w';
		public static final char TYPE_NODE = 'n';
		private char type;
		private long ref;
		private String role;
		public Member(char type,long ref, String role){
			this.type = type;
			this.ref = ref;
			this.role = role;
		}
		
		
	}

}
