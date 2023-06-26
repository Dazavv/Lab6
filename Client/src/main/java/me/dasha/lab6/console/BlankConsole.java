package me.dasha.lab6.console;


import me.dasha.lab6.utility.ConsoleColors;

public class BlankConsole implements ReaderWriter{

    @Override
    public Integer readInteger() {
        return null;
    }

    @Override
    public String readLine() {
        return null;
    }

    @Override
    public void write(String text) {

    }

    @Override
    public void write(String text, ConsoleColors consoleColors) {

    }

    @Override
    public void printError(String a) {

    }

    @Override
    public String getValidatedValue(String message) {
        return null;
    }
}
