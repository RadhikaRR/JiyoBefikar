package com.bb.CustomClasses;

import java.io.IOException;

import com.bb.Constants.Constants;
import com.bb.connection.ConnectionClass;

import net.rim.blackberry.api.browser.Browser;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

public class AppUpdater {

	public static AppUpdater INSTANCE = new AppUpdater();

	public boolean doCheckForUpdates(String newVersion) {
		if (newVersion != null) {

			String oldVersion = getAppVersion();

			if (!newVersion.equalsIgnoreCase(oldVersion)) {
				return true;
			}
		}
		return false;
	}

	public String getAppVersion() {
		String version = ApplicationDescriptor.currentApplicationDescriptor().getVersion();
		return version;
	}

	String newURL = "";
	int counter = 0;

	public void openDownloadURL(String downloadFullURL) {
		try {			
			if (Constants.connectTCP) {
				newURL = ConnectionClass.INSTANCE.connectTCP(downloadFullURL);
			} else if (Constants.connectBIS) {
				newURL = ConnectionClass.INSTANCE.connectBIS(downloadFullURL);
			}

			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Browser.getDefaultSession().displayPage(newURL);
					System.exit(0);
				}
			});
		} 		
		catch (final IOException ioException) {
			LogEventClass.logErrorEvent("Error in downloading app "+ioException.getMessage());
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication()
							.getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					Dialog.alert(""+ ioException.getMessage()+ ", please check apn setting and activate GPRS");
				}
			});
		} catch (Exception e) {
			LogEventClass.logErrorEvent("Error in downloading app "+e.getMessage());
			counter = counter + 1;
			if (counter <= 2) {
				while (counter == 2) {
					Constants.connectBIS = true;
					Constants.connectTCP = false;
					break;
				}
			} else {
				counter = 0;
				Constants.connectBIS = false;
				Constants.connectTCP = true;
				ShowDialog.INSTANCE.dialog("Authentication Failed! Please check connection");
			}
		}
	}
}

