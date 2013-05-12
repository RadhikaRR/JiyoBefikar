package com.bb.WebService;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.LogEventClass;
import com.bb.CustomClasses.ShowDialog;
import com.bb.connection.ConnectionClass;

public class CallWebService {

	private HttpTransportSE httpTransport;
	private String urlAgency = "";
	private int counter = 0;

	private Timer timer;
	private TimerTask timerTask;

	private boolean finishedWork = false;

	public static CallWebService INSTANCE = new CallWebService();

	public CallWebService() {

	}

	private void startTimer() {
		finishedWork = false;
		if (counter < 1) {
			timer = new Timer();
			timerTask = new TimerTask() {
				public void run() {
					counter = 0;
					finishedWork = true;
					ShowDialog.INSTANCE.status("Oops! Could not load content, Please try after some time");					
				}
			};
			timer.schedule(timerTask, 60 * 1000);
		}
	}

	public boolean authenticateUserWS(String userID, String password, String imei) {

		boolean nw = ConnectionClass.INSTANCE.checkNetworkConnection();
		if (nw == false) {
			ShowDialog.INSTANCE.status("Network Coverage is unavailable");
		} else {
			try {
				startTimer();

				if (Constants.connectTCP) {
					urlAgency = ConnectionClass.INSTANCE.connectTCP(Constants.AUTHENTICATIONURL);
				} else if (Constants.connectBIS) {
					urlAgency = ConnectionClass.INSTANCE.connectBIS(Constants.AUTHENTICATIONURL);
				}

				SoapObject request = new SoapObject(Constants.AUTHENTICATIONNAMESPACE, Constants.AUTHENTICATIONMETHOD);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

				request.addProperty(Constants.AUTHENTICATIONParamUserName, userID);
				request.addProperty(Constants.AUTHENTICATIONParamPassword, password);
				request.addProperty(Constants.AUTHENTICATIONParamIMEI, imei);

//				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);
				
				httpTransport = new HttpTransportSE(urlAgency);
				httpTransport.debug = true;
				httpTransport.call(Constants.AUTHENTICATIONSOAPACTION, envelope);

				SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();
				Object object = (Object) resultsRequestSOAP;
				System.out.println("WS O/p is --------------------" + object.toString());

				timerTask.cancel();
				timer.cancel();
				if (finishedWork == false) {
					WebServiceManager.INSTANCE.returnedAuthenticateUserWS(object);
				}
			} catch (final IOException ioException) {
				LogEventClass.logErrorEvent("Error in Authentication:" + ioException.getMessage());
				Constants.connectTCP = true;
				Constants.connectBIS = false;
				counter = 0;
				if (finishedWork == false) {
					timer.cancel();
					ShowDialog.INSTANCE.dialog("Connection Error!,Please check connection");
				}
			} catch (Exception e) {
				LogEventClass.logErrorEvent("Error in Authentication:" + e.getMessage());
				counter = counter + 1;
				if (counter <= 2) {
					while (counter == 2) {
						Constants.connectBIS = true;
						Constants.connectTCP = false;
						break;
					}
					return false;
				} else {
					counter = 0;
					Constants.connectBIS = false;
					Constants.connectTCP = true;
					ShowDialog.INSTANCE.dialog("Authentication Failed! Please check connection");
				}
			}
		}
		return true;
	}

