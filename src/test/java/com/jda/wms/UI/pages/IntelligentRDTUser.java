/*
 * Copyright (C) 2017 P9134107
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jda.wms.UI.pages;

import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.jda.wms.context.Context;
import com.jda.wms.db.Database;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class IntelligentRDTUser {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Screen screen = new Screen();
	private final Database database;
	private final Context context;

	@Inject
	public IntelligentRDTUser(Database database, Context context) {
		this.database = database;
		this.context = context;
	}

	public boolean doScreen(String username) {
		String rdtScreen = database
				.executeStatmentSingleResponse("select RDT_SCREEN from (select * from RDT_SCREEN_LOG where USER_ID = '"
						+ username + "' order by LAST_DSTAMP desc) where rownum = 1");

		while (rdtScreen.contains("  ")) {
			rdtScreen = rdtScreen.replace("  ", " ");
		}

		String[] parts = rdtScreen.split(" ");
		switch (parts[2]) {
		case "MaiMnu":
			maiMnu();
			break;
		case "UsrMnu":
			usrMnu();
			break;
		case "RcvMnu":
			rcvMnu();
			break;
		case "BasRcvMnu":
			basRcvMnu();
			break;
		case "RcvPreEnt":
			rcvPreEnt();
			break;
		case "RcvPreCmpPre-Advice:":
			rcvPreCmp();
			break;
		}
		return false;
	}

	private void maiMnu() {
		write("At the main menu");
		switch (context.getCurrentTask()) {
		case PRE_ADVICE_RECEIVE:
			type("2");
			type(Key.ENTER);
			break;
		case PUTAWAY:
			type("2");
			type(Key.ENTER);
			break;
		}
	}

	private void usrMnu() {
		write("At the user defined menu");
		switch (context.getCurrentTask()) {
		case PRE_ADVICE_RECEIVE:
			type("1");
			type(Key.ENTER);
			break;
		case PUTAWAY:
			type("2");
			type(Key.ENTER);
			break;
		case PICKING:
			type("3");
			type(Key.ENTER);
			break;
		}
	}

	public void rcvMnu() {
		write("At the receiving menu");
		switch (context.getCurrentTask()) {
		case PRE_ADVICE_RECEIVE:
			type("1");
			type(Key.ENTER);
			break;
		case BLIND_RECEIVE:
			type("1");
			type(Key.ENTER);
			break;
		default:
			write("Something went wrong. I should not be here");
		}
	}

	public void basRcvMnu() {
		write("At the basic receiving screen");
		switch (context.getCurrentTask()) {
		case PRE_ADVICE_RECEIVE:
			type("2");
			type(Key.ENTER);
			break;
		case BLIND_RECEIVE:
			type("1");
			type(Key.ENTER);
			break;
		default:
			write("Something went wrong. I should not be here");
		}
	}

	public void rcvPreEnt() {
		write("At the Pre Advice entry screen");
		type(context.getPreAdviceId());
		type(context.getSkuId());
		type(Key.ENTER);
	}

	public void rcvPreCmp() {

	}

	private void type(String string) {
		write(string);
		screen.type(string);
	}

	private void write(String string) {
		System.out.println(string);
	}
}
