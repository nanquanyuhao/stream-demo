package net.nanquanyuhao.pps;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**s
 * 过程处理器，接受类型为 Integer，处理结果为 String 类型
 */
public class SomeProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {

    /**
     * 声明订阅令牌
     */
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

        System.out.println("=== 执行处理器的 onSubscribe() 方法 ===");
        // 保存订阅关系, 需要用它来给发布者响应
        this.subscription = subscription;
        // 首次订阅 8 条消息
        this.subscription.request(8);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("处理器正在处理该item：" + item);
        if(item < 50) {
            this.submit("-----------消息已被处理器处理：" + item);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 处理者每处理一条消息，就会再订阅 10 条消息
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
        System.out.println("发布者已关闭，处理器已将消息全部消费完毕");
    }
}
