package com.mns.auto.cd.webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.WebElement;

public class Conditions {

	List<Predicate<WebElement>> conditions;

	public Conditions() {
		conditions = new ArrayList<Predicate<WebElement>>();
	}

	public static Conditions conditions() {
		return new Conditions();
	}

	private Predicate<WebElement> isDisplayedPredicate = p -> p.isDisplayed();
	private Predicate<WebElement> isSelectedPredicate = p -> p.isSelected();
	private Predicate<WebElement> isEnabledPredicate = p -> p.isEnabled();

	private Predicate<WebElement> textContainsPredicate(String text) {
		return p -> p.getText().contains(text);
	}

	private Predicate<WebElement> textEqualsPredicate(String text) {
		return p -> p.getText().equalsIgnoreCase(text);
	}

	private Predicate<WebElement> attributeContainsPredicate(String attribute, String text) {
		return p -> p.getAttribute(attribute).contains(text);
	}

	private Predicate<WebElement> attributeEqualsPredicate(String attribute, String text) {
		return p -> p.getAttribute(attribute).equalsIgnoreCase(text);
	}

	public Conditions displayed() {
		this.conditions.add(isDisplayedPredicate);
		return this;
	}

	public Conditions selected() {
		this.conditions.add(isSelectedPredicate);
		return this;
	}

	public Conditions enabled() {
		this.conditions.add(isEnabledPredicate);
		return this;
	}

	public Conditions textContains(String text) {
		this.conditions.add(textContainsPredicate(text));
		return this;
	}

	public Conditions textEquals(String text) {
		this.conditions.add(textEqualsPredicate(text));
		return this;
	}

	public Conditions attributeContains(String attribute, String text) {
		this.conditions.add(attributeContainsPredicate(attribute, text));
		return this;
	}

	public Conditions attributeEquals(String attribute, String text) {
		this.conditions.add(attributeEqualsPredicate(attribute, text));
		return this;
	}

	public WebElement match(WebElement element) {
		if (element == null) {
			return null;
		}
		return testConditions(element) ? element : null;
	}

	public List<WebElement> matches(List<WebElement> elements) {
		List<WebElement> matches = new ArrayList<WebElement>();
		for (WebElement element : elements) {
			if (testConditions(element)) {
				matches.add(element);
			}
		}
		return matches.isEmpty() ? null : matches;
	}

	private boolean testConditions(WebElement element) {
		for (Predicate<WebElement> predicate : this.conditions) {
			if (!predicate.test(element)) {
				return false;
			}
		}
		return true;
	}

}
