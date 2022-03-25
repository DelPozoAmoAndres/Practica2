package org.practica2;

import java.util.Comparator;
import java.util.Objects;

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
        if(position>this.size())
            throw new RuntimeException("La posicion no puede ser mayor que la longitud de la cola");
        int counter=0;
        DequeNode<T> myNodo=head;
        while (myNodo!=null){
            if(counter==position)
                return myNodo;
            myNodo=myNodo.getNext();
            counter++;
        }
        throw new RuntimeException("La posicion es mayor al tamaño de la cola");
    }

    @Override
    public DequeNode<T> find(T item) {
        if(item==null)
            throw new RuntimeException("El item a buscar no puede ser null");
        DequeNode<T> myNodo=head;
        while (myNodo!=null){
            if(myNodo.getItem().equals(item))
                return myNodo;
            myNodo=myNodo.getNext();
        }
        throw new RuntimeException("No se ha encontrado el item");
    }

    @Override
    public void delete(DequeNode<T> node) {
        if(node==null)
            throw new RuntimeException("El nodo a borrar no puede ser null");
        DequeNode<T> myNodo=head;
        while (myNodo!=null){
            if(myNodo.getItem().equals(node.getItem())) {
                if(myNodo.getItem().equals(head.getItem())) {
                    if(myNodo.getNext()!=null) {
                        head = myNodo.getNext();
                        head.setPrevious(null);
                    }
                    else {
                        head = null;
                    }
                    if (size()==2) {
                        head.setNext(null);
                    }
                }
                else if(myNodo.getItem().equals(this.peekLast().getItem())){
                    myNodo.getPrevious().setNext(null);
                }
                else
                    myNodo.getPrevious().setNext(myNodo.getNext());

                numElements--;
                return;
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
                if(head.equals(pivote)) {
                    head = pivote.getNext();
                    pivote.setNext(pivote.getNext().getNext());
                    head.setNext(pivote);
                    head.setPrevious(null);
                    pivote.setPrevious(head);

                }
                else {
                    var next=pivote.getNext().getNext();
                    var previous=pivote.getNext();
                    pivote.getNext().setNext(pivote);
                    pivote.getNext().setPrevious(pivote.getPrevious());
                    pivote.getPrevious().setNext(previous);
                    pivote.setPrevious(previous);
                    pivote.setNext(next);
                }
                pivote=head;
            }
            else{
                pivote=pivote.getNext();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        DoubleLinkedListQueue<?> that = (DoubleLinkedListQueue<?>) o;
        return numElements == that.numElements && head.getItem().equals(that.head.getItem());
    }
}
