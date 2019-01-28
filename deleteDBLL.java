public class deleteDBLL {

    static class DoubleListNode{
        int val;
        DoubleListNode prev, next;
        public DoubleListNode(int x){
            val = x;
        }
    }

    public static DoubleListNode delete(DoubleListNode head, DoubleListNode target){
        if(head == null || target == null){
            return null;
        }
        DoubleListNode dummy = new DoubleListNode(0);
        dummy.next = head;
        head.prev = dummy;
        DoubleListNode cur = head;
        while(cur.val != target.val){
            cur = cur.next;
        }
        if(cur.val == head.val && cur.next != null){
            dummy.next = head.next;
            head.next.prev = dummy;
        }else if(cur.val == head.val){
            dummy.next = null;
        }
        if(cur.next == null){
            cur.prev.next = null;
        }else{
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        DoubleListNode head = new DoubleListNode(0);
        DoubleListNode two = new DoubleListNode(1);
        DoubleListNode three = new DoubleListNode(2);
        DoubleListNode four = new DoubleListNode(3);
        head.prev = null;
        head.next = two;
        two.prev = head;
        two.next = three;
        three.prev = two;
        three.next = four;
        four.prev = three;
        four.next = null;
        DoubleListNode t = new DoubleListNode(3);
        DoubleListNode res = delete(head, t);
        while(res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }

}
