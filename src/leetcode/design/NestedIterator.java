package leetcode.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

interface NestedInteger {

    boolean isInteger();

    Integer getInteger();

    List<NestedInteger> getList();
}


@SuppressWarnings("all")
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> queue = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
         process(nestedList);
    }

    public void process(List<NestedInteger> nestedList) {

        if (nestedList == null && nestedList.isEmpty()) return;

        for (NestedInteger nestedInteger : nestedList) {

            if (nestedInteger.isInteger()) {
                queue.add(nestedInteger.getInteger());
            } else {
                process(nestedInteger.getList());
            }

        }
    }


    @Override
    public Integer next() {
        return queue.poll();
    }


    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }


}
