package com.bb.UiScreens;

import java.util.Hashtable;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.Constants.Constants;
import com.bb.control.MainScreenClass;
import com.bb.customFields.BorderLabelFieldImpl;
import com.bb.customFields.StringSplitter;

public class PremiumCalenderScreen extends MainScreenClass {

	BorderLabelFieldImpl b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13;

	GridFieldManager grid, grid1;

	Hashtable hash;

	String s, monthName;

	LabelField label;

	public String[] one = new String[12];
	public String[] two = new String[10];
	public String[] policyNo = new String[10];
	public String[] planName = new String[10];
	public String[] mode = new String[10];
	public String[] premiumDueDate = new String[10];
	public String[] premiumAmount = new String[10];

	String[] months = new String[12];

	int rowLength = 0;

	public PremiumCalenderScreen(String screenTitle, Hashtable hashtable) {
		super(screenTitle);

		this.hash = hashtable;

		for (int j = 0; j < months.length; j++) {
			months[j] = (String) hash.get("stringval" + (j + 1));
			if (months[j].equalsIgnoreCase("nil")) {
				rowLength = rowLength + 1;
			} else {
				one = StringSplitter.INSTANCE.split(months[j], "#M#");
				rowLength = rowLength + one.length;
			}
		}

		GridTable2();
		VerticalFieldManager gridHorizontalFieldManager = new VerticalFieldManager(Manager.HORIZONTAL_SCROLL
				| Manager.VERTICAL_SCROLL);
		gridHorizontalFieldManager.add(grid);
		gridHorizontalFieldManager.setBorder(roundedBorder);

		add(gridHorizontalFieldManager);
	}

