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
	public void addMember(String type, long ref, String role){
		members.add(new Member(type.charAt(0), ref, role));
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
		public char getType(){
			return this.type;
		}
		public long getRef(){
			return this.ref;
		}
		public String getRole(){
			return role;
		}
		
		
	}
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append( "id:"+this.getId()+"\n");
		for (Member member : members) {
			res.append("member:{type:"+member.getType()+",ref:"+member.getRef()+",role:"+member.getRole()+"}\n");
		}
		if (!this.getTags().isEmpty())
        {
            res.append(tagsToString());
            res.append("\n");
        }
		
		
		return res.toString();
	}

}
