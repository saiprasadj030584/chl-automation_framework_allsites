package com.jda.wms.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.jda.wms.config.Configuration;
import com.jda.wms.context.Context;
import com.jda.wms.context.OrderHeaderContext;
import com.jda.wms.hooks.Hooks;

import cucumber.api.guice.CucumberScopes;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			bind(Configuration.class).in(Scopes.SINGLETON);
			install(new WebDriverProviderModule());
			bind(Hooks.class).in(CucumberScopes.SCENARIO);
			bind(Context.class).in(CucumberScopes.SCENARIO);
			bind(OrderHeaderContext.class).in(CucumberScopes.SCENARIO);
		} catch (Exception e) {
			addError(e.getMessage());
		}
	}
}