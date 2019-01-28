import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator341 {

    interface NestedInteger{
        int getInteger();
        boolean isInteger();
        List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {
        Stack<Integer> stack;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            buildstack(nestedList);
        }

        private void buildstack(List<NestedInteger> nl){
            for(int i = nl.size() - 1; i >= 0; i--){
                NestedInteger l = nl.get(i);
                if(l.isInteger()){
                    stack.push(l.getInteger());
                }else{
                    buildstack(l.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return stack.pop();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

}
