package com.xxvlang.data;

import java.util.ArrayDeque;
import java.util.Collection;

import com.xxvlang.exception.XXVException;

public class XXVStack {
    
    public static final String MESSAGE_STACK_EMPTY = "Error: Popped from empty stack.";
    
    private ArrayDeque<XXVInt> content;

    public XXVInt pop() throws UnsupportedOperationException {
        if(this.isEmpty()) throw new UnsupportedOperationException(MESSAGE_STACK_EMPTY);
        return this.content.pop();
    }

    public void push(XXVInt value) {
        this.content.push(value);
    }

    public void push(int intValue) throws XXVException {
        this.content.push(new XXVInt(intValue));
    }

    public void dup() {
        this.push(this.content.peek());
    }

    public void swap() {
        XXVInt first = this.pop();
        XXVInt second = this.pop();
        this.push(first);
        this.push(second);
    }

    public void rotate(int depth) {
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
