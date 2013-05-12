package com.bb.customFields;

import java.util.Vector;

import com.bb.Constants.Constants;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

public class ListCallBack implements ListFieldCallback {
	private Vector listElements = new Vector();
	private Bitmap image = Bitmap.getBitmapResource("AppIndi.PNG");

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
		
		Font font = g.getFont();
		int xPos = 30;
		int yPos = y + (list.getRowHeight() - font.getHeight())/ 2;
		if(Constants.width > 320){
			g.drawBitmap(5, yPos + 5, image.getWidth(), image.getHeight(), image, 0, 0);
		}else{
			g.drawBitmap(5, yPos, image.getWidth(), image.getHeight(), image, 0, 0);
		}
		
		xPos = xPos + image.getWidth();					
		String text = (String) listElements.elementAt(index);
		g.drawText(text, xPos , yPos);	
		
		//for adding new textline
		//yPos = yPos + fontHeight;
		//graphics.drawText(SECOND_LINE_TEXT, xPos, yPos);
	}
	
	public Object get(ListField list, int index) {
		return listElements.elementAt(index);
	}

	public int getPreferredWidth(ListField list) {
		return Display.getWidth();
	}

	public void insert(String toInsert, int index) {
		listElements.insertElementAt(toInsert, index);
	}

	public void erase() {
		listElements.removeAllElements();
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		return listElements.indexOf(listField);
	}
}