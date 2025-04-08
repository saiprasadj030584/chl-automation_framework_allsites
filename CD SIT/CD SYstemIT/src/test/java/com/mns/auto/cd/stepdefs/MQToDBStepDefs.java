package com.mns.auto.cd.stepdefs;

import com.google.inject.Inject;

import com.mns.auto.cd.pages.MS_Middleware;
import com.mns.auto.cd.memory.KeepInMemory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class MQToDBStepDefs {

	// private final com.mns.auto.db.Exadata exadata;
	private KeepInMemory keepInMemory;
	// private TestDataReader testDataReader;
	private MS_Middleware ms_Middleware;
	// private DBResultSetComparator dBResultSetComparator;

	@Inject
	public MQToDBStepDefs(KeepInMemory keepInMemory, MS_Middleware ms_Middleware) {
		// this.exadata = exadata;
		this.keepInMemory = keepInMemory;
		// this.testDataReader = testDataReader;
		this.ms_Middleware = ms_Middleware;
		// this.dBResultSetComparator= dBResultSetComparator;
	}

	@Given("^I need to test the pattern for \"([^\"]*)\" in region \"([^\"]*)\" and version \"([^\"]*)\" for interface \"([^\"]*)\"$")
	public void i_need_to_test_the_pattern_for_in_region_and_old_version_for_interface(String source,
			String destination, String msgId, String interface_id) throws Throwable {
		System.out.println("entereed frst");
		keepInMemory.setRegion(source);
		keepInMemory.setProject(destination);
		keepInMemory.setVersion(msgId);
		System.out.println(KeepInMemory.getVersion());
		keepInMemory.setInterfaceId(interface_id);

	}

	@Given("^I need to test the pattern for \"([^\"]*)\" in region \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" for interface \"([^\"]*)\"$")
	public void i_need_to_test_the_pattern_for_in_region_and_for_interface(String source, String destination,
			String msg, String msg_id, String interface_id) throws Throwable {
		keepInMemory.setRegion(source);
		keepInMemory.setProject(destination);
		keepInMemory.setMsg(msg);
		keepInMemory.setVersion(msg_id);
		System.out.println(KeepInMemory.getVersion());
		System.out.println(KeepInMemory.getMsg());
		keepInMemory.setInterfaceId(interface_id);
	}

	@And("^the validation of audit in MS Middleware should be successful$")
	public void the_validation_of_audit_in_ms_middleware_should_be_successful() throws Throwable {
		ms_Middleware.MSMiddleWareAuditValidation();
		ms_Middleware.generate_result_file();

	}
	/*
	 * @And("^Save Results in file$") public void save_Results_In_File() throws
	 * Throwable{ ms_Middleware.generate_result_file();
	 * 
	 * 
	 * }
	 */
}
