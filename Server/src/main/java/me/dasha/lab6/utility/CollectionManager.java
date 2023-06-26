package me.dasha.lab6.utility;

import me.dasha.lab6.collectionClasses.Coordinates;
import me.dasha.lab6.collectionClasses.SpaceMarine;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;


/**
 * this class interacts with the collection
 */
public class CollectionManager {

    public CollectionManager() {
    }
    private static Stack<SpaceMarine> stack;
    private static LocalDateTime creationDate;
    static final Logger collectionManagerLogger = Logger.getLogger(CollectionManager.class.getName());

    /**
     * check the existence of the stack
     */
    public static void checkStack() {
        if (stack == null) {
            stack = new Stack<>();
            creationDate = LocalDateTime.now();
        }
    }
    public Stack<SpaceMarine> getCollection() {
        return stack;
    }
    public void validateAll() {
        checkStack();
        for (SpaceMarine spaceMarine : stack) {
            if (!spaceMarine.validate()) {
                collectionManagerLogger.info("SpaceMarine с id=" + spaceMarine.getId() + " имеет невалидные поля.");
                System.exit(0);
            }
        }
    }
    /**
     * check exist value SpaceMarine
     * @param Id
     * @return false or true
     */
    public boolean checkExist(Integer Id) {
        for (SpaceMarine spaceMarine: CollectionManager.getStack()) {
            if (spaceMarine.getId().equals(Id))
                return true;
        }
        return false;
    }
    public boolean checkExistAchievements(String achievement) {
        for (SpaceMarine spaceMarine: CollectionManager.getStack()) {
            if (spaceMarine.getAchievements().equals(achievement))
                return true;
        }
        return false;
    }
    /**
     * get items stack
     * @return stack
     */
    public static Stack<SpaceMarine> getStack() {
        return stack;
    }

    /**
     * add the SpaceMarine class to the collection
     * @param spaceMarine
     */
    public void add(SpaceMarine spaceMarine) {
        spaceMarine.setId(GeneratorID.generateID());
        stack.add(spaceMarine);
        collectionManagerLogger.info("Добавлен объект в коллекцию");
    }

    /**
     * set id item of the SpaceMarine class
     * @param spaceMarine
     */
    public static void addJSONObject(SpaceMarine spaceMarine) {
        if (spaceMarine.validate()) {
            spaceMarine.setId(GeneratorID.saveId(spaceMarine.getId()));
            stack.add(spaceMarine);
        }
        else {
            collectionManagerLogger.info("Какой-то из параметров не подходит/отсутствует\nДля запуска приложения исправьте файл JSON\n");
            System.exit(0);
        }

    }
    /**
     * get information stack collection
     */
    public String info() {
        return ("-----------------------------------"
                + "\nТип коллекции: " + stack.getClass().getName()
                + "\nДата инициализации: " + creationDate
                + "\nКоличество элементов в коллекции: " + stack.size()
                + "\n-----------------------------------\n");
    }
    /**
     * output all elements of the collection in string representation
     */
    public static String show() {
        StringBuilder information = new StringBuilder();
        for (SpaceMarine spaceMarine : stack) {
            information.append(spaceMarine.toString());
        }
        return information.toString();
    }
    /**
     * update the id of the element whose value is equal to the given one
     * @param sm
     * @param id
     */
    public void update(SpaceMarine sm, Integer id) {
        for (SpaceMarine spaceMarine : stack) {
            if (spaceMarine.getId().equals(id)) {
                spaceMarine.setName(sm.getName());
                spaceMarine.setCoordinates(sm.getCoordinates());
                spaceMarine.setHealth(sm.getHealth());
                spaceMarine.setAchievements(sm.getAchievements());
                spaceMarine.setWeaponType(sm.getWeaponType());
                spaceMarine.setMeleeWeapon(sm.getMeleeWeapon());
                spaceMarine.setChapter(sm.getChapter());
            }
        }
    }
    /**
     * remove an item from the collection by id
     * @param id
     */
    public void removeById(Integer id) {
        Iterator<SpaceMarine> i = stack.iterator();
        while (i.hasNext()) {
            SpaceMarine spaceMarine = i.next();
            {
                if (spaceMarine.getId().equals(id)) {
                    i.remove();
                }
            }
        }
    }
    /**
     * clear stack collection
     */
    public static void clear() {
        stack.clear();
        GeneratorID.clearSet();

    }
    /**
     * add b new element to b given position
     * @param index
     * @param element
     */
    public static void insertAt(int index, SpaceMarine element) {
        Stack<SpaceMarine> newStack = new Stack<>();
        int count = 0;
        while (!stack.isEmpty() && count < index) {
            newStack.push(stack.pop());
            count++;
        }
        stack.push(element);
        while (!newStack.isEmpty()) {
            stack.push(newStack.pop());
        }
    }
    /**
     * add b new element if its value is less than the smallest element of this collection
     * @param spaceMarine
     */
    public static void addIfMin(SpaceMarine spaceMarine) {
        SpaceMarineComparator comparator = new SpaceMarineComparator();
        for (SpaceMarine spaceMarine1 : stack) {
            if (comparator.compare(spaceMarine1, spaceMarine) == 1) {
                stack.add(spaceMarine1);
            } else {
                collectionManagerLogger.info("Невозможно добавить элемент");
                break;
            }
        }
    }
    /**
     * remove from the collection one item whose achievements field value is equivalent to the given one
     * @param achievements - field item of the class SpaceMarine
     */
    public static void removeAnyByAchievements(String achievements) {
        Iterator<SpaceMarine> i = stack.iterator();
        while (i.hasNext()) {
            SpaceMarine spaceMarine = i.next();
            {
                if (spaceMarine.getAchievements().equals(achievements)) {
                    i.remove();
                }
            }
        }
    }
    /**
     * sort items in natural order
     */
    public static void sort() {
        Stack<SpaceMarine> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            SpaceMarine topInputStack = stack.pop();
            while (!tempStack.empty() && tempStack.peek().compareTo(topInputStack) > 0) {
                SpaceMarine topTempStack = tempStack.pop();
                stack.push(topTempStack);
            }
            tempStack.push(topInputStack);
        }
        stack = tempStack;
    }

    /**
     * group the elements by coordinates value and print the number of elements in each group
     */
    public static Map<String, Integer> groupCountingByCoordinates() {
        Map<Coordinates, Integer> countMap = new HashMap<>();
        for (SpaceMarine item : stack) {
            Coordinates coordinates = item.getCoordinates();
            countMap.put(coordinates, countMap.getOrDefault(coordinates, 0) + 1);
        }
        Map<String, Integer> groupCountMap = new HashMap<>();
        for (SpaceMarine item : stack) {
            Coordinates coordinates = item.getCoordinates();
            int count = countMap.getOrDefault(coordinates, 0);
            if (count > 0) {
                String coordinateString = "Координата X: " + coordinates.getX() +
                        ", координата Y: " + coordinates.getY();
                groupCountMap.put(coordinateString, count);
                countMap.put(coordinates, 0);
            }
        }
        return groupCountMap;
    }
}
