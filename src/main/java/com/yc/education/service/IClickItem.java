package com.yc.education.service;

/**
 * @Description
 * @Author BlueSky
 * @Date 2018-11-22 14:25
 */
@FunctionalInterface
public interface IClickItem<T> {

    void click(T item);
}
