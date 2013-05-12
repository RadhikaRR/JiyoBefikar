package com.bb.CustomClasses;

import java.util.Timer;
import java.util.TimerTask;

import net.rim.blackberry.api.messagelist.ApplicationIcon;
import net.rim.blackberry.api.messagelist.ApplicationIndicator;
import net.rim.blackberry.api.messagelist.ApplicationIndicatorRegistry;
import net.rim.device.api.notification.NotificationsManager;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngine;

import com.bb.Constants.Constants;

public class BackgroundService extends Application {

	private static BackgroundService _instance;

	public BackgroundService() {
		EncodedImage mImageGreen = EncodedImage.getEncodedImageResource("AppIndi.PNG");
		ApplicationIcon mIconGreen = new ApplicationIcon(mImageGreen);
		Constants.mIcon = mIconGreen;

		NotificationsManager.triggerImmediateEvent(Constants.Jiyo_Befikar_ID, 0, null, null);
		registerIndicator();
	}

	// homescreen indicator
	public void registerIndicator() {
		try {
			ApplicationIndicatorRegistry reg = ApplicationIndicatorRegistry.getInstance();
			ApplicationIndicator indicator = reg.register(Constants.mIcon, true, true);
			indicator.setVisible(true);
		} catch (Exception e) {
			LogEventClass.logErrorEvent(e.getMessage());
		}
	}

	public static synchronized BackgroundService getInstance() {
		if (_instance == null) {
			_instance = new BackgroundService();
		}
		return (_instance);
	}

	// for displaying notification at perticuler time
	public void startTimer(long delay) {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				synchronized (getAppEventLock()) {
					// Status.show("rahul Message ala re...",
					// Bitmap.getPredefinedBitmap(Bitmap.EXCLAMATION), 3000,
					// Status.GLOBAL_STATUS, true, false, 1);
					// System.out.println("-------------------running in background1111");

					UiEngine ui = Ui.getUiEngine();
					DialogForNewsFeedIndicator screen1 = new DialogForNewsFeedIndicator(
							"Rahulya this is a new news arrived");
					ui.pushGlobalScreen(screen1, 1, UiEngine.GLOBAL_QUEUE);
				}
			}
		};
		timer.schedule(timerTask, delay, 24 * 60 * 60 * 1000);
	}
}

// Alert.startVibrate(2550);
// ApplicationDescriptor current =
// ApplicationDescriptor.currentApplicationDescriptor();
// current.setPowerOnBehavior(ApplicationDescriptor.DO_NOT_POWER_ON);
// ApplicationManager manager =
// ApplicationManager.getApplicationManager();
// manager.scheduleApplication(current, System.currentTimeMillis() +
// 10000, true);
