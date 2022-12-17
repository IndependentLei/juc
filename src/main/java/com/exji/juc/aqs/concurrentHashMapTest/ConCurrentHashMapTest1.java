package com.exji.juc.aqs.concurrentHashMapTest;

public class ConCurrentHashMapTest1 {
    public static void main(String[] args) {
        /**
         * 默认为0
         * 当初始化时候，为1
         * 当扩容时候，为-（1+扩容线程数）
         * 当初始化或扩容完成后，为下一次的扩容的阈值大小
         *
         * private transient volatile int sizeCtl
         *
         * 整个 ConcrrentHashMap 就是一个Node[]
         * static class Node<K,V> implements Map.Entry<K,V> {}
         *
         * Hash 表
         * transient volatile Node<K,V>[] table
         *
         * 扩容时的 新hash表
         * private transient volatile Node<K,V>[] nextTable
         *
         * 扩容时如果某个 bin 迁移完毕，用 ForwardingNode 作为旧 table bin 的头节点
         * static final class ForwardingNode<K,V> extends Node<K,V>{}
         *
         * 用 在 compute 以及 computeIfAbsent 时，用来占位,计算完成后替换为普通Node
         * static final class ReservationNode<K,V> extends Node<K,V> {}
         *
         * 作为 treebin 的头节点，存储 root 和 first
         * static final class TreeBin<K,V> extends Node<K,V>
         *
         * 作为treebin的节点，存储parent，left，right
         * static final class TreeNode<K,V> extends Node<K,V>{}
         */
    }
}
