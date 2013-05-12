package com.bb.UiScreens;

import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.PopupSpinnerScreen;
import com.bb.WebService.CallWebService;
import com.bb.control.Controller;
import com.bb.control.MainScreenClass;
import com.bb.customFields.ListCallBack;

public class NAVpolicyScreen extends MainScreenClass {

	private ListField _lf;
	private String[] listItemArray =Constants.policyArray;
	private String selectedPolicy;

	public NAVpolicyScreen(String screenTitle) {

		super(screenTitle);

		VerticalFieldManager vfmList = new VerticalFieldManager();
		vfmList.setBorder(roundedBorder);
		vfmList.setMargin(10, 10, 10, 10);

		_lf = new ListField() {
			protected boolean navigationClick(int status, int time) {
				final int index = _lf.getSelectedIndex();
				selectedPolicy = listItemArray[index];
//
//				Controller.showScreen(new PopupSpinnerScreen("Retrieving Policy Information"));
//				Thread thread = new Thread() {
//					public void run() {
//						boolean flag = true;
//						flag = CallWebService.INSTANCE.PolicynInformationWS(selectedPolicy, Constants.IMEI);
//						while (flag == false) {
//							flag = CallWebService.INSTANCE.PolicynInformationWS(selectedPolicy, Constants.IMEI);
//						}
//					}
//				};
//				thread.start();
				
				Controller.showScreen(new PopupSpinnerScreen("Retrieving NAV"));
				Thread thread1 = new Thread() {
					public void run() {
						boolean flag = true;
						flag = CallWebService.INSTANCE.navWS(selectedPolicy, Constants.IMEI);
						while (flag == false) {
							flag = CallWebService.INSTANCE.navWS(selectedPolicy, Constants.IMEI);
						}
					}
				};
				thread1.start();

				return true;
			}
		};
		ListCallBack _callback = new ListCallBack();
		_lf.setCallback(_callback);

		for (int i = 0; i < listItemArray.length; i++) {
			_lf.insert(i);
			_callback.insert(listItemArray[i], i);
		}
		if (Constants.width > 320) {
			_lf.setRowHeight(50);
		} else {
			_lf.setRowHeight(40);
		}
		_lf.setPadding(1, 0, 0, 0);

		vfmList.add(_lf);
		add(vfmList);
	}
}
