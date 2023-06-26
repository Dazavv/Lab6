package me.dasha.lab6.utility;

import java.util.HashSet;
import java.util.Random;

/**
 * this class for generation ID items
 */
public class GeneratorID {
    private static HashSet<Integer> hashSetGenerateID = new HashSet<>();//используем set для хранения уникальный значений

    public static Integer saveId(Integer id){
        if (hashSetGenerateID.contains(id)) {
            System.out.println("Элемент с таким id уже существует");
        }
        hashSetGenerateID.add(id);
        return id;
    }
    /**
     * create HashSet ID
     */
    public static Integer generateID(){
        Integer id = new Random().nextInt(Integer.MAX_VALUE);//возвращает следующее случайное значение типа Int в диапазоне от 0 до n
        if (hashSetGenerateID.contains(id)){//проверить, содержит ли String другую подстроку или нет
            while (hashSetGenerateID.contains(id)){
                id = new Random().nextInt(Integer.MAX_VALUE);
            }
        }
        else {
            hashSetGenerateID.add(id);
        }
        return id;
    }
    /**
     * remove id item
     * @param ID item
     * @return id item
     */
    public static Integer generateID(Integer ID){
        Integer id = ID;
        if (ID <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0");
        }
        if (!hashSetGenerateID.contains(id)) {
            System.out.println("Элемента с таким ID не существует");
        }
        if (hashSetGenerateID.contains(id)) {
            hashSetGenerateID.remove(id);
        }
        return id;
    }
    /**
     * clear collection
     */
    public static void clearSet() {//для команды clear
        hashSetGenerateID.clear();
    }

    //
}