import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: leetcode-0
 * @description:
 * @author: Mr.Yan
 * @create: 2021-08-30 11:27
 **/
public class MainClass {
    private String value;
    public MainClass(){
        this.value = "jjjjbbbb";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void method1(){
        System.out.println("value = " + value);
    }

    @org.junit.Test
    public void test(){
        double feiM = 8.06, yuanM = 10.4, yanM = 11.20;
        double sumM = feiM + yuanM + yanM;
        double sumZ = 12460 + 13350 + 13350;
        double feiZ = feiM/sumM * sumZ;
        double yuanZ = yuanM/sumM * sumZ;
        double yanZ = yanM/sumM * sumZ;
        System.out.println("feiZ = " + feiZ);
        System.out.println("yuanZ = " + yuanZ);
        System.out.println("yanZ = " + yanZ);

        double avg = sumZ/3;
        double feiGet = avg - feiZ;
        double yuanGet = avg - yuanZ;
        double yanGet = avg - yanZ;
        System.out.println("feiGet = " + feiGet);
        System.out.println("yuanGet = " + yuanGet);
        System.out.println("yanGet = " + yanGet);

        double water = 14.5, elec = 44.52, qi = 15.19, room = 75;
        yanGet -= elec;
        yuanGet += (elec)/2;
        feiGet += (elec)/2;

        System.out.println("feiGet = " + feiGet);
        System.out.println("yuanGet = " + yuanGet);
        System.out.println("yanGet = " + yanGet);


        double allget = 6525.79/3;
        double yan = allget + yanGet;
        double fei = allget + feiGet;
        double yuan = allget + yuanGet;
        System.out.println("yan = " + yan);
        System.out.println("fei = " + fei);
        System.out.println("yuan = " + yuan);

        System.out.println("(yan+fei+yuan) = " + (yan + fei + yuan));

        double allPay = (water+qi+room)/3;
//        feiGet-=allPay;
//        yuanGet -= allPay;
//        yanGet -= allPay;
//
//        System.out.println("feiGet = " + feiGet);
//        System.out.println("yuanGet = " + yuanGet);
//        System.out.println("yanGet = " + yanGet);
//

    }

    @org.junit.Test
    public void test1(){
//        String[] strs = {"A2\n" ,
//                "A3\n" ,
//                "A4\n" ,
//                "A5\n" ,
//                "A6\n" ,
//                "A7\n" ,
//                "A8\n" ,
//                "A9\n" ,
//                "A10\n" ,
//                "A11\n" ,
//                "B2\n" ,
//                "B3\n" ,
//                "B4\n" ,
//                "B5\n" ,
//                "B6\n" ,
//                "B7\n" ,
//                "B8\n" ,
//                "B9\n" ,
//                "B10\n" ,
//                "B11\n" ,
//                "C2\n" ,
//                "C3\n" ,
//                "C4\n" ,
//                "C5\n" ,
//                "C6\n" ,
//                "C7\n" ,
//                "C8\n" ,
//                "C9\n" ,
//                "C10\n" ,
//                "C11\n" ,
//                "D2\n" ,
//                "D3\n" ,
//                "D4\n" ,
//                "D5\n" ,
//                "D6\n" ,
//                "D7\n" ,
//                "D8\n" ,
//                "D9\n" ,
//                "D10\n" ,
//                "D11\n" ,
//                "E2\n" ,
//                "E3\n" ,
//                "E4\n" ,
//                "E5\n" ,
//                "E6\n" ,
//                "E7\n" ,
//                "E8\n" ,
//                "E9\n" ,
//                "E10\n" ,
//                "E11\n" ,
//                "F2\n" ,
//                "F3\n" ,
//                "F4\n" ,
//                "F5\n" ,
//                "F6\n" ,
//                "F7\n" ,
//                "F8\n" ,
//                "F9\n" ,
//                "F10\n" ,
//                "F11\n" ,
//                "G2\n" ,
//                "G3\n" ,
//                "G4\n" ,
//                "G5\n" ,
//                "G6\n" ,
//                "G7\n" ,
//                "G8\n" ,
//                "G9\n" ,
//                "G10\n" ,
//                "G11\n" ,
//                "H2\n" ,
//                "H3\n" ,
//                "H4\n" ,
//                "H5\n" ,
//                "H6\n" ,
//                "H7\n" ,
//                "H8\n" ,
//                "H9\n" ,
//                "H10\n" ,
//                "H11"};
//        String[] strs = {"NVP-QAV-572\n" ,
//                "Vindoline\n" ,
//                "AZD3458\n" ,
//                "Danicamtiv\n" ,
//                "A-443654\n" ,
//                "DCP-LA\n" ,
//                "Bisindolylmaleimide I\n" ,
//                "Phorbol 12-myristate 13-acetate\n" ,
//                "Chroman 1\n" ,
//                "EX229\n" ,
//                "Plinabulin\n" ,
//                "VER-50589\n" ,
//                "MK8722\n" ,
//                "OTSSP167 (hydrochloride)\n" ,
//                "7-Methoxyisoflavone\n" ,
//                "VTX-27\n" ,
//                "Rigosertib (sodium)\n" ,
//                "Nilotinib (monohydrochloride monohydrate)\n" ,
//                "Pyrintegrin\n" ,
//                "Lexibulin\n" ,
//                "Afuresertib (hydrochloride)\n" ,
//                "Mavacamten\n" ,
//                "Vinblastine (sulfate)\n" ,
//                "Quercetin (dihydrate)\n" ,
//                "H-1152 (dihydrochloride)\n" ,
//                "BRD5648\n" ,
//                "Paclitaxel\n" ,
//                "Asciminib\n" ,
//                "Uprosertib\n" ,
//                "Maytansinol\n" ,
//                "SSE15206\n" ,
//                "BML-284\n" ,
//                "Parbendazole\n" ,
//                "Tirbanibulin\n" ,
//                "Valecobulin (hydrochloride)\n" ,
//                "GSK-25\n" ,
//                "LX7101\n" ,
//                "Torin 2\n" ,
//                "TAS-301\n" ,
//                "SCH-1473759 (hydrochloride)\n" ,
//                "Autophinib\n" ,
//                "HA15\n" ,
//                "S516\n" ,
//                "Dasatinib (hydrochloride)\n" ,
//                "LTURM34\n" ,
//                "HTH-01-015\n" ,
//                "AT-1002 (TFA)\n" ,
//                "Tubulin inhibitor 1\n" ,
//                "Cyclo(-RGDfK)\n" ,
//                "BTS\n" ,
//                "SKL2001\n" ,
//                "Cyclo(RGDyK) (trifluoroacetate)\n" ,
//                "CK-869\n" ,
//                "KY1220\n" ,
//                "TR-14035\n" ,
//                "WAY-262611\n" ,
//                "WZ4003\n" ,
//                "VER-49009\n" ,
//                "Tamoxifen (Citrate)\n" ,
//                "Compound 401\n" ,
//                "Monastrol\n" ,
//                "CZC-8004\n" ,
//                "SF1670\n" ,
//                "CP-466722\n" ,
//                "KDU691\n" ,
//                "PKC-theta inhibitor\n" ,
//                "PF-06409577\n" ,
//                "AZ32\n" ,
//                "Wortmannin\n" ,
//                "IWP-O1\n" ,
//                "iCRT3\n" ,
//                "Danegaptide Hydrochloride\n" ,
//                "CCT251236\n" ,
//                "Retaspimycin (Hydrochloride)\n" ,
//                "DM4\n" ,
//                "HDACs/mTOR Inhibitor 1\n" ,
//                "Cardiogenol C (hydrochloride)\n" ,
//                "K858 (Racemic)\n" ,
//                "YLF-466D\n" ,
//                "Entasobulin"};
        String[] strs = {"PI3K\n" ,
                "Microtubule/Tubulin\n" ,
                "PI3K\n" ,
                "Myosin\n" ,
                "Akt\n" ,
                "PKC\n" ,
                "PKC\n" ,
                "PKC; SPHK\n" ,
                "ROCK\n" ,
                "AMPK\n" ,
                "Microtubule/Tubulin\n" ,
                "Apoptosis; HSP\n" ,
                "AMPK\n" ,
                "MELK\n" ,
                "AMPK\n" ,
                "PKC\n" ,
                "Apoptosis; PI3K; Polo-like Kinase (PLK)\n" ,
                "Autophagy; Bcr-Abl\n" ,
                "Integrin\n" ,
                "Apoptosis; Microtubule/Tubulin; Reactive Oxygen Species\n" ,
                "Akt; PKC; ROCK\n" ,
                "Myosin\n" ,
                "Autophagy; Microtubule/Tubulin\n" ,
                "Apoptosis; PI3K\n" ,
                "ROCK\n" ,
                "GSK-3\n" ,
                "ADC Cytotoxin; Apoptosis; Autophagy; Microtubule/Tubulin\n" ,
                "Bcr-Abl\n" ,
                "Akt\n" ,
                "ADC Cytotoxin; Apoptosis; Microtubule/Tubulin\n" ,
                "Apoptosis; Microtubule/Tubulin\n" ,
                "Wnt\n" ,
                "Microtubule/Tubulin; Parasite\n" ,
                "Microtubule/Tubulin; Src\n" ,
                "Microtubule/Tubulin\n" ,
                "Ribosomal S6 Kinase (RSK); ROCK\n" ,
                "ROCK\n" ,
                "Apoptosis; Autophagy; DNA-PK; mTOR\n" ,
                "PKC\n" ,
                "Aurora Kinase\n" ,
                "Autophagy; PI3K\n" ,
                "Apoptosis; Autophagy; HSP\n" ,
                "Microtubule/Tubulin\n" ,
                "Apoptosis; Autophagy; Bcr-Abl; Src\n" ,
                "DNA-PK\n" ,
                "AMPK\n" ,
                "Gap Junction Protein\n" ,
                "Apoptosis; Microtubule/Tubulin\n" ,
                "Integrin\n" ,
                "Myosin\n" ,
                "Wnt; β-catenin\n" ,
                "Integrin\n" ,
                "Arp2/3 Complex\n" ,
                "Wnt; β-catenin\n" ,
                "Integrin\n" ,
                "β-catenin\n" ,
                "AMPK\n" ,
                "HSP\n" ,
                "Apoptosis; Autophagy; Estrogen Receptor/ERR; HSP\n" ,
                "DNA-PK\n" ,
                "Apoptosis; Kinesin\n" ,
                "Bcr-Abl\n" ,
                "Autophagy; Phosphatase; PTEN\n" ,
                "ATM/ATR\n" ,
                "Parasite; PI4K\n" ,
                "PKC\n" ,
                "AMPK\n" ,
                "ATM/ATR\n" ,
                "Antibiotic; Autophagy; PI3K; Polo-like Kinase (PLK)\n" ,
                "Porcupine; Wnt\n" ,
                "Apoptosis; Wnt\n" ,
                "Gap Junction Protein\n" ,
                "HSP\n" ,
                "HSP\n" ,
                "Microtubule/Tubulin\n" ,
                "Apoptosis; HDAC; mTOR\n" ,
                "Wnt; β-catenin\n" ,
                "Apoptosis; Kinesin\n" ,
                "AMPK\n" ,
                "Microtubule/Tubulin"};
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(strs[i + j * 10]);
            }
        }

    }


    @org.junit.Test
    public void test3(){
        int res = -5;
        res >>>= 2;
        System.out.println("res" + res);
    }
    @org.junit.Test
    public void test4() {
        Stack<Integer> temp = new Stack<>();
        System.out.println("temp.getClass() = " + temp.getClass());
        System.out.println("temp.toString() = " + temp.toString());
    }



    @org.junit.Test
    public void test5(){
        String str = "1:2:::";
        String[] strs = str.split(":");
        System.out.println("strs.length = " + strs.length);
    }


    @org.junit.Test
    public void test6() throws Exception {
        Class<?> test = ClassLoader.getSystemClassLoader().loadClass("Test");
        MainClass o = (MainClass)test.newInstance();
        Method method1 = test.getDeclaredMethod("method1");
        method1.invoke(o);
        Field field = test.getDeclaredField("value");
        field.setAccessible(true);
        field.set(o, "gggggbbbbb");
        method1 = test.getDeclaredMethod("method1");
        method1.invoke(o);


        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            Future task = threadPoolExecutor.submit(new Callable<String>(){

                @Override
                public String call() throws Exception {
                    return Thread.currentThread().getName();
                }
            });
            System.out.println(task.get());
        }
    }

    @org.junit.Test
    public void test7() throws Exception {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(50, 200, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < 200; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        semaphore.acquire();

                        System.out.println(Thread.currentThread().getName());

                        semaphore.release();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        poolExecutor.shutdown();


        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    }

    @org.junit.Test
    public void test8() throws Exception{
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello");
//            }
//        }, 1, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, 0, 1, TimeUnit.SECONDS);
        Thread.sleep(10000);
    }

    @org.junit.Test
    public void test9() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(500);
        ExecutorService threadPool = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 500; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test10();
                        atomicInteger.incrementAndGet();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
        System.out.println("atomicInteger.get() = " + atomicInteger.get());
    }

    public void test10() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(1000);
    }

    @org.junit.Test
    public void test11() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 200, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        test10();
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        countDownLatch.countDown();
        Thread.sleep(1000);
    }


    @org.junit.Test
    public void test12() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ready");
                try {
                    barrier.await();
                    test10();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ready");
                try {
                    barrier.await();
                    test10();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("ready");
        barrier.await();
        Thread.sleep(1000);
        System.out.println("1 = " + 1);

    }



    class Person{
        String id;
        Person(){}
        Person(String id) {
            this.id = id;
        }
    }
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->new Integer(10));
    @org.junit.Test
    public void test13() throws InterruptedException {
        test14();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "-threadlocal: " + threadLocal.get());
        threadLocal.set(2);
        System.out.println(Thread.currentThread().getName() + "-threadlocal: " + threadLocal.get());
    }

    public void test14() {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "-threadlocal: " + threadLocal.get());
            threadLocal.set(1);
            System.out.println(Thread.currentThread().getName() + "-threadlocal: " + threadLocal.get());
        }, "jj").start();

    }
}


