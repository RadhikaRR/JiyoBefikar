package com.bb.UiScreens;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.control.MainScreenClass;
import com.bb.customFields.ListCallBack;

public class RenewalClass extends MainScreenClass {
	private ListField _lf;
	private String[] listItemArray = { "Renewal Details", "Cheque Pick Up Request" };

	public RenewalClass(String screenTitle) {

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
							showTransition(new RenewalDetailsScreen("Registered Policy No."));
							break;
						case 1:
							showTransition(new RenewalChequeScreen("Registered Policy No."));
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
		_lf.setRowHeight(50);
		_lf.setPadding(1, 0, 0, 0);

		vfmList.add(_lf);
		add(vfmList);
	}
}
