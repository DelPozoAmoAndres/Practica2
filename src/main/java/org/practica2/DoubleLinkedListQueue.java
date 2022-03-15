<<<<<<< Updated upstream
package org.practica2;public class DoubleLinkedListQueue {
=======
package org.practica2;

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
>>>>>>> Stashed changes
}
