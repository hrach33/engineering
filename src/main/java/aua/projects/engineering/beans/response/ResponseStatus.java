package aua.projects.engineering.beans.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStatus {
    SUCCESS("0"),
    GENERAL_ERROR("1"),
    INCORRECT_REQUEST_PARAMETERS("2");

    private final String value;

    @JsonCreator
    public static ResponseStatus fromValue(String v) {
        for (ResponseStatus c : ResponseStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }

    ResponseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}