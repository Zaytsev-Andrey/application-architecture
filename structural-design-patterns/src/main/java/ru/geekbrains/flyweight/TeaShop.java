package ru.geekbrains.flyweight;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TeaShop {

    private Map<Integer, KarakTea> orders = new LinkedHashMap<>();

    private TeaMaker teaMaker;

    public TeaShop(TeaMaker teaMaker) {
        this.teaMaker = teaMaker;
    }

    public void takeOrder(String teaType, int table) {
        orders.put(table, teaMaker.make(teaType));
    }

    public void serve() {
        orders.keySet().forEach(table -> System.out.println("Serving tea to table# " + table));
    }
}
