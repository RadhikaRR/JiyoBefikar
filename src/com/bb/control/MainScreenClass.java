package com.bb.control;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.bb.Constants.Constants;
import com.bb.CustomClasses.CustomTransperentDialog;
import com.bb.CustomClasses.LogoutMenuTransparentDialog;
import com.bb.UiScreens.NewUserRegistrationScreen;
import com.bb.UiScreens.RegistrationScreen;
import com.bb.UiScreens.ServicesScreen;
import com.bb.customFields.CustomTitlebar;
import com.bb.customFields.LabelFieldImpl;

public class MainScreenClass extends MainScreen {

	private Bitmap titleBitmap;
	private CustomTitlebar titleBarField;
	protected Border roundedBorder;
	protected LabelFieldImpl WelcomeNameLbl;
	protected LabelFieldImpl DisplayTimeLbl;

	protected HorizontalFieldManager horizontalWelcomeLabel;

	public MainScreenClass(String screenTitle) {
		// for linear background
		this.getMainManager().setBackground(
				BackgroundFactory.createLinearGradientBackground(0x00FFFFFF, 0x00FFFFFF, 0x00219AF7, 0x0095C9F8));

		Constants.width = Display.getWidth();

		Bitmap borderBitmap = Bitmap.getBitmapResource("rounded-border.png");
		XYEdges padding = new XYEdges(12, 12, 12, 12);
		roundedBorder = BorderFactory.createBitmapBorder(padding, borderBitmap);

		VerticalFieldManager vfm = new VerticalFieldManager();
		vfm.setBackground(BackgroundFactory.createLinearGradientBackground(0x00FFFFFF, 0x00FFFFFF, 0x0066B5F4,
				0x0095C9F8));

		titleBitmap = Bitmap.getBitmapResource("logo.PNG");
		titleBarField = new CustomTitlebar(screenTitle, Color.WHITE, 0x000072BC, titleBitmap, Field.USE_ALL_WIDTH);

		welcomeTitleDisplay();

		vfm.add(titleBarField);
		vfm.add(new SeparatorField());
		vfm.add(horizontalWelcomeLabel);

		setTitle(vfm);
	}

	// transition screen
	public static void showTransition(Screen screen) {

		TransitionContext transitionContextIn = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		transitionContextIn.setIntAttribute(TransitionContext.ATTR_DURATION, 150);
		transitionContextIn.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);

		TransitionContext transitionContextOut = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		transitionContextOut.setIntAttribute(TransitionContext.ATTR_DURATION, 150);
		transitionContextOut.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
		transitionContextOut.setIntAttribute(TransitionContext.ATTR_KIND, TransitionContext.KIND_OUT);

		UiEngineInstance engine = Ui.getUiEngineInstance();
		engine.setTransition(null, screen, UiEngineInstance.TRIGGER_PUSH, transitionContextIn);
		engine.setTransition(screen, null, UiEngineInstance.TRIGGER_POP, transitionContextOut);

		Controller.showScreen(screen);
	}

	// zoom transition
	public static void showZoomTransition(Screen screen) {

		UiEngineInstance engine = Ui.getUiEngineInstance();

		TransitionContext transitionContextIn = new TransitionContext(TransitionContext.TRANSITION_ZOOM);
		transitionContextIn.setIntAttribute(TransitionContext.ATTR_DURATION, 500);

		engine.setTransition(null, screen, UiEngineInstance.TRIGGER_PUSH, transitionContextIn);

		Controller.showScreen(screen);
	}

	// menu
	protected void makeMenu(Menu menu, int instance) {
		super.makeMenu(menu, instance);
		menu.add(new MenuItem("Logout", 0, 1) {
			public void run() {
				Screen screen = UiApplication.getUiApplication().getActiveScreen();
				if (screen instanceof RegistrationScreen || screen instanceof NewUserRegistrationScreen) {
					Controller.showScreen(new CustomTransperentDialog("Sorry! You must log in first"));
				} else {
					UiApplication.getUiApplication().pushScreen(
							new LogoutMenuTransparentDialog("Do you really want to LOGOUT?"));
				}
			}
		});

		menu.add(new MenuItem("Go to HomeScreen", 1, 1) {
			public void run() {
				Screen screen = UiApplication.getUiApplication().getActiveScreen();
				if (screen instanceof RegistrationScreen || screen instanceof NewUserRegistrationScreen) {
					Controller.showScreen(new CustomTransperentDialog("Log in first"));
				} else {
					if (!(screen instanceof ServicesScreen)) {
						UiApplication.getUiApplication().pushScreen(new ServicesScreen("Services"));
					}
				}
			}
		});
	}

	// welcome title
	public void welcomeTitleDisplay() {

		String curDateForDisplayLbl = new SimpleDateFormat("dd-MMM-yyyy").formatLocal(System.currentTimeMillis());

		DisplayTimeLbl = new LabelFieldImpl(curDateForDisplayLbl, DrawStyle.RIGHT | Field.USE_ALL_WIDTH
				| Field.USE_ALL_HEIGHT);

		DisplayTimeLbl.setBgColor(0x000072BC);
		DisplayTimeLbl.setFontColor(Color.WHITE);

		WelcomeNameLbl = new LabelFieldImpl("Welcome: " + Constants.UserName);
		WelcomeNameLbl.setBgColor(0x000072BC);
		WelcomeNameLbl.setFontColor(Color.WHITE);

		if (Constants.width > 320) {
			DisplayTimeLbl.setFont(Constants.font);
			WelcomeNameLbl.setFont(Constants.font);
		} else {
			DisplayTimeLbl.setFont(Constants.fontVerySmall);
			WelcomeNameLbl.setFont(Constants.fontVerySmall);
		}

		horizontalWelcomeLabel = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
		horizontalWelcomeLabel.add(WelcomeNameLbl);
		horizontalWelcomeLabel.add(DisplayTimeLbl);
		horizontalWelcomeLabel.setBorder(roundedBorder);
	}
}
