package com.bb.UiScreens;

import net.rim.device.api.system.Display;
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

public class PolicyNoPolicyStatusScreen extends MainScreenClass {

	private GridFieldManager Grid2;
	private BorderLabelFieldImpl borderLabelFieldImpl;

	String[] label1 = new String[WebServiceManager.PolicyFundString.length];
	String[] val1 = new String[WebServiceManager.PolicyFundString.length];

	public PolicyNoPolicyStatusScreen(String screenTitle) {
		super(screenTitle);

		HorizontalFieldManager labelHorizontalFieldManager = new HorizontalFieldManager();
		borderLabelFieldImpl = new BorderLabelFieldImpl("Policy Status", USE_ALL_WIDTH | Field.FOCUSABLE
				| DrawStyle.HCENTER);

		borderLabelFieldImpl.setFont(Constants.fontBold);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		borderLabelFieldImpl.setMargin(1, 3, 10, 3);
		labelHorizontalFieldManager.add(borderLabelFieldImpl);

		GridTable();
		HorizontalFieldManager gridHorizontalFieldManager = new HorizontalFieldManager(Manager.HORIZONTAL_SCROLL
				| Manager.VERTICAL_SCROLL | USE_ALL_WIDTH);
		gridHorizontalFieldManager.add(Grid2);
		gridHorizontalFieldManager.setBorder(roundedBorder);

		add(labelHorizontalFieldManager);
		add(gridHorizontalFieldManager);
	}

	public void GridTable() {

		int l = WebServiceManager.PolicyFundString.length;

		for (int i = 0; i < l; i++) {

			String[] columnsCells = StringSplitter.INSTANCE.split(WebServiceManager.PolicyFundString[i], "~");
			label1[i] = columnsCells[0];
			val1[i] = columnsCells[1];
		}

		int row = l + 1;

		Grid2 = new GridFieldManager(row, 2, FIELD_HCENTER);

		Grid2 = new GridFieldManager(row, 2, GridFieldManager.FIXED_SIZE);
		int columnWidth = (Display.getWidth() / 2) - 14;
		for (int i = 0; i < 2; i++) {
			Grid2.setColumnProperty(i, GridFieldManager.FIXED_SIZE, columnWidth);
		}

		borderLabelFieldImpl= new BorderLabelFieldImpl("Policy No.", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.font);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		Grid2.add(borderLabelFieldImpl);

		borderLabelFieldImpl = new BorderLabelFieldImpl("Policy Status", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		borderLabelFieldImpl.setFont(Constants.font);
		borderLabelFieldImpl.setFontColor(Color.WHITE);
		borderLabelFieldImpl.setBgColor(Constants.hbgColor);
		Grid2.add(borderLabelFieldImpl);

		for (int i = 0; i < l; i++) {

			borderLabelFieldImpl = new BorderLabelFieldImpl(label1[i], Field.FOCUSABLE | USE_ALL_WIDTH
					| DrawStyle.HCENTER);
			borderLabelFieldImpl.setFont(Constants.font);
			Grid2.add(borderLabelFieldImpl);

			borderLabelFieldImpl = new BorderLabelFieldImpl(val1[i], Field.FOCUSABLE | USE_ALL_WIDTH
					| DrawStyle.HCENTER);
			borderLabelFieldImpl.setFont(Constants.font);
			Grid2.add(borderLabelFieldImpl);
		}
	}
}
