package com.wuyue.checkstand;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：wuyue
 * Crested:2019/1/11
 */
public class Order {
    //订单Order对象创建完成之后，订单编号不能修改
    private final String orderId;
    //订单Order对象创建完成之后，goodsInfo属性实例化HashMap
    private final Map<String,Integer> goodsInfo = new HashMap<>();
    private Map<String, Integer> orderInfo;

    public Order(String orderId) {
        this.orderId = orderId;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        orderId = orderId;
    }

    public Map<String, Integer> getOrderInfo() {
        return goodsInfo;
    }

    public void setOrderInfo(Map<String, Integer> orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * 订单添加商品
     * @param goodsId 商品编号
     * @param count   数量
     */
    public void add(String goodsId, Integer count) {
        int sum;
        if(goodsInfo.containsKey(goodsId)){
            //如果订单中的商品信息包含指定的商品编号
            sum = goodsInfo.get(goodsId) + count;
        }else {
            sum = count;
        }
        this.goodsInfo.put(goodsId,sum);
    }


    public void cancel(String goodsId, Integer count){
        if(this.goodsInfo.containsKey(goodsId)){
            int sum = this.goodsInfo.get(goodsId);
            sum = sum - count;
            sum = sum < 0 ? 0 : sum;
            if(sum==0){
                this.goodsInfo.remove(goodsId);
            }else{
                this.goodsInfo.put(goodsId,sum);
            }
        }
    }

    public void clear(){

        this.goodsInfo.clear();
    }



}
