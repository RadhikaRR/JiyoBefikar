package com.bb.UiScreens;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.HorizontalFieldManager;

import com.bb.Constants.Constants;
import com.bb.WebService.WebServiceManager;
import com.bb.control.MainScreenClass;
import com.bb.customFields.BorderLabelFieldImpl;
import com.bb.customFields.StringSplitter;

public class FundAccountDetailsScreen extends MainScreenClass {

	private GridFieldManager Grid2;
	private BorderLabelFieldImpl borderLabelFieldImpl;

	String[] label1 = new String[WebServiceManager.FundAccDetailsRows.length];
	String[] val1 = new String[WebServiceManager.FundAccDetailsRows.length];
	String[] val2 = new String[WebServiceManager.FundAccDetailsRows.length];
	String[] val3 = new String[WebServiceManager.FundAccDetailsRows.length];
	String[] val4 = new String[WebServiceManager.FundAccDetailsRows.length];

	public FundAccountDetailsScreen(String screenTitle) {

		super(screenTitle);

		HorizontalFieldManager labelHorizontalFieldManager = new HorizontalFieldManager();
		borderLabelFieldImpl = new BorderLabelFieldImpl("Fund Account Details", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.fontBold);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		borderLabelFieldImpl.setMargin(1, 3, 10, 3);
		labelHorizontalFieldManager.add(borderLabelFieldImpl);

		GridTable();
		HorizontalFieldManager gridHorizontalFieldManager = new HorizontalFieldManager(Manager.HORIZONTAL_SCROLL
				| Manager.VERTICAL_SCROLL);
		gridHorizontalFieldManager.add(Grid2);

		add(labelHorizontalFieldManager);
		add(gridHorizontalFieldManager);
	}

	public void GridTable() {

		int l = WebServiceManager.FundAccDetailsRows.length;

		for (int i = 0; i < l; i++) {
			
			String[] columnsCells = StringSplitter.INSTANCE.split(WebServiceManager.FundAccDetailsRows[i], "~");
			label1[i] = columnsCells[0];
			val1[i] = columnsCells[1];
			val2[i] = columnsCells[2];
			val3[i] = columnsCells[3];
			val4[i] = columnsCells[4];
			System.out.println("");
		}
		int row = l + 1;

		Grid2 = new GridFieldManager(row, 5, Field.USE_ALL_WIDTH);
		Grid2.setPadding(0, 0, 0, 3);

		// for fixed width of a GridManager
		// GridFieldManager Grid2 = new GridFieldManager(NUM_ROWS, NUM_COLUMNS,
		// GridFieldManager.FIXED_SIZE);
		// int columnWidth = (Display.getWidth() / 5) -
		// Grid2.getColumnPadding();
		// for (int i = 0; i < 5; i++) {
		// Grid2.setColumnProperty(i, GridFieldManager.FIXED_SIZE, columnWidth);
		// }

		borderLabelFieldImpl= new BorderLabelFieldImpl("Fund Name", Field.FOCUSABLE | USE_ALL_WIDTH);
		borderLabelFieldImpl.setFont(Constants.font);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		Grid2.add(borderLabelFieldImpl);

		borderLabelFieldImpl = new BorderLabelFieldImpl("Apportionment %", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.font);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		Grid2.add(borderLabelFieldImpl);

		borderLabelFieldImpl = new BorderLabelFieldImpl("NAV(Rs.)", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.font);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		Grid2.add(borderLabelFieldImpl);

		borderLabelFieldImpl = new BorderLabelFieldImpl("Unit", Field.FOCUSABLE | USE_ALL_WIDTH | DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.font);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		Grid2.add(borderLabelFieldImpl);

		borderLabelFieldImpl = new BorderLabelFieldImpl("Acc Value(Rs.)", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.font);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		Grid2.add(borderLabelFieldImpl);

		for (int i = 0; i < l; i++) {

			BorderLabelFieldImpl b6 = new BorderLabelFieldImpl(label1[i], Field.FOCUSABLE | USE_ALL_WIDTH);
			b6.setFont(Constants.font);
			b6.setFontColor(Color.WHITE);
			b6.setBgColor(Constants.hbgColor);
			Grid2.add(b6);

			borderLabelFieldImpl = new BorderLabelFieldImpl(val1[i], Field.FOCUSABLE | USE_ALL_WIDTH
					| DrawStyle.HCENTER);
			borderLabelFieldImpl.setFont(Constants.font);
			Grid2.add(borderLabelFieldImpl);

			borderLabelFieldImpl = new BorderLabelFieldImpl(val2[i], Field.FOCUSABLE | USE_ALL_WIDTH
					| DrawStyle.HCENTER);
			borderLabelFieldImpl.setFont(Constants.font);
			Grid2.add(borderLabelFieldImpl);

			borderLabelFieldImpl = new BorderLabelFieldImpl(val3[i], Field.FOCUSABLE | USE_ALL_WIDTH
					| DrawStyle.HCENTER);
			borderLabelFieldImpl.setFont(Constants.font);
			Grid2.add(borderLabelFieldImpl);

			borderLabelFieldImpl = new BorderLabelFieldImpl(val4[i], Field.FOCUSABLE | USE_ALL_WIDTH
					| DrawStyle.HCENTER);
			borderLabelFieldImpl.setFont(Constants.font);
			Grid2.add(borderLabelFieldImpl);
		}
	}
}
