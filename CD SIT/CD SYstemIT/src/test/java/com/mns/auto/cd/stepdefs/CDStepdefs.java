package com.mns.auto.cd.stepdefs;

import com.google.inject.Inject;
import com.mns.auto.cd.pages.CDPage;
import com.mns.auto.cd.pages.GlobalBoxed;
import com.mns.auto.cd.pages.GlobalEDC;
import com.mns.auto.cd.pages.GlobalHanging;
import com.mns.auto.cd.pages.GlobalNDC;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CDStepdefs {

	private final GlobalEDC globalEDC;
	private final GlobalNDC globalNDC;
	private final CDPage cDPage;
	private final GlobalBoxed globalBoxed;
	private final GlobalHanging globalHanging;

	private static String getTestCompleteLogTime = null;

	@Inject
	public CDStepdefs(GlobalEDC globalEDC, GlobalHanging globalHanging, GlobalBoxed globalBoxed, GlobalNDC globalNDC,
			CDPage cDPage) {
		this.globalEDC = globalEDC;
		this.cDPage = cDPage;
		this.globalNDC = globalNDC;
		this.globalBoxed = globalBoxed;
		this.globalHanging = globalHanging;
	}

	@Given("^user logged in to the RPWMS application$")
	public void user_logged_in_to_the_RPWMS_application() throws Throwable {
		globalBoxed.TC().RunRoutine("CommonFunctions.LaunchAndLoginWMS");
	}

	@Given("^run the direct process$")
	public void run_the_direct_process() throws Throwable {
		globalBoxed.TC().RunRoutine("Boxed_goods.SIT_BGI_Direct_BCS_Processing");
		Thread.sleep(5000);
	}

	@Then("^validate the pallet location$")
	public void validate_the_pallet_location() throws Throwable {
		globalBoxed.TC().RunRoutine("Boxed_goods.SIT_BGI_Direct_BCS_Validation");
	}

	@Given("^A pallet in MWBB location and to perform putaway to reserve loaction$")
	public void a_pallet_in_MWBB_location_and_to_perform_putaway_to_reserve_loaction() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.NDC_BGI_PUT_001");
	}

	@Given("^An order with Auto Boxed and Auto Hanging elements with same consignment to be loaded on different trailers , DST workplace for boxed and RSH lane for hanging\\.$")
	public void an_order_with_Auto_Boxed_and_Auto_Hanging_elements_with_same_consignment_to_be_loaded_on_different_trailers_DST_workplace_for_boxed_and_RSH_lane_for_hanging()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.NDC_BGO_DESP_001");

	}

	@Given("^A loaded trailer which is to be unloaded and loaded back on a different trailer followed by dock booking$")
	public void a_loaded_trailer_which_is_to_be_unloaded_and_loaded_back_on_a_different_trailer_followed_by_dock_booking()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.NDC_BGO_DESP_002");
	}

	@Given("^An order with quantity greater than the SCS stock and to replenish SCS stock from BCS and the excess stock in ISC should move to SCS or to ETB if empty$")
	public void an_order_with_quantity_greater_than_the_SCS_stock_and_to_replenish_SCS_stock_from_BCS_and_the_excess_stock_in_ISC_should_move_to_SCS_or_to_ETB_if_empty()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_AutoPick_002");
	}

	@Given("^A Boxed sku with Postal urrn which is to be received followed by dock booking$")
	public void a_Boxed_sku_with_Postal_urrn_which_is_to_be_received_followed_by_dock_booking() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.Ollerton_001");
	}

	/*
	 * @Given("^An Hanging with Postal urrn which is to be received followed by dock booking$"
	 * ) public void
	 * an_Hanging_sku_with_Postal_urrn_which_is_to_be_received_followed_by_dock_booking
	 * () throws Throwable {
	 * 
	 * globalNDC.TC().RunRoutine("NDC.Ollerton_002"); }
	 */

	@Given("^An Hanging sku with Postal urrn which is to be received followed by dock booking$")
	public void an_Hanging_sku_with_Postal_urrn_which_is_to_be_received_followed_by_dock_booking() throws Throwable {
		globalNDC.TC().RunRoutine("NDC.Ollerton_002");
	}

	@Given("^A Boxed sku with urrn which is to be received followed by dock booking$")
	public void a_Boxed_sku_with_urrn_which_is_to_be_received_followed_by_dock_booking() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.Ollerton_003");
	}

	@Given("^An Hanging sku with urrn which is to be received followed by dock booking$")
	public void an_Hanging_sku_with_urrn_which_is_to_be_received_followed_by_dock_booking() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.Ollerton_004");
	}

	@Given("^A received hanging sku is to be putaway to final location and to create a transfer order and to ship the Transfer order$")
	public void a_received_hanging_sku_is_to_be_putaway_to_final_location_and_to_create_a_transfer_order_and_to_ship_the_Transfer_order()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.Ollerton_005");
	}

	@Given("^A received Boxed sku is to be putaway to final location$")
	public void a_received_Boxed_sku_is_to_be_putaway_to_final_location() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.Ollerton_006");
		globalNDC.TC().RunRoutine("CommonFunctions.IEKill");
	}

	@Given("^A received Boxed sku is to be Picked, marshalling to final location and to create a transfer order and to ship the Transfer orde$")
	public void a_received_Boxed_sku_is_to_be_Picked_marshalling_to_final_location_and_to_create_a_transfer_order_and_to_ship_the_Transfer_orde()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.Ollerton_007");
	}

	@Given("^An order with Auto Boxed,Manual pallet and Auto Hanging elements with same consignment to be loaded on same trailer , DST workplace for boxed and RSH lane for hanging\\.$")
	public void an_order_with_Auto_Boxed_Manual_pallet_and_Auto_Hanging_elements_with_same_consignment_to_be_loaded_on_same_trailer_DST_workplace_for_boxed_and_RSH_lane_for_hanging()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_DESPATCH_003");
	}

	@Given("^Two orders one of which is franchise and other is NDC, loading is done is two different trailers followed by shipping$")
	public void two_orders_one_of_which_is_franchise_and_other_is_NDC_loading_is_done_is_two_different_trailers_followed_by_shipping()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_DESPATCH_004");
	}

	@Given("^Multiple orders with same consignment which is picked and loading is done in same trailer followed by shipping$")
	public void multiple_orders_with_same_consignment_which_is_picked_and_loading_is_done_in_same_trailer_followed_by_shipping()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_DESPATCH_005");
	}

	@Given("^SCS Module to initiate consolodation which consolidation is done by  picking workplaces and switching ON the PTT work station$")
	public void scs_Module_to_initiate_consolodation_which_consolidation_is_done_by_picking_workplaces_and_switching_ON_the_PTT_work_station()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.NDC_CONS_001");
	}

	@Given("^SCS Module to initiate consolodation which consolidation is done by switching ON the PTT work station and amending the quantity of consolidation$")
	public void scs_Module_to_initiate_consolodation_which_consolidation_is_done_by_switching_ON_the_PTT_work_station_and_amending_the_quantity_of_consolidation()
			throws Throwable {

		globalNDC.TC().RunRoutine("NDC.NDC_CONS_002");
	}

	@Given("^An NDC order with a boxed sku$")
	public void an_NDC_order_with_a_boxed_sku() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_MANUALBOX_001");
	}

	@Given("^An EDC order with a boxed/Hanging sku$")
	public void an_EDC_order_with_a_boxed_Hanging_sku() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_MANUALBOX_002");
	}

	@Given("^An order with two SKUs$")
	public void an_order_with_two_SKUs() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_MANUALBOX_003");
	}

	@Given("^A SKU with  pick face inventory less than the Resv inventory$")
	public void a_SKU_with_pick_face_inventory_less_than_the_Resv_inventory() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.GB_MANUALBOX_004");
	}

	@Given("^Two NDC orders with same route/consignment$")
	public void two_NDC_orders_with_same_route_consignment() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.AUTOBOX_DESPATCH_01");
	}

	@Given("^one NDC order with Boxed Sku and Hanging SKU$")
	public void one_NDC_order_with_Boxed_Sku_and_Hanging_SKU() throws Throwable {

		globalNDC.TC().RunRoutine("NDC.BOXHANG_DESPATCH_01");
	}

	@Given("^To check that we can receive a RCPT_DIR_CNI_Processing$")
	public void to_check_that_we_can_receive_a_RCPT_DIR_CNI_Processing() throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_CNI_001_Processing");
	}

	@Given("^To check that we can receive a RCPT_DIR_CNI_Validating$")
	public void to_check_that_we_can_receive_a_RCPT_DIR_CNI_Validating() throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_CNI_001_validation");
	}

	@Given("^To check that we can receive a RCPT_DIR_FW_Processing$")
	public void to_check_that_we_can_receive_a_RCPT_DIR_FW_Processing() throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_FWL_001_Processing");
	}

	@Given("^To check that we can receive a RCPT_DIR_FW_Validating$")
	public void to_check_that_we_can_receive_a_RCPT_DIR_FW_Validating() throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_FWL_001_Validation");
	}

	@Given("^To check that inventory receipted from Goods In Hanging PUT_DIR_HBH as processing part$")
	public void to_check_that_inventory_receipted_from_Goods_In_Hanging_PUT_DIR_HBH_as_processing_part()
			throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_HBH_001_processing");
	}

	@Given("^To check that inventory receipted from Goods In Hanging PUT_DIR_HBH as Validation part$")
	public void to_check_that_inventory_receipted_from_Goods_In_Hanging_PUT_DIR_HBH_as_Validation_part()
			throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_HBH_001_Validation");
	}

	@Given("^To check where inventory in the MWH is unlocked in the WMS and WCS as processing part$")
	public void to_check_where_inventory_in_the_MWH_is_unlocked_in_the_WMS_and_WCS_as_processing_part()
			throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.UNLCK_WMS_MWH_003");
	}

	@Given("^To check where inventory in the MWH is locked in the WMS and WCS as processing part$")
	public void to_check_where_inventory_in_the_MWH_is_locked_in_the_WMS_and_WCS_as_processing_part() throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.LCK_WMS_DBH_001");
	}

	@Given("^To check that we can receive a RCPT_DIR_STNRD_Processing$")
	public void to_check_that_we_can_receive_a_RCPT_DIR_STNRD_Processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_STNRD_001_Processing");
	}

	@Given("^To check that we can receive a RCPT_DIR_STNRD_validating$")
	public void to_check_that_we_can_receive_a_RCPT_DIR_STNRD_validating() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_STNRD_001_Validation");

	}

	@Given("^To check where inventory in the DBH is locked in the WMS and WCS as processing part$")
	public void to_check_where_inventory_in_the_DBH_is_locked_in_the_WMS_and_WCS_as_processing_part() throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.LCK_WMS_DBH_001");
	}

	@Given("^To check that we can do Negative adjustment$")
	public void to_check_that_we_can_do_Negative_adjustment() throws Throwable {
		globalHanging.TC().RunRoutine("STK_MWH_ADJ_NEG_001.STK_MWH_ADJ_NEG_001");

	}

	@Given("^To check that we can do Positive adjustmentss$")
	public void to_check_that_we_can_do_Positive_adjustmentss() throws Throwable {

		globalHanging.TC().RunRoutine("STK_MWH_ADJ_NEG_001.STK_MWH_ADJ_POS_002");

	}

	@Given("^To check that we can do Putaway in HBH location as processing part$")
	public void to_check_that_we_can_do_Putaway_in_HBH_location_as_processing_part() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_HBH_001_processing");

	}

	@Given("^To check that we can do Putaway in HBH location as validation part$")
	public void to_check_that_we_can_do_Putaway_in_HBH_location_as_validation_part() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_HBH_001_Validation");

	}

	@Given("^To check that we can do Putaway in DBH location as processing part$")
	public void to_check_that_we_can_do_Putaway_in_DBH_location_as_processing_part() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_DBH_001_Processing");

	}

	@Given("^To check that we can do Putaway in DBH location as validation part$")
	public void to_check_that_we_can_do_Putaway_in_DBH_location_as_validation_part() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_DBH_001_Validation");

	}

	@Given("^To check that we can do Putaway in MWH location as processing part$")
	public void to_check_that_we_can_do_Putaway_in_MWH_location_as_processing_part() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_MWH_001_Processing");
	}

	@Given("^To check that we can do Putaway in MWH location as validation part$")
	public void to_check_that_we_can_do_Putaway_in_MWH_location_as_validation_part() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_DIR_MWH_001_Validation");

	}

	@Given("^To check that we can receive a RCPT_FSV_STNRD_Processing$")
	public void to_check_that_we_can_receive_a_RCPT_FSV_STNRD_Processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_FSV_STNRD_001_Processing");

	}

	@Given("^To check that we can receive a RCPT_FSV_STNRD_validating$")
	public void to_check_that_we_can_receive_a_RCPT_FSV_STNRD_validating() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_FSV_STNRD_001_Validation");

	}

	@Given("^To check that we can receive a RCPT_UKHC_processing$")
	public void to_check_that_we_can_receive_a_RCPT_UKHC_processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_UKHC_001_processing");

	}

	@Given("^To check that we can receive a RCPT_UKHC_validation$")
	public void to_check_that_we_can_receive_a_RCPT_UKHC_validation() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_UKHC_001_validation");

	}

	@Given("^To check that we can receive a DIR EXCPTN Processing$")
	public void to_check_that_we_can_receive_a_DIR_EXCPTN_Processing() throws Throwable {
		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_EXCPTN_001_Processing");

	}

	@Given("^To check that we can receive a DIR EXCPTN validation$")
	public void to_check_that_we_can_receive_a_DIR_EXCPTN_validation() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_EXCPTN_001_Validation");

	}

	@Given("^To check that we can receive a CR RLDIR processing$")
	public void to_check_that_we_can_receive_a_CR_RLDIR_processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_CR814_RL_DIR_001_Processing");

	}

	@Given("^To check that we can receive a CR RLDIR validation$")
	public void to_check_that_we_can_receive_a_CR_RLDIR_validation() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_BCH_SAME_1000_Processing");

	}

	@Given("^To check that we can receive a PUT_BCH_SAME processing$")
	public void to_check_that_we_can_receive_a_PUT_BCH_SAME_processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_BCH_SAME_1000_Processing");

	}

	@Given("^To check that we can receive a PUT_BCH_SAME validation$")
	public void to_check_that_we_can_receive_a_PUT_BCH_SAME_validation() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.PUT_BCH_SAME_1000_Validation");

	}

	@Given("^To check that we can receive a MWH VAL processing$")
	public void to_check_that_we_can_receive_a_MWH_VAL_processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.MWH_892_VAL_001");

	}

	@Given("^To check that we can receive a RCPT FSV CNI processing$")
	public void to_check_that_we_can_receive_a_RCPT_FSV_CNI_processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_FSV_CNI_Processing");

	}

	@Given("^To check that we can receive a RCPT FSV CNI validation$")
	public void to_check_that_we_can_receive_a_RCPT_FSV_CNI_validation() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_FWL_001_Processing");

	}

	@Given("^To check that we can receive a RCPT FSV FWL processing$")
	public void to_check_that_we_can_receive_a_RCPT_FSV_FWL_processing() throws Throwable {

		globalHanging.TC().RunRoutine("Hanging_Goods_In.RCPT_DIR_FWL_001_Validation");

	} 
	
	@Given("^To be verified is it possible to retrieve a SKU/Supplier from DBH to MWH lane$")
	public void to_be_verified_is_it_possible_to_retrieve_a_SKU_Supplier_from_DBH_to_MWH_lane() throws Throwable {
	}

	@Given("^To be verified is it possible to retrieve a Sku/Supplier from DBH to a RBH lane$")
	public void to_be_verified_is_it_possible_to_retrieve_a_Sku_Supplier_from_DBH_to_a_RBH_lane() throws Throwable {
	}
	

	@Given("^A full depal SKU with the stocks at HBW location$")
	public void a_full_depal_SKU_with_the_stocks_at_HBW_location() throws Throwable {

		globalNDC.TC().RunRoutine("Boxed_goods.Full_Depalletisation");
	}

	@Given("^A partial depal SKU with the stocks at HBW location$")
	public void a_partial_depal_SKU_with_the_stocks_at_HBW_location() throws Throwable {

		globalNDC.TC().RunRoutine("Boxed_goods.Partial_Depalletisation");
	}

	@Given("^A add depal SKU with the stocks at HBW location$")
	public void a_add_depal_SKU_with_the_stocks_at_HBW_location() throws Throwable {

		globalNDC.TC().RunRoutine("Boxed_goods.Add_Depalletisation_Manually");
	}

	@Given("^A delete depal SKU with the stocks at HBW location$")
	public void a_delete_depal_SKU_with_the_stocks_at_HBW_location() throws Throwable {

		globalNDC.TC().RunRoutine("Boxed_goods.Delete_Palletisation");
	}

	@Given("^A update demand depal SKU with the stocks at HBW location$")
	public void a_update_demand_depal_SKU_with_the_stocks_at_HBW_location() throws Throwable {

		globalNDC.TC().RunRoutine("Boxed_goods.Update_Depal_Demand");
	}

	@Given("^A update priority depal SKU with the stocks at HBW location$")
	public void a_update_priority_depal_SKU_with_the_stocks_at_HBW_location() throws Throwable {

		globalNDC.TC().RunRoutine("Boxed_goods.Update_Depal_Priority");
	}
	
	

	@Given("^To confirm direct delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part$")
	public void to_confirm_direct_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_processing_part()
			throws Throwable {
	}

	@Given("^To confirm FSV delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part$")
	public void to_confirm_FSV_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_processing_part()
			throws Throwable {
	}

	@Given("^To confirm UKHC delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part$")
	public void to_confirm_UKHC_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_processing_part()
			throws Throwable {
	}

	@Given("^To confirm Per Una delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving processing part$")
	public void to_confirm_Per_Una_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_processing_part()
			throws Throwable {
	}

	@Given("^To confirm that load units sent to a repack workstation for manual receiving can be sent to a repack workstation flagged for advanced error handling if the stock cannot be manually received processing part$")
	public void to_confirm_that_load_units_sent_to_a_repack_workstation_for_manual_receiving_can_be_sent_to_a_repack_workstation_flagged_for_advanced_error_handling_if_the_stock_cannot_be_manually_received_processing_part()
			throws Throwable {
	}

	@Given("^To confirm direct delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part$")
	public void to_confirm_direct_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_validation_part()
			throws Throwable {
	}

	@Given("^To confirm FSV delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part$")
	public void to_confirm_FSV_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_validation_part()
			throws Throwable {
	}

	@Given("^To confirm UKHC delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part$")
	public void to_confirm_UKHC_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_validation_part()
			throws Throwable {
	}

	@Given("^To confirm Per Una delivery load units that cannot be automatically received by the omniscanner are sent to a repack workstation for manual receiving validation part$")
	public void to_confirm_Per_Una_delivery_load_units_that_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_manual_receiving_validation_part()
			throws Throwable {
	}

	@Given("^To confirm that load units sent to a repack workstation for manual receiving can be sent to a repack workstation flagged for advanced error handling if the stock cannot be manually received validation part$")
	public void to_confirm_that_load_units_sent_to_a_repack_workstation_for_manual_receiving_can_be_sent_to_a_repack_workstation_flagged_for_advanced_error_handling_if_the_stock_cannot_be_manually_received_validation_part()
			throws Throwable {
	}

	@Given("^To confirm FSV delivery load units that detected as being a blue supplier tote and cannot be automatically received by the omniscanner are sent to a repack workstation for blue tote handling processing part$")
	public void to_confirm_FSV_delivery_load_units_that_detected_as_being_a_blue_supplier_tote_and_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_blue_tote_handling_processing_part()
			throws Throwable {
	}

	@Given("^To confirm UKHC delivery load units that detected as being a blue supplier tote and cannot be automatically received by the omniscanner are sent to a repack workstation for blue tote handling processing part$")
	public void to_confirm_UKHC_delivery_load_units_that_detected_as_being_a_blue_supplier_tote_and_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_blue_tote_handling_processing_part()
			throws Throwable {
	}

	@Given("^To confirm Returns delivery load units that detected as being a blue supplier tote and cannot be automatically received by the omniscanner are sent to a repack workstation for blue tote handling processing part$")
	public void to_confirm_Returns_delivery_load_units_that_detected_as_being_a_blue_supplier_tote_and_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_blue_tote_handling_processing_part()
			throws Throwable {
	}

	@Given("^To confirm FSV delivery load units that detected as being a blue supplier tote and cannot be automatically received by the omniscanner are sent to a repack workstation for blue tote handling validation part$")
	public void to_confirm_FSV_delivery_load_units_that_detected_as_being_a_blue_supplier_tote_and_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_blue_tote_handling_validation_part()
			throws Throwable {
	}

	@Given("^To confirm UKHC delivery load units that detected as being a blue supplier tote and cannot be automatically received by the omniscanner are sent to a repack workstation for blue tote handling validation part$")
	public void to_confirm_UKHC_delivery_load_units_that_detected_as_being_a_blue_supplier_tote_and_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_blue_tote_handling_validation_part()
			throws Throwable {
	}

	@Given("^To confirm Returns delivery load units that detected as being a blue supplier tote and cannot be automatically received by the omniscanner are sent to a repack workstation for blue tote handling validation part$")
	public void to_confirm_Returns_delivery_load_units_that_detected_as_being_a_blue_supplier_tote_and_cannot_be_automatically_received_by_the_omniscanner_are_sent_to_a_repack_workstation_for_blue_tote_handling_validation_part()
			throws Throwable {
	}

	@Given("^To confirm Direct delivery load units that are classified as large size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_Direct_delivery_load_units_that_are_classified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units that are claasified as large size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_FSV_delivery_load_units_that_are_claasified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm UKHC delivery load units that are claasified as large size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_UKHC_delivery_load_units_that_are_claasified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm Per Una delivery load units that are claasified as large size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_Per_Una_delivery_load_units_that_are_claasified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_Direct_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_FSV_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm UKHC delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_UKHC_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm Per Una delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC processing part$")
	public void to_confirm_Per_Una_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_processing_part()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units that are classified as large size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_Direct_delivery_load_units_that_are_classified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units that are claasified as large size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_FSV_delivery_load_units_that_are_claasified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm UKHC delivery load units that are claasified as large size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_UKHC_delivery_load_units_that_are_claasified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm Per Una delivery load units that are claasified as large size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_Per_Una_delivery_load_units_that_are_claasified_as_large_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_Direct_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_FSV_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm UKHC delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_UKHC_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm Per Una delivery load units that are claasified as small size are sent to a repack workstation for repacking into an ISC validation part$")
	public void to_confirm_Per_Una_delivery_load_units_that_are_claasified_as_small_size_are_sent_to_a_repack_workstation_for_repacking_into_an_ISC_validation_part()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS$")
	public void to_confirm_Direct_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS$")
	public void to_confirm_FSV_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS()
			throws Throwable {

	}

	@Given("^To confirm UKHC delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS$")
	public void to_confirm_UKHC_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS()
			throws Throwable {

	}

	@Given("^To confirm peruna delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS$")
	public void to_confirm_peruna_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units containing sku flagged for CNI are sent to the QC lane flagged for CNI$")
	public void to_confirm_Direct_delivery_load_units_containing_sku_flagged_for_CNI_are_sent_to_the_QC_lane_flagged_for_CNI()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units containing sku flagged for CNI are sent to the QC lane flagged for CNI$")
	public void to_confirm_FSV_delivery_load_units_containing_sku_flagged_for_CNI_are_sent_to_the_QC_lane_flagged_for_CNI()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units containing sku flagged for Firewall are sent to the QC lane flagged for Firewall$")
	public void to_confirm_Direct_delivery_load_units_containing_sku_flagged_for_Firewall_are_sent_to_the_QC_lane_flagged_for_Firewall()
			throws Throwable {

	}

	@Given("^To confirm fsv delivery load units containing sku flagged for Firewall are sent to the QC lane flagged for Firewall$")
	public void to_confirm_fsv_delivery_load_units_containing_sku_flagged_for_Firewall_are_sent_to_the_QC_lane_flagged_for_Firewall()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS validation part$")
	public void to_confirm_Direct_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS_validation_part()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS validation part$")
	public void to_confirm_FSV_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS_validation_part()
			throws Throwable {

	}

	@Given("^To confirm UKHC delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS validation part$")
	public void to_confirm_UKHC_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS_validation_part()
			throws Throwable {

	}

	@Given("^To confirm peruna delivery load units containing sku flagged for FTS are sent to the QC lane flagged for FTS validation part$")
	public void to_confirm_peruna_delivery_load_units_containing_sku_flagged_for_FTS_are_sent_to_the_QC_lane_flagged_for_FTS_validation_part()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units containing sku flagged for CNI are sent to the QC lane flagged for CNI validation part$")
	public void to_confirm_Direct_delivery_load_units_containing_sku_flagged_for_CNI_are_sent_to_the_QC_lane_flagged_for_CNI_validation_part()
			throws Throwable {

	}

	@Given("^To confirm FSV delivery load units containing sku flagged for CNI are sent to the QC lane flagged for CNI validation part$")
	public void to_confirm_FSV_delivery_load_units_containing_sku_flagged_for_CNI_are_sent_to_the_QC_lane_flagged_for_CNI_validation_part()
			throws Throwable {

	}

	@Given("^To confirm Direct delivery load units containing sku flagged for Firewall are sent to the QC lane flagged for Firewall validation part$")
	public void to_confirm_Direct_delivery_load_units_containing_sku_flagged_for_Firewall_are_sent_to_the_QC_lane_flagged_for_Firewall_validation_part()
			throws Throwable {

	}

	@Given("^To confirm fsv delivery load units containing sku flagged for Firewall are sent to the QC lane flagged for Firewall validation part$")
	public void to_confirm_fsv_delivery_load_units_containing_sku_flagged_for_Firewall_are_sent_to_the_QC_lane_flagged_for_Firewall_validation_part()
			throws Throwable {

	}

	@Given("^To be confirmed Direct delivery load units can be manually received in WMS$")
	public void to_be_confirmed_Direct_delivery_load_units_can_be_manually_received_in_WMS() throws Throwable {

	}

	@Given("^To be confirmed ukhc delivery load units can be manually received in WMS$")
	public void to_be_confirmed_ukhc_delivery_load_units_can_be_manually_received_in_WMS() throws Throwable {

	}

	@Given("^To be confirmed fsv delivery load units can be manually received in WMS$")
	public void to_be_confirmed_fsv_delivery_load_units_can_be_manually_received_in_WMS() throws Throwable {

	}

	@Given("^To be confirmed peruna delivery load units can be manually received in WMS$")
	public void to_be_confirmed_peruna_delivery_load_units_can_be_manually_received_in_WMS() throws Throwable {

	}

	@Given("^To be confirmed direct deliveries load units can be received sent to palletisation and stored in the HBW$")
	public void to_be_confirmed_direct_deliveries_load_units_can_be_received_sent_to_palletisation_and_stored_in_the_HBW()
			throws Throwable {

	}

	@Given("^To be confirmed ukhc deliveries load units can be received sent to palletisation and stored in the HBW$")
	public void to_be_confirmed_ukhc_deliveries_load_units_can_be_received_sent_to_palletisation_and_stored_in_the_HBW()
			throws Throwable {

	}

	@Given("^To be confirmed direct deliveries load units can be received sent to palletisation and stored in the HBW validation part$")
	public void to_be_confirmed_direct_deliveries_load_units_can_be_received_sent_to_palletisation_and_stored_in_the_HBW_validation_part()
			throws Throwable {

	}

	@Given("^To be confirmed ukhc deliveries load units can be received sent to palletisation and stored in the HBW validation part$")
	public void to_be_confirmed_ukhc_deliveries_load_units_can_be_received_sent_to_palletisation_and_stored_in_the_HBW_validation_part()
			throws Throwable {

	}

	@Given("^To be confirmed Direct delivery load units containing GOH SKUs are sent to palletisation$")
	public void to_be_confirmed_Direct_delivery_load_units_containing_GOH_SKUs_are_sent_to_palletisation()
			throws Throwable {

	}

	@Given("^To be confirmed Direct delivery load units containing Large SKUs are sent to palletisation$")
	public void to_be_confirmed_Direct_delivery_load_units_containing_Large_SKUs_are_sent_to_palletisation()
			throws Throwable {

	}

	@Given("^To be confirmed Direct delivery load units containing GOH SKUs are sent to palletisation validation part$")
	public void to_be_confirmed_Direct_delivery_load_units_containing_GOH_SKUs_are_sent_to_palletisation_validation_part()
			throws Throwable {

	}

	@Given("^To be confirmed Direct delivery load units containing Large SKUs are sent to palletisation validation part$")
	public void to_be_confirmed_Direct_delivery_load_units_containing_Large_SKUs_are_sent_to_palletisation_validation_part()
			throws Throwable {

	}

	@Given("^To be confirmed Direct deliveries load units can be received, sent to repack for PPP and then stored in the BCS$")
	public void to_be_confirmed_Direct_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS()
			throws Throwable {

	}

	@Given("^To be confirmed fsv deliveries load units can be received, sent to repack for PPP and then stored in the BCS$")
	public void to_be_confirmed_fsv_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS()
			throws Throwable {

	}

	@Given("^To be confirmed ukhc deliveries load units can be received, sent to repack for PPP and then stored in the BCS$")
	public void to_be_confirmed_ukhc_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS()
			throws Throwable {

	}

	@Given("^To be confirmed peruna deliveries load units can be received, sent to repack for PPP and then stored in the BCS$")
	public void to_be_confirmed_peruna_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS()
			throws Throwable {

	}

	@Given("^To be confirmed Direct deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation$")
	public void to_be_confirmed_Direct_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS_validation()
			throws Throwable {

	}

	@Given("^To be confirmed fsv deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation$")
	public void to_be_confirmed_fsv_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS_validation()
			throws Throwable {

	}

	@Given("^To be confirmed ukhc deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation$")
	public void to_be_confirmed_ukhc_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS_validation()
			throws Throwable {

	}

	@Given("^To be confirmed peruna deliveries load units can be received, sent to repack for PPP and then stored in the BCS validation$")
	public void to_be_confirmed_peruna_deliveries_load_units_can_be_received_sent_to_repack_for_PPP_and_then_stored_in_the_BCS_validation()
			throws Throwable {

	}

	@Given("^To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing a normal SKU at PPP$")
	public void to_be_confirmed_the_replenishment_of_non_pick_ready_stock_from_BCS_to_SCS_and_will_cover_the_processing_of_a_load_unit_containing_a_normal_SKU_at_PPP()
			throws Throwable {

	}

	@Given("^To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing an ecom only, fragile SKU at PPP$")
	public void to_be_confirmed_the_replenishment_of_non_pick_ready_stock_from_BCS_to_SCS_and_will_cover_the_processing_of_a_load_unit_containing_an_ecom_only_fragile_SKU_at_PPP()
			throws Throwable {

	}

	@Given("^To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing over (\\d+)\\.(\\d+)kg of stock PPP and ensuring the user is prompted to split the load unit into multiple ISCs$")
	public void to_be_confirmed_the_replenishment_of_non_pick_ready_stock_from_BCS_to_SCS_and_will_cover_the_processing_of_a_load_unit_containing_over_kg_of_stock_PPP_and_ensuring_the_user_is_prompted_to_split_the_load_unit_into_multiple_ISCs()
			throws Throwable {

	}

	@Given("^To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing a normal SKU at PPP validation part$")
	public void to_be_confirmed_the_replenishment_of_non_pick_ready_stock_from_BCS_to_SCS_and_will_cover_the_processing_of_a_load_unit_containing_a_normal_SKU_at_PPP_validation_part()
			throws Throwable {

	}

	@Given("^To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing an ecom only, fragile SKU at PPP validation part$")
	public void to_be_confirmed_the_replenishment_of_non_pick_ready_stock_from_BCS_to_SCS_and_will_cover_the_processing_of_a_load_unit_containing_an_ecom_only_fragile_SKU_at_PPP_validation_part()
			throws Throwable {

	}

	@Given("^To be confirmed the replenishment of non pick ready stock from BCS to SCS and will cover the processing of a load unit containing over (\\d+)\\.(\\d+)kg of stock PPP and ensuring the user is prompted to split the load unit into multiple ISCs validation part$")
	public void to_be_confirmed_the_replenishment_of_non_pick_ready_stock_from_BCS_to_SCS_and_will_cover_the_processing_of_a_load_unit_containing_over_kg_of_stock_PPP_and_ensuring_the_user_is_prompted_to_split_the_load_unit_into_multiple_ISCs_validation_part()
			throws Throwable {

	}

}