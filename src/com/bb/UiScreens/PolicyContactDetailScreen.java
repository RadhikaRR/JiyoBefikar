package com.bb.UiScreens;

import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.bb.Constants.Constants;
import com.bb.control.Controller;
import com.bb.control.MainScreenClass;
import com.bb.customFields.ListCallBack;

public class PolicyContactDetailScreen extends MainScreenClass {

	private ListField _lf;
	private String[] listItemArray = Constants.policyArray;
	public String selectedFundValue;

	public PolicyContactDetailScreen(String screenTitle) {

		super(screenTitle);

		VerticalFieldManager vfmList = new VerticalFieldManager();
		vfmList.setBorder(roundedBorder);
		vfmList.setMargin(10, 10, 10, 10);

		_lf = new ListField() {
			protected boolean navigationClick(int status, int time) {
				final int index = _lf.getSelectedIndex();
				selectedFundValue = listItemArray[index];
				Controller.showScreen(new ContactDetailsScreen("Contact Details"));
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
