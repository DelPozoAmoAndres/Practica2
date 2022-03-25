package org.practica2;

import java.util.Objects;

/**
 * Class representing a node of a double-ended queue (deque). Each node has pointers to
 * the next and previous nodes.
 * The previous and next of the first and last node of the deque is null.
 *
 * @param <T>
 */
public class DequeNode<T> {
    private T item;
    private DequeNode<T> next;
    private DequeNode<T> previous;

    public DequeNode(T item, DequeNode<T> next, DequeNode<T> previous) {
        this.item = item;
        this.next = next;
        this.previous = previous;
    }

    public DequeNode(T item) {
        this.item = item;
        this.next = null;
        this.previous = null;
    }

    public void setNext(DequeNode<T> next) {
        this.next = next;
    }

    public void setPrevious(DequeNode<T> previous) {
        this.previous = previous;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item){ this.item=item; }

    public DequeNode<T> getNext() {
        return next;
    }

    public DequeNode<T> getPrevious() {
        return previous;
    }

    public boolean isFirstNode() {
        return previous == null;
    }

    public boolean isLastNode() {
        return next == null;
    }

    public boolean isNotATerminalNode() {
        return (!isFirstNode() && !isLastNode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DequeNode<?> dequeNode = (DequeNode<?>) o;
        return Objects.equals(item, dequeNode.item) && Objects.equals(next, dequeNode.next) && Objects.equals(previous, dequeNode.previous);
    }
}