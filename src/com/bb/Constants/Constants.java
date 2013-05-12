package com.bb.Constants;

import net.rim.blackberry.api.messagelist.ApplicationIcon;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Ui;

import com.bb.CustomClasses.ImeiClass;

public class Constants {

	public Constants() {

	}

	public static String DATELABELVALUE = new SimpleDateFormat("dd-MMM-yyyy").formatLocal(System.currentTimeMillis());

	public static Font font = Font.getDefault().derive(Font.PLAIN, 6, Ui.UNITS_pt);
	public static Font fontVerySmall = Font.getDefault().derive(Font.PLAIN, 5, Ui.UNITS_pt);
	public static Font fontBold = Font.getDefault().derive(Font.BOLD, 7, Ui.UNITS_pt);
	public static Font fontBold6 = Font.getDefault().derive(Font.BOLD, 6, Ui.UNITS_pt);
	public static Font fontBold5 = Font.getDefault().derive(Font.BOLD, 5, Ui.UNITS_pt);

	public static int hbgColor = 0x000072BC;
	
	public static final long Jiyo_Befikar_ID = 0xecbd43db0a2424cfL;
	public static ApplicationIcon mIcon;

	public static final String IMEI = ImeiClass.INSTANCE.getIMEINumber();

	public static final String AUTHENTICATIONURL = "http://web7.bajajallianz.com/BagicWap/BagicWap";
	public static final String AUTHENTICATIONSOAPACTION = "BagicWapPortType";
	public static final String AUTHENTICATIONMETHOD = "validateCustomerPortal";
	public static final String AUTHENTICATIONNAMESPACE = "http://com.bajajallianz/BagicWap.xsd";

	public static final String AUTHENTICATIONParamUserName = "pUsername";
	public static final String AUTHENTICATIONParamPassword = "pPassword";
	public static final String AUTHENTICATIONParamIMEI = "pImeiNo";

	public static final String FundValueMETHOD = "customerPortalFundVal";
	public static final String FundValuePolicyNo = "pPolicyNo";
	public static final String FundValueIMEI = "pImeiNo";

	public static final String navMETHOD = "customerPortalNav";
	public static final String navPolicyNo = "pPolicyNo";
	public static final String navIMEI = "pImeiNo";

	public static final String FundAccDetailsMETHOD = "customerPortalFundAccDtls";
	public static final String FundAccDetailsPolicyNo = "pPolicyNo";
	public static final String FundAccDetailsIMEI = "pImeiNo";

	public static final String PolicyStatusMETHOD = "customerPortalPolStatus";
	public static final String PolicyStatusPolicyNo = "pPolicyNo";
	public static final String PolicyStatusIMEI = "pImeiNo";

	public static final String PolicyInformationMETHOD = "customerPortalPolInfo";
	public static final String PolicyInformationPolicyNo = "pPolicyNo";
	public static final String PolicyInformationIMEI = "pImeiNo";
	
	public static final String PremiumCalenderMETHOD = "customerPortalPremCal";
	public static final String PremiumCalenderPolicyNo = "pPolicyNo";
	public static final String PremiumCalenderIMEI = "pImeiNo";

	public static final long GUID = 0x2051fd67b72d11L;
	
	public static final String APP_NAME = "BajajAllianz";

	public static boolean connectTCP = true;
	public static boolean connectBIS = false;

	public static String[] policyArray;

	public static String firstTimeLogin = "FirstTime";

	public static String UserName = "";
	public static String RegisteredPolicyNo = "";
	public static String RegisteredPoliciyWSnumber = "";

	public static String persistID ="id";
	public static String persistPASSWORD = "password";

	public static final String newAppDownloadAppendURL = "/CustomerApp/BlackBerry/JiyoBefikar.jad";

	public static String persistFlag = "";
	
	public static int width = 0;
	
	public static final String SMSFLAG = "smsflag";
	
	public static final String AGREEFLAG = "agreeflag";
}