	public boolean FundValueWS(String selectedPolicy, String imei) {

		boolean nw = ConnectionClass.INSTANCE.checkNetworkConnection();
		if (nw == false) {
			ShowDialog.INSTANCE.status("Network Coverage is unavailable");
		} else {
			try {
				startTimer();
				
				if (Constants.connectTCP) {
					urlAgency = ConnectionClass.INSTANCE.connectTCP(Constants.AUTHENTICATIONURL);
				} else if (Constants.connectBIS) {
					urlAgency = ConnectionClass.INSTANCE.connectBIS(Constants.AUTHENTICATIONURL);
				}

				SoapObject request = new SoapObject(Constants.AUTHENTICATIONNAMESPACE, Constants.FundValueMETHOD);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

				request.addProperty(Constants.FundValuePolicyNo, selectedPolicy);
				request.addProperty(Constants.FundValueIMEI, imei);

//				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);
				httpTransport = new HttpTransportSE(urlAgency);
				httpTransport.debug = true;
				httpTransport.call(Constants.AUTHENTICATIONSOAPACTION, envelope);

				SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();
				Object object = (Object) resultsRequestSOAP;
				System.out.println("WS O/p is --------------------" + object.toString());

				timerTask.cancel();
				timer.cancel();
				if (finishedWork == false) {
					WebServiceManager.INSTANCE.returnedFundValueWS(object);
				}

			} catch (final IOException ioException) {
				LogEventClass.logErrorEvent("Error in Fund Value Class:" + ioException.getMessage());
				Constants.connectTCP = true;
				Constants.connectBIS = false;
				counter = 0;
				if (finishedWork == false) {
					timer.cancel();
					ShowDialog.INSTANCE.dialog("Connection Error!,Please check connection");
				}

			} catch (Exception e) {
				LogEventClass.logErrorEvent("Error in Fund Value Class:" + e.getMessage());
				counter = counter + 1;
				if (counter <= 2) {
					while (counter == 2) {
						Constants.connectBIS = true;
						Constants.connectTCP = false;
						break;
					}
					return false;
				} else {
					counter = 0;
					Constants.connectBIS = false;
					Constants.connectTCP = true;
					ShowDialog.INSTANCE.dialog("Failed to Retrieve FundValue! Please check connection");
				}
			}
		}
		return true;
	}

	public boolean navWS(String selectedPolicy, String imei) {

		boolean nw = ConnectionClass.INSTANCE.checkNetworkConnection();
		if (nw == false) {
			ShowDialog.INSTANCE.status("Network Coverage is unavailable");
		} else {
			try {
				startTimer();

				if (Constants.connectTCP) {
					urlAgency = ConnectionClass.INSTANCE.connectTCP(Constants.AUTHENTICATIONURL);
				} else if (Constants.connectBIS) {
					urlAgency = ConnectionClass.INSTANCE.connectBIS(Constants.AUTHENTICATIONURL);
				}

				SoapObject request = new SoapObject(Constants.AUTHENTICATIONNAMESPACE, Constants.navMETHOD);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

				request.addProperty(Constants.navPolicyNo, selectedPolicy);
				request.addProperty(Constants.navIMEI, imei);

//				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);
				httpTransport = new HttpTransportSE(urlAgency);
				httpTransport.debug = true;
				httpTransport.call(Constants.AUTHENTICATIONSOAPACTION, envelope);

				SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();
				Object object = (Object) resultsRequestSOAP;
				System.out.println("WS O/p is --------------------" + object.toString());

				timerTask.cancel();
				timer.cancel();
				if (finishedWork == false) {
					WebServiceManager.INSTANCE.returnedNavWS(object);
				}

			} catch (final IOException ioException) {
				LogEventClass.logErrorEvent("Error in NAV Class:" + ioException.getMessage());
				Constants.connectTCP = true;
				Constants.connectBIS = false;
				counter = 0;
				if (finishedWork == false) {
					timer.cancel();
					ShowDialog.INSTANCE.dialog("Connection Error!,Please check connection");
				}

			} catch (Exception e) {
				LogEventClass.logErrorEvent("Error in NAV Class:" + e.getMessage());
				counter = counter + 1;
				if (counter <= 2) {
					while (counter == 2) {
						Constants.connectBIS = true;
						Constants.connectTCP = false;
						break;
					}
					return false;
				} else {
					counter = 0;
					Constants.connectBIS = false;
					Constants.connectTCP = true;

					ShowDialog.INSTANCE.dialog("Failed to Retrieve NAV! Please check connection");
				}
			}
		}
		return true;
	}

