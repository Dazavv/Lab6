package me.dasha.lab6.utility;

import me.dasha.lab6.console.*;
import me.dasha.lab6.exp.FileModeExp;

/**
 * The class is responsible for what the user enters
 */
public class ReadNameManager implements Readable {
    private final ReaderWriter console;
    private final UserInput scanner;

    public ReadNameManager(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * method checks if the name is entered correctly, it contains only letters or not
     *
     * @return name
     */
    @Override
    public String readNameCanNotBeNull(String str) {
        String name;
        while (true) {
            console.write(str);
            name = scanner.nextLine().trim();
            if (name.equals("") || !name.matches("^[a-zA-Z-А-Яа-я]*$")) {
                console.printError("Имя не может быть пустой строкой/иными знаками, кроме букв");
                if (Console.isFileMode()) throw new FileModeExp();
            } else {
                return name;
            }
        }
    }
    @Override
    public String readNameCanBeNull(String str) {
        String name;
        while (true) {
            console.write(str);
            name = scanner.nextLine().trim();
            if (name.matches("^[a-zA-Z-А-Яа-я]*$") || name.equals("")){
                return name;
            } else {
                console.printError("Имя не может быть пустым или иными знаками, кроме букв");
                if (Console.isFileMode()) throw new FileModeExp();
            }
        }
    }
}