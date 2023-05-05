package exer3;



/**
 * @program: leetcode-0
 * @description:
 * @author: Mr.Yan
 * @create: 2022-07-24 09:17
 **/


import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Signature;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import java.util.*;

import java.util.*;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class Main {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


    }




}




    /*
public class Main {
    private static ReentrantLock lock = new ReentrantLock();
    private static int state = 0;//状态位
    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    static class ThreadA extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {

                try {
                    lock.lock();
                    if (state % 3 == 0) {
                        i++;
                        state++;
                        System.out.println("A");
                    }
                    //System.out.println("ThreadA");
                }finally {//发生异常时不会释放锁，保证释放锁，防止死锁
                    lock.unlock();
                }

            }
            countDownLatch.countDown();
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {

                try {
                    lock.lock();
                    if (state % 3 == 1) {
                        i++;
                        state++;
                        System.out.println("B");
                    }
                    //System.out.println("ThreadB");
                }finally {
                    lock.unlock();
                }

            }
            countDownLatch.countDown();
        }
    }

    static class ThreadC extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {

                try {
                    lock.lock();
                    if (state % 3 == 2) {
                        i++;
                        state++;
                        System.out.println("C");
                    }
                    //System.out.println("ThreadC");
                }finally {
                    lock.unlock();
                }

            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ) {
                    try {
                        lock.lock();
                        if (state%3 == 0) {
                            System.out.println(Thread.currentThread().getName());
                            i++;
                            state++;
                        }
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ) {
                    try {
                        lock.lock();
                        if (state % 3 == 1) {
                            System.out.println(Thread.currentThread().getName());
                            i++;
                            state++;
                        }
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ) {
                    try {
                        lock.lock();
                        if (state%3 == 2) {
                            System.out.println(Thread.currentThread().getName());
                            i++;
                            state++;
                        }
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }, "C").start();

    }


}

*/












/*
public class Singleton {

    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public  static Singleton getUniqueInstance() {
       //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
 */



/*
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        final Queue<Integer> sharedQueue = new LinkedList<>();
        Thread producer = new Producer(sharedQueue);
        Thread consumer = new Consumer(sharedQueue);
        producer.start();
        consumer.start();
    }

    static class Producer extends Thread {

        private static final int MAX_QUEUE_SIZE = 5;

        private  final  Queue sharedQueue;

        public Producer(Queue<Integer> sharedQueue) {
            super();
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                synchronized (sharedQueue){
                    while(sharedQueue.size() >= MAX_QUEUE_SIZE){
                        System.out.println("队列满了，等待消费");
                        try{
                           // wait方法调用后立刻释放对象锁
                            sharedQueue.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    sharedQueue.add(i);
                    System.out.println("进行生产："+i);
                    sharedQueue.notify();
                }
            }
        }
    }
    static class Consumer extends Thread {


        private  final  Queue sharedQueue;

        public Consumer(Queue<Integer> sharedQueue) {
            super();
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
             while (true){
                 synchronized (sharedQueue){
                     while(sharedQueue.size()==0){
                         try{
                             System.out.println("队列空了，等待生产");
                             // wait方法调用后立刻释放对象锁
                             sharedQueue.wait();
                         }catch(InterruptedException e){
                             e.printStackTrace();
                         }
                     }
                     int number = (int) sharedQueue.poll();
                     System.out.println("进行消费 : " + number );
                     sharedQueue.notify();
                 }
             }
        }
    }
}
 */


/*
//堆排序
class Solution {
    public int[] sortArray(int[] nums) {
        //建立大根堆
        buildMaxHeap(nums);

        for(int i = nums.length-1; i>=1; i--) {
            //交换大根堆的堆顶元素和堆底元素
            swap(nums, 0, i);
            //将新堆顶元素向下调整
            adjustDown(nums, 0, i-1);
        }
        return nums;
    }


    public void buildMaxHeap(int[] nums) {
        int n = nums.length;
        for(int i = (n-1)/2; i>=0; i--) {
            adjustDown(nums, i, n-1);
        }
    }

    //大根堆，向下调整
    public void adjustDown(int[] nums, int i, int last) {
        while(2*i+1 <= last) {
            int j = 2*i+1;
            if(j+1<=last && nums[j] < nums[j+1]) {
                j+=1;
            }
            if(nums[i] < nums[j]) {
                swap(nums, i, j);
            }else{
                break;
            }
            i = j;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

 */


