package com.bb.control;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.BackgroundService;
import com.bb.CustomClasses.MeSMSSender;
import com.bb.CustomClasses.PopupSpinnerScreen;
import com.bb.DataBase.PersistProvider;
import com.bb.UiScreens.RegistrationScreen;
import com.bb.UiScreens.SplashScreen;
import com.bb.WebService.CallWebService;
import com.bb.customFields.BorderLabelFieldImpl;

public class Controller extends UiApplication {

	public Controller() {
		// Splash screen
		showScreen(new SplashScreen());
		invokeLater(runnable);
	}

	Runnable runnable = new Runnable() {
		public void run() {
			// for message
			String smsFlag = PersistProvider.INSTANCE.getObject(Constants.SMSFLAG);
			if (smsFlag == null) {
				MeSMSSender.meSMSSender.sendSMSMessage("09773500500");
			}
			// for Desclaimer screen
			final String firstTimeLogin = PersistProvider.INSTANCE.getObject(Constants.firstTimeLogin);
			if (firstTimeLogin == null) {
				final String DeclaimerFlag = PersistProvider.INSTANCE.getObject(Constants.AGREEFLAG);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				if (DeclaimerFlag == null) {
					pushScreen(new DeclaimerDialogClasss());
				} else {
					pushScreen(new RegistrationScreen("Registration"));
				}
			} else {
				final String id = PersistProvider.INSTANCE.getObject(Constants.persistID);
				final String password = PersistProvider.INSTANCE.getObject(Constants.persistPASSWORD);
				// for oppup screen
				Controller.showScreen(new PopupSpinnerScreen(""));
				Thread threadd = new Thread() {
					public void run() {
						boolean flag = true;
						flag = CallWebService.INSTANCE.authenticateUserWS(id, password, Constants.IMEI);
						while (flag == false) {
							flag = CallWebService.INSTANCE.authenticateUserWS(id, password, Constants.IMEI);
						}
					}
				};
				threadd.start();
			}
		}
	};

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("Bajaj")) {
			BackgroundService app = BackgroundService.getInstance();
			// Calendar calendar = Calendar.getInstance();
			// long hour = (calendar.get(Calendar.HOUR_OF_DAY));
			// if(hour == 18){
			// app.startTimer(hour);
			// }
			EventLogger.register(Constants.GUID, Constants.APP_NAME, EventLogger.VIEWER_STRING);
			app.enterEventDispatcher();
		} else {
			Controller controller = new Controller();
			EventLogger.register(Constants.GUID, Constants.APP_NAME, EventLogger.VIEWER_STRING);
			controller.enterEventDispatcher();
		}
	}

	public static void showScreen(Screen screen) {
		if (screen == null) {
			return;
		}
		synchronized (getEventLock()) {
			getUiApplication().pushScreen(screen);
		}
	}

	// Desclaimer screen
	public class DeclaimerDialogClasss extends PopupScreen {
		LabelField textDisclaimer;
		ButtonField btnAgree, btnDissAgree;

		public DeclaimerDialogClasss() {
			super(new VerticalFieldManager(), Field.FOCUSABLE);

			Bitmap borderBitmap = Bitmap.getBitmapResource("BlackTrans.png");
			XYEdges padding = new XYEdges(14, 14, 14, 14);
			Border roundedBorder = BorderFactory.createBitmapBorder(padding, borderBitmap);
			this.setBorder(roundedBorder);

			VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | VERTICAL_SCROLLBAR);

			HorizontalFieldManager labelHorizontalFieldManager = new HorizontalFieldManager();
			BorderLabelFieldImpl label = new BorderLabelFieldImpl("Disclaimer", USE_ALL_WIDTH | Field.FOCUSABLE
					| DrawStyle.HCENTER);
			labelHorizontalFieldManager.add(label);

			textDisclaimer = new LabelField(
					"Your use of BajajAllianz constitutes unconditional acceptance of the term and conditions for the usage,access,etc as stated herein below or as amended by Bajaj Allianz Life Insurance Co.Ltd.(BALIC)from time to time.Upon completion of the process of downloading and installation of the application on your mobile phone you are given a limited, non-exclusive,non transferable,non assignable,non-sub licensable and revocable license to use application on your mobile phone.You shall use the application on your mobile phone at your own risk and agree not to blame BALIC for any malfunction/s developed on your phone subsequently.No part of this work may be copied,reproduced,or translated in any form or medium without the prior written consent of BALIC.The access provided through the said application on your mobile are provided for your personal use only and may not be retransmitted or redistributed.You may not upload any of this site's material to any public server,on-line service,network,or bulletin board without prior written permission from BALIC.You may not make copies for any commercial purpose.You do not obtain any ownership right,title,or other interest in BALIC trademarks,artwork,logo,graphics,application or copyrights by downloading,installing,copying,or otherwise using these application.You assume the entire risk related to your use of this application and access.BALIC is providing this data as is,and BALIC disclaims any and all warranties,whether express or implied,including(without limitation)any implied warranties.In no event will BALIC be liable to you or to any third party for any direct,indirect,incidental,consequential,special or exemplary damages or lost profit resulting from any use or misuse of this Application.Insurance is the subject matter of the solicitation.");
			textDisclaimer.setFont(Constants.font);

			HorizontalFieldManager hfm = new HorizontalFieldManager(Field.FIELD_HCENTER);
			btnAgree = new ButtonField("Agree", Field.FIELD_HCENTER | ButtonField.CONSUME_CLICK);
			btnAgree.setChangeListener(new FieldChangeListener() {
				public void fieldChanged(Field field, int context) {
					if (field == btnAgree) {
						PersistProvider.INSTANCE.saveObject(Constants.AGREEFLAG, "true");
						pushScreen(new RegistrationScreen("Registration"));
					}
				}
			});

			btnDissAgree = new ButtonField("Disagree", Field.FIELD_HCENTER | ButtonField.CONSUME_CLICK);
			btnDissAgree.setChangeListener(new FieldChangeListener() {
				public void fieldChanged(Field field, int context) {
					if (field == btnDissAgree) {
						Status.show("exitting...");
						System.exit(0);
					}
				}
			});
			hfm.add(btnAgree);
			hfm.add(btnDissAgree);

			vfm.add(labelHorizontalFieldManager);
			vfm.add(textDisclaimer);
			vfm.add(hfm);
			add(vfm);
		}
	}
}
