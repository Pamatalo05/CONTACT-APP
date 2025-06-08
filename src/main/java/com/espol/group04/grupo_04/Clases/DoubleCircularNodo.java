package com.espol.group04.grupo_04.Clases;

public class DoubleCircularNodo<E> {
    private E data;
    DoubleCircularNodo<E> next;
    DoubleCircularNodo<E> previous;
    
    public DoubleCircularNodo(E data){
        this.data = data;
        next = null;
        previous = null;
    }
    public E getData(){
        return this.data;
    }
    public DoubleCircularNodo<E> getNext(){
        return this.next;
    }
    public DoubleCircularNodo<E> getPrevious(){
        return this.previous;
    }
    public void setNext(DoubleCircularNodo<E> next){
        this.next = next;
    }
    public void setPrevious(DoubleCircularNodo<E> previous){
        this.previous = previous;
    }
    public void setData(E data){
        this.data = data;
    }
}
