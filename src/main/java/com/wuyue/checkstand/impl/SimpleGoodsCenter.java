package com.wuyue.checkstand.impl;

import com.wuyue.checkstand.Goods;
import com.wuyue.checkstand.GoodsCenter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：wuyue
 * Crested:2019/3/14
 */
public class SimpleGoodsCenter implements GoodsCenter {

    private final Map<String,Goods> goodsMap = new HashMap<>();

    //当前存储在文件中
    private  String filePath = System.getProperty("user.dir")+ File.separator+"goods.txt";

    @Override
    public void addGoods(Goods goods) {
        this.goodsMap.put(goods.getId(),goods);
    }

    @Override
    public void removeGoods(String goodsId) {
        this.goodsMap.remove(goodsId);
    }

    @Override
    public void updateGoods(Goods goods) {
        if(this.goodsMap.containsKey(goods.getId())){
            this.goodsMap.put(goods.getId(),goods);
        }
    }

    @Override
    public boolean isExistGoods(String goodsId) {
        return this.goodsMap.containsKey(goodsId);
    }

    @Override
    public Goods getGoods(String goodsId) {
        return this.goodsMap.get(goodsId);
    }

    @Override
    public String listGoods() {
        StringBuilder sb = new StringBuilder();
        sb.append("****************商品信息*****************\n");
        sb.append("\t\t编号\t商品名称\t单价\n");
        for(Map.Entry<String,Goods> entry : this.goodsMap.entrySet()){
            Goods goods = entry.getValue();
            sb.append(String.format("\t\t%s\t%s\t%.2f\n",goods.getId(),goods.getName(),goods.getPrice()));
        }
        sb.append("****************************************\n");
        return sb.toString();
    }

    @Override
    public void store() {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter((new FileWriter((file))))){
            for(Map.Entry<String,Goods> entry : this.goodsMap.entrySet()){
                Goods goods = entry.getValue();
                writer.write(String.format("%s:%s:%.2f\n",goods.getId(),goods.getName(),goods.getPrice()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        File file = new File(filePath);
        if(file.exists() && file.isFile()){
            //加载，读取文件
            try(BufferedReader reader = new BufferedReader((new FileReader((file))))) {
                String line;
                while ((line = reader.readLine())!=null){
                    String[] values = line.split(":");
                    if(values.length == 3){
                        Goods goods = new Goods(values[0],values[1],Double.parseDouble(values[2]));
                        this.addGoods(goods);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
