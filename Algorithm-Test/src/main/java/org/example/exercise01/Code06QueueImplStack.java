package org.example.exercise01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图的宽度优先遍历 队列实现
 * 图的深度优先遍历 栈实现
 *
 * 怎么用栈实现图的深度优先遍历 -> 利用队列实现栈结构
 * @author zhaoqw
 * @date 2022/7/3
 */
public class Code06QueueImplStack {
    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueueStack() {
            this.queue = new LinkedList<T>();
            this.help = new LinkedList<T>();
        }

        public void push(T value) {
            queue.offer(value);
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T res = queue.poll();
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return res;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T res = queue.peek();
            help.offer(queue.poll());
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<Integer>();
        Stack<Integer> test = new Stack<Integer>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
