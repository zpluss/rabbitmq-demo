package com.example.consumer;

/**
 * @author zhujh
 * @date 2021/5/7
 */
public class Constants {

    /**
     * 第一个配置Queue,Exchange,Key(非注解方式)
     */
    public final static String HORSE_SIMPLE_QUEUE = "HORSE_SIMPLE_QUEUE";
    public final static String HORSE_SIMPLE_EXCHANGE = "HORSE_SIMPLE_EXCHANGE";
    public final static String HORSE_SIMPLE_KEY = "HORSE_SIMPLE_KEY";

    /**
     * 第二个配置Queue,Exchange,Key（注解方式）
     */
    public final static String HORSE_ANNOTATION_QUEUE = "HORSE_ANNOTATION_QUEUE";
    public final static String HORSE_ANNOTATION_EXCHANGE = "HORSE_ANNOTATION_EXCHANGE";
    public final static String HORSE_ANNOTATION_KEY = "HORSE_ANNOTATION_KEY";


    //************************************延时消息队列配置信息**************************
    /**
     * 延时队列信息配置
     */
    public final static String HORSE_DELAY_EXCHANGE = "HORSE_DELAY_EXCHANGE";
    public final static String HORSE_DELAY_QUEUE = "HORSE_DELAY_QUEUE";
    public final static String HORSE_DELAY_KEY = "HORSE_DELAY_KEY";

    /**
     * 死信队列
     */
    public final static String HORSE_DEAD_EXCHANGE = "HORSE_DEAD_EXCHANGE";
    public final static String HORSE_DEAD_QUEUE = "HORSE_DEAD_QUEUE";
    public final static String HORSE_DEAD_KEY = "HORSE_DEAD_KEY";

    //**************************************延时消息队列配置信息(插件版)******************************
    /**
     * 新延时队列信息配置
     */
    public final static String HORSE_PLUGIN_EXCHANGE = "HORSE_PLUGIN_EXCHANGE";
    public final static String HORSE_PLUGIN_QUEUE = "HORSE_PLUGIN_QUEUE";
    public final static String HORSE_PLUGIN_KEY = "HORSE_PLUGIN_KEY";

}
