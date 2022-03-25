package org.practica2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DequeNodeTest {
    @Nested
    class Constructor {
        @Test
        @DisplayName("Constructor con Item")
        void ConstructorItem() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode(1);
            //Act
            var itemActual = nodo.getItem();
            var nextActual = nodo.getNext();
            var previousActual = nodo.getPrevious();
            //Assert
            assertEquals(1, itemActual);
            assertEquals(null, nextActual);
            assertEquals(null, previousActual);
        }

        @Test
        @DisplayName("Constructor con Item, Next, Previous")
        void ConstructorTodosParametros() {
            //Arrange
            DequeNode<Integer> nextExpected = new DequeNode<>(2);
            DequeNode<Integer> previousExpected = new DequeNode<>(0);
            DequeNode<Integer> nodo = new DequeNode(1, nextExpected, previousExpected);
            //Act
            var itemActual = nodo.getItem();
            var nextActual = nodo.getNext();
            var previousActual = nodo.getPrevious();
            //Assert
            assertEquals(1, itemActual);
            assertEquals(nextExpected, nodo.getNext());
            assertEquals(previousExpected, nodo.getPrevious());
        }
    }

    @Nested
    class IsLast {
        @Test
        @DisplayName("isLastNode | Es el último")
        void esUltimo() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode<>(1);
            //Act
            var isLastActual = nodo.isLastNode();
            //Assert
            assertEquals(true, isLastActual);
        }

        @Test
        @DisplayName("isLastNode | No es el último")
        void noEsUltimo() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode<>(1, new DequeNode<>(2), null);
            //Act
            var isLastActual = nodo.isLastNode();
            //Assert
            assertEquals(false, isLastActual);
        }
    }

    @Nested
    class isFirst {
        @Test
        @DisplayName("isFirstNode | Es el primero")
        void esElPrimero() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode<>(1, null, null);
            //Act
            var isFirstActual = nodo.isFirstNode();
            //Assert
            assertEquals(true, isFirstActual);
        }

        @Test
        @DisplayName("isFirstNode | No es el primero")
        void noEsElPrimero() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode<>(1, null, new DequeNode<>(1));
            //Act
            var isFirstActual = nodo.isFirstNode();
            //Assert
            assertEquals(false, isFirstActual);
        }
    }

    @Nested
    class isNotATerminal {
        @Test
        @DisplayName("isNotATerminalNode | Es intermedio")
        void esUnoIntermedio() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode<>(1, new DequeNode<>(2), new DequeNode<>(0));
            //Act
            var isNotATerminateNodeActual = nodo.isNotATerminalNode();
            //Assert
            assertEquals(true, isNotATerminateNodeActual);
        }

        @Test
        @DisplayName("isNotATerminalNode | No es intermedio, es el primero")
        void noEsUnoIntermedioEsPrimero() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode<>(1, new DequeNode<>(2), null);
            //Act
            var isNotATerminateNodeActual = nodo.isNotATerminalNode();
            //Assert
            assertEquals(false, isNotATerminateNodeActual);
        }

        @Test
        @DisplayName("isNotATerminalNode | No es intermedio, es el último")
        void noEsUnoIntermedioEsUltimo() {
            //Arrange
            DequeNode<Integer> nodo = new DequeNode<>(1, null, new DequeNode<>(0));
            //Act
            var isNotATerminateNodeActual = nodo.isNotATerminalNode();
            //Assert
            assertEquals(false, isNotATerminateNodeActual);
        }
    }
}