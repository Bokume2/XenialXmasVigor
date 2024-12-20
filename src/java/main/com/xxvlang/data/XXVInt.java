package com.xxvlang.data;

import java.util.Arrays;

import com.xxvlang.exception.*;

public class XXVInt {

    public static final int MAX_VALUE = 244140624;  //25^6 - 1
    public static final int MIN_VALUE = -MAX_VALUE;

    public static final int DIGITS_NUM = 6;
    
    private int intValue;
    private byte[] digits;

    public XXVInt add(XXVInt op, boolean canOverflow) throws XXVException {
        return new XXVInt(this.intValue + op.intValue(),canOverflow);
    }

    public XXVInt add(XXVInt op) throws XXVException {
        return add(op,false);
    }

    public XXVInt subtract(XXVInt op, boolean canOverflow) throws XXVException {
        return new XXVInt(this.intValue - op.intValue(),canOverflow);
    }

    public XXVInt subtract(XXVInt op) throws XXVException {
        return subtract(op,false);
    }

    public XXVInt multiply(XXVInt op, boolean canOverflow, boolean quickCompute)
        throws XXVException
    {
        if (!quickCompute) {
            XXVInt result = new XXVInt(0);
            int opValue = op.intValue;
            for (int i = 1; i < opValue; i++) {
                result = result.add(this,canOverflow);
            }
            return result;
        } else {
            return new XXVInt(this.intValue * op.intValue());
        }
    }
    
    public XXVInt multiply(XXVInt op, boolean canOverflow) throws XXVException {
        return multiply(op,canOverflow,false);
    }

    public XXVInt multiply(XXVInt op) throws XXVException {
        return multiply(op,false);
    }

    public XXVInt divide(XXVInt op, boolean canOverflow)
        throws XXVException, ArithmeticException
    {
        if (op.intValue == 0) throw new ArithmeticException(XXVException.MESSAGE_ZERO_DEVIDE);
        return new XXVInt(this.intValue / op.intValue(),canOverflow);
    }

    public XXVInt divide(XXVInt op)
        throws XXVException, ArithmeticException
    {
        return divide(op,false);
    }

    public XXVInt modulo(XXVInt op, boolean canOverflow) throws XXVException {
        return new XXVInt(this.intValue % op.intValue(),canOverflow);
    }

    public XXVInt modulo(XXVInt op) throws XXVException {
        return modulo(op,false);
    }

    public XXVInt exponentiate(XXVInt op, boolean canOverflow, boolean quickCompute)
        throws XXVException
    {
        if (!quickCompute) {
            XXVInt result = new XXVInt(1);
            int opValue = op.intValue();
            for (int i = 0; i < opValue; i++) {
                result = result.multiply(this,canOverflow);
            }
            return result;
        } else {
            int opValue = op.intValue(), result = 1;
            for (int i = 0; i < opValue; i++) {
                result *= this.intValue;
            }
            return new XXVInt(result,canOverflow);
        }
    }
    
    public XXVInt exponentiate(XXVInt op, boolean canOverflow) throws XXVException {
        return exponentiate(op,canOverflow,false);
    }

    public XXVInt exponentiate(XXVInt op) throws XXVException {
        return exponentiate(op,false);
    }

    public XXVInt shift(int length) throws XXVException {
        if (length >= DIGITS_NUM || length <= -DIGITS_NUM)
            throw new XXVException(XXVExceptionType.ILLEGAL_SHIFT_LENGTH);
        if (length == 0) return this;
        byte[] resultDigits = new byte[DIGITS_NUM];
        for (int i = 0; i < DIGITS_NUM - 1; i++) {
            if (i - length >= 0 && i - length < DIGITS_NUM) {
                resultDigits[i - length] = this.digits[i];
            }
        }
        return new XXVInt(resultDigits);
    }

    public XXVInt negate() {
        XXVInt result = null;
        try {
            result = new XXVInt(-this.intValue);
        } catch(XXVException xe) {
            // never thrown exception
        }
        return result;
    }

    public int intValue() {
        return this.intValue;
    }

    public char charValue() throws XXVException {
        if (this.intValue > Character.MAX_VALUE)
            throw new XXVException(XXVExceptionType.LARGE_FOR_CHAR);
        return (char)this.intValue;
    }

    public byte[] getDigits() {
        return Arrays.copyOf(this.digits,this.digits.length);
    }

    public boolean isNegative() {
        return this.intValue < 0;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        if (this.isNegative()) result.append('-');
        boolean hasAppeared = false;
        for (byte digit : this.digits) {
            if (hasAppeared && digit == 0) {
                result.append('Z');
            } else if(digit != 0) {
                result.append((char)(digit + 'A' - 1));
                hasAppeared = true;
            }
        }
        if (!hasAppeared) result.append('Z');
        return result.toString();
    }
    
    public XXVInt(int intValue, boolean canOverflow) throws XXVException {
        if (intValue > MAX_VALUE || intValue < MIN_VALUE) {
            if (canOverflow) {
                while (intValue > MAX_VALUE) {
                    intValue -= MAX_VALUE + 1;
                }
                while (intValue < MIN_VALUE) {
                    intValue += MAX_VALUE + 1;
                }
                if (intValue >= 0) {
                    intValue = MIN_VALUE + intValue;
                } else {
                    intValue = MAX_VALUE - intValue;
                }
            } else {
                throw new XXVException(XXVExceptionType.OVERFLOW);
            }
        }
        this.intValue = intValue;
        if (intValue < 0) intValue = -intValue;
        this.digits = new byte[DIGITS_NUM];
        for (int i = DIGITS_NUM - 1; i >= 0; i--) {
            this.digits[i] = (byte)(intValue % 25);
            intValue /= 25;
        }
    }

    public XXVInt(String strDigits) throws XXVException {
        if (strDigits.length() > DIGITS_NUM) {
            throw new XXVException(XXVExceptionType.TOO_MUCH_STR_DIGITS);
        }
        char[] charDigits = strDigits.toUpperCase().toCharArray();
        for (int i = DIGITS_NUM - charDigits.length; i < DIGITS_NUM; i++) {
            if (charDigits[i] == 'Z') {
                this.digits[i] = 0;
            } else if (charDigits[i] >= 'A' && charDigits[i] <= 'X') {
                this.digits[i] = (byte)(charDigits[i] - 'A' + 1);
            } else {
                throw new XXVException(XXVExceptionType.ILLEGAL_STR_DIGIT);
            }
        }
    }

    public XXVInt(byte[] digits) throws XXVException {
        if (digits.length > DIGITS_NUM) {
            throw new XXVException(XXVExceptionType.TOO_MUCH_DIGITS);
        }
        for (int i = DIGITS_NUM - digits.length; i < DIGITS_NUM; i++) {
            if (digits[i] < 25) {
                this.digits[i] = digits[i];
            } else {
                throw new XXVException(XXVExceptionType.ILLEGAL_DIGIT);
            }
        }
    }

    public XXVInt(int intValue) throws XXVException {
        this(intValue, false);
    }

}
