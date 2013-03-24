package org.jakub1221.customitems.objects;

import java.util.ArrayList;

public class CustomRecipe {
	
private CustomID s1;
private CustomID s2;
private CustomID s3;
private CustomID s4;
private CustomID s5;
private CustomID s6;
private CustomID s7;
private CustomID s8;
private CustomID s9;

public CustomRecipe(CustomID _s1,CustomID _s2,CustomID _s3,CustomID _s4,CustomID _s5,CustomID _s6,CustomID _s7,CustomID _s8,CustomID _s9){
	s1=_s1;
	s2=_s2;
	s3=_s3;
	s4=_s4;
	s5=_s5;
	s6=_s6;
	s7=_s7;
	s8=_s8;
	s9=_s9;
}

@SuppressWarnings("rawtypes")

public ArrayList getData(){
	
	ArrayList<CustomID> data = new ArrayList<CustomID>();
	data.add(s1);
	data.add(s2);
	data.add(s3);
	data.add(s4);
	data.add(s5);
	data.add(s6);
	data.add(s7);
	data.add(s8);
	data.add(s9);
	return data;
	
}

}
