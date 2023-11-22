package dev.omedia.models;

import dev.omedia.enums.ActionType;
import dev.omedia.enums.BorderCrossType;

import java.time.LocalDate;

public class BorderCross {
    private final String cross_id;
    private final BorderCrossType cross_type;
    private final String p_id;
    private final String v_number;
    private final LocalDate c_date;
    private final ActionType action;
    private final String country_code;

    public BorderCross(String cross_id, BorderCrossType cross_type, String p_id, String v_number, LocalDate c_date, ActionType action, String country_code) {
        this.cross_id = cross_id;
        this.cross_type = cross_type;
        this.p_id = p_id;
        this.v_number = v_number;
        this.c_date = c_date;
        this.action = action;
        this.country_code = country_code;
    }

    public String getCross_id() {
        return cross_id;
    }

    public BorderCrossType getCross_type() {
        return cross_type;
    }

    public String getP_id() {
        return p_id;
    }

    public String getV_number() {
        return v_number;
    }

    public LocalDate getC_date() {
        return c_date;
    }

    public ActionType getAction() {
        return action;
    }

    public String getCountry_code() {
        return country_code;
    }
}
