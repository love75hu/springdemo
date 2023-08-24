package cn.mediinfo.springdemo.controller.javacore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 接口与回调
 * ActionListener 可以在各种场景下使用，尤其是用于处理用户界面上的交互性操作。
 */

public class CallbackSimple implements ActionListener {
    public CallbackSimple()
    {
        //1.调用ActionListener
        var listener =new CallbackSimple();
        var timer=new Timer(2000,listener);
        timer.start();

        //2.使用lamdba实现
        var timer2=new Timer(2000,event->{
            System.out.println("回调函数执行啦2！");
        });
        timer2.start();


        //3.使用方法引用实现
        var timer3=new Timer(2000, System.out::println);
        timer3.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("回调函数执行啦！");
    }
}
