package com.xxvlang.data;

import java.util.Arrays;

import com.xxvlang.exception.*;

public class XXVTrees {

    private boolean[] flags;
    private XXVStack[] stacks;

    private int pc;

    public void connect(int srcIndex, int destIndex, boolean sameOrder) {
        try {
            if (!sameOrder) {
                move(srcIndex,destIndex,stacks[srcIndex].getContentSize());
            } else {
                XXVStack tmp = new XXVStack();
                while (!stacks[srcIndex].isEmpty()) {
                    tmp.push(stacks[srcIndex].pop());
                }
                while (!tmp.isEmpty()) {
                    stacks[destIndex].push(tmp.pop());
                }
            }
        } catch(XXVException xe) {
            //already checked
        }
    }

    public void move(int srcIndex, int destIndex, int cnt) throws XXVException {
        for (int i = 0; i < cnt; i++) {
            stacks[destIndex].push(stacks[srcIndex].pop());
        }
    }

    public void sizePush(int index) throws XXVException {
        stacks[index].push(stacks[index].getContentSize());
    }

    public void next() {
        this.pc++;
    }

    public void previous() {
        this.pc--;
    }

    public void skip(int length) {
        this.pc += length;
    }

    public void back(int length) {
        this.pc -= length;
    }

    public XXVInt popStack(int index) throws XXVException {
        return this.stacks[index].pop();
    }

    public void pushStack(XXVInt value, int index){
        this.stacks[index].push(value);
    }

    public void pushStack(int value, int index) throws XXVException {
        this.pushStack(new XXVInt(value),index);
    }

    public void dupStack(int cnt, int index) {
        this.stacks[index].dup(cnt);
    }

    public void swapStack(int index) throws XXVException {
        this.stacks[index].swap();
    }

    public void rotateStack(int depth, int index) throws XXVException {
        this.stacks[index].rotate(depth);
    }

    public boolean stackIsEmpty(int index) {
        return this.stacks[index].isEmpty();
    }

    public int getStackSize(int index) {
        return this.stacks[index].getContentSize();
    }

    public void reverseFlag(int index) {
        this.flags[index] = !this.flags[index];
    }

    public void setStack(XXVStack stack, int index) {
        this.stacks[index] = stack.copy();
    }

    public void setPC(int value) {
        this.pc = value;
    }

    public boolean getFlag(int index) {
        return this.flags[index];
    }

    public XXVStack getStack(int index) {
        if (this.stacks[index] == null) this.stacks[index] = new XXVStack();
        return this.stacks[index].copy();
    }

    public boolean[] getFlags() {
        return Arrays.copyOf(this.flags,this.flags.length);
    }

    public XXVStack[] getStacks() {
        return Arrays.copyOf(this.stacks,this.stacks.length);
    }

    public int getPC() {
        return this.pc;
    }

    public XXVTrees() {
        flags = new boolean[25];
        stacks = new XXVStack[25];
        pc = 0;
    }

}
