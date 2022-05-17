package net.nanquanyuhao.pps;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class SomeSubscriber implements Flow.Subscriber<String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

        System.out.println("=== 执行订阅者的 onSubscribe() 方法 ===");
        // 保存订阅关系, 需要用它来给发布者响应
        this.subscription = subscription;
        // 首次订阅 8 条消息
        this.subscription.request(8);
    }

    @Override
    public void onNext(String item) {

        System.out.println("订阅者正在消费的消息数据为：" + item);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 订阅者每消费一条数据，就会再订阅 10 条数据
        this.subscription.request(10);
    }

    @Override
    public void onError(Throwable throwable) {

        System.out.println(" --- 执行 onError() 方法 --- ");
        throwable.printStackTrace();
        // 取消订阅关系
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("处理器已关闭，订阅者已将消息全部消费完毕");
    }
}
