/**
 * 
 */
/**
 * @author priyanka.selvaraj
 *
 */
package com.jda.wms.stepdefs.Exit;
import com.google.inject.Inject;
import com.jda.wms.pages.Exit.JdaExitLoginPage;

import cucumber.api.java.en.Given;

public class JDAExitLoginStepDefs {
	private final JdaExitLoginPage JdaExitLoginPage;

	@Inject
	public JDAExitLoginStepDefs(JdaExitLoginPage JdaExitLoginPage) {
		this.JdaExitLoginPage = JdaExitLoginPage;
	}
	@Given("^Logging in as warehouse user in Exit application$")
	public void Logging_in_as_warehouse_user_in_Exit_application() throws Throwable {
		JdaExitLoginPage.login();
		}
	}