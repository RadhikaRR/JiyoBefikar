package com.bb.WebService;

import java.util.Hashtable;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.AppUpdater;
import com.bb.CustomClasses.PopupSpinnerScreen;
import com.bb.CustomClasses.ShowDialog;
import com.bb.DataBase.PersistProvider;
import com.bb.UiScreens.FundAccountDetailsScreen;
import com.bb.UiScreens.FundPolicyDetailsScreen;
import com.bb.UiScreens.NetAssetValueScreen;
import com.bb.UiScreens.PolicyInformation;
import com.bb.UiScreens.PolicyNoPolicyStatusScreen;
import com.bb.UiScreens.PremiumCalenderScreen;
import com.bb.UiScreens.RegistrationScreen;
import com.bb.UiScreens.ServicesScreen;
import com.bb.control.MainScreenClass;
import com.bb.customFields.StringSplitter;
import com.bb.customFields.StringTokenizer;

public class WebServiceManager {

	public static WebServiceManager INSTANCE = new WebServiceManager();
	public static String[] NAVRows = new String[20];
	public static String[] FundAccDetailsRows = new String[20];

	public static String[] PolicyFundString = new String[50];

	public WebServiceManager() {
	}

	public Hashtable converObjectToHashTable(String string) {
		Hashtable hashtable = new Hashtable();
		try {
			string = string.substring(string.indexOf("{") + 1, string.length() - 1);
			string = string.substring(string.indexOf("{") + 1);
			string = string.replace('}', ' ');
			StringTokenizer tokenizer = new StringTokenizer(string, ";");
			while (tokenizer.hasMoreElements()) {
				String token = (String) tokenizer.nextElement();
				if (token.indexOf("=") != -1) {
					String key = token.substring(0, token.indexOf("="));
					String value = (token.substring(token.indexOf("=") + 1)).trim();
					if (!(value.equals("null") || value.equals(""))) {
						hashtable.put(key.trim(), value.trim());
					}
				}
			}
		} catch (Exception e) {
			ShowDialog.INSTANCE.status("Fail to parsed object in convertObjectToHashTable" + e.toString());
		}
		return hashtable;
	}

