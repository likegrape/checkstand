package com.wuyue.checkstand;

/**
 * Author：wuyue
 * Crested:2019/1/11
 */
public interface OrderCenter {
    /**
     * 添加订单
     * @param order
     */
    void addOrder(Order order);

    /**
     * 移除订单
     * @param order
     */
    void removeOrder(Order order);

    /**
     * 所有订单信息
     * @return
     */
    String ordersTable();

    /**
     * 根据订单编号获取信息
     * @param  orderId
     * @return
     */
    String orderTable(String orderId);

    /**
     * 存储订单信息
     */
    void storeOrder();

    /**
     * 加载订单信息
     */
    void loadOrders();
}

