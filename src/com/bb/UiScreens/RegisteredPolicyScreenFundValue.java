//package com.bb.UiScreens;
//
//import net.rim.device.api.ui.component.ListField;
//import net.rim.device.api.ui.container.VerticalFieldManager;
//
//import com.bb.Constants.Constants;
//import com.bb.CustomClasses.PopupSpinnerScreen;
//import com.bb.WebService.CallWebService;
//import com.bb.control.Controller;
//import com.bb.control.MainScreenClass;
//import com.bb.customFields.ListCallBack;
//
//public class RegisteredPolicyScreenFundValue extends MainScreenClass {
//
//	private ListField _lf;
//	private String[] listItemArray = Constants.policyArray;
//	private String selectedPolicy;
//
//	public RegisteredPolicyScreenFundValue(String screenTitle) {
//
//		super(screenTitle);
//
//		VerticalFieldManager vfmList = new VerticalFieldManager();
//		vfmList.setBorder(roundedBorder);
//		vfmList.setMargin(10, 10, 10, 10);
//
//		_lf = new ListField() {
//			protected boolean navigationClick(int status, int time) {
//				int index = _lf.getSelectedIndex();
//				selectedPolicy = listItemArray[index];				
//
//				Controller.showScreen(new PopupSpinnerScreen("Retrieving Fund Value"));
//				Thread thread = new Thread() {
//					public void run() {	
//						boolean flag = true;					
//						flag = CallWebService.INSTANCE.FundValueWS(selectedPolicy, Constants.IMEI);
//						while (flag == false) {
//							flag =CallWebService.INSTANCE.FundValueWS( selectedPolicy, Constants.IMEI);
//						}
//					}
//				};
//				thread.start();
//				return true;
//			}
//		};
//		ListCallBack _callback = new ListCallBack();
//		_lf.setCallback(_callback);
//
//		for (int i = 0; i < listItemArray.length; i++) {
//			_lf.insert(i);
//			_callback.insert(listItemArray[i], i);
//		}
//		_lf.setRowHeight(50);
//		_lf.setPadding(1, 0, 0, 0);
//
//		vfmList.add(_lf);
//		add(vfmList);
//	}
//}
