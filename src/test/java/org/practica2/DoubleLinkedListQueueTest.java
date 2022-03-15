package org.practica2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListQueueTest {

    @Test
    @DisplayName("Constructor")
    void constructor() {
        //Arrange
        DoubleLinkedListQueue<Integer> cola;
        //Act
        cola = new DoubleLinkedListQueue<>();
        //Assert
        assertEquals(0, cola.size());
        assertNull(cola.peekFirst());
    }

    @Nested
    class Appending {
        @Test
        @DisplayName("append | primer elemento")
        void addRightFirst() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode nodo = new DequeNode<Integer>(0);
            //Act
            cola.append(nodo);
            //Assert
            assertEquals(1, cola.size());
            assertEquals(nodo, cola.peekFirst());
            assertEquals(nodo, cola.peekLast());
        }

        @Test
        @DisplayName("append | varios elementos")
        void addRightMany() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode head = new DequeNode<Integer>(0);
            DequeNode nodoExpected = new DequeNode<Integer>(2);
            cola.append(head);
            cola.append(new DequeNode<Integer>(1));
            //Act
            cola.append(nodoExpected);
            //Assert
            assertEquals(3, cola.size());
            assertEquals(head, cola.peekFirst());
            assertEquals(nodoExpected, cola.peekLast());
        }

        @Test
        @DisplayName("append | añadimos null y salta excepcion")
        void addRightError() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            //Act & Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.append(new DequeNode<Integer>(null)));
            assertThrowsExactly(RuntimeException.class, () -> cola.append(null));
            assertEquals(0, cola.size());
            assertEquals(null, cola.peekFirst());
            assertEquals(null, cola.peekLast());
        }

        @Test
        @DisplayName("appendLeft | primer elemento")
        void addLeftFirst() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode nodo = new DequeNode<Integer>(0);
            //Act
            cola.appendLeft(nodo);
            //Assert
            assertEquals(1, cola.size());
            assertEquals(nodo, cola.peekFirst());
            assertEquals(nodo, cola.peekLast());
        }

        @Test
        @DisplayName("appendLeft | varios elementos")
        void addLeftMany() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode head = new DequeNode<Integer>(0);
            DequeNode nodoExpected = new DequeNode<Integer>(1);
            cola.append(head);
            //Act
            cola.appendLeft(nodoExpected);
            //Assert
            assertEquals(2, cola.size());
            assertEquals(nodoExpected, cola.peekFirst());
            assertEquals(head, cola.peekLast());
        }

        @Test
        @DisplayName("appendLeft | añadimos null y salta excepcion")
        void addLeftError() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            //Act & Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.appendLeft(new DequeNode<Integer>(null)));
            assertEquals(0, cola.size());
            assertEquals(null, cola.peekFirst());
            assertEquals(null, cola.peekLast());
        }
    }

    @Nested
    class Deleting {
        @Test
        @DisplayName("deleteLast | un elemento")
        void deleteLastOne() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode nodo = new DequeNode<Integer>(0);
            cola.append(nodo);
            //Act
            cola.deleteLast();
            //Assert
            assertEquals(0, cola.size());
            assertEquals(null, cola.peekFirst());
            assertEquals(null, cola.peekLast());
        }

        @Test
        @DisplayName("deleteLast | varios elementos")
        void deleteLastMany() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode head = new DequeNode<Integer>(0);
            DequeNode nodoExpected = new DequeNode<Integer>(1);
            cola.append(head);
            cola.append(nodoExpected);
            //Act
            cola.deleteLast();
            //Assert
            assertEquals(1, cola.size());
            assertEquals(head, cola.peekFirst());
            assertEquals(head, cola.peekLast());
        }

        @Test
        @DisplayName("deleteLast | cola vacia y salta excepcion")
        void deleteLastError() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            //Act & Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.deleteLast());
        }

        @Test
        @DisplayName("deleteFirst | un elemento")
        void deleteFirstOne() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode nodo = new DequeNode<Integer>(0);
            cola.append(nodo);
            //Act
            cola.deleteFirst();
            //Assert
            assertEquals(0, cola.size());
            assertEquals(null, cola.peekFirst());
            assertEquals(null, cola.peekLast());
        }

        @Test
        @DisplayName("deleteFirst | varios elementos")
        void deleteFirstMany() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            DequeNode head = new DequeNode<Integer>(0);
            DequeNode nodoExpected = new DequeNode<Integer>(1);
            cola.append(head);
            cola.append(nodoExpected);
            //Act
            cola.deleteFirst();
            //Assert
            assertEquals(1, cola.size());
            assertEquals(nodoExpected, cola.peekFirst());
            assertEquals(nodoExpected, cola.peekLast());
        }

        @Test
        @DisplayName("deleteFirst | cola vacia y salta excepcion")
        void deleteFirstError() {
            //Arrange
            DoubleLinkedListQueue<Integer> cola = new DoubleLinkedListQueue<>();
            //Act & Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.deleteFirst());
        }
    }
}
