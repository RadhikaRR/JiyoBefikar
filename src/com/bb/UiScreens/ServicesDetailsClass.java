package com.bb.UiScreens;

import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.CustomClasses.CustomUIScreens;
import com.bb.control.MainScreenClass;

public class ServicesDetailsClass extends MainScreenClass {
	
	LabelField labelName, textName, labelProduct, textProduct, labelDate, textDate;

	public ServicesDetailsClass(String screenTitle) {
		super(screenTitle);
		
		VerticalFieldManager vfm = new VerticalFieldManager();
		
		HorizontalFieldManager h1 = CustomUIScreens.getHorizontalFieldManager();
		h1.setMargin(5, 5, 10, 5);
		labelName = new LabelField("Toll Free Numbers :");
		textName = new LabelField("1800-233-7272\n1800-209-7272\n1800-103-7272\n");
		h1.add(labelName);
		h1.add(textName);
		
		HorizontalFieldManager h2 = CustomUIScreens.getHorizontalFieldManager();
		h2.setMargin(10, 5, 10, 5);
		labelProduct = new LabelField("Email Id :");
		textProduct = new LabelField("customercare@bajajallianz.co.in");
		h2.add(labelProduct);
		h2.add(textProduct);
		
		HorizontalFieldManager h3 = CustomUIScreens.getHorizontalFieldManager();
		h3.setMargin(10, 5, 10, 5);
		labelDate = new LabelField("Online Chat :");
		textDate = new LabelField("www.bajajallianz.com");
		h3.add(labelDate);
		h3.add(textDate);		
		
		vfm.add(h1);
		vfm.add(h2);	
		
		add(vfm);		
	}
}
