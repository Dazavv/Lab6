package me.dasha.lab6.forms;

import me.dasha.lab6.collectionClasses.Chapter;
import me.dasha.lab6.console.*;
import me.dasha.lab6.utility.ExecuteFileManager;
import me.dasha.lab6.utility.ReadNameManager;

public class ChapterForm extends Form<Chapter>{
    private final ReaderWriter console;
    private final UserInput scanner;

    public ChapterForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link Chapter}
     * @return объект класса {@link Chapter}
     */
    @Override
    public Chapter build() {
        ReadNameManager readManager = new ReadNameManager(console);
        return new Chapter(
                readManager.readNameCanNotBeNull("Введите имя главы: "),
                readManager.readNameCanBeNull("Введите родительский легион: "),
                readManager.readNameCanBeNull("Введите мир: ")
        );
    }
}

