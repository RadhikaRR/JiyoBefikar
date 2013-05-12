package com.bb.UiScreens;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.PopupSpinnerScreen;
import com.bb.WebService.CallWebService;
import com.bb.control.Controller;
import com.bb.control.MainScreenClass;
import com.bb.customFields.ListCallBack;

public class PolicyDetailsScreen extends MainScreenClass {

	private ListField _lf;
	private String[] listItemArray = { "Policy Status", "Policy Information" };

	public PolicyDetailsScreen(String screenTitle) {

		super(screenTitle);

		VerticalFieldManager vfmList = new VerticalFieldManager();
		vfmList.setBorder(roundedBorder);
		vfmList.setMargin(10, 10, 10, 10);

		_lf = new ListField() {
			protected boolean navigationClick(int status, int time) {
				final int index = _lf.getSelectedIndex();
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						switch (index) {
						case 0:
							Controller.showScreen(new PopupSpinnerScreen("Retrieving Policy Status"));
							Thread thread = new Thread() {
								public void run() {
									boolean flag = true;
									flag = CallWebService.INSTANCE.PolicyStatusWS(Constants.RegisteredPoliciyWSnumber,
											Constants.IMEI);
									while (flag == false) {
										flag = CallWebService.INSTANCE.PolicyStatusWS(
												Constants.RegisteredPoliciyWSnumber, Constants.IMEI);
									}
								}
							};
							thread.start();
							break;
						case 1:
							showTransition(new PolicyInformationScreen("Registered Policy no."));
							break;
						}
					}
				});
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
