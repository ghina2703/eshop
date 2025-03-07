package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String param) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
