package com.espol.group05.grupo_05.Classes;

import com.espol.group05.grupo_05.Utilities.DoubleCircularList;

public interface InterfaceFindable<T> {
    T find(DoubleCircularList<T> list);
}