	public boolean fundAccountDetailsWS(String selectedPolicy, String imei) {
		boolean nw = ConnectionClass.INSTANCE.checkNetworkConnection();
		if (nw == false) {
			ShowDialog.INSTANCE.status("Network Coverage is unavailable");
		} else {
			try {
				startTimer();

				if (Constants.connectTCP) {
					urlAgency = ConnectionClass.INSTANCE.connectTCP(Constants.AUTHENTICATIONURL);
				} else if (Constants.connectBIS) {
					urlAgency = ConnectionClass.INSTANCE.connectBIS(Constants.AUTHENTICATIONURL);
				}

				SoapObject request = new SoapObject(Constants.AUTHENTICATIONNAMESPACE, Constants.FundAccDetailsMETHOD);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

				request.addProperty(Constants.FundAccDetailsPolicyNo, selectedPolicy);
				request.addProperty(Constants.FundAccDetailsIMEI, imei);

//				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);
				httpTransport = new HttpTransportSE(urlAgency);
				httpTransport.debug = true;
				httpTransport.call(Constants.AUTHENTICATIONSOAPACTION, envelope);

				SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();
				Object object = (Object) resultsRequestSOAP;
				System.out.println("WS O/p is --------------------" + object.toString());

				timerTask.cancel();
				timer.cancel();
				if (finishedWork == false) {
					WebServiceManager.INSTANCE.returnedFundAccDetailsWS(object);
				}

			} catch (final IOException ioException) {
				LogEventClass.logErrorEvent("Error in FundAccount details Class:" + ioException.getMessage());
				Constants.connectTCP = true;
				Constants.connectBIS = false;
				counter = 0;
				if (finishedWork == false) {
					timer.cancel();
					ShowDialog.INSTANCE.dialog("Connection Error!,Please check connection");
				}

			} catch (Exception e) {
				LogEventClass.logErrorEvent("Error in FundAccount Details Class:" + e.getMessage());
				counter = counter + 1;
				if (counter <= 2) {
					while (counter == 2) {
						Constants.connectBIS = true;
						Constants.connectTCP = false;
						break;
					}
					return false;
				} else {
					counter = 0;
					Constants.connectBIS = false;
					Constants.connectTCP = true;

					ShowDialog.INSTANCE.dialog("Failed to Retrieve Fund Account Details! Please check connection");
				}
			}
		}
		return true;
	}

	public boolean PolicyStatusWS(String selectedPolicy, String imei) {

		boolean nw = ConnectionClass.INSTANCE.checkNetworkConnection();
		if (nw == false) {
			ShowDialog.INSTANCE.status("Network Coverage is unavailable");
		} else {
			try {
				startTimer();

				if (Constants.connectTCP) {
					urlAgency = ConnectionClass.INSTANCE.connectTCP(Constants.AUTHENTICATIONURL);
				} else if (Constants.connectBIS) {
					urlAgency = ConnectionClass.INSTANCE.connectBIS(Constants.AUTHENTICATIONURL);
				}

				SoapObject request = new SoapObject(Constants.AUTHENTICATIONNAMESPACE, Constants.PolicyStatusMETHOD);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

				request.addProperty(Constants.PolicyStatusPolicyNo, selectedPolicy);
				request.addProperty(Constants.PolicyStatusIMEI, imei);

//				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);
				httpTransport = new HttpTransportSE(urlAgency);
				httpTransport.debug = true;
				httpTransport.call(Constants.AUTHENTICATIONSOAPACTION, envelope);

				SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();
				Object object = (Object) resultsRequestSOAP;
				System.out.println("WS O/p is --------------------" + object.toString());

				timerTask.cancel();
				timer.cancel();
				if (finishedWork == false) {
					WebServiceManager.INSTANCE.returnedPolicyStatusWS(object);
				}

			} catch (final IOException ioException) {
				LogEventClass.logErrorEvent("Error in Policy Status Class:" + ioException.getMessage());
				Constants.connectTCP = true;
				Constants.connectBIS = false;
				counter = 0;
				if (finishedWork == false) {
					timer.cancel();
					ShowDialog.INSTANCE.dialog("Connection Error!,Please check connection");
				}

			} catch (Exception e) {
				LogEventClass.logErrorEvent("Error in Policy Status Class:" + e.getMessage());
				counter = counter + 1;
				if (counter <= 2) {
					while (counter == 2) {
						Constants.connectBIS = true;
						Constants.connectTCP = false;
						break;
					}
					return false;
				} else {
					counter = 0;
					Constants.connectBIS = false;
					Constants.connectTCP = true;

					ShowDialog.INSTANCE.dialog("Failed to Retrieve Policy Status! Please check connection");
				}
			}
		}
		return true;
	}

