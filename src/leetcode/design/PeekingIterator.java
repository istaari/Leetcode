package leetcode.design;

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> iterator;
    Integer peekElement;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        this.peekElement = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peekElement == null) {
            peekElement = next();
        }

        return peekElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {

        if (peekElement != null) {
            Integer temp = peekElement;
            peekElement = null;
            return temp;
        } else {
            return iterator.next();
        }

    }


    @Override
    public boolean hasNext() {
      return this.iterator.hasNext() || peekElement != null;
    }

}