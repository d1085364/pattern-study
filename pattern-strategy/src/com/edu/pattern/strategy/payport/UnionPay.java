package com.edu.pattern.strategy.payport;

import com.edu.pattern.strategy.pay.PayState;

public class UnionPay implements Payment{
    @Override
    public PayState pay(String uid, double amount) {
        System.out.println("欢迎使用银联");
        System.out.println("查询账户余额，开始扣款");
        return new PayState(200,"支付成功",amount);
    }
}
