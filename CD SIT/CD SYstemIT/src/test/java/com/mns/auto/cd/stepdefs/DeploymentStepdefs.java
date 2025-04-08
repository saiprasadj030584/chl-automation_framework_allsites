package com.mns.auto.cd.stepdefs;

import com.google.inject.Inject;
import com.mns.auto.cd.config.Constants;
import com.mns.auto.cd.pages.DeploymentPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DeploymentStepdefs {

	private Constants constants;
	private DeploymentPage deploymentPage;

	@Inject
	public DeploymentStepdefs(Constants constants, DeploymentPage deploymentPage) {

		this.constants = constants;
		this.deploymentPage = deploymentPage;

	}

	@Given("^user to launch telnet server for specific region$")
	public void user_to_launch_telnet_server_for_specific_region() throws Throwable {
		System.out.println(constants.DEPLOYMENT_BATCHFILES + "PuttyStart.lnk");
		deploymentPage.connectTelnet();
		deploymentPage.enterAsSudoUser();
	}

	@Given("^user checking the space in given environment$")
	public void user_checking_the_space_in_given_environment() throws Throwable {
		deploymentPage.spaceCheckInSpecificEnvironment();
	}

	@Given("^user stopping the app services$")
	public void user_stopping_the_app_services() throws Throwable {
//		deploymentPage.stopApplication();

	}

	@Then("^execute the given package for release version \"([^\"]*)\"$")
	public void execute_the_given_package_for_release_version(String version) throws Throwable {
		deploymentPage.runReleaseDeployPackage(version);

	}

	@Then("^user  start the app services$")
	public void user_starting_the_app_services() throws Throwable {
		deploymentPage.startApplication();

	}

}
