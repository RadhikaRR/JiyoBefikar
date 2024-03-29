package com.bb.CustomClasses;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.bb.UiScreens.ServicesScreen;

public class CustomTransperentDialog extends PopupScreen {

	private LabelField msgTextMessage;
	private ButtonField ok, okExit, cancel;

	public CustomTransperentDialog(String msg) {

		super(new VerticalFieldManager(), Field.FOCUSABLE);

		Bitmap borderBitmap = Bitmap.getBitmapResource("BlackTrans.png");
		XYEdges padding = new XYEdges(12, 12, 12, 12);
		Border roundedBorder = BorderFactory.createBitmapBorder(padding, borderBitmap);
		this.setBorder(roundedBorder);

		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);

		msgTextMessage = new LabelField(msg, Field.FIELD_HCENTER | Field.NON_FOCUSABLE);

		ok = new ButtonField("OK", Field.FIELD_HCENTER);
		ok.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (field == ok) {
					close();
				}
			}
		});

		okExit = new ButtonField("OK", Field.FIELD_HCENTER);
		okExit.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (field == okExit) {
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

		Screen screen = UiApplication.getUiApplication().getActiveScreen();
		if (screen instanceof ServicesScreen) {
			vfm.add(okExit);
			vfm.add(cancel);
		} else {
			vfm.add(ok);
		}
		add(vfm);
	}
}
