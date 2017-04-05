package com.jda.wms.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.BackOfficeContext;
import com.jda.wms.context.SPContext;
import com.jda.wms.hooks.Hooks;
import com.jda.wms.webdriver.Locator;

import cucumber.api.guice.CucumberScopes;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			bind(Configuration.class).in(Scopes.SINGLETON);
			bind(Locator.class).in(Scopes.SINGLETON);
			install(new WebDriverProviderModule());
			bind(Hooks.class).in(CucumberScopes.SCENARIO);
			bind(SPContext.class).in(CucumberScopes.SCENARIO);
			bind(BackOfficeContext.class).in(CucumberScopes.SCENARIO);
		} catch (Exception e) {
			addError(e.getMessage());
		}
	}
}
