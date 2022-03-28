package org.practica2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;

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

    @Nested
    class GetAt {

        private DoubleLinkedListQueue<Integer> cola;

        @BeforeEach
        void createQueue(){
            //Arrange
            cola= new DoubleLinkedListQueue();
            cola.append(new DequeNode<>(1));
            cola.append(new DequeNode<>(2));
            cola.append(new DequeNode<>(3));
        }
        @ParameterizedTest
        @DisplayName("getAt | posicion dentro de los limites")
        @CsvSource({"0,1","1,2","2,3"})
        void getAtValidPosition(int postion,int itemExpected) {
            //Act
            var node=cola.getAt(postion);
            //Assert
            assertEquals(itemExpected, node.getItem());
        }

        @Test
        @DisplayName("getAt | posicion negativa lanza excepcion")
        void getAtNegativePosition(){
            //Act && Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.getAt(-1));
        }

        @Test
        @DisplayName("getAt | posicion mayor que size lanza excepcion")
        void getAtInvalidPosition(){
            //Act && Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.getAt(4));
        }
    }

    @Nested
    class Find {
        private DoubleLinkedListQueue<Integer> cola;

        @BeforeEach
        void createQueue(){
            //Arrange
            cola= new DoubleLinkedListQueue();
            cola.append(new DequeNode<>(1));
            cola.append(new DequeNode<>(2));
        }

        @Test
        @DisplayName("find | elementos existentes")
        void findValidElements() {
            //Arrange
            var colaExpected = new DoubleLinkedListQueue<Integer>();
            var node1Expected=new DequeNode<>(1);
            var node2Expected=new DequeNode<>(2);
            colaExpected.append(node1Expected);
            colaExpected.append(node2Expected);
            //Act
            var node1Actual=cola.find(1);
            var node2Actual=cola.find(2);
            //Assert
            assertEquals(node1Expected.getItem(), node1Actual.getItem());
            assertEquals(node2Expected.getItem(), node2Actual.getItem());
        }
        @Test
        @DisplayName("find | elemento inexistente")
        void findInValidElement() {
            //Act && Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.find(3));
        }
        @Test
        @DisplayName("find | elemento null lanza excepcion")
        void findNullElement() {
            //Act && Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.find(null));
        }

    }

    @Nested
    class Delete {
        private DoubleLinkedListQueue<Integer> cola;

        @BeforeEach
        void createQueue(){
            //Arrange
            cola= new DoubleLinkedListQueue();
            cola.append(new DequeNode<>(1));
            cola.append(new DequeNode<>(2));
            cola.append(new DequeNode<>(3));

        }
        @Test
        @DisplayName("Delete | elemento null lanza excepcion")
        void DeleteNullElement() {
            //Act && Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.delete(null));
        }
        @Test
        @DisplayName("Delete | un unico elemento en la lista")
        void DeleteUnicoElement() {
            //Arange
            cola.deleteLast();
            cola.deleteLast();
            var length=cola.size();
            //Act
            cola.delete(cola.find(1));
            // Assert
            assertThrowsExactly(RuntimeException.class,()->cola.find(1));
            assertEquals(length-1,cola.size());
        }

        @Test
        @DisplayName("Delete | primer elemento en la lista")
        void DeleteFirstElement() {
            //Arange
            var length=cola.size();
            //Act
            cola.delete(cola.find(1));
            // Assert
            assertThrowsExactly(RuntimeException.class,()->cola.find(1));
            assertEquals(length-1,cola.size());
        }

        @Test
        @DisplayName("Delete | ultimo elemento en la lista")
        void DeleteLastElement() {
            //Arange
            var length=cola.size();
            //Act
            cola.delete(cola.find(3));
            // Assert
            assertThrowsExactly(RuntimeException.class,()->cola.find(3));
            assertEquals(length-1,cola.size());
        }

        @Test
        @DisplayName("Delete | elemento intermedio de la lista")
        void DeleteMiddleElement() {
            //Arange
            var length=cola.size();
            //Act
            cola.delete(cola.find(2));
            // Assert
            assertThrowsExactly(RuntimeException.class,()->cola.find(2));
            assertEquals(length-1,cola.size());
        }

        @Test
        @DisplayName("Delete | todos los elementos")
        void DeleteAllElements() {
            //Arange
            var length=cola.size();
            //Act
            cola.delete(cola.find(2));
            // Assert
            assertThrowsExactly(RuntimeException.class,()->cola.find(2));
            assertEquals(length-1,cola.size());
            // Act 2
             cola.delete(cola.find(1));
            // Assert 2
            assertThrowsExactly(RuntimeException.class,()->cola.find(1));
            assertEquals(length-2,cola.size());
            // Act 3
            cola.delete(cola.find(3));
            // Assert 3
            assertThrowsExactly(RuntimeException.class,()->cola.find(3));
            assertEquals(length-3,cola.size());
        }

        @Test
        @DisplayName("Delete | elemento no existente en la lista")
        void DeleteInvalidElement() {
            //Act && Assert
            assertThrowsExactly(RuntimeException.class, () -> cola.delete(new DequeNode<>(4)));
        }


    }

    @Nested
    class Sort {
        private DoubleLinkedListQueue<Integer> cola;

        @BeforeEach
        void createQueue(){
            //Arrange
            cola= new DoubleLinkedListQueue();
            cola.append(new DequeNode<>(1));
        }
        @Test
        @DisplayName("Sort | solo un elemento en la lista")
        void sortOneElement() {
            //Arrange
            var colaEpected= new DoubleLinkedListQueue();
            colaEpected.append(new DequeNode<>(1));
            //Act
            cola.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            // Assert
            assertEquals(colaEpected,cola);
        }

        @Test
        @DisplayName("Sort | mas de 1 elemento en la lista")
        void sortManyElement() {
            //Arrange
            cola.append(new DequeNode<>(3));
            cola.append(new DequeNode<>(2));
            cola.append(new DequeNode<>(6));
            cola.append(new DequeNode<>(4));
            cola.append(new DequeNode<>(5));
            var colaEpected= new DoubleLinkedListQueue();
            colaEpected.append(new DequeNode<>(6));
            colaEpected.append(new DequeNode<>(5));
            colaEpected.append(new DequeNode<>(4));
            colaEpected.append(new DequeNode<>(3));
            colaEpected.append(new DequeNode<>(2));
            colaEpected.append(new DequeNode<>(1));
            //Act
            cola.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            });
            // Assert
            assertEquals(colaEpected,cola);
        }

    }
}
