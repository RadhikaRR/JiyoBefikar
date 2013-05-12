package com.bb.UiScreens;

import java.util.Hashtable;

import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.HorizontalFieldManager;

import com.bb.Constants.Constants;
import com.bb.control.MainScreenClass;
import com.bb.customFields.BorderLabelFieldImpl;

public class PolicyInformation extends MainScreenClass {

	private GridFieldManager Grid2;

	public PolicyInformation(String screenTitle, Hashtable hashtable) {
		super(screenTitle);

		GridTable(hashtable);
		HorizontalFieldManager gridHorizontalFieldManager = new HorizontalFieldManager(Manager.HORIZONTAL_SCROLL
				| Manager.VERTICAL_SCROLL | USE_ALL_WIDTH);
		gridHorizontalFieldManager.add(Grid2);
		gridHorizontalFieldManager.setBorder(roundedBorder);

		add(gridHorizontalFieldManager);
	}

	public void GridTable(Hashtable hashtable) {
		Grid2 = new GridFieldManager(11, 2, GridFieldManager.FIXED_SIZE);

		String PolicyHolderName = (String) hashtable.get("stringval1");
		BorderLabelFieldImpl b1 = new BorderLabelFieldImpl("Policy Holders Name", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		b1.setFont(Constants.fontBold6);
		Grid2.add(b1);

		BorderLabelFieldImpl b11 = new BorderLabelFieldImpl(PolicyHolderName, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b11);

		String ProductName = (String) hashtable.get("stringval3");
		BorderLabelFieldImpl b2 = new BorderLabelFieldImpl("Product Name", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b2);

		BorderLabelFieldImpl b22 = new BorderLabelFieldImpl(ProductName, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b22);

		String InceptionDate = (String) hashtable.get("stringval2");
		BorderLabelFieldImpl b3 = new BorderLabelFieldImpl("Inception Date", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b3);

		BorderLabelFieldImpl b33 = new BorderLabelFieldImpl(InceptionDate, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b33);

		String SumAssured = (String) hashtable.get("stringval4");
		BorderLabelFieldImpl b4 = new BorderLabelFieldImpl("Sum Assured", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b4);

		BorderLabelFieldImpl b44 = new BorderLabelFieldImpl(SumAssured, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b44);

		String PolicyTerm = (String) hashtable.get("stringval8");
		BorderLabelFieldImpl b5 = new BorderLabelFieldImpl("Benifit Term(Yrs)", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b5);

		BorderLabelFieldImpl b55 = new BorderLabelFieldImpl(PolicyTerm, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b55);

		String InstallmentPremium = (String) hashtable.get("stringval5");
		BorderLabelFieldImpl b6 = new BorderLabelFieldImpl("Installment Premium (Rs.)", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b6);

		BorderLabelFieldImpl b66 = new BorderLabelFieldImpl(InstallmentPremium, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b66);

		String NextDueDate = (String) hashtable.get("stringval6");
		BorderLabelFieldImpl b7 = new BorderLabelFieldImpl("Next Due Date", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b7);

		BorderLabelFieldImpl b77 = new BorderLabelFieldImpl(NextDueDate, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b77);

		String FrequencyOfPayment = (String) hashtable.get("stringval7");
		BorderLabelFieldImpl b8 = new BorderLabelFieldImpl("Frequency of Payment", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b8);

		BorderLabelFieldImpl b88 = new BorderLabelFieldImpl(FrequencyOfPayment, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b88);

		String LastPremiumDate = (String) hashtable.get("stringval9");
		BorderLabelFieldImpl b9 = new BorderLabelFieldImpl("Last Premium Date", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b9);

		BorderLabelFieldImpl b99 = new BorderLabelFieldImpl(LastPremiumDate, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b99);

		String PremiumPayingTerm = (String) hashtable.get("stringval10");
		BorderLabelFieldImpl b10 = new BorderLabelFieldImpl("Premium Paying Term(Yrs)", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b10);

		BorderLabelFieldImpl b101 = new BorderLabelFieldImpl(PremiumPayingTerm, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b101);

		String NomineeName = (String) hashtable.get("stringval11");
		BorderLabelFieldImpl b110 = new BorderLabelFieldImpl("Nominee Name", Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b110);

		BorderLabelFieldImpl b111 = new BorderLabelFieldImpl(NomineeName, Field.FOCUSABLE | USE_ALL_WIDTH
				| DrawStyle.HCENTER);
		Grid2.add(b111);

		if (Constants.width > 320) {
			b1.setFont(Constants.fontBold6);
			b11.setFont(Constants.font);
			b2.setFont(Constants.fontBold6);
			b22.setFont(Constants.font);
			b3.setFont(Constants.fontBold6);
			b33.setFont(Constants.font);
			b4.setFont(Constants.fontBold6);
			b44.setFont(Constants.font);
			b5.setFont(Constants.fontBold6);
			b55.setFont(Constants.font);
			b6.setFont(Constants.fontBold6);
			b66.setFont(Constants.font);
			b7.setFont(Constants.fontBold6);
			b77.setFont(Constants.font);
			b8.setFont(Constants.fontBold6);
			b88.setFont(Constants.font);
			b9.setFont(Constants.fontBold6);
			b99.setFont(Constants.font);
			b10.setFont(Constants.fontBold6);
			b101.setFont(Constants.font);
			b110.setFont(Constants.fontBold6);
			b111.setFont(Constants.font);
		} else {
			b1.setFont(Constants.fontBold5);
			b11.setFont(Constants.fontVerySmall);
			b2.setFont(Constants.fontBold5);
			b22.setFont(Constants.fontVerySmall);
			b3.setFont(Constants.fontBold5);
			b33.setFont(Constants.fontVerySmall);
			b4.setFont(Constants.fontBold5);
			b44.setFont(Constants.fontVerySmall);
			b5.setFont(Constants.fontBold5);
			b55.setFont(Constants.fontVerySmall);
			b6.setFont(Constants.fontBold5);
			b66.setFont(Constants.fontVerySmall);
			b7.setFont(Constants.fontBold5);
			b77.setFont(Constants.fontVerySmall);
			b8.setFont(Constants.fontBold5);
			b88.setFont(Constants.fontVerySmall);
			b9.setFont(Constants.fontBold5);
			b99.setFont(Constants.fontVerySmall);
			b10.setFont(Constants.fontBold5);
			b101.setFont(Constants.fontVerySmall);
			b110.setFont(Constants.fontBold5);
			b111.setFont(Constants.fontVerySmall);
		}
	}
}