	public boolean PolicynInformationWS(String selectedPolicy, String imei) {

		boolean nw = ConnectionClass.INSTANCE.checkNetworkConnection();
		if (nw == false) {
			ShowDialog.INSTANCE.status("Network Coverage is unavailable");
		} else {
			try {
				startTimer();

				if (Constants.connectTCP) {
					urlAgency = ConnectionClass.INSTANCE.connectTCP(Constants.AUTHENTICATIONURL);
				} else if (Constants.connectBIS) {
					urlAgency = ConnectionClass.INSTANCE.connectBIS(Constants.AUTHENTICATIONURL);
				}

				SoapObject request = new SoapObject(Constants.AUTHENTICATIONNAMESPACE,
						Constants.PolicyInformationMETHOD);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

				request.addProperty(Constants.PolicyInformationPolicyNo, selectedPolicy);
				request.addProperty(Constants.PolicyInformationIMEI, imei);

//				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);
				httpTransport = new HttpTransportSE(urlAgency);
				httpTransport.debug = true;
				httpTransport.call(Constants.AUTHENTICATIONSOAPACTION, envelope);

				SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();
				Object object = (Object) resultsRequestSOAP;
				System.out.println("WS O/p is --------------------" + object.toString());

				timerTask.cancel();
				timer.cancel();
				if (finishedWork == false) {
					WebServiceManager.INSTANCE.returnedPolicyInformationWS(object);
				}

			} catch (final IOException ioException) {
				LogEventClass.logErrorEvent("Error in Policy Information Class:" + ioException.getMessage());
				Constants.connectTCP = true;
				Constants.connectBIS = false;
				counter = 0;
				if (finishedWork == false) {
					timer.cancel();
					ShowDialog.INSTANCE.dialog("Connection Error!,Please check connection");
				}

			} catch (Exception e) {
				LogEventClass.logErrorEvent("Error in Policy Information Class:" + e.getMessage());
				counter = counter + 1;
				if (counter <= 2) {
					while (counter == 2) {
						Constants.connectBIS = true;
						Constants.connectTCP = false;
						break;
					}
					return false;
				} else {
					counter = 0;
					Constants.connectBIS = false;
					Constants.connectTCP = true;

					ShowDialog.INSTANCE.dialog("Failed to Retrieve Policy Information! Please check connection");
				}
			}
		}
		return true;
	}
	
	public boolean PremiumCalenderWS(String selectedPolicy, String imei) {

		boolean nw = ConnectionClass.INSTANCE.checkNetworkConnection();
		if (nw == false) {
			ShowDialog.INSTANCE.status("Network Coverage is unavailable");
		} else {
			try {
				startTimer();

				if (Constants.connectTCP) {
					urlAgency = ConnectionClass.INSTANCE.connectTCP(Constants.AUTHENTICATIONURL);
				} else if (Constants.connectBIS) {
					urlAgency = ConnectionClass.INSTANCE.connectBIS(Constants.AUTHENTICATIONURL);
				}

				SoapObject request = new SoapObject(Constants.AUTHENTICATIONNAMESPACE, Constants.PremiumCalenderMETHOD);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

				request.addProperty(Constants.PremiumCalenderPolicyNo, selectedPolicy);
				request.addProperty(Constants.PremiumCalenderIMEI, imei);

//				envelope.dotNet = true;

				envelope.setOutputSoapObject(request);
				httpTransport = new HttpTransportSE(urlAgency);
				httpTransport.debug = true;
				httpTransport.call(Constants.AUTHENTICATIONSOAPACTION, envelope);

				SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();
				Object object = (Object) resultsRequestSOAP;
				System.out.println("WS O/p is --------------------" + object.toString());

				timerTask.cancel();
				timer.cancel();
				if (finishedWork == false) {
					WebServiceManager.INSTANCE.returnedPremiumCalenderWS(object);
				}

			} catch (final IOException ioException) {
				LogEventClass.logErrorEvent("Error in PremiumCalender:" + ioException.getMessage());
				Constants.connectTCP = true;
				Constants.connectBIS = false;
				counter = 0;
				if (finishedWork == false) {
					timer.cancel();
					ShowDialog.INSTANCE.dialog("Connection Error!,Please check connection");
				}

			} catch (Exception e) {
				LogEventClass.logErrorEvent("Error in PremiumCalender:" + e.getMessage());
				counter = counter + 1;
				if (counter <= 2) {
					while (counter == 2) {
						Constants.connectBIS = true;
						Constants.connectTCP = false;
						break;
					}
					return false;
				} else {
					counter = 0;
					Constants.connectBIS = false;
					Constants.connectTCP = true;

					ShowDialog.INSTANCE.dialog("Failed to Retrieve PremiumCalender! Please check connection");
				}
			}
		}
		return true;
	}
}
