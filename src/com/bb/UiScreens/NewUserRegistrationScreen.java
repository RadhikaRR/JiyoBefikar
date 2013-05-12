package com.bb.UiScreens;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.picker.DateTimePicker;

import com.bb.Constants.Constants;
import com.bb.control.MainScreenClass;
import com.bb.customFields.StringSplitter;

public class NewUserRegistrationScreen extends MainScreenClass {

	private LabelField PolicyLabel, premiumAmountLabel, loginLabel, DOBlabel,ddmmyyyyLabel;
	private EditField PolicyEdit, premiumAmountEdit, DOBedit;
	private ButtonField btnSubmit;
	private Calendar cal;
	private Date date;
	private static String selDate;
	private boolean isFutureDate;

	VerticalFieldManager vfmNewUser;
	HorizontalFieldManager hfmPolicy, hfmPremiumAmount, hfmDOB;

	public NewUserRegistrationScreen(String screenTitle) {
		
		super(screenTitle);

		vfmNewUser = new VerticalFieldManager();

		loginLabel = new LabelField("Already Registered", FIELD_RIGHT | FOCUSABLE) {
			protected boolean navigationClick(int status, int time) {
				clearFields();
				Screen screen = UiApplication.getUiApplication().getActiveScreen();
				UiApplication.getUiApplication().popScreen(screen);
				return true;
			}
		};
		loginLabel.setFont(Constants.fontVerySmall);
		loginLabel.setMargin(5, 10, 0, 0);
		
		HorizontalFieldManager hfmm = new HorizontalFieldManager();
		hfmm.setBorder(roundedBorder);
		hfmm.setMargin(30, 0, 15, 0);
		
		VerticalFieldManager vfmm1 = new VerticalFieldManager();		
		PolicyLabel = new LabelField("Policy No.");
		PolicyLabel.setPadding(12, 0, 0, 0);
		premiumAmountLabel = new LabelField("Premium Amount");
		premiumAmountLabel.setPadding(25, 0, 0, 0);
		DOBlabel = new LabelField("Date of Birth");
		DOBlabel.setPadding(20, 0, 0, 0);		
		ddmmyyyyLabel = new LabelField("Set Date", FIELD_LEFT | FOCUSABLE){
			protected boolean navigationClick(int status, int time) {	
				showDatePicker();
				return true;
			}		
		};
		ddmmyyyyLabel.setFont(Constants.font);
		ddmmyyyyLabel.setMargin(-15, 0, 0, 25);		
		vfmm1.add(PolicyLabel);
		vfmm1.add(premiumAmountLabel);
		vfmm1.add(DOBlabel);
		vfmm1.add(ddmmyyyyLabel);		
		
		VerticalFieldManager vfmm2 = new VerticalFieldManager();
		PolicyEdit = new EditField("", "", 15, EditField.FILTER_NUMERIC);
		PolicyEdit.setBorder(roundedBorder);
		premiumAmountEdit = new EditField("", "", 10, EditField.FILTER_NUMERIC);
		premiumAmountEdit.setBorder(roundedBorder);
		DOBedit = new EditField("", "", 12, EditField.EDITABLE){
			public void paint(Graphics g){
				if(getTextLength() == 0){
					g.setColor(0x00a0a0a0);
					g.drawText("i.e. 01-jan-2011", 0, 0);					
				}
				invalidate();
				super.paint(g);
			}
		};
		DOBedit.setBorder(roundedBorder);
		vfmm2.add(PolicyEdit);
		vfmm2.add(premiumAmountEdit);
		vfmm2.add(DOBedit);		
		
		hfmm.add(vfmm1);
		hfmm.add(vfmm2);		

		btnSubmit = new ButtonField("Login", Field.FIELD_HCENTER | ButtonField.CONSUME_CLICK){
			protected boolean navigationClick(int status, int time) {
				Dialog.alert("Login Successful!!!");
				return true;
			}
		};

		vfmNewUser.add(loginLabel);
		vfmNewUser.add(hfmm);
		vfmNewUser.add(btnSubmit);

		add(vfmNewUser);

		PolicyEdit.setFocus();
	}
	private void showDatePicker() {
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			public void run() {
				final DateTimePicker dateTimePicker = DateTimePicker
						.createInstance(Calendar.getInstance(), "yyyy:MM:dd", null);
				dateTimePicker.doModal();
				cal = dateTimePicker.getDateTime();
				date = cal.getTime();
				if (date != null) {
					selDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date(date.getTime()));
					String curDate = new SimpleDateFormat("dd-MM-yyyy").formatLocal(System.currentTimeMillis());
					isFutureDate = doCheckFutureDate(selDate, curDate);
					if (isFutureDate) {
						DOBedit.setText(Constants.DATELABELVALUE);
						Dialog.alert("Future date is not allowed.");
					} else {
						String validDateForlbl = new SimpleDateFormat(
								"dd-MMM-yyyy").format(new Date(date.getTime()));
						DOBedit.setText(validDateForlbl);
					}
				}
			}
		});
	}
	public boolean doCheckFutureDate(String selectedDate, String currentDate) {
		String[] splitSelectedDate = StringSplitter.INSTANCE.split(selectedDate, "-");
		String[] splitCurrentDate = StringSplitter.INSTANCE.split(currentDate,"-");

		int currentDay = Integer.parseInt(splitCurrentDate[0]);
		int currentMonth = Integer.parseInt(splitCurrentDate[1]);
		int currentYear = Integer.parseInt(splitCurrentDate[2]);

		int selectedDay = Integer.parseInt(splitSelectedDate[0]);
		int selectedMonth = Integer.parseInt(splitSelectedDate[1]);
		int selectedYear = Integer.parseInt(splitSelectedDate[2]);

		if (selectedYear > currentYear) {
			return true;
		} else if (selectedMonth > currentMonth && selectedYear >= currentYear) {
			return true;
		} else if (selectedDay > currentDay && selectedMonth >= currentMonth
				&& selectedYear >= currentYear) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clearFields(){
		PolicyEdit.setText("");
		premiumAmountEdit.setText("");
		DOBedit.setText("");
	}
	
	public boolean onClose() {
		System.exit(0);
		return true;
	}
}
