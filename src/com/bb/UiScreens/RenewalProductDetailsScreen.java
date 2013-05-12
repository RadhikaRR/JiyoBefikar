package com.bb.UiScreens;

import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.CustomClasses.CustomUIScreens;
import com.bb.control.MainScreenClass;

public class RenewalProductDetailsScreen extends MainScreenClass {
	
	LabelField labelName, textName, labelProduct, textProduct, labelDate, textDate;

	public RenewalProductDetailsScreen(String screenTitle) {
		super(screenTitle);
		
		VerticalFieldManager vfm = new VerticalFieldManager();
		
		HorizontalFieldManager h1 = CustomUIScreens.getHorizontalFieldManager();
		h1.setMargin(5, 5, 10, 5);
		labelName = new LabelField("Next Due Date :");
		textName = new LabelField("14-FEB-2012");
		h1.add(labelName);
		h1.add(textName);
		
		HorizontalFieldManager h2 = CustomUIScreens.getHorizontalFieldManager();
		h2.setMargin(10, 5, 10, 5);
		labelProduct = new LabelField("Renewal Amount :");
		textProduct = new LabelField("100000");
		h2.add(labelProduct);
		h2.add(textProduct);
		
		HorizontalFieldManager h3 = CustomUIScreens.getHorizontalFieldManager();
		h3.setMargin(10, 5, 10, 5);
		labelDate = new LabelField("Payment Frequency :");
		textDate = new LabelField("00000000");
		h3.add(labelDate);
		h3.add(textDate);		
		
		vfm.add(h1);
		vfm.add(h2);
		vfm.add(h3);		
		
		add(vfm);	
	}
}
