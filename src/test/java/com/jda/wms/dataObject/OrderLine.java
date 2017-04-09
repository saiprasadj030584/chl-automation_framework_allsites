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
public class OrderLine {

    private String client_id;
    private String order_id;
    private String line_id;
    private String host_order_id;
    private String host_line_id;
    private String sku_id;
    private String customer_sku_id;
    private String config_id;
    private String tracking_level;
    private String batch_id;
    private String batch_mixing;
    private String batch_id_set;
    private String shelf_life_days;
    private String shelf_life_percent;
    private String origin_id;
    private String condition_id;
    private String lock_code;
    private String spec_code;
    private String qty_ordered;
    private String qty_tasked;
    private String qty_picked;
    private String qty_shipped;
    private String qty_delivered;
    private String qty_returned;
    private String qty_soft_allocated;
    private String allocate;
    private String back_ordered;
    private String kit_split;
    private String deallocate;
    private String disallow_merge_rules;
    private String created_by;
    private String creation_date;
    private String last_updated_by;
    private String last_update_date;
    private String rule_id;
    private String line_value;
    private String line_value_user_def;
    private String notes;
    private String psft_int_line;
    private String psft_schd_line;
    private String psft_dmnd_line;
    private String sap_pick_req;
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
    private String alloc_session_id;
    private String alloc_inst_id;
    private String alloc_status;
    private String original_line_id;
    private String conversion_factor;
    private String substitute_flag;
    private String task_per_each;
    private String catch_weight;
    private String use_pick_to_grid;
    private String ignore_weight_capture;
    private String stage_route_id;
    private String qty_substituted;
    private String min_qty_ordered;
    private String max_qty_ordered;
    private String expected_volume;
    private String expected_weight;
    private String expected_value;
    private String customer_sku_desc1;
    private String customer_sku_desc2;
    private String purchase_order;
    private String product_price;
    private String product_currency;
    private String documentation_unit;
    private String extended_price;
    private String tax_1;
    private String tax_2;
    private String documentation_text_1;
    private String serial_number;
    private String owner_id;
    private String ce_receipt_type;
    private String ce_coo;
    private String kit_plan_id;
    private String master_order_id;
    private String master_order_line_id;
    private String tm_ship_line_id;
    private String soft_allocated;
    private String location_id;
    private String unallocatable;
    private String min_full_pallet_perc;
    private String max_full_pallet_perc;
    private String full_tracking_level_only;
    private String substitute_grade;
    private String disallow_substitution;

