package com.pet.shop.eunm;

/**
 * 订单状态枚举
 */
public enum OrderStatus {
    /**
     * 订单状态枚举类
     */
    DELETE(0 ,"关闭"),
    COMPLETE(1,"完成"),
    WAITPAY(2,"待支付"),
    WAITSEND(3,"待发货"),
    SEND(4,"已发货");


    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    OrderStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OrderStatus getEnum(Integer code) {
        OrderStatus[] enums = values();
        for (OrderStatus item : enums) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
