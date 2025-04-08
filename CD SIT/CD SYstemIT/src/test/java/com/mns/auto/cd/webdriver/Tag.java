package com.mns.auto.cd.webdriver;

public enum Tag {

	GENERIC("*"), INPUT("input"), BUTTON("button"), HYPERLINK("a"), LIST("li"), SPAN("span"), DIV("div"), SELECT(
			"select"), OPTION("option"), IMAGE("img"), TABLE("table"), TABLE_BODY("tbody"), TABLE_DATA_CELL(
					"td"), TABLE_ROW("tr"), TABLE_FOOTER("tfoot");

	private final String tag;

	private Tag(final String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

}