    public OrderLine(String order_id, String line_id, String sku_id, String config_id, String tracking_level, String qty_ordered, String user_def_type_6, String user_def_type_7) {
        this.order_id = order_id;
        this.line_id = line_id;
        this.sku_id = sku_id;
        this.config_id = config_id;
        this.tracking_level = tracking_level;
        this.qty_ordered = qty_ordered;
        this.user_def_type_6 = user_def_type_6;
        this.user_def_type_7 = user_def_type_7;
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

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getHost_order_id() {
        return host_order_id;
    }

    public void setHost_order_id(String host_order_id) {
        this.host_order_id = host_order_id;
    }

    public String getHost_line_id() {
        return host_line_id;
    }

    public void setHost_line_id(String host_line_id) {
        this.host_line_id = host_line_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getCustomer_sku_id() {
        return customer_sku_id;
    }

    public void setCustomer_sku_id(String customer_sku_id) {
        this.customer_sku_id = customer_sku_id;
    }

    public String getConfig_id() {
        return config_id;
    }

    public void setConfig_id(String config_id) {
        this.config_id = config_id;
    }

    public String getTracking_level() {
        return tracking_level;
    }

    public void setTracking_level(String tracking_level) {
        this.tracking_level = tracking_level;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_mixing() {
        return batch_mixing;
    }

    public void setBatch_mixing(String batch_mixing) {
        this.batch_mixing = batch_mixing;
    }

    public String getBatch_id_set() {
        return batch_id_set;
    }

    public void setBatch_id_set(String batch_id_set) {
        this.batch_id_set = batch_id_set;
    }

    public String getShelf_life_days() {
        return shelf_life_days;
    }

    public void setShelf_life_days(String shelf_life_days) {
        this.shelf_life_days = shelf_life_days;
    }

    public String getShelf_life_percent() {
        return shelf_life_percent;
    }

    public void setShelf_life_percent(String shelf_life_percent) {
        this.shelf_life_percent = shelf_life_percent;
    }

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public String getCondition_id() {
        return condition_id;
    }

    public void setCondition_id(String condition_id) {
        this.condition_id = condition_id;
    }

    public String getLock_code() {
        return lock_code;
    }

    public void setLock_code(String lock_code) {
        this.lock_code = lock_code;
    }

    public String getSpec_code() {
        return spec_code;
    }

    public void setSpec_code(String spec_code) {
        this.spec_code = spec_code;
    }

    public String getQty_ordered() {
        return qty_ordered;
    }

    public void setQty_ordered(String qty_ordered) {
        this.qty_ordered = qty_ordered;
    }

    public String getQty_tasked() {
        return qty_tasked;
    }

    public void setQty_tasked(String qty_tasked) {
        this.qty_tasked = qty_tasked;
    }

    public String getQty_picked() {
        return qty_picked;
    }

    public void setQty_picked(String qty_picked) {
        this.qty_picked = qty_picked;
    }

    public String getQty_shipped() {
        return qty_shipped;
    }

    public void setQty_shipped(String qty_shipped) {
        this.qty_shipped = qty_shipped;
    }

    public String getQty_delivered() {
        return qty_delivered;
    }

    public void setQty_delivered(String qty_delivered) {
        this.qty_delivered = qty_delivered;
    }

    public String getQty_returned() {
        return qty_returned;
    }

    public void setQty_returned(String qty_returned) {
        this.qty_returned = qty_returned;
    }

    public String getQty_soft_allocated() {
        return qty_soft_allocated;
    }

    public void setQty_soft_allocated(String qty_soft_allocated) {
        this.qty_soft_allocated = qty_soft_allocated;
    }

    public String getAllocate() {
        return allocate;
    }

    public void setAllocate(String allocate) {
        this.allocate = allocate;
    }

    public String getBack_ordered() {
        return back_ordered;
    }

    public void setBack_ordered(String back_ordered) {
        this.back_ordered = back_ordered;
    }

    public String getKit_split() {
        return kit_split;
    }

    public void setKit_split(String kit_split) {
        this.kit_split = kit_split;
    }

    public String getDeallocate() {
        return deallocate;
    }

    public void setDeallocate(String deallocate) {
        this.deallocate = deallocate;
    }

    public String getDisallow_merge_rules() {
        return disallow_merge_rules;
    }

    public void setDisallow_merge_rules(String disallow_merge_rules) {
        this.disallow_merge_rules = disallow_merge_rules;
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

    public String getRule_id() {
        return rule_id;
    }

    public void setRule_id(String rule_id) {
        this.rule_id = rule_id;
    }

    public String getLine_value() {
        return line_value;
    }

    public void setLine_value(String line_value) {
        this.line_value = line_value;
    }

    public String getLine_value_user_def() {
        return line_value_user_def;
    }

    public void setLine_value_user_def(String line_value_user_def) {
        this.line_value_user_def = line_value_user_def;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPsft_int_line() {
        return psft_int_line;
    }

    public void setPsft_int_line(String psft_int_line) {
        this.psft_int_line = psft_int_line;
    }

    public String getPsft_schd_line() {
        return psft_schd_line;
    }

    public void setPsft_schd_line(String psft_schd_line) {
        this.psft_schd_line = psft_schd_line;
    }

    public String getPsft_dmnd_line() {
        return psft_dmnd_line;
    }

    public void setPsft_dmnd_line(String psft_dmnd_line) {
        this.psft_dmnd_line = psft_dmnd_line;
    }

    public String getSap_pick_req() {
        return sap_pick_req;
    }

    public void setSap_pick_req(String sap_pick_req) {
        this.sap_pick_req = sap_pick_req;
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

    public String getAlloc_session_id() {
        return alloc_session_id;
    }

    public void setAlloc_session_id(String alloc_session_id) {
        this.alloc_session_id = alloc_session_id;
    }

    public String getAlloc_inst_id() {
        return alloc_inst_id;
    }

    public void setAlloc_inst_id(String alloc_inst_id) {
        this.alloc_inst_id = alloc_inst_id;
    }

    public String getAlloc_status() {
        return alloc_status;
    }

    public void setAlloc_status(String alloc_status) {
        this.alloc_status = alloc_status;
    }

    public String getOriginal_line_id() {
        return original_line_id;
    }

    public void setOriginal_line_id(String original_line_id) {
        this.original_line_id = original_line_id;
    }

    public String getConversion_factor() {
        return conversion_factor;
    }

    public void setConversion_factor(String conversion_factor) {
        this.conversion_factor = conversion_factor;
    }

    public String getSubstitute_flag() {
        return substitute_flag;
    }

    public void setSubstitute_flag(String substitute_flag) {
        this.substitute_flag = substitute_flag;
    }

    public String getTask_per_each() {
        return task_per_each;
    }

    public void setTask_per_each(String task_per_each) {
        this.task_per_each = task_per_each;
    }

    public String getCatch_weight() {
        return catch_weight;
    }

    public void setCatch_weight(String catch_weight) {
        this.catch_weight = catch_weight;
    }

    public String getUse_pick_to_grid() {
        return use_pick_to_grid;
    }

    public void setUse_pick_to_grid(String use_pick_to_grid) {
        this.use_pick_to_grid = use_pick_to_grid;
    }

    public String getIgnore_weight_capture() {
        return ignore_weight_capture;
    }

    public void setIgnore_weight_capture(String ignore_weight_capture) {
        this.ignore_weight_capture = ignore_weight_capture;
    }

    public String getStage_route_id() {
        return stage_route_id;
    }

    public void setStage_route_id(String stage_route_id) {
        this.stage_route_id = stage_route_id;
    }

    public String getQty_substituted() {
        return qty_substituted;
    }

    public void setQty_substituted(String qty_substituted) {
        this.qty_substituted = qty_substituted;
    }

    public String getMin_qty_ordered() {
        return min_qty_ordered;
    }

    public void setMin_qty_ordered(String min_qty_ordered) {
        this.min_qty_ordered = min_qty_ordered;
    }

    public String getMax_qty_ordered() {
        return max_qty_ordered;
    }

    public void setMax_qty_ordered(String max_qty_ordered) {
        this.max_qty_ordered = max_qty_ordered;
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

    public String getCustomer_sku_desc1() {
        return customer_sku_desc1;
    }

    public void setCustomer_sku_desc1(String customer_sku_desc1) {
        this.customer_sku_desc1 = customer_sku_desc1;
    }

    public String getCustomer_sku_desc2() {
        return customer_sku_desc2;
    }

    public void setCustomer_sku_desc2(String customer_sku_desc2) {
        this.customer_sku_desc2 = customer_sku_desc2;
    }

    public String getPurchase_order() {
        return purchase_order;
    }

    public void setPurchase_order(String purchase_order) {
        this.purchase_order = purchase_order;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_currency() {
        return product_currency;
    }

    public void setProduct_currency(String product_currency) {
        this.product_currency = product_currency;
    }

    public String getDocumentation_unit() {
        return documentation_unit;
    }

    public void setDocumentation_unit(String documentation_unit) {
        this.documentation_unit = documentation_unit;
    }

    public String getExtended_price() {
        return extended_price;
    }

    public void setExtended_price(String extended_price) {
        this.extended_price = extended_price;
    }

    public String getTax_1() {
        return tax_1;
    }

    public void setTax_1(String tax_1) {
        this.tax_1 = tax_1;
    }

    public String getTax_2() {
        return tax_2;
    }

    public void setTax_2(String tax_2) {
        this.tax_2 = tax_2;
    }

    public String getDocumentation_text_1() {
        return documentation_text_1;
    }

    public void setDocumentation_text_1(String documentation_text_1) {
        this.documentation_text_1 = documentation_text_1;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getCe_receipt_type() {
        return ce_receipt_type;
    }

    public void setCe_receipt_type(String ce_receipt_type) {
        this.ce_receipt_type = ce_receipt_type;
    }

    public String getCe_coo() {
        return ce_coo;
    }

    public void setCe_coo(String ce_coo) {
        this.ce_coo = ce_coo;
    }

    public String getKit_plan_id() {
        return kit_plan_id;
    }

    public void setKit_plan_id(String kit_plan_id) {
        this.kit_plan_id = kit_plan_id;
    }

    public String getMaster_order_id() {
        return master_order_id;
    }

    public void setMaster_order_id(String master_order_id) {
        this.master_order_id = master_order_id;
    }

    public String getMaster_order_line_id() {
        return master_order_line_id;
    }

    public void setMaster_order_line_id(String master_order_line_id) {
        this.master_order_line_id = master_order_line_id;
    }

    public String getTm_ship_line_id() {
        return tm_ship_line_id;
    }

    public void setTm_ship_line_id(String tm_ship_line_id) {
        this.tm_ship_line_id = tm_ship_line_id;
    }

    public String getSoft_allocated() {
        return soft_allocated;
    }

    public void setSoft_allocated(String soft_allocated) {
        this.soft_allocated = soft_allocated;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getUnallocatable() {
        return unallocatable;
    }

    public void setUnallocatable(String unallocatable) {
        this.unallocatable = unallocatable;
    }

    public String getMin_full_pallet_perc() {
        return min_full_pallet_perc;
    }

    public void setMin_full_pallet_perc(String min_full_pallet_perc) {
        this.min_full_pallet_perc = min_full_pallet_perc;
    }

    public String getMax_full_pallet_perc() {
        return max_full_pallet_perc;
    }

    public void setMax_full_pallet_perc(String max_full_pallet_perc) {
        this.max_full_pallet_perc = max_full_pallet_perc;
    }

    public String getFull_tracking_level_only() {
        return full_tracking_level_only;
    }

    public void setFull_tracking_level_only(String full_tracking_level_only) {
        this.full_tracking_level_only = full_tracking_level_only;
    }

    public String getSubstitute_grade() {
        return substitute_grade;
    }

    public void setSubstitute_grade(String substitute_grade) {
        this.substitute_grade = substitute_grade;
    }

    public String getDisallow_substitution() {
        return disallow_substitution;
    }

    public void setDisallow_substitution(String disallow_substitution) {
        this.disallow_substitution = disallow_substitution;
    }

    
}
