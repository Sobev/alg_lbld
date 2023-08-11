package org.labuladong.doublepointer;

import org.labuladong.binarytree.common.ListNode;

import java.util.PriorityQueue;

/**
 * @author luojx
 * @date 2022/6/24 16:42
 */
public class DoublePointer {
    public static void main(String[] args) {
        DoublePointer doublePointer = new DoublePointer();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(2);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        doublePointer.partition(listNode, 3);

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 == null) {
            p.next = p2;
        }
        if (p2 == null) {
            p.next = p1;
        }
        return dummy.next;
    }

    /**
     * 单链表分解
     */
    public ListNode partition(ListNode head, int x) {
        // 存放小于 x 的链表的虚拟头结点
        ListNode dummy1 = new ListNode(-1);
        // 存放大于等于 x 的链表的虚拟头结点
        ListNode dummy2 = new ListNode(-1);
        // p1, p2 指针负责生成结果链表
        ListNode p1 = dummy1, p2 = dummy2;
        // p 负责遍历原链表，类似合并两个有序链表的逻辑
        // 这里是将一个链表分解成两个链表
        ListNode p = head;
        while (p != null) {
            //1 4 3 2 5 2
            if (p.val >= x) {
                p2.next = p;
                p2 = p2.next;
            } else {
                p1.next = p;
                p1 = p1.next;
            }
            // 断开原链表中的每个节点的 next 指针
            //temp为新的指针指向p.next的地址
            ListNode temp = p.next;
            //p的next指向为空
            p.next = null;
            p = temp;
        }
        // 连接两个链表
        p1.next = dummy2.next;

        return dummy1.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        ListNode dummy = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(((o1, o2) -> {
            return o1.val - o2.val;
        }));
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        while (!pq.isEmpty()) {
            ListNode poll = pq.poll();
            ListNode temp = poll.next;
            poll.next = null;
            dummy.next = poll;
            dummy = dummy.next;
            if (temp != null) {
                pq.offer(temp);
            }
        }
        return head.next;
    }

    public ListNode findFromEnd(ListNode head, int k){
        ListNode attack = head;
        ListNode realPointer = head;
        for (int i = 0; i < k; i++) {
            attack = attack.next;
        }
        while (attack != null){
            attack = attack.next;
            realPointer = realPointer.next;
        }
        return realPointer;
    }

    /**
     * 移除倒数第n个
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode attack = dummy;
        ListNode real = dummy;
        for (int i = 0; i < n; i++) {
            attack = attack.next;
        }
        while (attack.next != null){
            attack = attack.next;
            real = real.next;
        }
        real.next = real.next.next;
        return dummy.next;
    }

    public boolean hasCycle(ListNode head){
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取环形起点 ------
     *              |   |
     *              ----
    */
    public ListNode detectCycleStart(ListNode head){
        ListNode dummy = new ListNode(-1 ,head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return null;
        }
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 获取相交  /--    1.公共部分长度为x A独有长度为a B独有长度为b，a+x+b == b+x+a
     *      ----
     *          \_____
     *          2.计算连个listnode的长度差 让长度长的先走几步
     *          3.将其中一个和x连成环
    */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2){
            if(p1 == null){
                p1 = headB;
            }else{
                p1 = p1.next;
            }
            if(p2 == null){
                p2 = headA;
            }else{
                p2 = p2.next;
            }
        }
        return p1;
    }
}
