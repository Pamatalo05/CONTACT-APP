/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group05.grupo_05.Utilities;

/**
 *
 * @author misae
 */
public class DoubleCircularNodo<E> {
    private E data;
    private DoubleCircularNodo<E> next;
    private DoubleCircularNodo<E> previous;

    public DoubleCircularNodo(E data){
        this.data = data;
        this.next = this;
        this.previous = this;
    }

    public E getData() { return data; }
    public void setData(E data) { this.data = data; }

    public DoubleCircularNodo<E> getNext() { return next; }
    public void setNext(DoubleCircularNodo<E> next) { this.next = next; }

    public DoubleCircularNodo<E> getPrevious() { return previous; }
    public void setPrevious(DoubleCircularNodo<E> previous) { this.previous = previous; }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
