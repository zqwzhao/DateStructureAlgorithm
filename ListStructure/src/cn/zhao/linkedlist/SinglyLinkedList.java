package cn.zhao.linkedlist;

/**
 * 单向非循环链表
 * 单链表的插入、删除、查找操作；
 * 链表中存储的是int类型的数据；
 *
 * 如果链表为空时，代码是否能正常工作？
 * 如果链表只包含一个结点时，代码是否能正常工作？
 * 如果链表只包含两个结点时，代码是否能正常工作？
 * 代码逻辑在处理头结点和尾结点的时候，是否能正常工作？
 * @author zhaoqw
 * @date 2022/06/10
 */
public class SinglyLinkedList {
  private Node head = null;

  public static class Node {
    private int data;
    private Node next;

    public Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }

    public int getData() {
      return data;
    }

    public void setData(int data) {
      this.data = data;
    }
  }

  public static Node createNode(int value) {
    return new Node(value, null);
  }


  /**
   * 根据index查找链表中的元素
   * @param index 索引
   * @return
   */
  public Node findByIndex(int index) {
    Node p = head;
    int pos = 0;
    while(p != null && pos != index) {
      p = p.next;
      ++ pos;
    }
    return p;
  }

  public Node findByValue(int value) {
    Node p = head;
    while(p != null && p.data != value) {
        p = p.next;
    }
      return p;
  }

  /**
   * 无头结点
   * 表头部插入
   * 这种操作将于输入的顺序相反，逆序
   *
   * @param value 值
   */
  public void insertToHead(int value) {
    Node node = new Node(value, null);
    insertToHead(node);
  }

  // 无头节点
  public void insertToHead(Node newNode) {
    // 空链表，直接作为头
    if (head == null) {
      head = newNode;
    }else {
      newNode.next = head;
      head = newNode;
    }
  }

  //
  // 顺序插入
  //链表尾部插入
  public void insertTail(int value) {
    Node node = new Node(value, null);
    insertTail(node);
  }

  /**
   * 链表尾插入新节点
   *
   * @param newNode
   */
  public void insertTail(Node newNode) {
    if (head == null) {
      head = newNode;
    } else {
      Node p = head;
      while(p.next != null) {
        p = p.next;
      }
      newNode.next = p.next;
      p.next = newNode;
    }
  }

  /**
   * 再 p的后面插入节点
   *
   * @param p 节点p
   * @param value 节点的值
   */
  public void insertAfter(Node p, int value) {
    Node newNode = new Node(value, null);
    insertAfter(p, newNode);
  }

  /**
   * 在 p的后面插入新节点
   * @param p 节点p
   * @param newNode 新节点
   */
  public void insertAfter(Node p, Node newNode) {
    if (p == null) return;

    newNode.next = p.next;
    p.next = newNode;
  }

  /**
   * 在p节点之前插入新节点
   *
   * @param p 节点p
   * @param value 值
   */
  public void insertBefore(Node p, int value) {
    Node newNode = new Node(value, null);
    insertBefore(p, newNode);
  }

  /**
   * 在节点p之前插入新节点
   *
   * @param p 节点p
   * @param newNode 新节点
   */
  public void insertBefore(Node p, Node newNode) {
    if (p == null)  return;
    if (head == p) {
      insertToHead(newNode);
      return;
    }
    // 循环找到q前面的第一个节点
    Node q = head;
    while (q != null && q.next != p) {
      q = q.next;
    }
    if (q == null) return;
    newNode.next = q.next;
    q.next = newNode;
  }


  /**
   * 删除节点
   *
   * @param p 节点p
   */
  public void deleteByNode(Node p) {
    if (p == null || head == null) {
      return;
    }
    if (p == head) {
      head = head.next;
    }

    Node q = head;
    while(q != null && q.next != p) {
      q = q.next;
    }
    q.next = q.next.next;
  }

  /**
   * 删除值为value的第一个节点
   * 删除值为value的所有节点
   *
   * @param value
   */
  public void deleteByValue(int value) {
    if (head == null) {
      return ;
    }
    Node p = head;
    Node q = null;
    while (p != null && p.getData() != value) {
      q = p;
      p = p.next;
    }

    // 没找到
    if (p == null) {
      return ;
    }
    // 头节点就是找到的第一个value
    if (q == null) {
      head = head.next;
    } else {
      q.next = q.next.next;
    }

    // 删除值为value的所有节点
    /*
       if (head != null && head.data == value) {
       head = head.next;
       }
       Node pNode = head;
       while (pNode != null) {
       if (pNode.next.data == data) {
       pNode.next = pNode.next.next;
       continue;
       }
       pNode = pNode.next;
       }
     */

  }

  public void printAll() {
    Node p = head;
    while (p != null) {
      System.out.print(p.data + " ");
      p = p.next;
    }
    System.out.println();
  }

  //判断true or false
  public boolean TFResult(Node left, Node right) {
    Node l  = left;
    Node r = right;

    boolean flag=true;
    System.out.println("left_:"+l.data);
    System.out.println("right_:"+r.data);
    while(l != null && r != null) {
      if (l.data == r.data) {
        l = l.next;
        r = r.next;
      } else {
        flag = false;
        break;
      }
    }
    System.out.println("什么结果！");
    return flag;
  }

  //判断是否为回文
  public boolean palindrome() {
    if (head == null) {
      return false;
    }else {
      System.out.println("开始执行找中间节点! ");
      Node p = head;
      Node q = head;
      if (p.next == null) {
        System.out.println("只有一个元素!");
        return true;
      }

      while (q.next != null && q.next.next !=null) {
        p = p.next;
        q = q.next.next;
      }
      System.out.println("中间节点" + p.getData());
      System.out.println("开始执行奇数节点的回文判断");
      Node leftLink = null;
      Node rightLink = null;
      if (q.next == null) {
        rightLink = p.next;
        leftLink = inverseLinkList(p).next;
        System.out.println("左边第一个节点" + leftLink.getData());
        System.out.println("右边第一个节点" + rightLink.getData());
      } else {
        System.out.println("发现是偶数个节点");
        rightLink = p.next;
        leftLink = inverseLinkList(p);
      }
      return TFResult(leftLink,rightLink);
    }
  }

  //带结点的链表翻转
  public Node inverseLinkList_head(Node p) {
    // Head为新建的头结点
    Node linkHead = new Node(9999, null);
    linkHead.next = p;

    Node cur = p.next;
    p.next = null;
    Node next = null;
    while (cur != null) {
      next = cur.next;
      cur.next = linkHead.next;
      linkHead.next = cur;
      cur = next;
    }
    return linkHead;
  }

  //无头结点的链表翻转
  public Node inverseLinkList(Node p) {
    Node pre = null;
    Node r = head;
    System.out.println("z-------" + r.data);
    Node next = null;
    while (r != p) {
      next = r.next;
      r.next = pre;
      pre = r;
      r = next;
    }
    r.next = pre;
    return r;
  }

  public static void main(String[] args) {
    SinglyLinkedList link = new SinglyLinkedList();
    System.out.println("hello");
    //int data[] = {1};
    //int data[] = {1,2};
    //int data[] = {1,2,3,1};
    //int data[] = {1,2,5};
    //int data[] = {1,2,2,1};
    // int data[] = {1,2,5,2,1};
    int data[] = {1,2,3,5,5,3,2,1};

    for(int i =0; i < data.length; i++){
      //link.insertToHead(data[i]);
      link.insertTail(data[i]);
    }
    // link.printAll();
    // Node p = link.inverseLinkList_head(link.head);
    // while(p != null){
    //     System.out.println("aa"+p.data);
    //     p = p.next;
    // }

    System.out.println("打印原始:");
    link.printAll();
    if (link.palindrome()){
      System.out.println("回文");
    }else{
      System.out.println("不是回文");
    }
  }
}
