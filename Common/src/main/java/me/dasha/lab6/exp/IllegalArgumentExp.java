package me.dasha.lab6.exp;

import java.io.IOException;

/**
 * Exception class for invalid command arguments
 */
public class IllegalArgumentExp extends IOException {

    public IllegalArgumentExp(){}

    public IllegalArgumentExp(String str){
        super(str);
    }
}
