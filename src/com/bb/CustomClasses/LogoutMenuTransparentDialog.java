package com.bb.CustomClasses;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.bb.Constants.Constants;
import com.bb.DataBase.PersistProvider;

public class LogoutMenuTransparentDialog extends PopupScreen{

	private LabelField msgTextMessage;
	private ButtonField okExit, cancel;

	public LogoutMenuTransparentDialog(String msg) {

		super(new VerticalFieldManager(), Field.FOCUSABLE);

		Bitmap borderBitmap = Bitmap.getBitmapResource("BlackTrans.png");
		XYEdges padding = new XYEdges(12, 12, 12, 12);
		Border roundedBorder = BorderFactory.createBitmapBorder(padding, borderBitmap);
		this.setBorder(roundedBorder);

		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);

		msgTextMessage = new LabelField(msg, Field.FIELD_HCENTER | Field.NON_FOCUSABLE);		

		okExit = new ButtonField("OK", Field.FIELD_HCENTER);
		okExit.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (field == okExit) {
					PersistProvider.INSTANCE.removeObject(Constants.firstTimeLogin);
					PersistProvider.INSTANCE.removeObject(Constants.persistID);
					PersistProvider.INSTANCE.removeObject(Constants.persistPASSWORD);
					Constants.persistFlag = "";
					System.exit(0);
				}
			}
		});

		cancel = new ButtonField("CANCEL", Field.FIELD_HCENTER);
		cancel.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (field == cancel) {
					close();
				}
			}
		});

		vfm.add(msgTextMessage);
		vfm.add(new LabelField());
		vfm.add(okExit);
		vfm.add(cancel);
		add(vfm);
	}
}
