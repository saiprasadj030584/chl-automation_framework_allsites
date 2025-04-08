package com.mns.auto.cd.stepdefs;

import com.google.inject.Inject;
import com.mns.auto.cd.pages.SAPScriptExecutionPage;

import cucumber.api.java.en.Given;

public class SAPstepdefs {

	private final SAPScriptExecutionPage sAPScriptExecutionPage;

	@Inject
	public SAPstepdefs(SAPScriptExecutionPage sAPScriptExecutionPage) {
		this.sAPScriptExecutionPage = sAPScriptExecutionPage;
	}

	@Given("^user has triggered Donington ollerton urrn return  receipts Urrn receipts interface(\\d+) to SAP ECC system$")
	public void user_has_triggered_Donington_ollerton_urrn_return_receipts_Urrn_receipts_interface_to_SAP_ECC_system()
			throws Throwable {

		sAPScriptExecutionPage.triggerTheInterfaceI181FromSap();

	}

	@Given("^user has triggered Donington ollerton urrn return  receipts Urrn interface(\\d+) to SAP ECC system$")
	public void user_has_triggered_Donington_ollerton_urrn_return_receipts_Urrn_interface_to_SAP_ECC_system()
			throws Throwable {

		sAPScriptExecutionPage.triggerTheInterfaceI102FromSap();
	}

}