	public void returnedAuthenticateUserWS(Object object) {
		if (object != null) {
			String response = object.toString();
			Hashtable hashtable = converObjectToHashTable(response);
			if (hashtable != null) {

				String authResponse = (String) hashtable.get("stringval20");
				if (authResponse.equals("SUCCESS")) {

					String ID = RegistrationScreen.ID;
					String PASSWORD = RegistrationScreen.PASSWORD;
					System.out.println("");

					PersistProvider.INSTANCE.saveObject(Constants.persistID, ID);
					PersistProvider.INSTANCE.saveObject(Constants.persistPASSWORD, PASSWORD);

					Constants.RegisteredPolicyNo = (String) hashtable.get("stringval3");
					int l = Constants.RegisteredPolicyNo.length();
					Constants.RegisteredPoliciyWSnumber = Constants.RegisteredPolicyNo.substring(1, l);
					Constants.policyArray = StringSplitter.INSTANCE.split(Constants.RegisteredPolicyNo, "~");
					Constants.UserName = (String) hashtable.get("stringval1");

					if (Constants.persistFlag == null || Constants.persistFlag == "") {
						PersistProvider.INSTANCE.saveObject(Constants.firstTimeLogin, "true");
						Constants.persistFlag = "FirstTimeLogged";
					}

					String newVersion = (String) hashtable.get("stringval4");
					boolean downloadFlag = false;
					if (newVersion != null && !newVersion.equalsIgnoreCase("null")) {
						downloadFlag = AppUpdater.INSTANCE.doCheckForUpdates(newVersion);
					}
					if (downloadFlag) {
						final String downloadPath = (String) hashtable.get("stringval5");
						UiApplication.getUiApplication().invokeLater(new Runnable() {
							public void run() {
								Screen screen = UiApplication.getUiApplication().getActiveScreen();
								if (screen instanceof PopupSpinnerScreen) {
									UiApplication.getUiApplication().popScreen(screen);
								}
								int ask = Dialog.ask(Dialog.OK, "New Update is available, Please Update");
								if (ask == Dialog.OK) {
									if (downloadPath != null && !downloadPath.equalsIgnoreCase("null")) {
										String fullDownloadpath = downloadPath + Constants.newAppDownloadAppendURL;
										AppUpdater.INSTANCE.openDownloadURL(fullDownloadpath);
									}
								}
							}
						});
					} else {
						UiApplication.getUiApplication().invokeLater(new Runnable() {
							public void run() {
								Screen screen = UiApplication.getUiApplication().getActiveScreen();
								if (screen instanceof PopupSpinnerScreen) {
									UiApplication.getUiApplication().popScreen(screen);
								}
								MainScreenClass.showZoomTransition(new ServicesScreen("Services"));
							}
						});
					}
				} else {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							ShowDialog.INSTANCE.dialog("User is not valid");
						}
					});
					RegistrationScreen.passEdit.setText("");
				}
			}
		} else {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication().getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					ShowDialog.INSTANCE.dialog("Unable to retrive Authentication Details, Please try after some time");
				}
			});
		}
	}

	public void returnedFundValueWS(Object object) {
		if (object != null) {
			String response = object.toString();
			Hashtable hashtable = converObjectToHashTable(response);
			if (hashtable != null) {
				String authResponse = (String) hashtable.get("pfundvalOut");
				PolicyFundString = StringSplitter.INSTANCE.split(authResponse, "#M#");
				if (authResponse != null) {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							MainScreenClass.showTransition(new FundPolicyDetailsScreen("Policy Details"));
						}
					});
				} else {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							ShowDialog.INSTANCE
									.dialog("Unable to retrive Fund Value Details, Please try after some time");
						}
					});
				}
			}
		} else {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication().getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					ShowDialog.INSTANCE.dialog("Unable to retrive Fund Value Details, Please try after some time");
				}
			});
		}
	}

	public void returnedNavWS(Object object) {
		if (object != null) {
			String response = object.toString();
			Hashtable hashtable = converObjectToHashTable(response);
			if (hashtable != null) {
				String authResponse = (String) hashtable.get("stringval10");
				if (authResponse.equals("SUCCESS")) {
					String RowColumn = (String) hashtable.get("stringval1");
					NAVRows = StringSplitter.INSTANCE.split(RowColumn, "#M#");

					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							MainScreenClass.showTransition(new NetAssetValueScreen("NAV for Product"));
						}
					});
				} else {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							ShowDialog.INSTANCE.dialog("Pre Requisite NAV is not available");
						}
					});
				}
			}
		} else {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication().getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					ShowDialog.INSTANCE.dialog("Unable to retrive NAV Details, Please try after some time");
				}
			});
		}
	}

	public void returnedFundAccDetailsWS(Object object) {
		if (object != null) {
			String response = object.toString();
			Hashtable hashtable = converObjectToHashTable(response);
			if (hashtable != null) {
				String authResponse = (String) hashtable.get("stringval10");
				if (authResponse.equals("SUCCESS")) {
					String RowColumn = (String) hashtable.get("stringval1");

					FundAccDetailsRows = StringSplitter.INSTANCE.split(RowColumn, "#M#");

					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							MainScreenClass.showTransition(new FundAccountDetailsScreen("Fund Account Details"));
						}
					});
				} else {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							ShowDialog.INSTANCE.dialog("Pre Requisite Fund Account Details not available");
						}
					});
				}
			}
		} else {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication().getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					ShowDialog.INSTANCE.dialog("Unable to retrive Fund Account Details, Please try after some time");
				}
			});
		}
	}

	public void returnedPolicyStatusWS(Object object) {
		if (object != null) {
			String response = object.toString();
			Hashtable hashtable = converObjectToHashTable(response);
			if (hashtable != null) {
				String authResponse = (String) hashtable.get("ppolicystatusOut");
				PolicyFundString = StringSplitter.INSTANCE.split(authResponse, "#M#");
				if (authResponse != null) {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							MainScreenClass.showTransition(new PolicyNoPolicyStatusScreen("Policy Status"));
						}
					});
				} else {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							ShowDialog.INSTANCE.dialog("Unable to retrive Policy Status, Please try after some time");
						}
					});
				}
			}
		} else {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication().getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					ShowDialog.INSTANCE.dialog("Unable to retrive Policy Status, Please try after some time");
				}
			});

		}
	}

	public void returnedPolicyInformationWS(Object object) {
		if (object != null) {
			String response = object.toString();
			final Hashtable hashtable = converObjectToHashTable(response);
			if (hashtable != null) {
				String authResponse = (String) hashtable.get("stringval20");
				if (authResponse.equals("SUCCESS")) {

					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							MainScreenClass.showTransition(new PolicyInformation("Policy Information", hashtable));
						}
					});

				} else {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							ShowDialog.INSTANCE.dialog("Pre Requisite Policy information is not available");
						}
					});
				}
			}
		} else {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication().getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					ShowDialog.INSTANCE.dialog("Unable to retrive Policy Information, Please try after some time");
				}
			});
		}
	}

	public void returnedPremiumCalenderWS(Object object) {
		if (object != null) {
			String response = object.toString();
			final Hashtable hashtable = converObjectToHashTable(response);
			final Hashtable hash = new Hashtable();
			if (hashtable != null) {
				String authResponse = (String) hashtable.get("stringval20");
				if (authResponse.equals("SUCCESS")) {

					for (int j = 0; j < 12; j++) {
						String st = (String) hashtable.get("stringval" + (j + 1));
						if (st == null || st.equalsIgnoreCase("x")) {
							hash.put("stringval" + (j + 1), "nil");
						} else {
							hash.put("stringval" + (j + 1), "#M#~" + st);
						}
					}
					System.out.println("");

					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							MainScreenClass.showTransition(new PremiumCalenderScreen("Premium Calender", hash));
						}
					});
				} else {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Screen screen = UiApplication.getUiApplication().getActiveScreen();
							if (screen instanceof PopupSpinnerScreen) {
								UiApplication.getUiApplication().popScreen(screen);
							}
							ShowDialog.INSTANCE
									.dialog("Unable to retrive Premium Calender details, Please try after some time");
						}
					});
				}
			}
		} else {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					Screen screen = UiApplication.getUiApplication().getActiveScreen();
					if (screen instanceof PopupSpinnerScreen) {
						UiApplication.getUiApplication().popScreen(screen);
					}
					ShowDialog.INSTANCE.dialog("Unable to retrive Fund Value Details, Please try after some time");
				}
			});
		}
	}
}
