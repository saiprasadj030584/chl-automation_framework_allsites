/*
 * Copyright (C) 2017 P9134107
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jda.wms.dataObject;

import java.util.ArrayList;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class Order {

	private String client_id;
	private String order_id;
	private String order_type;
	private String work_order_type;
	private String status;
	private String move_task_status;
	private String priority;
	private String repack;
	private String repack_loc_id;
	private String ship_dock;
	private String work_group;
	private String consignment;
	private String delivery_point;
	private String load_sequence;
	private String from_site_id;
	private String to_site_id;
	private String owner_id;
	private String customer_id;
	private String order_date;
	private String ship_by_date;
	private String deliver_by_date;
	private String shipped_date;
	private String delivered_dstamp;
	private String signatory;
	private String purchase_order;
	private String carrier_id;
	private String dispatch_method;
	private String service_level;
	private String fastest_carrier;
	private String cheapest_carrier;
	private String inv_address_id;
	private String inv_contact;
	private String inv_contact_phone;
	private String inv_contact_mobile;
	private String inv_contact_fax;
	private String inv_contact_email;
	private String inv_name;
	private String inv_address1;
	private String inv_address2;
	private String inv_town;
	private String inv_county;
	private String inv_postcode;
	private String inv_country;
	private String instructions;
	private String order_volume;
	private String order_weight;
	private String contact;
	private String contact_phone;
	private String contact_mobile;
	private String contact_fax;
	private String contact_email;
	private String name;
	private String address1;
	private String address2;
	private String town;
	private String county;
	private String postcode;
	private String country;
	private String route_planned;
	private String uploaded;
	private String uploaded_ws2pc_id;
	private String uploaded_dstamp;
	private String uploaded_filename;
	private String uploaded_vview;
	private String uploaded_header_key;
	private String psft_dmnd_srce;
	private String psft_order_id;
	private String site_replen;
	private String order_id_link;
	private String allocation_run;
	private String no_shipment_email;
	private String cid_number;
	private String sid_number;
	private String location_number;
	private String freight_charges;
	private String disallow_merge_rules;
	private String order_source;
	private String export;
	private String num_lines;
	private String highest_label;
	private String created_by;
	private String creation_date;
	private String last_updated_by;
	private String last_update_date;
	private String user_def_type_1;
	private String user_def_type_2;
	private String user_def_type_3;
	private String user_def_type_4;
	private String user_def_type_5;
	private String user_def_type_6;
	private String user_def_type_7;
	private String user_def_type_8;
	private String user_def_chk_1;
	private String user_def_chk_2;
	private String user_def_chk_3;
	private String user_def_chk_4;
	private String user_def_date_1;
	private String user_def_date_2;
	private String user_def_date_3;
	private String user_def_date_4;
	private String user_def_num_1;
	private String user_def_num_2;
	private String user_def_num_3;
	private String user_def_num_4;
	private String user_def_note_1;
	private String user_def_note_2;
	private String ce_reason_code;
	private String ce_reason_notes;
	private String ce_order_type;
	private String ce_customs_customer;
	private String ce_excise_customer;
	private String ce_instructions;
	private String route_id;
	private String cross_dock_to_site;
	private String web_service_alloc_immed;
	private String web_service_alloc_clean;
	private String disallow_short_ship;
	private String uploaded_customs;
	private String uploaded_labor;
	private String hub_address_id;
	private String hub_contact;
	private String hub_contact_phone;
	private String hub_contact_mobile;
	private String hub_contact_fax;
	private String hub_contact_email;
	private String hub_name;
	private String hub_address1;
	private String hub_address2;
	private String hub_town;
	private String hub_county;
	private String hub_postcode;
	private String hub_country;
	private String status_reason_code;
	private String stage_route_id;
	private String single_order_sortation;
	private String archived;
	private String closure_date;
	private String order_closed;
	private String total_repack_containers;
	private String force_single_carrier;
	private String hub_carrier_id;
	private String hub_service_level;
	private String order_grouping_id;
	private String ship_by_date_err;
	private String del_by_date_err;
	private String ship_by_date_err_msg;
	private String del_by_date_err_msg;
	private String order_value;
	private String expected_volume;
	private String expected_weight;
	private String expected_value;
	private String tod;
	private String tod_place;
	private String language;
	private String seller_name;
	private String seller_phone;
	private String documentation_text_1;
	private String documentation_text_2;
	private String documentation_text_3;
	private String cod;
	private String cod_value;
	private String cod_currency;
	private String cod_type;
	private String vat_number;
	private String inv_vat_number;
	private String hub_vat_number;
	private String print_invoice;
	private String inv_reference;
	private String inv_dstamp;
	private String inv_currency;
	private String letter_of_credit;
	private String payment_terms;
	private String subtotal_1;
	private String subtotal_2;
	private String subtotal_3;
	private String subtotal_4;
	private String freight_cost;
	private String freight_terms;
	private String insurance_cost;
	private String misc_charges;
	private String discount;
	private String other_fee;
	private String inv_total_1;
	private String inv_total_2;
	private String inv_total_3;
	private String inv_total_4;
	private String tax_rate_1;
	private String tax_basis_1;
	private String tax_amount_1;
	private String tax_rate_2;
	private String tax_basis_2;
	private int numberOfLines;
	private ArrayList<OrderLine> lines;

	public Order(String order_id, String order_type, String customer_id, String order_date, String ship_by_date,
			String deliver_by_date, String user_def_date_1) {
		this.order_id = order_id;
		this.order_type = order_type;
		this.customer_id = customer_id;
		this.order_date = order_date;
		this.ship_by_date = ship_by_date;
		this.deliver_by_date = deliver_by_date;
		this.user_def_date_1 = user_def_date_1;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getWork_order_type() {
		return work_order_type;
	}

	public void setWork_order_type(String work_order_type) {
		this.work_order_type = work_order_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMove_task_status() {
		return move_task_status;
	}

	public void setMove_task_status(String move_task_status) {
		this.move_task_status = move_task_status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRepack() {
		return repack;
	}

	public void setRepack(String repack) {
		this.repack = repack;
	}

	public String getRepack_loc_id() {
		return repack_loc_id;
	}

	public void setRepack_loc_id(String repack_loc_id) {
		this.repack_loc_id = repack_loc_id;
	}

	public String getShip_dock() {
		return ship_dock;
	}

	public void setShip_dock(String ship_dock) {
		this.ship_dock = ship_dock;
	}

	public String getWork_group() {
		return work_group;
	}

	public void setWork_group(String work_group) {
		this.work_group = work_group;
	}

	public String getConsignment() {
		return consignment;
	}

	public void setConsignment(String consignment) {
		this.consignment = consignment;
	}

	public String getDelivery_point() {
		return delivery_point;
	}

	public void setDelivery_point(String delivery_point) {
		this.delivery_point = delivery_point;
	}

	public String getLoad_sequence() {
		return load_sequence;
	}

	public void setLoad_sequence(String load_sequence) {
		this.load_sequence = load_sequence;
	}

	public String getFrom_site_id() {
		return from_site_id;
	}

	public void setFrom_site_id(String from_site_id) {
		this.from_site_id = from_site_id;
	}

	public String getTo_site_id() {
		return to_site_id;
	}

	public void setTo_site_id(String to_site_id) {
		this.to_site_id = to_site_id;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getShip_by_date() {
		return ship_by_date;
	}

	public void setShip_by_date(String ship_by_date) {
		this.ship_by_date = ship_by_date;
	}

	public String getDeliver_by_date() {
		return deliver_by_date;
	}

	public void setDeliver_by_date(String deliver_by_date) {
		this.deliver_by_date = deliver_by_date;
	}

	public String getShipped_date() {
		return shipped_date;
	}

	public void setShipped_date(String shipped_date) {
		this.shipped_date = shipped_date;
	}

	public String getDelivered_dstamp() {
		return delivered_dstamp;
	}

	public void setDelivered_dstamp(String delivered_dstamp) {
		this.delivered_dstamp = delivered_dstamp;
	}

	public String getSignatory() {
		return signatory;
	}

	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}

	public String getPurchase_order() {
		return purchase_order;
	}

	public void setPurchase_order(String purchase_order) {
		this.purchase_order = purchase_order;
	}

	public String getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}

	public String getDispatch_method() {
		return dispatch_method;
	}

	public void setDispatch_method(String dispatch_method) {
		this.dispatch_method = dispatch_method;
	}

	public String getService_level() {
		return service_level;
	}

	public void setService_level(String service_level) {
		this.service_level = service_level;
	}

	public String getFastest_carrier() {
		return fastest_carrier;
	}

	public void setFastest_carrier(String fastest_carrier) {
		this.fastest_carrier = fastest_carrier;
	}

	public String getCheapest_carrier() {
		return cheapest_carrier;
	}

	public void setCheapest_carrier(String cheapest_carrier) {
		this.cheapest_carrier = cheapest_carrier;
	}

	public String getInv_address_id() {
		return inv_address_id;
	}

	public void setInv_address_id(String inv_address_id) {
		this.inv_address_id = inv_address_id;
	}

	public String getInv_contact() {
		return inv_contact;
	}

	public void setInv_contact(String inv_contact) {
		this.inv_contact = inv_contact;
	}

	public String getInv_contact_phone() {
		return inv_contact_phone;
	}

	public void setInv_contact_phone(String inv_contact_phone) {
		this.inv_contact_phone = inv_contact_phone;
	}

	public String getInv_contact_mobile() {
		return inv_contact_mobile;
	}

	public void setInv_contact_mobile(String inv_contact_mobile) {
		this.inv_contact_mobile = inv_contact_mobile;
	}

	public String getInv_contact_fax() {
		return inv_contact_fax;
	}

	public void setInv_contact_fax(String inv_contact_fax) {
		this.inv_contact_fax = inv_contact_fax;
	}

	public String getInv_contact_email() {
		return inv_contact_email;
	}

	public void setInv_contact_email(String inv_contact_email) {
		this.inv_contact_email = inv_contact_email;
	}

	public String getInv_name() {
		return inv_name;
	}

	public void setInv_name(String inv_name) {
		this.inv_name = inv_name;
	}

	public String getInv_address1() {
		return inv_address1;
	}

	public void setInv_address1(String inv_address1) {
		this.inv_address1 = inv_address1;
	}

	public String getInv_address2() {
		return inv_address2;
	}

	public void setInv_address2(String inv_address2) {
		this.inv_address2 = inv_address2;
	}

	public String getInv_town() {
		return inv_town;
	}

	public void setInv_town(String inv_town) {
		this.inv_town = inv_town;
	}

	public String getInv_county() {
		return inv_county;
	}

	public void setInv_county(String inv_county) {
		this.inv_county = inv_county;
	}

	public String getInv_postcode() {
		return inv_postcode;
	}

	public void setInv_postcode(String inv_postcode) {
		this.inv_postcode = inv_postcode;
	}

	public String getInv_country() {
		return inv_country;
	}

	public void setInv_country(String inv_country) {
		this.inv_country = inv_country;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getOrder_volume() {
		return order_volume;
	}

	public void setOrder_volume(String order_volume) {
		this.order_volume = order_volume;
	}

	public String getOrder_weight() {
		return order_weight;
	}

	public void setOrder_weight(String order_weight) {
		this.order_weight = order_weight;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getContact_mobile() {
		return contact_mobile;
	}

	public void setContact_mobile(String contact_mobile) {
		this.contact_mobile = contact_mobile;
	}

	public String getContact_fax() {
		return contact_fax;
	}

	public void setContact_fax(String contact_fax) {
		this.contact_fax = contact_fax;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRoute_planned() {
		return route_planned;
	}

	public void setRoute_planned(String route_planned) {
		this.route_planned = route_planned;
	}

	public String getUploaded() {
		return uploaded;
	}

	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}

	public String getUploaded_ws2pc_id() {
		return uploaded_ws2pc_id;
	}

	public void setUploaded_ws2pc_id(String uploaded_ws2pc_id) {
		this.uploaded_ws2pc_id = uploaded_ws2pc_id;
	}

	public String getUploaded_dstamp() {
		return uploaded_dstamp;
	}

	public void setUploaded_dstamp(String uploaded_dstamp) {
		this.uploaded_dstamp = uploaded_dstamp;
	}

	public String getUploaded_filename() {
		return uploaded_filename;
	}

	public void setUploaded_filename(String uploaded_filename) {
		this.uploaded_filename = uploaded_filename;
	}

	public String getUploaded_vview() {
		return uploaded_vview;
	}

	public void setUploaded_vview(String uploaded_vview) {
		this.uploaded_vview = uploaded_vview;
	}

	public String getUploaded_header_key() {
		return uploaded_header_key;
	}

	public void setUploaded_header_key(String uploaded_header_key) {
		this.uploaded_header_key = uploaded_header_key;
	}

	public String getPsft_dmnd_srce() {
		return psft_dmnd_srce;
	}

	public void setPsft_dmnd_srce(String psft_dmnd_srce) {
		this.psft_dmnd_srce = psft_dmnd_srce;
	}

	public String getPsft_order_id() {
		return psft_order_id;
	}

	public void setPsft_order_id(String psft_order_id) {
		this.psft_order_id = psft_order_id;
	}

	public String getSite_replen() {
		return site_replen;
	}

	public void setSite_replen(String site_replen) {
		this.site_replen = site_replen;
	}

	public String getOrder_id_link() {
		return order_id_link;
	}

	public void setOrder_id_link(String order_id_link) {
		this.order_id_link = order_id_link;
	}

	public String getAllocation_run() {
		return allocation_run;
	}

	public void setAllocation_run(String allocation_run) {
		this.allocation_run = allocation_run;
	}

	public String getNo_shipment_email() {
		return no_shipment_email;
	}

	public void setNo_shipment_email(String no_shipment_email) {
		this.no_shipment_email = no_shipment_email;
	}

	public String getCid_number() {
		return cid_number;
	}

	public void setCid_number(String cid_number) {
		this.cid_number = cid_number;
	}

	public String getSid_number() {
		return sid_number;
	}

	public void setSid_number(String sid_number) {
		this.sid_number = sid_number;
	}

	public String getLocation_number() {
		return location_number;
	}

	public void setLocation_number(String location_number) {
		this.location_number = location_number;
	}

	public String getFreight_charges() {
		return freight_charges;
	}

	public void setFreight_charges(String freight_charges) {
		this.freight_charges = freight_charges;
	}

	public String getDisallow_merge_rules() {
		return disallow_merge_rules;
	}

	public void setDisallow_merge_rules(String disallow_merge_rules) {
		this.disallow_merge_rules = disallow_merge_rules;
	}

	public String getOrder_source() {
		return order_source;
	}

	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}

	public String getExport() {
		return export;
	}

	public void setExport(String export) {
		this.export = export;
	}

	public String getNum_lines() {
		return num_lines;
	}

	public void setNum_lines(String num_lines) {
		this.num_lines = num_lines;
	}

	public String getHighest_label() {
		return highest_label;
	}

	public void setHighest_label(String highest_label) {
		this.highest_label = highest_label;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public String getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public String getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}

	public String getUser_def_type_1() {
		return user_def_type_1;
	}

	public void setUser_def_type_1(String user_def_type_1) {
		this.user_def_type_1 = user_def_type_1;
	}

	public String getUser_def_type_2() {
		return user_def_type_2;
	}

	public void setUser_def_type_2(String user_def_type_2) {
		this.user_def_type_2 = user_def_type_2;
	}

	public String getUser_def_type_3() {
		return user_def_type_3;
	}

	public void setUser_def_type_3(String user_def_type_3) {
		this.user_def_type_3 = user_def_type_3;
	}

	public String getUser_def_type_4() {
		return user_def_type_4;
	}

	public void setUser_def_type_4(String user_def_type_4) {
		this.user_def_type_4 = user_def_type_4;
	}

	public String getUser_def_type_5() {
		return user_def_type_5;
	}

	public void setUser_def_type_5(String user_def_type_5) {
		this.user_def_type_5 = user_def_type_5;
	}

	public String getUser_def_type_6() {
		return user_def_type_6;
	}

	public void setUser_def_type_6(String user_def_type_6) {
		this.user_def_type_6 = user_def_type_6;
	}

	public String getUser_def_type_7() {
		return user_def_type_7;
	}

	public void setUser_def_type_7(String user_def_type_7) {
		this.user_def_type_7 = user_def_type_7;
	}

	public String getUser_def_type_8() {
		return user_def_type_8;
	}

	public void setUser_def_type_8(String user_def_type_8) {
		this.user_def_type_8 = user_def_type_8;
	}

	public String getUser_def_chk_1() {
		return user_def_chk_1;
	}

	public void setUser_def_chk_1(String user_def_chk_1) {
		this.user_def_chk_1 = user_def_chk_1;
	}

	public String getUser_def_chk_2() {
		return user_def_chk_2;
	}

	public void setUser_def_chk_2(String user_def_chk_2) {
		this.user_def_chk_2 = user_def_chk_2;
	}

	public String getUser_def_chk_3() {
		return user_def_chk_3;
	}

	public void setUser_def_chk_3(String user_def_chk_3) {
		this.user_def_chk_3 = user_def_chk_3;
	}

	public String getUser_def_chk_4() {
		return user_def_chk_4;
	}

	public void setUser_def_chk_4(String user_def_chk_4) {
		this.user_def_chk_4 = user_def_chk_4;
	}

	public String getUser_def_date_1() {
		return user_def_date_1;
	}

	public void setUser_def_date_1(String user_def_date_1) {
		this.user_def_date_1 = user_def_date_1;
	}

	public String getUser_def_date_2() {
		return user_def_date_2;
	}

	public void setUser_def_date_2(String user_def_date_2) {
		this.user_def_date_2 = user_def_date_2;
	}

	public String getUser_def_date_3() {
		return user_def_date_3;
	}

	public void setUser_def_date_3(String user_def_date_3) {
		this.user_def_date_3 = user_def_date_3;
	}

	public String getUser_def_date_4() {
		return user_def_date_4;
	}

	public void setUser_def_date_4(String user_def_date_4) {
		this.user_def_date_4 = user_def_date_4;
	}

	public String getUser_def_num_1() {
		return user_def_num_1;
	}

	public void setUser_def_num_1(String user_def_num_1) {
		this.user_def_num_1 = user_def_num_1;
	}

	public String getUser_def_num_2() {
		return user_def_num_2;
	}

	public void setUser_def_num_2(String user_def_num_2) {
		this.user_def_num_2 = user_def_num_2;
	}

	public String getUser_def_num_3() {
		return user_def_num_3;
	}

	public void setUser_def_num_3(String user_def_num_3) {
		this.user_def_num_3 = user_def_num_3;
	}

	public String getUser_def_num_4() {
		return user_def_num_4;
	}

	public void setUser_def_num_4(String user_def_num_4) {
		this.user_def_num_4 = user_def_num_4;
	}

	public String getUser_def_note_1() {
		return user_def_note_1;
	}

	public void setUser_def_note_1(String user_def_note_1) {
		this.user_def_note_1 = user_def_note_1;
	}

	public String getUser_def_note_2() {
		return user_def_note_2;
	}

	public void setUser_def_note_2(String user_def_note_2) {
		this.user_def_note_2 = user_def_note_2;
	}

	public String getCe_reason_code() {
		return ce_reason_code;
	}

	public void setCe_reason_code(String ce_reason_code) {
		this.ce_reason_code = ce_reason_code;
	}

	public String getCe_reason_notes() {
		return ce_reason_notes;
	}

	public void setCe_reason_notes(String ce_reason_notes) {
		this.ce_reason_notes = ce_reason_notes;
	}

	public String getCe_order_type() {
		return ce_order_type;
	}

	public void setCe_order_type(String ce_order_type) {
		this.ce_order_type = ce_order_type;
	}

	public String getCe_customs_customer() {
		return ce_customs_customer;
	}

	public void setCe_customs_customer(String ce_customs_customer) {
		this.ce_customs_customer = ce_customs_customer;
	}

	public String getCe_excise_customer() {
		return ce_excise_customer;
	}

	public void setCe_excise_customer(String ce_excise_customer) {
		this.ce_excise_customer = ce_excise_customer;
	}

	public String getCe_instructions() {
		return ce_instructions;
	}

	public void setCe_instructions(String ce_instructions) {
		this.ce_instructions = ce_instructions;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getCross_dock_to_site() {
		return cross_dock_to_site;
	}

	public void setCross_dock_to_site(String cross_dock_to_site) {
		this.cross_dock_to_site = cross_dock_to_site;
	}

	public String getWeb_service_alloc_immed() {
		return web_service_alloc_immed;
	}

	public void setWeb_service_alloc_immed(String web_service_alloc_immed) {
		this.web_service_alloc_immed = web_service_alloc_immed;
	}

	public String getWeb_service_alloc_clean() {
		return web_service_alloc_clean;
	}

	public void setWeb_service_alloc_clean(String web_service_alloc_clean) {
		this.web_service_alloc_clean = web_service_alloc_clean;
	}

	public String getDisallow_short_ship() {
		return disallow_short_ship;
	}

	public void setDisallow_short_ship(String disallow_short_ship) {
		this.disallow_short_ship = disallow_short_ship;
	}

	public String getUploaded_customs() {
		return uploaded_customs;
	}

	public void setUploaded_customs(String uploaded_customs) {
		this.uploaded_customs = uploaded_customs;
	}

	public String getUploaded_labor() {
		return uploaded_labor;
	}

	public void setUploaded_labor(String uploaded_labor) {
		this.uploaded_labor = uploaded_labor;
	}

	public String getHub_address_id() {
		return hub_address_id;
	}

	public void setHub_address_id(String hub_address_id) {
		this.hub_address_id = hub_address_id;
	}

	public String getHub_contact() {
		return hub_contact;
	}

	public void setHub_contact(String hub_contact) {
		this.hub_contact = hub_contact;
	}

	public String getHub_contact_phone() {
		return hub_contact_phone;
	}

	public void setHub_contact_phone(String hub_contact_phone) {
		this.hub_contact_phone = hub_contact_phone;
	}

	public String getHub_contact_mobile() {
		return hub_contact_mobile;
	}

	public void setHub_contact_mobile(String hub_contact_mobile) {
		this.hub_contact_mobile = hub_contact_mobile;
	}

	public String getHub_contact_fax() {
		return hub_contact_fax;
	}

	public void setHub_contact_fax(String hub_contact_fax) {
		this.hub_contact_fax = hub_contact_fax;
	}

	public String getHub_contact_email() {
		return hub_contact_email;
	}

	public void setHub_contact_email(String hub_contact_email) {
		this.hub_contact_email = hub_contact_email;
	}

	public String getHub_name() {
		return hub_name;
	}

	public void setHub_name(String hub_name) {
		this.hub_name = hub_name;
	}

	public String getHub_address1() {
		return hub_address1;
	}

	public void setHub_address1(String hub_address1) {
		this.hub_address1 = hub_address1;
	}

	public String getHub_address2() {
		return hub_address2;
	}

	public void setHub_address2(String hub_address2) {
		this.hub_address2 = hub_address2;
	}

	public String getHub_town() {
		return hub_town;
	}

	public void setHub_town(String hub_town) {
		this.hub_town = hub_town;
	}

	public String getHub_county() {
		return hub_county;
	}

	public void setHub_county(String hub_county) {
		this.hub_county = hub_county;
	}

	public String getHub_postcode() {
		return hub_postcode;
	}

	public void setHub_postcode(String hub_postcode) {
		this.hub_postcode = hub_postcode;
	}

	public String getHub_country() {
		return hub_country;
	}

	public void setHub_country(String hub_country) {
		this.hub_country = hub_country;
	}

	public String getStatus_reason_code() {
		return status_reason_code;
	}

	public void setStatus_reason_code(String status_reason_code) {
		this.status_reason_code = status_reason_code;
	}

	public String getStage_route_id() {
		return stage_route_id;
	}

	public void setStage_route_id(String stage_route_id) {
		this.stage_route_id = stage_route_id;
	}

	public String getSingle_order_sortation() {
		return single_order_sortation;
	}

	public void setSingle_order_sortation(String single_order_sortation) {
		this.single_order_sortation = single_order_sortation;
	}

	public String getArchived() {
		return archived;
	}

	public void setArchived(String archived) {
		this.archived = archived;
	}

	public String getClosure_date() {
		return closure_date;
	}

	public void setClosure_date(String closure_date) {
		this.closure_date = closure_date;
	}

	public String getOrder_closed() {
		return order_closed;
	}

	public void setOrder_closed(String order_closed) {
		this.order_closed = order_closed;
	}

	public String getTotal_repack_containers() {
		return total_repack_containers;
	}

	public void setTotal_repack_containers(String total_repack_containers) {
		this.total_repack_containers = total_repack_containers;
	}

	public String getForce_single_carrier() {
		return force_single_carrier;
	}

	public void setForce_single_carrier(String force_single_carrier) {
		this.force_single_carrier = force_single_carrier;
	}

	public String getHub_carrier_id() {
		return hub_carrier_id;
	}

	public void setHub_carrier_id(String hub_carrier_id) {
		this.hub_carrier_id = hub_carrier_id;
	}

	public String getHub_service_level() {
		return hub_service_level;
	}

	public void setHub_service_level(String hub_service_level) {
		this.hub_service_level = hub_service_level;
	}

	public String getOrder_grouping_id() {
		return order_grouping_id;
	}

	public void setOrder_grouping_id(String order_grouping_id) {
		this.order_grouping_id = order_grouping_id;
	}

	public String getShip_by_date_err() {
		return ship_by_date_err;
	}

	public void setShip_by_date_err(String ship_by_date_err) {
		this.ship_by_date_err = ship_by_date_err;
	}

	public String getDel_by_date_err() {
		return del_by_date_err;
	}

	public void setDel_by_date_err(String del_by_date_err) {
		this.del_by_date_err = del_by_date_err;
	}

	public String getShip_by_date_err_msg() {
		return ship_by_date_err_msg;
	}

	public void setShip_by_date_err_msg(String ship_by_date_err_msg) {
		this.ship_by_date_err_msg = ship_by_date_err_msg;
	}

	public String getDel_by_date_err_msg() {
		return del_by_date_err_msg;
	}

	public void setDel_by_date_err_msg(String del_by_date_err_msg) {
		this.del_by_date_err_msg = del_by_date_err_msg;
	}

	public String getOrder_value() {
		return order_value;
	}

	public void setOrder_value(String order_value) {
		this.order_value = order_value;
	}

	public String getExpected_volume() {
		return expected_volume;
	}

	public void setExpected_volume(String expected_volume) {
		this.expected_volume = expected_volume;
	}

	public String getExpected_weight() {
		return expected_weight;
	}

	public void setExpected_weight(String expected_weight) {
		this.expected_weight = expected_weight;
	}

	public String getExpected_value() {
		return expected_value;
	}

	public void setExpected_value(String expected_value) {
		this.expected_value = expected_value;
	}

	public String getTod() {
		return tod;
	}

	public void setTod(String tod) {
		this.tod = tod;
	}

	public String getTod_place() {
		return tod_place;
	}

	public void setTod_place(String tod_place) {
		this.tod_place = tod_place;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_phone() {
		return seller_phone;
	}

	public void setSeller_phone(String seller_phone) {
		this.seller_phone = seller_phone;
	}

	public String getDocumentation_text_1() {
		return documentation_text_1;
	}

	public void setDocumentation_text_1(String documentation_text_1) {
		this.documentation_text_1 = documentation_text_1;
	}

	public String getDocumentation_text_2() {
		return documentation_text_2;
	}

	public void setDocumentation_text_2(String documentation_text_2) {
		this.documentation_text_2 = documentation_text_2;
	}

	public String getDocumentation_text_3() {
		return documentation_text_3;
	}

	public void setDocumentation_text_3(String documentation_text_3) {
		this.documentation_text_3 = documentation_text_3;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getCod_value() {
		return cod_value;
	}

	public void setCod_value(String cod_value) {
		this.cod_value = cod_value;
	}

	public String getCod_currency() {
		return cod_currency;
	}

	public void setCod_currency(String cod_currency) {
		this.cod_currency = cod_currency;
	}

	public String getCod_type() {
		return cod_type;
	}

	public void setCod_type(String cod_type) {
		this.cod_type = cod_type;
	}

	public String getVat_number() {
		return vat_number;
	}

	public void setVat_number(String vat_number) {
		this.vat_number = vat_number;
	}

	public String getInv_vat_number() {
		return inv_vat_number;
	}

	public void setInv_vat_number(String inv_vat_number) {
		this.inv_vat_number = inv_vat_number;
	}

	public String getHub_vat_number() {
		return hub_vat_number;
	}

	public void setHub_vat_number(String hub_vat_number) {
		this.hub_vat_number = hub_vat_number;
	}

	public String getPrint_invoice() {
		return print_invoice;
	}

	public void setPrint_invoice(String print_invoice) {
		this.print_invoice = print_invoice;
	}

	public String getInv_reference() {
		return inv_reference;
	}

	public void setInv_reference(String inv_reference) {
		this.inv_reference = inv_reference;
	}

	public String getInv_dstamp() {
		return inv_dstamp;
	}

	public void setInv_dstamp(String inv_dstamp) {
		this.inv_dstamp = inv_dstamp;
	}

	public String getInv_currency() {
		return inv_currency;
	}

	public void setInv_currency(String inv_currency) {
		this.inv_currency = inv_currency;
	}

	public String getLetter_of_credit() {
		return letter_of_credit;
	}

	public void setLetter_of_credit(String letter_of_credit) {
		this.letter_of_credit = letter_of_credit;
	}

	public String getPayment_terms() {
		return payment_terms;
	}

	public void setPayment_terms(String payment_terms) {
		this.payment_terms = payment_terms;
	}

	public String getSubtotal_1() {
		return subtotal_1;
	}

	public void setSubtotal_1(String subtotal_1) {
		this.subtotal_1 = subtotal_1;
	}

	public String getSubtotal_2() {
		return subtotal_2;
	}

	public void setSubtotal_2(String subtotal_2) {
		this.subtotal_2 = subtotal_2;
	}

	public String getSubtotal_3() {
		return subtotal_3;
	}

	public void setSubtotal_3(String subtotal_3) {
		this.subtotal_3 = subtotal_3;
	}

	public String getSubtotal_4() {
		return subtotal_4;
	}

	public void setSubtotal_4(String subtotal_4) {
		this.subtotal_4 = subtotal_4;
	}

	public String getFreight_cost() {
		return freight_cost;
	}

	public void setFreight_cost(String freight_cost) {
		this.freight_cost = freight_cost;
	}

	public String getFreight_terms() {
		return freight_terms;
	}

	public void setFreight_terms(String freight_terms) {
		this.freight_terms = freight_terms;
	}

	public String getInsurance_cost() {
		return insurance_cost;
	}

	public void setInsurance_cost(String insurance_cost) {
		this.insurance_cost = insurance_cost;
	}

	public String getMisc_charges() {
		return misc_charges;
	}

	public void setMisc_charges(String misc_charges) {
		this.misc_charges = misc_charges;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getOther_fee() {
		return other_fee;
	}

	public void setOther_fee(String other_fee) {
		this.other_fee = other_fee;
	}

	public String getInv_total_1() {
		return inv_total_1;
	}

	public void setInv_total_1(String inv_total_1) {
		this.inv_total_1 = inv_total_1;
	}

	public String getInv_total_2() {
		return inv_total_2;
	}

	public void setInv_total_2(String inv_total_2) {
		this.inv_total_2 = inv_total_2;
	}

	public String getInv_total_3() {
		return inv_total_3;
	}

	public void setInv_total_3(String inv_total_3) {
		this.inv_total_3 = inv_total_3;
	}

	public String getInv_total_4() {
		return inv_total_4;
	}

	public void setInv_total_4(String inv_total_4) {
		this.inv_total_4 = inv_total_4;
	}

	public String getTax_rate_1() {
		return tax_rate_1;
	}

	public void setTax_rate_1(String tax_rate_1) {
		this.tax_rate_1 = tax_rate_1;
	}

	public String getTax_basis_1() {
		return tax_basis_1;
	}

	public void setTax_basis_1(String tax_basis_1) {
		this.tax_basis_1 = tax_basis_1;
	}

	public String getTax_amount_1() {
		return tax_amount_1;
	}

	public void setTax_amount_1(String tax_amount_1) {
		this.tax_amount_1 = tax_amount_1;
	}

	public String getTax_rate_2() {
		return tax_rate_2;
	}

	public void setTax_rate_2(String tax_rate_2) {
		this.tax_rate_2 = tax_rate_2;
	}

	public String getTax_basis_2() {
		return tax_basis_2;
	}

	public void setTax_basis_2(String tax_basis_2) {
		this.tax_basis_2 = tax_basis_2;
	}

}
