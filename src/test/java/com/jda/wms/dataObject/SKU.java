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

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class SKU {

	private String client_id;
	private String sku_id;
	private String ean;
	private String upc;
	private String description;
	private String product_group;
	private String each_height;
	private String each_weight;
	private String each_volume;
	private String each_value;
	private String each_quantity;
	private String qc_status;
	private String shelf_life;
	private String qc_frequency;
	private String qc_rec_count;
	private String split_lowest;
	private String condition_reqd;
	private String expiry_reqd;
	private String origin_reqd;
	private String serial_at_pack;
	private String serial_at_pick;
	private String serial_at_receipt;
	private String serial_range;
	private String serial_format;
	private String serial_valid_merge;
	private String serial_no_reuse;
	private String serial_per_each;
	private String pick_sequence;
	private String pick_count_qty;
	private String count_frequency;
	private String count_dstamp;
	private String count_list_id;
	private String oap_wip_enabled;
	private String kit_sku;
	private String kit_split;
	private String kit_trigger_qty;
	private String kit_qty_due;
	private String kitting_loc_id;
	private String allocation_group;
	private String putaway_group;
	private String abc_disable;
	private String handling_class;
	private String obsolete_product;
	private String new_product;
	private String disallow_upload;
	private String disallow_cross_dock;
	private String manuf_dstamp_reqd;
	private String manuf_dstamp_dflt;
	private String min_shelf_life;
	private String colour;
	private String sku_size;
	private String ship_shelf_life;
	private String nmfc_number;
	private String incub_rule;
	private String incub_hours;
	private String each_width;
	private String each_depth;
	private String reorder_trigger_qty;
	private String low_trigger_qty;
	private String disallow_merge_rules;
	private String pack_despatch_repack;
	private String spec_id;
	private String beam_units;
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
	private String created_by;
	private String creation_date;
	private String last_updated_by;
	private String last_update_date;
	private String ce_warehouse_type;
	private String ce_customs_excise;
	private String ce_standard_cost;
	private String ce_standard_currency;
	private String count_list_id_1;
	private String disallow_clustering;
	private String max_stack;
	private String stack_description;
	private String stack_limitation;
	private String ce_duty_stamp;
	private String capture_weight;
	private String weigh_at_receipt;
	private String upper_weight_tolerance;
	private String lower_weight_tolerance;
	private String serial_at_loading;
	private String serial_at_kitting;
	private String serial_at_unkitting;
	private String allocalg_locking_stn;
	private String putalg_locking_stn;
	private String ce_commodity_code;
	private String ce_coo;
	private String ce_cwc;
	private String ce_vat_code;
	private String ce_product_type;
	private String commodity_code;
	private String commodity_desc;
	private String family_group;
	private String breakpack;
	private String clearable;
	private String stage_route_id;
	private String serial_dynamic_range;
	private String serial_max_range;
	private String manufacture_at_repack;
	private String expiry_at_repack;
	private String udf_at_repack;
	private String repack_by_piece;
	private String packed_height;
	private String packed_width;
	private String packed_depth;
	private String packed_volume;
	private String packed_weight;
	private String hanging_garment;
	private String conveyable;
	private String fragile;
	private String gender;
	private String high_security;
	private String ugly;
	private String collatable;
	private String ecommerce;
	private String promotion;
	private String foldable;
	private String style;
	private String business_unit_code;
	private String two_man_lift;
	private String awkward;
	private String decatalogued;
	private String stock_check_rule_id;
	private String unkitting_inherit;
	private String serial_at_stock_check;
	private String serial_at_stock_adjust;
	private String kit_ship_components;
	private String unallocatable;
	private String batch_at_kitting;
	private String hazmat;
	private String hazmat_id;
	private String batch_id_generation_alg;
	private String vmi_allow_allocation;
	private String vmi_allow_replenish;
	private String vmi_allow_manual;
	private String vmi_allow_interfaced;
	private String vmi_overstock_qty;
	private String vmi_aging_days;
	private String scrap_on_return;
	private String harmonised_product_code;
	private String tag_merge;
	private String uploaded;
	private String uploaded_ws2pc_id;
	private String uploaded_filename;
	private String uploaded_dstamp;
	private String carrier_pallet_mixing;
	private String special_container_type;
	private String disallow_rdt_over_picking;
	private String no_alloc_back_order;
	private String return_min_shelf_life;
	private String weigh_at_grid_pick;
	private String ce_excise_product_code;
	private String ce_degree_plato;
	private String ce_designation_origin;
	private String ce_density;
	private String ce_brand_name;
	private String ce_alcoholic_strength;
	private String ce_fiscal_mark;
	private String ce_size_of_producer;
	private String ce_commercial_desc;
	private String serial_no_outbound;
	private String full_pallet_at_receipt;
	private String always_full_pallet;
	private String sub_within_product_grp;
	private String serial_check_string;
	private String carrier_product_type;
	private String max_pack_configs;
	private String parcel_packing_by_piece;

	public SKU() {
	}

	public SKU(String sku_id, String description, String product_group) {
		this.sku_id = sku_id;
		this.description = description;
		this.product_group = product_group;
	}

	public SKU(String client_id, String sku_id, String ean, String upc, String description, String product_group,
			String each_height, String each_weight, String each_volume, String each_value, String each_quantity,
			String qc_status, String shelf_life, String qc_frequency, String qc_rec_count, String split_lowest,
			String condition_reqd, String expiry_reqd, String origin_reqd, String serial_at_pack, String serial_at_pick,
			String serial_at_receipt, String serial_range, String serial_format, String serial_valid_merge,
			String serial_no_reuse, String serial_per_each, String pick_sequence, String pick_count_qty,
			String count_frequency, String count_dstamp, String count_list_id, String oap_wip_enabled, String kit_sku,
			String kit_split, String kit_trigger_qty, String kit_qty_due, String kitting_loc_id,
			String allocation_group, String putaway_group, String abc_disable, String handling_class,
			String obsolete_product, String new_product, String disallow_upload, String disallow_cross_dock,
			String manuf_dstamp_reqd, String manuf_dstamp_dflt, String min_shelf_life, String colour, String sku_size,
			String ship_shelf_life, String nmfc_number, String incub_rule, String incub_hours, String each_width,
			String each_depth, String reorder_trigger_qty, String low_trigger_qty, String disallow_merge_rules,
			String pack_despatch_repack, String spec_id, String beam_units, String user_def_type_1,
			String user_def_type_2, String user_def_type_3, String user_def_type_4, String user_def_type_5,
			String user_def_type_6, String user_def_type_7, String user_def_type_8, String user_def_chk_1,
			String user_def_chk_2, String user_def_chk_3, String user_def_chk_4, String user_def_date_1,
			String user_def_date_2, String user_def_date_3, String user_def_date_4, String user_def_num_1,
			String user_def_num_2, String user_def_num_3, String user_def_num_4, String user_def_note_1,
			String user_def_note_2, String created_by, String creation_date, String last_updated_by,
			String last_update_date, String ce_warehouse_type, String ce_customs_excise, String ce_standard_cost,
			String ce_standard_currency, String count_list_id_1, String disallow_clustering, String max_stack,
			String stack_description, String stack_limitation, String ce_duty_stamp, String capture_weight,
			String weigh_at_receipt, String upper_weight_tolerance, String lower_weight_tolerance,
			String serial_at_loading, String serial_at_kitting, String serial_at_unkitting, String allocalg_locking_stn,
			String putalg_locking_stn, String ce_commodity_code, String ce_coo, String ce_cwc, String ce_vat_code,
			String ce_product_type, String commodity_code, String commodity_desc, String family_group, String breakpack,
			String clearable, String stage_route_id, String serial_dynamic_range, String serial_max_range,
			String manufacture_at_repack, String expiry_at_repack, String udf_at_repack, String repack_by_piece,
			String packed_height, String packed_width, String packed_depth, String packed_volume, String packed_weight,
			String hanging_garment, String conveyable, String fragile, String gender, String high_security, String ugly,
			String collatable, String ecommerce, String promotion, String foldable, String style,
			String business_unit_code, String two_man_lift, String awkward, String decatalogued,
			String stock_check_rule_id, String unkitting_inherit, String serial_at_stock_check,
			String serial_at_stock_adjust, String kit_ship_components, String unallocatable, String batch_at_kitting,
			String hazmat, String hazmat_id, String batch_id_generation_alg, String vmi_allow_allocation,
			String vmi_allow_replenish, String vmi_allow_manual, String vmi_allow_interfaced, String vmi_overstock_qty,
			String vmi_aging_days, String scrap_on_return, String harmonised_product_code, String tag_merge,
			String uploaded, String uploaded_ws2pc_id, String uploaded_filename, String uploaded_dstamp,
			String carrier_pallet_mixing, String special_container_type, String disallow_rdt_over_picking,
			String no_alloc_back_order, String return_min_shelf_life, String weigh_at_grid_pick,
			String ce_excise_product_code, String ce_degree_plato, String ce_designation_origin, String ce_density,
			String ce_brand_name, String ce_alcoholic_strength, String ce_fiscal_mark, String ce_size_of_producer,
			String ce_commercial_desc, String serial_no_outbound, String full_pallet_at_receipt,
			String always_full_pallet, String sub_within_product_grp, String serial_check_string,
			String carrier_product_type, String max_pack_configs, String parcel_packing_by_piece) {
		this.client_id = client_id;
		this.sku_id = sku_id;
		this.ean = ean;
		this.upc = upc;
		this.description = description;
		this.product_group = product_group;
		this.each_height = each_height;
		this.each_weight = each_weight;
		this.each_volume = each_volume;
		this.each_value = each_value;
		this.each_quantity = each_quantity;
		this.qc_status = qc_status;
		this.shelf_life = shelf_life;
		this.qc_frequency = qc_frequency;
		this.qc_rec_count = qc_rec_count;
		this.split_lowest = split_lowest;
		this.condition_reqd = condition_reqd;
		this.expiry_reqd = expiry_reqd;
		this.origin_reqd = origin_reqd;
		this.serial_at_pack = serial_at_pack;
		this.serial_at_pick = serial_at_pick;
		this.serial_at_receipt = serial_at_receipt;
		this.serial_range = serial_range;
		this.serial_format = serial_format;
		this.serial_valid_merge = serial_valid_merge;
		this.serial_no_reuse = serial_no_reuse;
		this.serial_per_each = serial_per_each;
		this.pick_sequence = pick_sequence;
		this.pick_count_qty = pick_count_qty;
		this.count_frequency = count_frequency;
		this.count_dstamp = count_dstamp;
		this.count_list_id = count_list_id;
		this.oap_wip_enabled = oap_wip_enabled;
		this.kit_sku = kit_sku;
		this.kit_split = kit_split;
		this.kit_trigger_qty = kit_trigger_qty;
		this.kit_qty_due = kit_qty_due;
		this.kitting_loc_id = kitting_loc_id;
		this.allocation_group = allocation_group;
		this.putaway_group = putaway_group;
		this.abc_disable = abc_disable;
		this.handling_class = handling_class;
		this.obsolete_product = obsolete_product;
		this.new_product = new_product;
		this.disallow_upload = disallow_upload;
		this.disallow_cross_dock = disallow_cross_dock;
		this.manuf_dstamp_reqd = manuf_dstamp_reqd;
		this.manuf_dstamp_dflt = manuf_dstamp_dflt;
		this.min_shelf_life = min_shelf_life;
		this.colour = colour;
		this.sku_size = sku_size;
		this.ship_shelf_life = ship_shelf_life;
		this.nmfc_number = nmfc_number;
		this.incub_rule = incub_rule;
		this.incub_hours = incub_hours;
		this.each_width = each_width;
		this.each_depth = each_depth;
		this.reorder_trigger_qty = reorder_trigger_qty;
		this.low_trigger_qty = low_trigger_qty;
		this.disallow_merge_rules = disallow_merge_rules;
		this.pack_despatch_repack = pack_despatch_repack;
		this.spec_id = spec_id;
		this.beam_units = beam_units;
		this.user_def_type_1 = user_def_type_1;
		this.user_def_type_2 = user_def_type_2;
		this.user_def_type_3 = user_def_type_3;
		this.user_def_type_4 = user_def_type_4;
		this.user_def_type_5 = user_def_type_5;
		this.user_def_type_6 = user_def_type_6;
		this.user_def_type_7 = user_def_type_7;
		this.user_def_type_8 = user_def_type_8;
		this.user_def_chk_1 = user_def_chk_1;
		this.user_def_chk_2 = user_def_chk_2;
		this.user_def_chk_3 = user_def_chk_3;
		this.user_def_chk_4 = user_def_chk_4;
		this.user_def_date_1 = user_def_date_1;
		this.user_def_date_2 = user_def_date_2;
		this.user_def_date_3 = user_def_date_3;
		this.user_def_date_4 = user_def_date_4;
		this.user_def_num_1 = user_def_num_1;
		this.user_def_num_2 = user_def_num_2;
		this.user_def_num_3 = user_def_num_3;
		this.user_def_num_4 = user_def_num_4;
		this.user_def_note_1 = user_def_note_1;
		this.user_def_note_2 = user_def_note_2;
		this.created_by = created_by;
		this.creation_date = creation_date;
		this.last_updated_by = last_updated_by;
		this.last_update_date = last_update_date;
		this.ce_warehouse_type = ce_warehouse_type;
		this.ce_customs_excise = ce_customs_excise;
		this.ce_standard_cost = ce_standard_cost;
		this.ce_standard_currency = ce_standard_currency;
		this.count_list_id_1 = count_list_id_1;
		this.disallow_clustering = disallow_clustering;
		this.max_stack = max_stack;
		this.stack_description = stack_description;
		this.stack_limitation = stack_limitation;
		this.ce_duty_stamp = ce_duty_stamp;
		this.capture_weight = capture_weight;
		this.weigh_at_receipt = weigh_at_receipt;
		this.upper_weight_tolerance = upper_weight_tolerance;
		this.lower_weight_tolerance = lower_weight_tolerance;
		this.serial_at_loading = serial_at_loading;
		this.serial_at_kitting = serial_at_kitting;
		this.serial_at_unkitting = serial_at_unkitting;
		this.allocalg_locking_stn = allocalg_locking_stn;
		this.putalg_locking_stn = putalg_locking_stn;
		this.ce_commodity_code = ce_commodity_code;
		this.ce_coo = ce_coo;
		this.ce_cwc = ce_cwc;
		this.ce_vat_code = ce_vat_code;
		this.ce_product_type = ce_product_type;
		this.commodity_code = commodity_code;
		this.commodity_desc = commodity_desc;
		this.family_group = family_group;
		this.breakpack = breakpack;
		this.clearable = clearable;
		this.stage_route_id = stage_route_id;
		this.serial_dynamic_range = serial_dynamic_range;
		this.serial_max_range = serial_max_range;
		this.manufacture_at_repack = manufacture_at_repack;
		this.expiry_at_repack = expiry_at_repack;
		this.udf_at_repack = udf_at_repack;
		this.repack_by_piece = repack_by_piece;
		this.packed_height = packed_height;
		this.packed_width = packed_width;
		this.packed_depth = packed_depth;
		this.packed_volume = packed_volume;
		this.packed_weight = packed_weight;
		this.hanging_garment = hanging_garment;
		this.conveyable = conveyable;
		this.fragile = fragile;
		this.gender = gender;
		this.high_security = high_security;
		this.ugly = ugly;
		this.collatable = collatable;
		this.ecommerce = ecommerce;
		this.promotion = promotion;
		this.foldable = foldable;
		this.style = style;
		this.business_unit_code = business_unit_code;
		this.two_man_lift = two_man_lift;
		this.awkward = awkward;
		this.decatalogued = decatalogued;
		this.stock_check_rule_id = stock_check_rule_id;
		this.unkitting_inherit = unkitting_inherit;
		this.serial_at_stock_check = serial_at_stock_check;
		this.serial_at_stock_adjust = serial_at_stock_adjust;
		this.kit_ship_components = kit_ship_components;
		this.unallocatable = unallocatable;
		this.batch_at_kitting = batch_at_kitting;
		this.hazmat = hazmat;
		this.hazmat_id = hazmat_id;
		this.batch_id_generation_alg = batch_id_generation_alg;
		this.vmi_allow_allocation = vmi_allow_allocation;
		this.vmi_allow_replenish = vmi_allow_replenish;
		this.vmi_allow_manual = vmi_allow_manual;
		this.vmi_allow_interfaced = vmi_allow_interfaced;
		this.vmi_overstock_qty = vmi_overstock_qty;
		this.vmi_aging_days = vmi_aging_days;
		this.scrap_on_return = scrap_on_return;
		this.harmonised_product_code = harmonised_product_code;
		this.tag_merge = tag_merge;
		this.uploaded = uploaded;
		this.uploaded_ws2pc_id = uploaded_ws2pc_id;
		this.uploaded_filename = uploaded_filename;
		this.uploaded_dstamp = uploaded_dstamp;
		this.carrier_pallet_mixing = carrier_pallet_mixing;
		this.special_container_type = special_container_type;
		this.disallow_rdt_over_picking = disallow_rdt_over_picking;
		this.no_alloc_back_order = no_alloc_back_order;
		this.return_min_shelf_life = return_min_shelf_life;
		this.weigh_at_grid_pick = weigh_at_grid_pick;
		this.ce_excise_product_code = ce_excise_product_code;
		this.ce_degree_plato = ce_degree_plato;
		this.ce_designation_origin = ce_designation_origin;
		this.ce_density = ce_density;
		this.ce_brand_name = ce_brand_name;
		this.ce_alcoholic_strength = ce_alcoholic_strength;
		this.ce_fiscal_mark = ce_fiscal_mark;
		this.ce_size_of_producer = ce_size_of_producer;
		this.ce_commercial_desc = ce_commercial_desc;
		this.serial_no_outbound = serial_no_outbound;
		this.full_pallet_at_receipt = full_pallet_at_receipt;
		this.always_full_pallet = always_full_pallet;
		this.sub_within_product_grp = sub_within_product_grp;
		this.serial_check_string = serial_check_string;
		this.carrier_product_type = carrier_product_type;
		this.max_pack_configs = max_pack_configs;
		this.parcel_packing_by_piece = parcel_packing_by_piece;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_group() {
		return product_group;
	}

	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}

	public String getEach_height() {
		return each_height;
	}

	public void setEach_height(String each_height) {
		this.each_height = each_height;
	}

	public String getEach_weight() {
		return each_weight;
	}

	public void setEach_weight(String each_weight) {
		this.each_weight = each_weight;
	}

	public String getEach_volume() {
		return each_volume;
	}

	public void setEach_volume(String each_volume) {
		this.each_volume = each_volume;
	}

	public String getEach_value() {
		return each_value;
	}

	public void setEach_value(String each_value) {
		this.each_value = each_value;
	}

	public String getEach_quantity() {
		return each_quantity;
	}

	public void setEach_quantity(String each_quantity) {
		this.each_quantity = each_quantity;
	}

	public String getQc_status() {
		return qc_status;
	}

	public void setQc_status(String qc_status) {
		this.qc_status = qc_status;
	}

	public String getShelf_life() {
		return shelf_life;
	}

	public void setShelf_life(String shelf_life) {
		this.shelf_life = shelf_life;
	}

	public String getQc_frequency() {
		return qc_frequency;
	}

	public void setQc_frequency(String qc_frequency) {
		this.qc_frequency = qc_frequency;
	}

	public String getQc_rec_count() {
		return qc_rec_count;
	}

	public void setQc_rec_count(String qc_rec_count) {
		this.qc_rec_count = qc_rec_count;
	}

	public String getSplit_lowest() {
		return split_lowest;
	}

	public void setSplit_lowest(String split_lowest) {
		this.split_lowest = split_lowest;
	}

	public String getCondition_reqd() {
		return condition_reqd;
	}

	public void setCondition_reqd(String condition_reqd) {
		this.condition_reqd = condition_reqd;
	}

	public String getExpiry_reqd() {
		return expiry_reqd;
	}

	public void setExpiry_reqd(String expiry_reqd) {
		this.expiry_reqd = expiry_reqd;
	}

	public String getOrigin_reqd() {
		return origin_reqd;
	}

	public void setOrigin_reqd(String origin_reqd) {
		this.origin_reqd = origin_reqd;
	}

	public String getSerial_at_pack() {
		return serial_at_pack;
	}

	public void setSerial_at_pack(String serial_at_pack) {
		this.serial_at_pack = serial_at_pack;
	}

	public String getSerial_at_pick() {
		return serial_at_pick;
	}

	public void setSerial_at_pick(String serial_at_pick) {
		this.serial_at_pick = serial_at_pick;
	}

	public String getSerial_at_receipt() {
		return serial_at_receipt;
	}

	public void setSerial_at_receipt(String serial_at_receipt) {
		this.serial_at_receipt = serial_at_receipt;
	}

	public String getSerial_range() {
		return serial_range;
	}

	public void setSerial_range(String serial_range) {
		this.serial_range = serial_range;
	}

	public String getSerial_format() {
		return serial_format;
	}

	public void setSerial_format(String serial_format) {
		this.serial_format = serial_format;
	}

	public String getSerial_valid_merge() {
		return serial_valid_merge;
	}

	public void setSerial_valid_merge(String serial_valid_merge) {
		this.serial_valid_merge = serial_valid_merge;
	}

	public String getSerial_no_reuse() {
		return serial_no_reuse;
	}

	public void setSerial_no_reuse(String serial_no_reuse) {
		this.serial_no_reuse = serial_no_reuse;
	}

	public String getSerial_per_each() {
		return serial_per_each;
	}

	public void setSerial_per_each(String serial_per_each) {
		this.serial_per_each = serial_per_each;
	}

	public String getPick_sequence() {
		return pick_sequence;
	}

	public void setPick_sequence(String pick_sequence) {
		this.pick_sequence = pick_sequence;
	}

	public String getPick_count_qty() {
		return pick_count_qty;
	}

	public void setPick_count_qty(String pick_count_qty) {
		this.pick_count_qty = pick_count_qty;
	}

	public String getCount_frequency() {
		return count_frequency;
	}

	public void setCount_frequency(String count_frequency) {
		this.count_frequency = count_frequency;
	}

	public String getCount_dstamp() {
		return count_dstamp;
	}

	public void setCount_dstamp(String count_dstamp) {
		this.count_dstamp = count_dstamp;
	}

	public String getCount_list_id() {
		return count_list_id;
	}

	public void setCount_list_id(String count_list_id) {
		this.count_list_id = count_list_id;
	}

	public String getOap_wip_enabled() {
		return oap_wip_enabled;
	}

	public void setOap_wip_enabled(String oap_wip_enabled) {
		this.oap_wip_enabled = oap_wip_enabled;
	}

	public String getKit_sku() {
		return kit_sku;
	}

	public void setKit_sku(String kit_sku) {
		this.kit_sku = kit_sku;
	}

	public String getKit_split() {
		return kit_split;
	}

	public void setKit_split(String kit_split) {
		this.kit_split = kit_split;
	}

	public String getKit_trigger_qty() {
		return kit_trigger_qty;
	}

	public void setKit_trigger_qty(String kit_trigger_qty) {
		this.kit_trigger_qty = kit_trigger_qty;
	}

	public String getKit_qty_due() {
		return kit_qty_due;
	}

	public void setKit_qty_due(String kit_qty_due) {
		this.kit_qty_due = kit_qty_due;
	}

	public String getKitting_loc_id() {
		return kitting_loc_id;
	}

	public void setKitting_loc_id(String kitting_loc_id) {
		this.kitting_loc_id = kitting_loc_id;
	}

	public String getAllocation_group() {
		return allocation_group;
	}

	public void setAllocation_group(String allocation_group) {
		this.allocation_group = allocation_group;
	}

	public String getPutaway_group() {
		return putaway_group;
	}

	public void setPutaway_group(String putaway_group) {
		this.putaway_group = putaway_group;
	}

	public String getAbc_disable() {
		return abc_disable;
	}

	public void setAbc_disable(String abc_disable) {
		this.abc_disable = abc_disable;
	}

	public String getHandling_class() {
		return handling_class;
	}

	public void setHandling_class(String handling_class) {
		this.handling_class = handling_class;
	}

	public String getObsolete_product() {
		return obsolete_product;
	}

	public void setObsolete_product(String obsolete_product) {
		this.obsolete_product = obsolete_product;
	}

	public String getNew_product() {
		return new_product;
	}

	public void setNew_product(String new_product) {
		this.new_product = new_product;
	}

	public String getDisallow_upload() {
		return disallow_upload;
	}

	public void setDisallow_upload(String disallow_upload) {
		this.disallow_upload = disallow_upload;
	}

	public String getDisallow_cross_dock() {
		return disallow_cross_dock;
	}

	public void setDisallow_cross_dock(String disallow_cross_dock) {
		this.disallow_cross_dock = disallow_cross_dock;
	}

	public String getManuf_dstamp_reqd() {
		return manuf_dstamp_reqd;
	}

	public void setManuf_dstamp_reqd(String manuf_dstamp_reqd) {
		this.manuf_dstamp_reqd = manuf_dstamp_reqd;
	}

	public String getManuf_dstamp_dflt() {
		return manuf_dstamp_dflt;
	}

	public void setManuf_dstamp_dflt(String manuf_dstamp_dflt) {
		this.manuf_dstamp_dflt = manuf_dstamp_dflt;
	}

	public String getMin_shelf_life() {
		return min_shelf_life;
	}

	public void setMin_shelf_life(String min_shelf_life) {
		this.min_shelf_life = min_shelf_life;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getSku_size() {
		return sku_size;
	}

	public void setSku_size(String sku_size) {
		this.sku_size = sku_size;
	}

	public String getShip_shelf_life() {
		return ship_shelf_life;
	}

	public void setShip_shelf_life(String ship_shelf_life) {
		this.ship_shelf_life = ship_shelf_life;
	}

	public String getNmfc_number() {
		return nmfc_number;
	}

	public void setNmfc_number(String nmfc_number) {
		this.nmfc_number = nmfc_number;
	}

	public String getIncub_rule() {
		return incub_rule;
	}

	public void setIncub_rule(String incub_rule) {
		this.incub_rule = incub_rule;
	}

	public String getIncub_hours() {
		return incub_hours;
	}

	public void setIncub_hours(String incub_hours) {
		this.incub_hours = incub_hours;
	}

	public String getEach_width() {
		return each_width;
	}

	public void setEach_width(String each_width) {
		this.each_width = each_width;
	}

	public String getEach_depth() {
		return each_depth;
	}

	public void setEach_depth(String each_depth) {
		this.each_depth = each_depth;
	}

	public String getReorder_trigger_qty() {
		return reorder_trigger_qty;
	}

	public void setReorder_trigger_qty(String reorder_trigger_qty) {
		this.reorder_trigger_qty = reorder_trigger_qty;
	}

	public String getLow_trigger_qty() {
		return low_trigger_qty;
	}

	public void setLow_trigger_qty(String low_trigger_qty) {
		this.low_trigger_qty = low_trigger_qty;
	}

	public String getDisallow_merge_rules() {
		return disallow_merge_rules;
	}

	public void setDisallow_merge_rules(String disallow_merge_rules) {
		this.disallow_merge_rules = disallow_merge_rules;
	}

	public String getPack_despatch_repack() {
		return pack_despatch_repack;
	}

	public void setPack_despatch_repack(String pack_despatch_repack) {
		this.pack_despatch_repack = pack_despatch_repack;
	}

	public String getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(String spec_id) {
		this.spec_id = spec_id;
	}

	public String getBeam_units() {
		return beam_units;
	}

	public void setBeam_units(String beam_units) {
		this.beam_units = beam_units;
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

	public String getCe_warehouse_type() {
		return ce_warehouse_type;
	}

	public void setCe_warehouse_type(String ce_warehouse_type) {
		this.ce_warehouse_type = ce_warehouse_type;
	}

	public String getCe_customs_excise() {
		return ce_customs_excise;
	}

	public void setCe_customs_excise(String ce_customs_excise) {
		this.ce_customs_excise = ce_customs_excise;
	}

	public String getCe_standard_cost() {
		return ce_standard_cost;
	}

	public void setCe_standard_cost(String ce_standard_cost) {
		this.ce_standard_cost = ce_standard_cost;
	}

	public String getCe_standard_currency() {
		return ce_standard_currency;
	}

	public void setCe_standard_currency(String ce_standard_currency) {
		this.ce_standard_currency = ce_standard_currency;
	}

	public String getCount_list_id_1() {
		return count_list_id_1;
	}

	public void setCount_list_id_1(String count_list_id_1) {
		this.count_list_id_1 = count_list_id_1;
	}

	public String getDisallow_clustering() {
		return disallow_clustering;
	}

	public void setDisallow_clustering(String disallow_clustering) {
		this.disallow_clustering = disallow_clustering;
	}

	public String getMax_stack() {
		return max_stack;
	}

	public void setMax_stack(String max_stack) {
		this.max_stack = max_stack;
	}

	public String getStack_description() {
		return stack_description;
	}

	public void setStack_description(String stack_description) {
		this.stack_description = stack_description;
	}

	public String getStack_limitation() {
		return stack_limitation;
	}

	public void setStack_limitation(String stack_limitation) {
		this.stack_limitation = stack_limitation;
	}

	public String getCe_duty_stamp() {
		return ce_duty_stamp;
	}

	public void setCe_duty_stamp(String ce_duty_stamp) {
		this.ce_duty_stamp = ce_duty_stamp;
	}

	public String getCapture_weight() {
		return capture_weight;
	}

	public void setCapture_weight(String capture_weight) {
		this.capture_weight = capture_weight;
	}

	public String getWeigh_at_receipt() {
		return weigh_at_receipt;
	}

	public void setWeigh_at_receipt(String weigh_at_receipt) {
		this.weigh_at_receipt = weigh_at_receipt;
	}

	public String getUpper_weight_tolerance() {
		return upper_weight_tolerance;
	}

	public void setUpper_weight_tolerance(String upper_weight_tolerance) {
		this.upper_weight_tolerance = upper_weight_tolerance;
	}

	public String getLower_weight_tolerance() {
		return lower_weight_tolerance;
	}

	public void setLower_weight_tolerance(String lower_weight_tolerance) {
		this.lower_weight_tolerance = lower_weight_tolerance;
	}

	public String getSerial_at_loading() {
		return serial_at_loading;
	}

	public void setSerial_at_loading(String serial_at_loading) {
		this.serial_at_loading = serial_at_loading;
	}

	public String getSerial_at_kitting() {
		return serial_at_kitting;
	}

	public void setSerial_at_kitting(String serial_at_kitting) {
		this.serial_at_kitting = serial_at_kitting;
	}

	public String getSerial_at_unkitting() {
		return serial_at_unkitting;
	}

	public void setSerial_at_unkitting(String serial_at_unkitting) {
		this.serial_at_unkitting = serial_at_unkitting;
	}

	public String getAllocalg_locking_stn() {
		return allocalg_locking_stn;
	}

	public void setAllocalg_locking_stn(String allocalg_locking_stn) {
		this.allocalg_locking_stn = allocalg_locking_stn;
	}

	public String getPutalg_locking_stn() {
		return putalg_locking_stn;
	}

	public void setPutalg_locking_stn(String putalg_locking_stn) {
		this.putalg_locking_stn = putalg_locking_stn;
	}

	public String getCe_commodity_code() {
		return ce_commodity_code;
	}

	public void setCe_commodity_code(String ce_commodity_code) {
		this.ce_commodity_code = ce_commodity_code;
	}

	public String getCe_coo() {
		return ce_coo;
	}

	public void setCe_coo(String ce_coo) {
		this.ce_coo = ce_coo;
	}

	public String getCe_cwc() {
		return ce_cwc;
	}

	public void setCe_cwc(String ce_cwc) {
		this.ce_cwc = ce_cwc;
	}

	public String getCe_vat_code() {
		return ce_vat_code;
	}

	public void setCe_vat_code(String ce_vat_code) {
		this.ce_vat_code = ce_vat_code;
	}

	public String getCe_product_type() {
		return ce_product_type;
	}

	public void setCe_product_type(String ce_product_type) {
		this.ce_product_type = ce_product_type;
	}

	public String getCommodity_code() {
		return commodity_code;
	}

	public void setCommodity_code(String commodity_code) {
		this.commodity_code = commodity_code;
	}

	public String getCommodity_desc() {
		return commodity_desc;
	}

	public void setCommodity_desc(String commodity_desc) {
		this.commodity_desc = commodity_desc;
	}

	public String getFamily_group() {
		return family_group;
	}

	public void setFamily_group(String family_group) {
		this.family_group = family_group;
	}

	public String getBreakpack() {
		return breakpack;
	}

	public void setBreakpack(String breakpack) {
		this.breakpack = breakpack;
	}

	public String getClearable() {
		return clearable;
	}

	public void setClearable(String clearable) {
		this.clearable = clearable;
	}

	public String getStage_route_id() {
		return stage_route_id;
	}

	public void setStage_route_id(String stage_route_id) {
		this.stage_route_id = stage_route_id;
	}

	public String getSerial_dynamic_range() {
		return serial_dynamic_range;
	}

	public void setSerial_dynamic_range(String serial_dynamic_range) {
		this.serial_dynamic_range = serial_dynamic_range;
	}

	public String getSerial_max_range() {
		return serial_max_range;
	}

	public void setSerial_max_range(String serial_max_range) {
		this.serial_max_range = serial_max_range;
	}

	public String getManufacture_at_repack() {
		return manufacture_at_repack;
	}

	public void setManufacture_at_repack(String manufacture_at_repack) {
		this.manufacture_at_repack = manufacture_at_repack;
	}

	public String getExpiry_at_repack() {
		return expiry_at_repack;
	}

	public void setExpiry_at_repack(String expiry_at_repack) {
		this.expiry_at_repack = expiry_at_repack;
	}

	public String getUdf_at_repack() {
		return udf_at_repack;
	}

	public void setUdf_at_repack(String udf_at_repack) {
		this.udf_at_repack = udf_at_repack;
	}

	public String getRepack_by_piece() {
		return repack_by_piece;
	}

	public void setRepack_by_piece(String repack_by_piece) {
		this.repack_by_piece = repack_by_piece;
	}

	public String getPacked_height() {
		return packed_height;
	}

	public void setPacked_height(String packed_height) {
		this.packed_height = packed_height;
	}

	public String getPacked_width() {
		return packed_width;
	}

	public void setPacked_width(String packed_width) {
		this.packed_width = packed_width;
	}

	public String getPacked_depth() {
		return packed_depth;
	}

	public void setPacked_depth(String packed_depth) {
		this.packed_depth = packed_depth;
	}

	public String getPacked_volume() {
		return packed_volume;
	}

	public void setPacked_volume(String packed_volume) {
		this.packed_volume = packed_volume;
	}

	public String getPacked_weight() {
		return packed_weight;
	}

	public void setPacked_weight(String packed_weight) {
		this.packed_weight = packed_weight;
	}

	public String getHanging_garment() {
		return hanging_garment;
	}

	public void setHanging_garment(String hanging_garment) {
		this.hanging_garment = hanging_garment;
	}

	public String getConveyable() {
		return conveyable;
	}

	public void setConveyable(String conveyable) {
		this.conveyable = conveyable;
	}

	public String getFragile() {
		return fragile;
	}

	public void setFragile(String fragile) {
		this.fragile = fragile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHigh_security() {
		return high_security;
	}

	public void setHigh_security(String high_security) {
		this.high_security = high_security;
	}

	public String getUgly() {
		return ugly;
	}

	public void setUgly(String ugly) {
		this.ugly = ugly;
	}

	public String getCollatable() {
		return collatable;
	}

	public void setCollatable(String collatable) {
		this.collatable = collatable;
	}

	public String getEcommerce() {
		return ecommerce;
	}

	public void setEcommerce(String ecommerce) {
		this.ecommerce = ecommerce;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getFoldable() {
		return foldable;
	}

	public void setFoldable(String foldable) {
		this.foldable = foldable;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getBusiness_unit_code() {
		return business_unit_code;
	}

	public void setBusiness_unit_code(String business_unit_code) {
		this.business_unit_code = business_unit_code;
	}

	public String getTwo_man_lift() {
		return two_man_lift;
	}

	public void setTwo_man_lift(String two_man_lift) {
		this.two_man_lift = two_man_lift;
	}

	public String getAwkward() {
		return awkward;
	}

	public void setAwkward(String awkward) {
		this.awkward = awkward;
	}

	public String getDecatalogued() {
		return decatalogued;
	}

	public void setDecatalogued(String decatalogued) {
		this.decatalogued = decatalogued;
	}

	public String getStock_check_rule_id() {
		return stock_check_rule_id;
	}

	public void setStock_check_rule_id(String stock_check_rule_id) {
		this.stock_check_rule_id = stock_check_rule_id;
	}

	public String getUnkitting_inherit() {
		return unkitting_inherit;
	}

	public void setUnkitting_inherit(String unkitting_inherit) {
		this.unkitting_inherit = unkitting_inherit;
	}

	public String getSerial_at_stock_check() {
		return serial_at_stock_check;
	}

	public void setSerial_at_stock_check(String serial_at_stock_check) {
		this.serial_at_stock_check = serial_at_stock_check;
	}

	public String getSerial_at_stock_adjust() {
		return serial_at_stock_adjust;
	}

	public void setSerial_at_stock_adjust(String serial_at_stock_adjust) {
		this.serial_at_stock_adjust = serial_at_stock_adjust;
	}

	public String getKit_ship_components() {
		return kit_ship_components;
	}

	public void setKit_ship_components(String kit_ship_components) {
		this.kit_ship_components = kit_ship_components;
	}

	public String getUnallocatable() {
		return unallocatable;
	}

	public void setUnallocatable(String unallocatable) {
		this.unallocatable = unallocatable;
	}

	public String getBatch_at_kitting() {
		return batch_at_kitting;
	}

	public void setBatch_at_kitting(String batch_at_kitting) {
		this.batch_at_kitting = batch_at_kitting;
	}

	public String getHazmat() {
		return hazmat;
	}

	public void setHazmat(String hazmat) {
		this.hazmat = hazmat;
	}

	public String getHazmat_id() {
		return hazmat_id;
	}

	public void setHazmat_id(String hazmat_id) {
		this.hazmat_id = hazmat_id;
	}

	public String getBatch_id_generation_alg() {
		return batch_id_generation_alg;
	}

	public void setBatch_id_generation_alg(String batch_id_generation_alg) {
		this.batch_id_generation_alg = batch_id_generation_alg;
	}

	public String getVmi_allow_allocation() {
		return vmi_allow_allocation;
	}

	public void setVmi_allow_allocation(String vmi_allow_allocation) {
		this.vmi_allow_allocation = vmi_allow_allocation;
	}

	public String getVmi_allow_replenish() {
		return vmi_allow_replenish;
	}

	public void setVmi_allow_replenish(String vmi_allow_replenish) {
		this.vmi_allow_replenish = vmi_allow_replenish;
	}

	public String getVmi_allow_manual() {
		return vmi_allow_manual;
	}

	public void setVmi_allow_manual(String vmi_allow_manual) {
		this.vmi_allow_manual = vmi_allow_manual;
	}

	public String getVmi_allow_interfaced() {
		return vmi_allow_interfaced;
	}

	public void setVmi_allow_interfaced(String vmi_allow_interfaced) {
		this.vmi_allow_interfaced = vmi_allow_interfaced;
	}

	public String getVmi_overstock_qty() {
		return vmi_overstock_qty;
	}

	public void setVmi_overstock_qty(String vmi_overstock_qty) {
		this.vmi_overstock_qty = vmi_overstock_qty;
	}

	public String getVmi_aging_days() {
		return vmi_aging_days;
	}

	public void setVmi_aging_days(String vmi_aging_days) {
		this.vmi_aging_days = vmi_aging_days;
	}

	public String getScrap_on_return() {
		return scrap_on_return;
	}

	public void setScrap_on_return(String scrap_on_return) {
		this.scrap_on_return = scrap_on_return;
	}

	public String getHarmonised_product_code() {
		return harmonised_product_code;
	}

	public void setHarmonised_product_code(String harmonised_product_code) {
		this.harmonised_product_code = harmonised_product_code;
	}

	public String getTag_merge() {
		return tag_merge;
	}

	public void setTag_merge(String tag_merge) {
		this.tag_merge = tag_merge;
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

	public String getUploaded_filename() {
		return uploaded_filename;
	}

	public void setUploaded_filename(String uploaded_filename) {
		this.uploaded_filename = uploaded_filename;
	}

	public String getUploaded_dstamp() {
		return uploaded_dstamp;
	}

	public void setUploaded_dstamp(String uploaded_dstamp) {
		this.uploaded_dstamp = uploaded_dstamp;
	}

	public String getCarrier_pallet_mixing() {
		return carrier_pallet_mixing;
	}

	public void setCarrier_pallet_mixing(String carrier_pallet_mixing) {
		this.carrier_pallet_mixing = carrier_pallet_mixing;
	}

	public String getSpecial_container_type() {
		return special_container_type;
	}

	public void setSpecial_container_type(String special_container_type) {
		this.special_container_type = special_container_type;
	}

	public String getDisallow_rdt_over_picking() {
		return disallow_rdt_over_picking;
	}

	public void setDisallow_rdt_over_picking(String disallow_rdt_over_picking) {
		this.disallow_rdt_over_picking = disallow_rdt_over_picking;
	}

	public String getNo_alloc_back_order() {
		return no_alloc_back_order;
	}

	public void setNo_alloc_back_order(String no_alloc_back_order) {
		this.no_alloc_back_order = no_alloc_back_order;
	}

	public String getReturn_min_shelf_life() {
		return return_min_shelf_life;
	}

	public void setReturn_min_shelf_life(String return_min_shelf_life) {
		this.return_min_shelf_life = return_min_shelf_life;
	}

	public String getWeigh_at_grid_pick() {
		return weigh_at_grid_pick;
	}

	public void setWeigh_at_grid_pick(String weigh_at_grid_pick) {
		this.weigh_at_grid_pick = weigh_at_grid_pick;
	}

	public String getCe_excise_product_code() {
		return ce_excise_product_code;
	}

	public void setCe_excise_product_code(String ce_excise_product_code) {
		this.ce_excise_product_code = ce_excise_product_code;
	}

	public String getCe_degree_plato() {
		return ce_degree_plato;
	}

	public void setCe_degree_plato(String ce_degree_plato) {
		this.ce_degree_plato = ce_degree_plato;
	}

	public String getCe_designation_origin() {
		return ce_designation_origin;
	}

	public void setCe_designation_origin(String ce_designation_origin) {
		this.ce_designation_origin = ce_designation_origin;
	}

	public String getCe_density() {
		return ce_density;
	}

	public void setCe_density(String ce_density) {
		this.ce_density = ce_density;
	}

	public String getCe_brand_name() {
		return ce_brand_name;
	}

	public void setCe_brand_name(String ce_brand_name) {
		this.ce_brand_name = ce_brand_name;
	}

	public String getCe_alcoholic_strength() {
		return ce_alcoholic_strength;
	}

	public void setCe_alcoholic_strength(String ce_alcoholic_strength) {
		this.ce_alcoholic_strength = ce_alcoholic_strength;
	}

	public String getCe_fiscal_mark() {
		return ce_fiscal_mark;
	}

	public void setCe_fiscal_mark(String ce_fiscal_mark) {
		this.ce_fiscal_mark = ce_fiscal_mark;
	}

	public String getCe_size_of_producer() {
		return ce_size_of_producer;
	}

	public void setCe_size_of_producer(String ce_size_of_producer) {
		this.ce_size_of_producer = ce_size_of_producer;
	}

	public String getCe_commercial_desc() {
		return ce_commercial_desc;
	}

	public void setCe_commercial_desc(String ce_commercial_desc) {
		this.ce_commercial_desc = ce_commercial_desc;
	}

	public String getSerial_no_outbound() {
		return serial_no_outbound;
	}

	public void setSerial_no_outbound(String serial_no_outbound) {
		this.serial_no_outbound = serial_no_outbound;
	}

	public String getFull_pallet_at_receipt() {
		return full_pallet_at_receipt;
	}

	public void setFull_pallet_at_receipt(String full_pallet_at_receipt) {
		this.full_pallet_at_receipt = full_pallet_at_receipt;
	}

	public String getAlways_full_pallet() {
		return always_full_pallet;
	}

	public void setAlways_full_pallet(String always_full_pallet) {
		this.always_full_pallet = always_full_pallet;
	}

	public String getSub_within_product_grp() {
		return sub_within_product_grp;
	}

	public void setSub_within_product_grp(String sub_within_product_grp) {
		this.sub_within_product_grp = sub_within_product_grp;
	}

	public String getSerial_check_string() {
		return serial_check_string;
	}

	public void setSerial_check_string(String serial_check_string) {
		this.serial_check_string = serial_check_string;
	}

	public String getCarrier_product_type() {
		return carrier_product_type;
	}

	public void setCarrier_product_type(String carrier_product_type) {
		this.carrier_product_type = carrier_product_type;
	}

	public String getMax_pack_configs() {
		return max_pack_configs;
	}

	public void setMax_pack_configs(String max_pack_configs) {
		this.max_pack_configs = max_pack_configs;
	}

	public String getParcel_packing_by_piece() {
		return parcel_packing_by_piece;
	}

	public void setParcel_packing_by_piece(String parcel_packing_by_piece) {
		this.parcel_packing_by_piece = parcel_packing_by_piece;
	}

}
