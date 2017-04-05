package com.jda.wms.guice;

import org.openqa.selenium.WebDriver;

import com.google.inject.AbstractModule;

import cucumber.api.guice.CucumberScopes;

public class WebDriverProviderModule extends AbstractModule {

	public WebDriverProviderModule() {
	}

	@Override
	protected void configure() {
		bind(WebDriver.class).toProvider(WebDriverProvider.class).in(CucumberScopes.SCENARIO);

	}

}
