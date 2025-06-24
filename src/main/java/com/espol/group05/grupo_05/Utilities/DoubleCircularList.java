package com.espol.group05.grupo_05.Utilities;

import java.util.Iterator;

/**
 *
 * @author misae
 * @param E
 */
public class DoubleCircularList<E> implements Iterable<E>{
    public DoubleCircularNodo<E> head;
    
    public DoubleCircularList(){
        head=null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>(){
            DoubleCircularNodo<E> current = head;
            boolean firstVisit = true;
            @Override
            public boolean hasNext() {
                return current != null && (firstVisit || current != head);
            }
            @Override
            public E next() {
                if(!hasNext()){
                    throw new java.util.NoSuchElementException("No such element");
                }
                E data = (E) current.getData();
                current = current.getNext();
                firstVisit = false;
                return data;
            }
        };
    }

    public E get(int index) {
        if (index < 0 || index >= this.length()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.length());
        }
        
        DoubleCircularNodo<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }
    
    public void add(E newData){
        DoubleCircularNodo<E> newNodo = new DoubleCircularNodo<>(newData);
        if (head == null) {
            head = newNodo;
            head.setNext(head);
            head.setPrevious(head);
        } else {
            DoubleCircularNodo<E> lastOne = head.getPrevious();
            newNodo.setNext(head);
            newNodo.setPrevious(lastOne);
            head.setPrevious(newNodo);
            lastOne.setNext(newNodo);
        }
    }

    
    public void addFirst(E newData){
        DoubleCircularNodo<E> newNodo = new DoubleCircularNodo<>(newData);
        if (head == null) {
            head = newNodo;
            head.setNext(head);
            head.setPrevious(head);
        } else {
            DoubleCircularNodo<E> lastOne = head.getPrevious();
            newNodo.setNext(head);
            newNodo.setPrevious(lastOne);
            head.setPrevious(newNodo);
            lastOne.setNext(newNodo);
            head = newNodo;
        }
    }

    public int length(){
        if(head==null){return 0;}
        DoubleCircularNodo<E> current = head.getNext();
        int count=1;
        while(current!=null || current!=head){
            count++;
            current = current.getNext();
        }
        return count;
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
        DoubleCircularNodo<E> current = head;
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
        DoubleCircularNodo<E> current = head.getPrevious();
        DoubleCircularNodo<E> end = current;
        do {
            System.out.print(current.getData() + " ⇄ ");
            current = current.getPrevious();
        } while (current != end);
        System.out.println("(returns to end)");
    }

    public int size() {
        if (head == null) return 0;
        int count = 0;
        DoubleCircularNodo<E> current = head;
        do {
            count++;
            current = current.getNext();
        } while (current != head);
        return count;
    }
    
    public java.util.ListIterator<E> listIterator() {
        return new java.util.ListIterator<E>() {
            private DoubleCircularNodo<E> current = head;
            private boolean firstVisit = true;

            @Override
            public boolean hasNext() {
                return current != null && (firstVisit || current != head);
            }

            @Override
            public boolean hasPrevious() {
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

            @Override
            public E previous() {
                if(!hasPrevious()) throw new java.util.NoSuchElementException();
                E data = current.getData();
                current = current.getPrevious();
                firstVisit = false;
                return data;
            }

            public void add(E data) {
                DoubleCircularNodo<E> newNode = new DoubleCircularNodo<E>(data);
                if(head == null){
                    head = newNode;
                    head.setNext(newNode);
                    head.setPrevious(newNode);
                    current = head;
                }
                else{
                    DoubleCircularNodo<E> prevNode = current.getPrevious();
                    newNode.setNext(current);
                    newNode.setPrevious(prevNode);
                    prevNode.setNext(newNode);
                    current.setPrevious(newNode);
                    if(current==head){
                        head = newNode;
                    }
                }
                firstVisit = true;
            }

            @Override
            public void remove() {
                if(current==null || head==null){throw new IllegalStateException();}
                DoubleCircularNodo<E> toRemove = current.getPrevious();
                if(toRemove==current){
                    head = null;
                    current = null;
                }else{
                    toRemove.getPrevious().setNext(current);
                    current.setPrevious(toRemove.getPrevious());
                    if(toRemove==head){
                        head = current;
                    }
                }
            }

            @Override
            public void set(E data){
                if(current==null || head ==null){throw new IllegalStateException();}
                current.getPrevious().setData(data);
            }

            @Override
            public int nextIndex(){
                if(head == null){return -1;}
                DoubleCircularNodo<E> newNode = head;
                int index = 0;
                while(newNode != current){
                    newNode = newNode.getNext();
                    index++;
                    if(newNode==head){break;}
                }
                return index % size();
            }
            @Override
            public int previousIndex(){
                int index = nextIndex();
                return(index -1 + size())%size();
            }
        };
    }

    public DoubleCircularNodo<E> getHead() {
        return this.head;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    };
}
