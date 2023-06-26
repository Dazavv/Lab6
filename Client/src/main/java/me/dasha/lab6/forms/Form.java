package me.dasha.lab6.forms;


import me.dasha.lab6.exp.InvalidFormExp;

/**
 * Абстрактный класс для пользовательских форм ввода
 * @param <T> класс формы
 */
public abstract class Form<T>{
    public abstract T build() throws InvalidFormExp;
}
