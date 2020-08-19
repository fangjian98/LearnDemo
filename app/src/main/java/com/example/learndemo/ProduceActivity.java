package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ProduceActivity extends AppCompatActivity {

    private TextView mProduce;
    private TextView mConsume;
    private String strProduce="";
    private String strConsume="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produce);

        mProduce=findViewById(R.id.tvProduce);
        mConsume=findViewById(R.id.tvConsume);

        // 创建资源对象
        Res res = new Res();

        // 创建生产者任务
        ProduceRunnable produceRunnable = new ProduceRunnable(res);

        // 创建消费者任务
        ConsumeRunnable consumeRunnable = new ConsumeRunnable(res);

        // 启动生产者任务
        new Thread(produceRunnable).start();

        // 启动消费者任务
        new Thread(consumeRunnable).start();
    }

    // 描述资源
    class Res {
        // name 是共享数据，被Thread-0 Thread-1公用使用
        private String name;

        // id 是共享数据，被Thread-0 Thread-1公用使用
        private int id;

        // flag 是共享数据，被Thread-0 Thread-1公用使用
        private boolean flag; // 定义标记 默认第一次为false

        /**
         * 对操作共享数据的地方加入同步锁的方式来解决安全问题
         * public synchronized(this) void put(String name) {
         */
        public synchronized void put(String name) {

            // 生产之前判断标记
            if (!flag) {

                // 开始生产
                id += 1;
                this.name = name + " 商品编号:" + id;
                System.out.println(Thread.currentThread().getName() + "生产者 生产了：" + this.name);
                strProduce += (Thread.currentThread().getName()+ "生产者 生产了：" + this.name) + "\n";
                // 生产完毕

                // 修改标记
                flag = true;

                /**
                 * 唤醒 wait(); 冻结的线程，如果没有就是空唤醒，Java是支持的
                 */
                notify(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着

                /**
                 * 当前自己线程 冻结，释放CPU执行资格，释放CPU执行权，CPU就会去执行其他线程了
                 */
                try {
                    wait(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 对操作共享数据的地方加入同步锁的方式来解决安全问题
         * public synchronized(this) void put(String name) {
         */
        public synchronized void out() {

            // 消费之前判断标记
            if (flag) {

                // 开始消费
                System.out.println(Thread.currentThread().getName() +  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> 消费者 消费了：" + this.name);
                strConsume += (Thread.currentThread().getName() +  "消费者 消费了：" + this.name) + "\n";
                // 消费完毕

                // 修改标记
                flag = false;

                /**
                 * 唤醒 wait(); 冻结的线程，如果没有就是空唤醒，Java是支持的
                 */
                notify(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着

                /**
                 * 当前自己线程 冻结，释放CPU执行资格，释放CPU执行权，CPU就会去执行其他线程了
                 */
                try {
                    wait(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 描述生产者任务
     */
    class ProduceRunnable implements Runnable {

        /**
         * 此变量已经不是共享数据了，因为：
         *              new Thread(produceRunnable).start();
         *              new Thread(consumeRunnable).start();
         *
         * 所以：Thread-0有自己的res     Thread-1也有自己的res
         */
        private Res res;

        ProduceRunnable(Res res) {
            this.res = res;
        }

        // 执行线程任务
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                res.put("面包🍞");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 1;  //消息(一个整型值)
                mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
            }
        }
    }

    /**
     * 描述消费者任务
     */
    class ConsumeRunnable implements Runnable {

        /**
         * 此变量已经不是共享数据了，因为：
         *              new Thread(produceRunnable).start();
         *              new Thread(consumeRunnable).start();
         *
         * 所以：Thread-0有自己的res     Thread-1也有自己的res
         */
        private Res res;

        ConsumeRunnable(Res res) {
            this.res = res;
        }

        // 执行线程任务
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                res.out();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 2;  //消息(一个整型值)
                mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
            }
        }
    }

    //在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mProduce.setText(strProduce);
                    break;
                case 2:
                    mConsume.setText(strConsume);
                    break;
                default:
                    break;
            }
        }
    };

}
