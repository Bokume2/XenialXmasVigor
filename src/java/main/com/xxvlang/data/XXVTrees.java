package com.xxvlang.data;

import java.util.Arrays;

import com.xxvlang.XXVFlag;
import com.xxvlang.exception.*;

import static com.xxvlang.XXVFlag.*;

public class XXVTrees {

    private boolean[] flags;
    private XXVStack[] stacks;

    private int pc;

    public void connect(int subject, int target) {
        int srcIndex, destIndex;
        if (this.getFlag(MOVE_TARGET_REVERSE)) {
            srcIndex = subject;
            destIndex = target;
        } else {
            srcIndex = target;
            destIndex = subject;
        }
        try {
            if (!this.getFlag(CONNECT_IN_SAME_ORDER)) {
                move(subject,target,_getStack(srcIndex).getContentSize());
            } else {
                XXVStack tmp = new XXVStack();
                while (!_getStack(srcIndex).isEmpty()) {
                    tmp.push(_getStack(srcIndex).pop());
                }
                while (!tmp.isEmpty()) {
                    _getStack(destIndex).push(tmp.pop());
                }
            }
        } catch(XXVException xe) {
            // never thrown exception
        }
    }

    public void move(int subject, int target, int cnt) throws XXVException {
        int srcIndex, destIndex;
        if (this.getFlag(MOVE_TARGET_REVERSE)) {
            srcIndex = subject;
            destIndex = target;
        } else {
            srcIndex = target;
            destIndex = subject;
        }
        for (int i = 0; i < cnt; i++) {
            _getStack(destIndex).push(_getStack(srcIndex).pop());
        }
    }

    public void sizePush(int index) throws XXVException {
        _getStack(index).push(_getStack(index).getContentSize());
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
        return this._getStack(index).pop();
    }

    public void pushStack(XXVInt value, int index) {
        if (this.getFlag(PUSH_NEGATIVE)) {
            this._getStack(index).push(value.negate());
        } else {
            this._getStack(index).push(value);
        }
    }

    public void pushStack(int value, int index) throws XXVException {
        this.pushStack(new XXVInt(value,this.getFlag(CAN_OVERFLOW)),index);
    }

    public void dupStack(int cnt, int index) {
        if (this.getFlag(DUP_IN_RANGE)) {
            int size = this.getStackSize(index);
            XXVInt[] tmpArray = this._getStack(index).getContent().toArray(new XXVInt[size]);
            for (int i = cnt - 1; i >= 0; i--) {
                this.pushStack(tmpArray[i],index);
            }
        } else {
            this._getStack(index).dup(cnt);
        }
    }

    public void swapStack(int index) throws XXVException {
        this._getStack(index).swap();
    }

    public void floatToTopStack(int depth, int index) throws XXVException {
        this._getStack(index).floatToTop(depth);
    }

    public boolean stackIsEmpty(int index) {
        return this._getStack(index).isEmpty();
    }

    public int getStackSize(int index) {
        return this._getStack(index).getContentSize();
    }

    public void reverseFlag(XXVFlag flag) {
        this.reverseFlag(flag.getIndex());
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

    public boolean getFlag(XXVFlag flag) {
        return this.flags[flag.getIndex()];
    }

    public XXVStack getStack(int index) {
        return this._getStack(index).copy();
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

    private XXVStack _getStack(int index) {
        if (this.stacks[index] == null) this.stacks[index] = new XXVStack();
        return this._getStack(index);
    }

    public XXVTrees() {
        flags = new boolean[25];
        stacks = new XXVStack[25];
        pc = 0;
    }

}
