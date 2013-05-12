package com.bb.CustomClasses;

import java.util.Timer;
import java.util.TimerTask;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

public class TickerClass extends Field {

	String text;
	final int screenWidth = Display.getWidth();
	int offset = screenWidth;
	Timer timer = new Timer();
	final int delay = 30;
	int col = 0x00CD3278;	

	public TickerClass(String text) {		
		this.text = text;
		final int width = Font.getDefault().getAdvance(text);
		TimerTask timerTask = new TimerTask() {
			public void run() {
				offset--;
				if (offset + width == 0) {
					offset = screenWidth;
				}
				invalidate();
			}
		};
		timer.scheduleAtFixedRate(timerTask, delay, delay);
	}

	protected void layout(int width, int height) {
		int w = Display.getWidth();
		int h = Font.getDefault().getHeight();
		setExtent(w, h);
	}

	protected void paint(Graphics graphics) {
		graphics.drawText(text, offset, 0);
	}

	public void setFont(Font font) {		
		super.setFont(font);
	}

	protected void paintBackground(Graphics g) {
		g.setColor(col);
	}
}
