/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.group04.grupo_04.Utilities;

/**
 *
 * @author misae
 */
public class DoubleCircularList<E> implements Iterable<E>{
    DoubleCircularNodo head;
    
    public DoubleCircularList(){
        head=null;
    }
    
    public void addLast(E newData){
        DoubleCircularNodo<E> newNodo = new DoubleCircularNodo(newData);
        if(head==null){
            head = newNodo;
        }else{
            DoubleCircularNodo lastOne = head.getPrevious();
            newNodo.setNext(head);
            newNodo.setPrevious(lastOne);
            head.setPrevious(newNodo);
            lastOne.setNext(newNodo);
        }
    }
    
    public void addFirst(E newData){
        DoubleCircularNodo<E> newNodo = new DoubleCircularNodo(newData);
        if(head==null){
            head = newNodo;
        }else{
            DoubleCircularNodo lastOne = head.getPrevious();
            newNodo.setNext(head);
            newNodo.setPrevious(lastOne);
            head.setPrevious(newNodo);
            lastOne.setNext(newNodo);
            head = newNodo;
        }
    }
    
    public boolean delete(E Data) {
        if (head == null) return false;
        DoubleCircularNodo<E> current = head;
        do {
            if (current.getData().equals(Data)) {
                if (current == head && current.getNext() == head) {
                    head = null;
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                  if (current == head) {
                        head = current.getNext();
                    }
                }
                return true;
            }
            current = current.getNext();
        } while (current != head);
        return false; 
        }
    public void showForward() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        DoubleCircularNodo current = head;
            do {
                System.out.print(current.getData() + " ⇄ ");
                current = current.getNext();
            } while (current != head);
            System.out.println("(returns to start)");
        }

    public void showBackward() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        DoubleCircularNodo current = head.getPrevious();
        DoubleCircularNodo end = current;
        do {
            System.out.print(current.getData() + " ⇄ ");
            current = current.getPrevious();
        } while (current != end);
        System.out.println("(returns to end)");
    }

    public int size() {
        if (head == null) return 0;
        int count = 0;
        DoubleCircularNodo current = head;
        do {
            count++;
            current = current.getNext();
        } while (current != head);
        return count;
    }
    
    @Override
    public java.util.Iterator<E> iterator() {
        return new java.util.Iterator<E>() {
            private DoubleCircularNodo<E> current = head;
            private boolean firstVisit = true;

            @Override
            public boolean hasNext() {
                return current != null && (firstVisit || current != head);
            }

            @Override
            public E next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                E data = current.getData();
                current = current.getNext();
                firstVisit = false;
                return data;
            }
        };
    }

    public DoubleCircularNodo<E> getHead() {
        return head;
    }


    
    
}