	public void GridTable2() {

		grid = new GridFieldManager(rowLength + 1, 6, GridFieldManager.FIXED_SIZE);

		grid.setColumnProperty(3, GridFieldManager.FIXED_SIZE, 100);
		grid.setColumnProperty(4, GridFieldManager.FIXED_SIZE, 150);
		grid.setRowProperty(0, GridFieldManager.FIXED_SIZE, 70);

		b13 = new BorderLabelFieldImpl("Month", Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER) {
			protected void layout(int width, int height) {
				super.layout(width, height);
				this.setExtent(this.getWidth(), 50);
			}
		};
		b13.setFont(Constants.font);
		b13.setFontColor(Color.WHITE);
		b13.setBgColor(Constants.hbgColor);
		grid.add(b13);

		b13 = new BorderLabelFieldImpl("Policy No.", Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER) {
			protected void layout(int width, int height) {
				super.layout(width, height);
				this.setExtent(this.getWidth(), 50);
			}
		};
		b13.setFont(Constants.font);
		b13.setFontColor(Color.WHITE);
		b13.setBgColor(Constants.hbgColor);
		grid.add(b13);

		b13 = new BorderLabelFieldImpl("Mode", Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER) {
			protected void layout(int width, int height) {
				super.layout(width, height);
				this.setExtent(this.getWidth(), 50);
			}
		};
		b13.setFont(Constants.font);
		b13.setFontColor(Color.WHITE);
		b13.setBgColor(Constants.hbgColor);
		grid.add(b13);

		b13 = new BorderLabelFieldImpl("Premium Due Date", Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER) {
			protected void layout(int width, int height) {
				super.layout(width, height);
				this.setExtent(this.getWidth(), 50);
			}
		};
		b13.setFont(Constants.font);
		b13.setFontColor(Color.WHITE);
		b13.setBgColor(Constants.hbgColor);
		grid.add(b13);

		b13 = new BorderLabelFieldImpl("Premium Amount(Rs.)", Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER) {
			protected void layout(int width, int height) {
				super.layout(width, height);
				this.setExtent(this.getWidth(), 50);
			}
		};
		b13.setFont(Constants.font);
		b13.setFontColor(Color.WHITE);
		b13.setBgColor(Constants.hbgColor);
		grid.add(b13);

		b13 = new BorderLabelFieldImpl("Plan Name", Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER) {
			protected void layout(int width, int height) {
				super.layout(width, height);
				this.setExtent(this.getWidth(), 50);
			}
		};
		b13.setFont(Constants.font);
		b13.setFontColor(Color.WHITE);
		b13.setBgColor(Constants.hbgColor);
		grid.add(b13);
		// //////////////////

		// b7 = new BorderLabelFieldImpl("DEC", Field.FOCUSABLE | USE_ALL_WIDTH
		// | DrawStyle.HCENTER);
		// b7.setFont(Constants.font);
		// b7.setFontColor(Color.WHITE);
		// b7.setBgColor(Constants.hbgColor);
		// grid.add(b7);
		//
		// b8 = new BorderLabelFieldImpl("0093320344", Field.FOCUSABLE |
		// USE_ALL_WIDTH | DrawStyle.HCENTER);
		// b8.setFont(Constants.font);
		// b8.setBgColor(0x00D9EEFD);
		// grid.add(b8);
		// b9 = new BorderLabelFieldImpl("Monthely", Field.FOCUSABLE |
		// USE_ALL_WIDTH | DrawStyle.HCENTER);
		// b9.setFont(Constants.font);
		// b9.setBgColor(0x00D9EEFD);
		// grid.add(b9);
		// b10 = new BorderLabelFieldImpl("28-DEC-12", Field.FOCUSABLE |
		// USE_ALL_WIDTH | DrawStyle.HCENTER);
		// b10.setFont(Constants.font);
		// b10.setBgColor(0x00D9EEFD);
		// grid.add(b10);
		// b11 = new BorderLabelFieldImpl("1000", Field.FOCUSABLE |
		// USE_ALL_WIDTH | DrawStyle.HCENTER);
		// b11.setFont(Constants.font);
		// b11.setBgColor(0x00D9EEFD);
		// grid.add(b11);
		// b12 = new BorderLabelFieldImpl("NEW UG EASYPEN PLUS SIZE ONE",
		// Field.FOCUSABLE | USE_ALL_WIDTH
		// | DrawStyle.HCENTER);
		// b12.setFont(Constants.font);
		// b12.setBgColor(0x00D9EEFD);
		// grid.add(b12);
		//		
		// label = new LabelField("", USE_ALL_WIDTH);
		// grid.add(label);
		//
		// label.setFont(Constants.fontBold6);
		// label = new LabelField("", USE_ALL_WIDTH);
		// grid.add(label);
		//
		// label.setFont(Constants.fontBold6);
		// label = new LabelField("Due ", USE_ALL_WIDTH | DrawStyle.RIGHT);
		// grid.add(label);
		//
		// label.setFont(Constants.fontBold6);
		// label = new LabelField(" For DEC", USE_ALL_WIDTH | DrawStyle.LEFT);
		// label.setFont(Constants.fontBold6);
		// grid.add(label);
		//
		// b13 = new BorderLabelFieldImpl("1000", Field.FOCUSABLE |
		// USE_ALL_WIDTH | DrawStyle.HCENTER);
		// b13.setFont(Constants.font);
		// b13.setFontColor(Color.WHITE);
		// b13.setBgColor(Constants.hbgColor);
		// grid.add(b13);
		//
		// label.setFont(Constants.fontBold6);
		// label = new LabelField("", USE_ALL_WIDTH);
		// grid.add(label);
		// ///////////////////////////////

		String rr = new SimpleDateFormat("MM").formatLocal(System.currentTimeMillis());
		int currentMonth = Integer.parseInt(rr);
		int mm = 1;

		System.out.println("");

		for (int j = 0; j < 12; j++) {
			months[j] = (String) hash.get("stringval" + (currentMonth));
			System.out.println("");
			switch (currentMonth) {
			case 1:
				monthName = "JAN";
				break;
			case 2:
				monthName = "FEB";
				break;
			case 3:
				monthName = "MAR";
				break;
			case 4:
				monthName = "APR";
				break;
			case 5:
				monthName = "MAY";
				break;
			case 6:
				monthName = "JUN";
				break;
			case 7:
				monthName = "JUL";
				break;
			case 8:
				monthName = "AUG";
				break;
			case 9:
				monthName = "SEP";
				break;
			case 10:
				monthName = "OCT";
				break;
			case 11:
				monthName = "NOV";
				break;
			case 12:
				monthName = "DEC";
				break;
			}

			if (months[j].equalsIgnoreCase("nil")) {

			} else {
				String str = months[j];
				int index = nthOccurrence(str, "#M#", 1);
				String aa = str.substring(index);
				s = str.substring(4, index);
				one = StringSplitter.INSTANCE.split(aa, "#M#");

				for (int i = 0; i < one.length; i++) {
					two = StringSplitter.INSTANCE.split(one[i], "~");
					policyNo[i] = two[0];
					planName[i] = two[1];
					mode[i] = two[2];
					premiumDueDate[i] = two[3];
					premiumAmount[i] = two[4];

					if (i == 0) {
						b7 = new BorderLabelFieldImpl(monthName, Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER);
						b7.setFont(Constants.font);
						b7.setFontColor(Color.WHITE);
						b7.setBgColor(Constants.hbgColor);
						grid.add(b7);
					} else {
						label = new LabelField("", USE_ALL_WIDTH);
						grid.add(label);
					}

					b8 = new BorderLabelFieldImpl(policyNo[i], Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER);
					b8.setFont(Constants.font);
					b8.setBgColor(0x00D9EEFD);
					grid.add(b8);
					b9 = new BorderLabelFieldImpl(planName[i], Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER);
					b9.setFont(Constants.font);
					b9.setBgColor(0x00D9EEFD);
					grid.add(b9);
					b10 = new BorderLabelFieldImpl(mode[i], Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER);
					b10.setFont(Constants.font);
					b10.setBgColor(0x00D9EEFD);
					grid.add(b10);
					b11 = new BorderLabelFieldImpl(premiumDueDate[i], Field.FOCUSABLE | USE_ALL_WIDTH
							| DrawStyle.HCENTER);
					b11.setFont(Constants.font);
					b11.setBgColor(0x00D9EEFD);
					grid.add(b11);
					b12 = new BorderLabelFieldImpl(premiumAmount[i], Field.FOCUSABLE | USE_ALL_WIDTH
							| DrawStyle.HCENTER);
					b12.setFont(Constants.font);
					b12.setBgColor(0x00D9EEFD);
					grid.add(b12);
				}

				label = new LabelField("", USE_ALL_WIDTH);
				grid.add(label);

				label.setFont(Constants.fontBold6);
				label = new LabelField("", USE_ALL_WIDTH);
				grid.add(label);

				label.setFont(Constants.fontBold6);
				label = new LabelField("Due ", USE_ALL_WIDTH | DrawStyle.RIGHT);
				grid.add(label);

				label.setFont(Constants.fontBold6);
				label = new LabelField(" For " + monthName, USE_ALL_WIDTH | DrawStyle.LEFT);
				label.setFont(Constants.fontBold6);
				grid.add(label);

				b13 = new BorderLabelFieldImpl(s, Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER);
				b13.setFont(Constants.font);
				b13.setFontColor(Color.WHITE);
				b13.setBgColor(Constants.hbgColor);
				grid.add(b13);

				label.setFont(Constants.fontBold6);
				label = new LabelField("", USE_ALL_WIDTH);
				grid.add(label);
			}
			System.out.println("");
			
			
			if(currentMonth == 12){
				currentMonth = mm;				
			}else{
				currentMonth += 1;
			}
			
		}
	}

	public static int nthOccurrence(String str, String c, int n) {
		int pos = str.indexOf(c, 0);
		while (n-- > 0 && pos != -1)
			pos = str.indexOf(c, pos + 1);
		return pos;
	}
}