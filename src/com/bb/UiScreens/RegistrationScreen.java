package com.bb.UiScreens;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.CustomTransperentDialog;
import com.bb.CustomClasses.PopupSpinnerScreen;
import com.bb.WebService.CallWebService;
import com.bb.control.Controller;
import com.bb.control.MainScreenClass;
import com.bb.customFields.LabelFieldImpl;

public class RegistrationScreen extends MainScreenClass {
	private LabelField idNameLabel, passLabel;
	public static EditField idEdit;
	public static PasswordEditField passEdit;
	private ButtonField btnSubmit;

	private VerticalFieldManager vfmLogin;
	HorizontalFieldManager hfmUserName, hfmPassword;

	public static String ID, PASSWORD;

	public RegistrationScreen(String screenTitle) {

		super(screenTitle);		

		Constants.width = Display.getWidth();

		vfmLogin = new VerticalFieldManager();

		VerticalFieldManager roundImageBGVerticalFieldManager = new VerticalFieldManager();
		roundImageBGVerticalFieldManager.setBorder(roundedBorder);

		if (Constants.width > 320) {
			roundImageBGVerticalFieldManager.setMargin(35, 0, 0, 0);
		}

		idNameLabel = new LabelField("USER NAME");
		idEdit = new EditField("", "", 20, EditField.EDITABLE ) {
			public void paint(Graphics g) {
				if (getTextLength() == 0) {
					g.setColor(0x00a0a0a0);
					g.drawText("Enter valid 'User Id'", 0, 0);
				}
				invalidate();
				super.paint(g);
			}
		};
		idEdit.setBorder(roundedBorder);
		
		passLabel = new LabelField("PASSWORD");
		passEdit = new PasswordEditField("", "", 10, EditField.EDITABLE | DrawStyle.RIGHT) {
			public void paint(Graphics g) {
				if (getTextLength() == 0) {
					g.setColor(0x00a0a0a0);
					g.drawText("Enter upto 10 digit password", 0, 0);
				}
				invalidate();
				super.paint(g);
			}

		};
		passEdit.setBorder(roundedBorder);

		if (Constants.width < 321) {
			idNameLabel.setFont(Constants.font);
			idEdit.setFont(Constants.font);
			passLabel.setFont(Constants.font);
			passEdit.setFont(Constants.font);
		}

		roundImageBGVerticalFieldManager.add(idNameLabel);
		roundImageBGVerticalFieldManager.add(idEdit);

		roundImageBGVerticalFieldManager.add(passLabel);
		roundImageBGVerticalFieldManager.add(passEdit);

		btnSubmit = new ButtonField("Login", Field.FIELD_HCENTER | ButtonField.CONSUME_CLICK);
		if (Constants.width > 320) {
			btnSubmit.setMargin(10, 0, 0, 0);
		}

		btnSubmit.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (field == btnSubmit) {

					ID = idEdit.getText().toUpperCase();
					PASSWORD = passEdit.getText().toString();
					System.out.println("");

					if (!ID.equals("") && !PASSWORD.equals("")) {
						if (Constants.IMEI != null) {
							Controller.showScreen(new PopupSpinnerScreen("Authenticating User..."));
							Thread thread = new Thread() {
								public void run() {
									boolean flag = true;
									flag = CallWebService.INSTANCE.authenticateUserWS(ID, PASSWORD, Constants.IMEI);
									while (flag == false) {
										flag = CallWebService.INSTANCE.authenticateUserWS(ID, PASSWORD, Constants.IMEI);
									}
								}
							};
							thread.start();
						}
					} else {
						if (ID.equals("")) {
							Controller.showScreen(new CustomTransperentDialog("Enter valid ID"));
						} else if (PASSWORD.equals("")) {
							Controller.showScreen(new CustomTransperentDialog("Enter valid Password"));
						}
					}
				}
			}
		});

		vfmLogin.add(roundImageBGVerticalFieldManager);
		vfmLogin.add(btnSubmit);

		add(vfmLogin);
	}

	public boolean onClose() {
		System.exit(0);
		return true;
	}

	int count = 0;
	String update;

	public void welcomeTitleDisplay() {

		String curDateForDisplayLbl = new SimpleDateFormat("dd-MMM-yyyy").formatLocal(System.currentTimeMillis());

		DisplayTimeLbl = new LabelFieldImpl(curDateForDisplayLbl, DrawStyle.RIGHT | Field.USE_ALL_WIDTH
				| Field.USE_ALL_HEIGHT);

		DisplayTimeLbl.setBgColor(0x000072BC);
		DisplayTimeLbl.setFontColor(Color.WHITE);

		if (Constants.width > 320) {
			DisplayTimeLbl.setFont(Constants.font);
		} else {
			DisplayTimeLbl.setFont(Constants.fontVerySmall);
		}

		horizontalWelcomeLabel = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
		horizontalWelcomeLabel.add(DisplayTimeLbl);
		horizontalWelcomeLabel.setBorder(roundedBorder);
	}
}
