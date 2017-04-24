package com.jda.wms.pages.foods;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.inject.Inject;

public class InventoryUpdatePage {

	Screen screen = new Screen();
	int timeoutInSec = 20;
	Region reg = new Region(0, 0, 4000, 1000);

	@Inject
	public InventoryUpdatePage() {

	}

	public void enterExpiryDateUpdate(String expiryDateUpdate) throws FindFailed {
		screen.type(expiryDateUpdate);
	}

	public void enterTagId(String tagId) throws FindFailed {
		screen.type(tagId);
	}

	public String enterFutureDate() throws FindFailed {
		SimpleDateFormat formattedExpiryDate = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 364);
		String futureDateExpiry = formattedExpiryDate.format(cal.getTime());
		screen.type(futureDateExpiry);
		return futureDateExpiry;
	}

	public void selectReasonCode(String reasonCode) throws FindFailed {
		screen.type(Key.TAB);
		screen.type(Key.TAB);
		screen.type(reasonCode);
	}
}