package com.mns.auto.cd.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.mns.auto.cd.config.Configuration;
import com.mns.auto.cd.webdriver.Locator;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			bind(Configuration.class).in(Scopes.SINGLETON);
			bind(Locator.class).in(Scopes.SINGLETON);
			install(new WebDriverProviderModule());
		} catch (Exception e) {
			addError(e.getMessage());
		}
	}
}