/*
//快排
class Solution {
    Random random = new Random();
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    public void quickSort(int[] nums, int l, int r) {
        if(l>=r) return;
        int index = partition(nums, l, r);
        quickSort(nums, l, index-1);
        quickSort(nums, index+1, r);
    }

    public int partition(int[] nums, int l, int r) {
        int ind = random.nextInt(r-l+1) + l;
        swap(nums, l, ind);
        int left = l+1, right = r;
        while(true) {
            while(left <= r && nums[left] <= nums[l]) {
                left++;
            }
            while(right >= l && nums[right] > nums[l]) {
                right--;
            }
            if(left >= right) break;
            swap(nums, left, right);
            left++;
            right--;
        }
        swap(nums, l, right);
        return right;
    }
    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
 */

/*
//归并
class Solution {
    int[] tmp;

    public int[] sortArray(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        int i = l, j = mid + 1;
        int cnt = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                tmp[cnt++] = nums[i++];
            } else {
                tmp[cnt++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[cnt++] = nums[i++];
        }
        while (j <= r) {
            tmp[cnt++] = nums[j++];
        }
        for (int k = 0; k < r - l + 1; ++k) {
            nums[k + l] = tmp[k];
        }
    }
}


 */


/*
//插入排序
public class Solution {

    // 插入排序：稳定排序，在接近有序的情况下，表现优异

    public int[] sortArray(int[] nums) {
        int len = nums.length;
        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
        for (int i = 1; i < len; i++) {
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;
            // 注意边界 j > 0
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }
}


 */

/*

//树状数组
class NumArray {
    int[] tree;
    int[] nums;
    int lowbit(int x) {
        return x&-x;
    }
    void add(int index, int x) {
        for(int i = index+1; i < tree.length; i+=lowbit(i)) {
            tree[i] += x;
        }
    }
    int getSum(int index) {
        int sum = 0;
        for(int i = index+1; i>0; i-=lowbit(i)) {
            sum += tree[i];
        }
        return sum;
    }
    public NumArray(int[] nums) {
        int n = nums.length;
        tree = new int[n+1];
        for(int i = 0; i < n; i++) {
            add(i, nums[i]);
        }
        this.nums = nums;
    }

    public void update(int index, int val) {
        add(index, val-nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return getSum(right) - getSum(left-1);
    }
}
*/








//线段树
//class MyCalendarTwo {
//    class Node{
//        int value;
//        int lazy;
//        Node lchild, rchild;
//    }
//    public void update(Node node, int left, int right, int start, int end, int value) {
//        if(start <= left && right <= end) {
//            node.value += value;
//            node.lazy += value;
//            return;
//        }
//        pushdown(node);
//        int mid = left+right >> 1;
//        if(start <= mid) {
//            update(node.lchild, left, mid, start, end, value);
//        }
//        if(mid+1 <= end) {
//            update(node.rchild, mid+1, right, start, end, value);
//        }
//        pushup(node);
//    }
//    public int query(Node node, int left, int right, int start, int end) {
//        if(start <= left && right <= end) {
//            return node.value;
//        }
//        pushdown(node);
//        int ans = 0;
//        int mid = left+right >> 1;
//        if(start <= mid) {
//            ans = query(node.lchild, left, mid, start, end);
//        }
//        if(mid+1 <= end) {
//            ans = Math.max(ans, query(node.rchild, mid+1, right, start, end));
//        }
//        return ans;
//    }
//    public void pushdown(Node node) {
//        if(node.lchild == null) {
//            node.lchild = new Node();
//        }
//        if(node.rchild == null) {
//            node.rchild = new Node();
//        }
//        node.lchild.lazy += node.lazy;
//        node.rchild.lazy += node.lazy;
//        node.lchild.value += node.lazy;
//        node.rchild.value += node.lazy;
//        node.lazy = 0;
//    }
//    public void pushup(Node node) {
//        node.value = Math.max(node.lchild.value, node.rchild.value);
//    }
//    Node root;
//    int max = (int)(1e9+7);
//    public MyCalendarTwo() {
//        root = new Node();
//    }
//
//    public boolean book(int start, int end) {
//        int temp = query(root, 0, max, start, end-1);
//        if(temp == 2) {
//            return false;
//        }
//        update(root, 0, max, start, end-1, 1);
//        return true;
//    }
//}




