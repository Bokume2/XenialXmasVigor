package com.xxvlang.data;

import java.util.ArrayDeque;
import java.util.Collection;

import com.xxvlang.exception.*;

public class XXVStack {
    
    private ArrayDeque<XXVInt> content;

    public XXVInt pop() throws XXVException {
        if(this.isEmpty()) throw new XXVException(XXVExceptionType.STACK_EMPTY);
        return this.content.pop();
    }

    public void push(XXVInt value) {
        this.content.push(value);
    }

    public void push(int intValue) throws XXVException {
        this.content.push(new XXVInt(intValue));
    }

    public void dup(int cnt) {
        XXVInt top = this.content.peek();
        for (int i = 0; i < cnt; i++) {
            this.push(top);
        }
    }

    public void swap() throws XXVException {
        XXVInt first = this.pop();
        XXVInt second = this.pop();
        this.push(first);
        this.push(second);
    }

    public void floatToTop(int depth) throws XXVException {
        XXVStack tmpStack = new XXVStack();
        for (int i = 0; i < depth - 1; i++) {
            tmpStack.push(this.pop());
        }
        XXVInt nextTop = this.pop();
        while (!tmpStack.isEmpty()) {
            this.push(tmpStack.pop());
        }
        this.push(nextTop);
    }

    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    public ArrayDeque<XXVInt> getContent() {
        return this.content.clone();
    }

    public int getContentSize() {
        return this.content.size();
    }

    public void setContent(Collection<XXVInt> stack) {
        for(XXVInt i : stack) {
            this.content.addLast(i);
        }
    }

    public XXVStack copy() {
        return new XXVStack(this.content);
    }

    public XXVStack(Collection<XXVInt> stack) {
        this();
        this.setContent(stack);
    }

    public XXVStack() {
        this.content = new ArrayDeque<>();
    }

}
