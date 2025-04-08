package com.mns.auto.cd.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.yaml.snakeyaml.Yaml;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Constants;
import com.mns.auto.cd.exception.LocatorException;

public class Locator {

	private enum Locators {
		id, name, css, xpath
	}

	private Map<String, Map<String, String>> locators = new HashMap<String, Map<String, String>>();

	@Inject
	public Locator() {
		loadLocatorFiles();
	}

	@SuppressWarnings("unchecked")
	private void loadLocatorFiles() {
		File folder = new File(Constants.LOCATOR_PATH);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			File locatorFile = new File(Constants.LOCATOR_PATH + listOfFiles[i].getName());
			if (locatorFile.getName().contains("locator")) {
				InputStream inputStream;
				try {
					inputStream = new FileInputStream(locatorFile);
					Yaml yaml = new Yaml();
					Map<String, Object> locatorData = (Map<String, Object>) yaml.load(inputStream);
					loadLocators(locatorData, Locators.id.toString());
					loadLocators(locatorData, Locators.name.toString());
					loadLocators(locatorData, Locators.css.toString());
					loadLocators(locatorData, Locators.xpath.toString());
				} catch (FileNotFoundException e) {
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	void loadLocators(Map<String, Object> locatorData, String locatorType) {
		if (!locatorData.containsKey(locatorType)) {
			return;
		}
		if (locatorData.get(locatorType) == null) {
			return;
		}
		if (hasKey(locatorType)) {
			Map<String, String> mergedMap = Stream
					.of(this.locators.get(locatorType), (Map<String, String>) locatorData.get(locatorType))
					.flatMap(m -> m.entrySet().stream()).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
			this.locators.put(locatorType, mergedMap);
		} else {
			this.locators.put(locatorType, (Map<String, String>) locatorData.get(locatorType));
		}
	}

	public Map<String, String> getLocatorString(String key) {
		Map<String, String> locatorString = new HashMap<String, String>();
		for (Entry<String, Map<String, String>> entry : locators.entrySet()) {
			Map<String, String> map = entry.getValue();
			if (map.containsKey(key)) {
				locatorString.put(entry.getKey(), map.get(key));
			}
		}
		return locatorString;
	}

	public boolean hasKey(String key) {
		return this.locators.containsKey(key);
	}

	public By getBy(String locator) throws LocatorException {
		return getBy(locator, Scope.ELEMENT, Tag.GENERIC);
	}

	public By getBy(String locator, Scope scope, Tag tag) throws LocatorException {
		By by = null;
		Map<String, String> locatorString = getLocatorString(locator);
		if (locatorString.isEmpty()) {
			throw new LocatorException("Locator - '" + locator + "' not found in the locator file");
		}
		for (Entry<String, String> entry : locatorString.entrySet()) {
			switch (entry.getKey()) {
			case "id":
				by = getIdLocator(entry.getValue(), scope, tag);
				break;
			case "name":
				by = getNameLocator(entry.getValue(), scope, tag);
				break;
			case "css":
				by = getCssLocator(entry.getValue(), scope, tag);
				break;
			case "xpath":
				by = getXpathLocator(entry.getValue(), scope, tag);
				break;
			}
		}
		return by;
	}

	private By getIdLocator(String locatorString, Scope scope, Tag tag) throws LocatorException {
		switch (scope) {
		case ELEMENT:
			if (tag == Tag.GENERIC) {
				return By.id(locatorString);
			}
			return By.cssSelector(tag.getTag() + "[id=" + locatorString + "]");
		case CHILDREN:
			return By.cssSelector("#" + locatorString + " > " + tag.getTag());
		case DESCENDANTS:
			return By.cssSelector("#" + locatorString + " " + tag.getTag());
		}
		throw new LocatorException("Scope - " + scope.toString() + " is not defined");
	}

	private By getCssLocator(String locatorString, Scope scope, Tag tag) throws LocatorException {
		switch (scope) {
		case ELEMENT:
			if (tag == Tag.GENERIC) {
				return By.cssSelector(locatorString);
			}
			return By.cssSelector(locatorString + " " + tag.getTag());
		case CHILDREN:
			return By.cssSelector(Arrays.asList(locatorString.split(",")).stream()
					.map(i -> i.toString() + " " + tag.getTag()).collect(Collectors.joining(",")));
		case DESCENDANTS:
			return By.cssSelector(Arrays.asList(locatorString.split(",")).stream()
					.map(i -> i.toString() + " " + tag.getTag()).collect(Collectors.joining(",")));
		}
		throw new LocatorException("Scope - " + scope.toString() + " is not defined");
	}

	private By getNameLocator(String locatorString, Scope scope, Tag tag) throws LocatorException {
		switch (scope) {
		case ELEMENT:
			if (tag == Tag.GENERIC) {
				return By.name(locatorString);
			}
			return By.cssSelector(tag.getTag() + "[name=\"" + locatorString + "\"]");
		case CHILDREN:
			return By.cssSelector("*[name=\"" + locatorString + "\"] > " + tag.getTag());
		case DESCENDANTS:
			return By.cssSelector("*[name=\"" + locatorString + "\" " + tag.getTag());
		}
		throw new LocatorException("Scope - " + scope.toString() + " is not defined");
	}

	private By getXpathLocator(String locatorString, Scope scope, Tag tag) throws LocatorException {
		switch (scope) {
		case ELEMENT:
			return By.xpath(locatorString);
		case CHILDREN:
			return By.xpath(locatorString + "/" + tag.getTag());
		case DESCENDANTS:
			return By.xpath(locatorString + "//" + tag.getTag());
		}
		throw new LocatorException("Scope - " + scope.toString() + " is not defined");
	}

}
