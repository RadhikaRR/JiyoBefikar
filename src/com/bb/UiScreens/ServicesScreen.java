package com.bb.UiScreens;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.TouchEvent;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.VirtualKeyboard;
import net.rim.device.api.ui.component.TextSpinBoxField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.CustomTransperentDialog;
import com.bb.CustomClasses.PopupSpinnerScreen;
import com.bb.CustomClasses.TickerClass;
import com.bb.WebService.CallWebService;
import com.bb.control.Controller;
import com.bb.control.MainScreenClass;

public class ServicesScreen extends MainScreenClass {

	private String[] listItemArray = { "Fund Details", "Policy Details", "Services", "Premium Calender" };

	private TextSpinBoxField _spinBoxField;

	TickerClass ticker;

	public ServicesScreen(String screenTitle) {

		super(screenTitle);

		ticker = new TickerClass("Welcome " + Constants.UserName + " to Customer Apps");
		ticker.setFont(Constants.fontVerySmall);

		VerticalFieldManager vfmList = new VerticalFieldManager();
		vfmList.setBorder(roundedBorder);
		vfmList.setMargin(0, 5, 0, 5);

		final Screen screen = getScreen();
		if (screen != null) {
			final VirtualKeyboard virtualKeyboard = screen.getVirtualKeyboard();
			if (virtualKeyboard != null) {
				virtualKeyboard.setVisibility(VirtualKeyboard.HIDE);
			}
		}

		_spinBoxField = new TextSpinBoxField(listItemArray) {
			protected boolean navigationClick(int status, int time) {
				Field focus = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus();
				if (focus instanceof TextSpinBoxField) {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							int index = (_spinBoxField.getSelectedIndex());
							String text = (String) _spinBoxField.get(index);

							if (text.equals("Fund Details")) {
								showTransition(new FundDetailScreen("Fund Details"));
							} else if (text.equals("Policy Details")) {
								showTransition(new PolicyDetailsScreen("Policy Details"));
							} else if (text.equals("Services")) {
								showTransition(new ServicesDetailsClass("Services"));
							} else {
								Controller.showScreen(new PopupSpinnerScreen("Retrieving Premium Calender"));
								Thread thread = new Thread() {
									public void run() {
										boolean flag = true;
										flag = CallWebService.INSTANCE.PremiumCalenderWS(Constants.RegisteredPolicyNo,
												Constants.IMEI);
										while (flag == false) {
											flag = CallWebService.INSTANCE.PremiumCalenderWS(
													Constants.RegisteredPolicyNo, Constants.IMEI);
										}
									}
								};
								thread.start();
							}
						}
					});
				}
				return super.navigationClick(status, time);
			}

			protected boolean touchEvent(TouchEvent message) {
				if (TouchEvent.CLICK == message.getEvent()) {

					Field focus = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus();
					if (focus instanceof TextSpinBoxField) {
						UiApplication.getUiApplication().invokeLater(new Runnable() {
							public void run() {
								int index = (_spinBoxField.getSelectedIndex());
								String text = (String) _spinBoxField.get(index);

								if (text.equals("Fund Details")) {
									showTransition(new FundDetailScreen("Fund Details"));
								} else if (text.equals("Policy Details")) {
									showTransition(new PolicyDetailsScreen("Policy Details"));
								} else if (text.equals("Services")) {
									showTransition(new ServicesDetailsClass("Services"));
								} else {
									Controller.showScreen(new PopupSpinnerScreen("Retrieving Premium Calender"));
									Thread thread = new Thread() {
										public void run() {
											boolean flag = true;
											flag = CallWebService.INSTANCE.PremiumCalenderWS(
													Constants.RegisteredPolicyNo, Constants.IMEI);
											while (flag == false) {
												flag = CallWebService.INSTANCE.PremiumCalenderWS(
														Constants.RegisteredPolicyNo, Constants.IMEI);
											}
										}
									};
									thread.start();
								}
							}
						});
					}

					FieldChangeListener listener = getChangeListener();
					if (null != listener)
						listener.fieldChanged(this, 1);
				}
				return super.touchEvent(message);
			}

			public int getPreferredWidth() {
				return 600;
			}
		};
		_spinBoxField.setVisibleRows(3);
		_spinBoxField.setSelectedIndex(0);

		Constants.width = Display.getWidth();
		if (Constants.width > 320) {
			_spinBoxField.setRowHeight(70);
		}

		vfmList.add(_spinBoxField);
		add(ticker);
		add(vfmList);
	}

	public boolean onClose() {
		Controller.showScreen(new CustomTransperentDialog("Do you want to EXIT?"));		
		return true;
	}
}
