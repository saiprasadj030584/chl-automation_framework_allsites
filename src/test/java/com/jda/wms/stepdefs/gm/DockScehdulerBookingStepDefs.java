package com.jda.wms.stepdefs.gm;

import java.util.ArrayList;

import org.junit.Assert;
import org.sikuli.script.Screen;
import org.sikuli.script.Key;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.gm.BookingInDiary;
import com.jda.wms.db.gm.BookingInDiaryLog;
import com.jda.wms.pages.gm.DockSchedulerPage;
import com.jda.wms.pages.gm.JDAFooter;
import com.jda.wms.pages.gm.JdaHomePage;
import com.jda.wms.pages.gm.Verification;

import cucumber.api.java.en.Then;

public class DockScehdulerBookingStepDefs {
	Screen screen = new Screen();
	private Verification verification;
	private Context context;
	private BookingInDiary bookingInDiary;
	private BookingInDiaryLog bookingInDiaryLog;
	private DockSchedulerPage dockSchedulerPage;
	private JdaHomePage jdaHomePage;
	private JDAFooter jdaFooter;

	@Inject
	public DockScehdulerBookingStepDefs(Verification verification, Context context, BookingInDiary bookingInDiary,
			BookingInDiaryLog bookingInDiaryLog, DockSchedulerPage dockSchedulerPage, JdaHomePage jdaHomePage,
			JDAFooter jdaFooter) {
		this.verification = verification;
		this.context = context;
		this.bookingInDiary = bookingInDiary;
		this.bookingInDiaryLog = bookingInDiaryLog;
		this.dockSchedulerPage = dockSchedulerPage;
		this.jdaHomePage = jdaHomePage;
		this.jdaFooter = jdaFooter;
	}

	

}
