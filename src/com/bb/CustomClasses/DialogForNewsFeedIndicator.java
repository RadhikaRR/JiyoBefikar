package com.bb.CustomClasses;

import java.util.Timer;
import java.util.TimerTask;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiEngine;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

public class DialogForNewsFeedIndicator extends PopupScreen {

	private LabelField msgTextHeading, msgTextMessage;
	private ButtonField ok;

	public DialogForNewsFeedIndicator(String msg) {

		super(new VerticalFieldManager(), Field.FOCUSABLE);

		Bitmap borderBitmap = Bitmap.getBitmapResource("BlackTrans.png");
		XYEdges padding = new XYEdges(12, 12, 12, 12);
		Border roundedBorder = BorderFactory.createBitmapBorder(padding, borderBitmap);
		this.setBorder(roundedBorder);

		Timer timerr = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				try {
					if (getScreen().isDisplayed()) {
						synchronized (getScreen().getApplication().getAppEventLock()) {
							UiEngine ui = getScreen().getUiEngine();
							ui.popScreen(getScreen());
							ui.relayout();
						}
					}
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		};
		timerr.schedule(timerTask, 15 * 1000);

		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);

		HorizontalFieldManager hfm = new HorizontalFieldManager(Field.FIELD_HCENTER | Field.NON_FOCUSABLE);

		msgTextHeading = new LabelField("Bajaj Allianz news");

		hfm.add(new BitmapField(Bitmap.getBitmapResource("53X48pxmisdata2icon.png")));
		hfm.add(msgTextHeading);

		msgTextMessage = new LabelField(msg, Field.FIELD_HCENTER | Field.NON_FOCUSABLE);

		ok = new ButtonField("OK", Field.FIELD_HCENTER);
		ok.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (field == ok) {
					close();
				}
			}
		});

		vfm.add(hfm);
		vfm.add(new SeparatorField());
		vfm.add(msgTextMessage);
		vfm.add(new TextField());
		vfm.add(ok);

		add(vfm);
	}
}
