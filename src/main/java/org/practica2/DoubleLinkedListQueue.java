package org.practica2;

import java.util.Comparator;

public class DoubleLinkedListQueue<T> implements DoubleEndedQueue<T>{

    private int numElements;
    private DequeNode<T> head;

    DoubleLinkedListQueue(){
        numElements=0;
        head=null;
    }

    @Override
    public void append(DequeNode<T> node) {
        if (node==null || node.getItem()==null) throw new RuntimeException("Nodo con valor null");
        if (head==null) head=node;
        else {
            DequeNode<T> ultimoNodo = head;
            while (ultimoNodo.getNext() != null) {
                ultimoNodo = ultimoNodo.getNext();
            }
            ultimoNodo.setNext(node);
            node.setPrevious(ultimoNodo);
        }
        numElements++;
    }

    @Override
    public void appendLeft(DequeNode<T> node) {
        if (node==null || node.getItem()==null) throw new RuntimeException("Nodo con valor null");
        if (head==null) head=node;
        else{
            node.setNext(head);
            head= node;
        }
        numElements++;
    }

    @Override
    public void deleteFirst() {
        if (head==null) throw new RuntimeException("Empty queue");
        else if (head.getNext()==null) head=null;
        else {
            head=head.getNext();
            head.setPrevious(null);
        }
        numElements--;
    }

    @Override
    public void deleteLast() {
        if (head==null) throw new RuntimeException("Empty queue");
        else if (head.getNext()==null) head=null;
        else {
            DequeNode<T> ultimoNodo = head;
            while (ultimoNodo.getNext() != null) {
                ultimoNodo = ultimoNodo.getNext();
            }
            ultimoNodo.getPrevious().setNext(null);
        }
        numElements--;
    }

    @Override
    public DequeNode peekFirst() {
        return head;
    }

    @Override
    public DequeNode peekLast() {
        if (head==null) return null;
        DequeNode<T> ultimoNodo=head;
        while (ultimoNodo.getNext()!=null){
            ultimoNodo=ultimoNodo.getNext();
        }
        return ultimoNodo;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public DequeNode<T> getAt(int position) {
        if(position<0)
            throw new RuntimeException("La posicion no puede ser negativa");
        int counter=0;
        DequeNode<T> myNodo=head;
        while (myNodo.getNext()!=null){
            if(counter==position)
                return myNodo;
            myNodo=myNodo.getNext();
            counter++;
        }
        throw new RuntimeException("La posicion es mayor al tamaño de la cola");
    }

    @Override
    public DequeNode<T> find(T item) {
        DequeNode<T> myNodo=head;
        while (myNodo.getNext()!=null){
            if(myNodo.getItem().equals(item))
                return myNodo;
            myNodo=myNodo.getNext();
        }
        throw new RuntimeException("No se ha encontrado el item");
    }

    @Override
    public void delete(DequeNode<T> node) {
        DequeNode<T> myNodo=head;
        while (myNodo.getNext()!=null){
            if(myNodo.equals(node)) {
                myNodo.getPrevious().setNext(myNodo.getNext());
                numElements--;
            }
            myNodo=myNodo.getNext();
        }
        throw new RuntimeException("No se ha encontrado el item");
    }

    @Override
    public void sort(Comparator<T> comparator) {
        DequeNode<T> pivote = head;
        while(pivote.getNext()!=null){
            if (comparator.compare(pivote.getItem(), pivote.getNext().getItem())<0){
                T aux = pivote.getItem();
                pivote.setItem(pivote.getNext().getItem());
                pivote.getNext().setItem(aux);
            }
            pivote=pivote.getNext();
        }
    }
}
