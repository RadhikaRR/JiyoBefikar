package com.bb.CustomClasses;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class CustomUIScreens {

	public CustomUIScreens() {

	}

	public static HorizontalFieldManager getHorizontalFieldManager() {
		
		HorizontalFieldManager hfm = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Manager.NO_HORIZONTAL_SCROLL) {
			public void paint(Graphics g) {
				g.setBackgroundColor(0x00B0E2FF);
				g.clear();
				invalidate();
				super.paint(g);
			}
		}; 		
		return hfm; 
	}
}