//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 生产者
// */
//public class Producer implements Runnable {
//    //该字段用于做是否循环的字段  这里使用volatile保证可见性和防止指令重排
//    private volatile boolean isRunning = true;
//    //线程休眠时间，防止线程调用速度太快
//    private final static int SLEEP_TIME = 1000;
//    //包子阻塞队列，用来存储生产的包子
//    private BlockingQueue<String> bumQueue;
//    //包子序号
//    private static AtomicInteger serial = new AtomicInteger();
//
//    /**
//     * 构造方法
//     * 参数为：包子队列
//     */
//    public Producer(BlockingQueue<String> bumQueue) {
//        this.bumQueue = bumQueue;
//    }
//
//
//    /**
//     * 重写run方法
//     */
//    @Override
//    public void run() {
//        //当isRunning=true的时候，循环进行生产
//        while (isRunning) {
//            try {
//                /**
//                 * boolean offer(E e, long timeout, TimeUnit unit) 若2秒还没有加入，则代表队列阻塞，这时候打印加入队列失败,否则则加入队列
//                 */
//                if (!bumQueue.offer("包子" + serial.getAndAdd(1), 2, TimeUnit.SECONDS)) {
//                    System.out.println("队列的数量为：" + bumQueue.size() + "，无法加入队列");
//                } else {
//                    System.out.println("【生产者】" + Thread.currentThread().getName() + "生产包子" + serial.get());
//                }
//                //线程休眠
//                Thread.sleep(SLEEP_TIME);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//
//
//    public void stopProduct() {
//        isRunning = false;
//    }
//}
//
//
//public class Consumer implements Runnable {
//    //包子队列
//    private BlockingQueue<String> bumQueue;
//    //是否支持消费
//    private volatile boolean isConsumer=true;
//
//    //构造方法
//    public Consumer(BlockingQueue<String> bumQueue) {
//        this.bumQueue = bumQueue;
//    }
//
//    @Override
//    public void run() {
//
//        while(isConsumer){
//            //从队列中获取包子
//            String bum = bumQueue.poll();
//
//            //若包子存在，则消费  若不存在则提示没有包子消费
//            if (bum != null) {
//                System.out.println("【消费者】" + Thread.currentThread().getName() + "消费" + bum);
//            }else{
//                System.out.println("包子数量剩余"+bumQueue.size()+"，没有包子可以消费");
//            }
//            //此处让线程休眠一下，防止消费太快
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void stopConsumer(){
//        isConsumer=false;
//    }
//}