/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group04.grupo_04.Utilities;


public class DoubleCircularNodo<E> {
    private E data;
    DoubleCircularNodo next;
    DoubleCircularNodo previous;
    
    public DoubleCircularNodo(E data){
        this.data = data;
        next = null;
        previous = null;
    }
    public E getData(){
        return this.data;
    }
    public DoubleCircularNodo getNext(){
        return this.next;
    }
    public DoubleCircularNodo getPrevious(){
        return this.previous;
    }
    public void setNext(DoubleCircularNodo next){
        this.next = next;
    }
    public void setPrevious(DoubleCircularNodo previous){
        this.previous = previous;
    }
    public void setData(E data){
        this.data = data;
    }
}
