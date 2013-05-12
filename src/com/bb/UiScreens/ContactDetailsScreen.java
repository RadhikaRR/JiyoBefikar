package com.bb.UiScreens;

import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.CustomClasses.CustomUIScreens;
import com.bb.control.MainScreenClass;

public class ContactDetailsScreen extends MainScreenClass {

	LabelField labelPadd, textPadd, labelMadd, textMadd, labelMob, textMob,
			labelLL, textLL, labelEmail, textEmail;

	public ContactDetailsScreen(String screenTitle) {
		super(screenTitle);

		VerticalFieldManager vfm = new VerticalFieldManager();

		HorizontalFieldManager h1 = CustomUIScreens.getHorizontalFieldManager();
		h1.setMargin(5, 5, 10, 5);
		labelPadd = new LabelField("Permanent Address :");
		textPadd = new LabelField("Pune");
		h1.add(labelPadd);
		h1.add(textPadd);

		HorizontalFieldManager h2 = CustomUIScreens.getHorizontalFieldManager();
		h2.setMargin(10, 5, 10, 5);
		labelMadd = new LabelField("Mailing Address :");
		textMadd = new LabelField("www.google.com");
		h2.add(labelMadd);
		h2.add(textMadd);

		HorizontalFieldManager h3 = CustomUIScreens.getHorizontalFieldManager();
		h3.setMargin(10, 5, 10, 5);
		labelMob = new LabelField("Mobile Number :");
		textMob = new LabelField("9876543210");
		h3.add(labelMob);
		h3.add(textMob);

		HorizontalFieldManager h4 = CustomUIScreens.getHorizontalFieldManager();
		h4.setMargin(10, 5, 10, 5);
		labelLL = new LabelField("LandLine number :");
		textLL = new LabelField("020-1234567");
		h4.add(labelLL);
		h4.add(textLL);

		HorizontalFieldManager h5 = CustomUIScreens.getHorizontalFieldManager();
		h5.setMargin(10, 5, 10, 5);
		labelEmail = new LabelField("Email Id :");
		textEmail = new LabelField("abcd@efgh.co.in");
		h5.add(labelEmail);
		h5.add(textEmail);

		vfm.add(h1);
		vfm.add(h2);
		vfm.add(h3);
		vfm.add(h4);
		vfm.add(h5);

		add(vfm);
	}
}
