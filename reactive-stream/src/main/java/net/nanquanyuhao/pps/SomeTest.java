package net.nanquanyuhao.pps;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class SomeTest {

    public static void main(String[] args) {

        // 创建发布者
        SubmissionPublisher<Integer> publisher = null;
        try {
            publisher = new SubmissionPublisher<>();
            // 创建订阅者
            SomeSubscriber subscriber = new SomeSubscriber();
            // 创建处理器
            SomeProcessor processor = new SomeProcessor();
            // 建立订阅关系，同时触发 subscriber 的 onSubscribe() 方法
            // 设置生产者让处理器订阅，然后消费者订阅处理器即可
            publisher.subscribe(processor);
            processor.subscribe(subscriber);

            // 生产消息
            for (int i = 0; i < 300; i++) {
                // 生成一个 [0,100) 的随机数
                int item = new Random().nextInt(100);
                System.out.println("生产出第" + i + "条消息：" + item);

                // 发布消息，容量256，多了就需要消费掉了才能进，卡在发布方法
                System.out.println("发布开始时间：" + System.nanoTime());
                publisher.submit(item);
                System.out.println("发布结束时间：" + System.nanoTime());
            }
        } finally {
            // 当所有消息发布完毕，关闭发布者
            if (publisher != null) {
                publisher.close();
            }
        }

        System.out.println("主线程开始等待");
        try {
            TimeUnit.SECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
