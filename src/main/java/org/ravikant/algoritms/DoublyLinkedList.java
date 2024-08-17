package org.ravikant.algoritms;


import org.ravikant.algoritms.exceptions.InvalidElementException;

import java.util.NoSuchElementException;

/**
 * An object that supports creating a list with non-contiguous memory allocation. You cannot access a random element
 * directly using index. But if you have a pointer to a node, then you can traverse the list both in forward and
 * backward direction in the list.
 *
 * @param <E> Type of element stored in a list.
 */
public class DoublyLinkedList<E> {

    DoublyLinkedListNode<E> dummyHead;
    DoublyLinkedListNode<E> dummyTail;

    public DoublyLinkedList() {
        // We can instantiate these by null, since we are never going to use val for these dummyNodes
        dummyHead = new DoublyLinkedListNode<>(null);
        dummyTail = new DoublyLinkedListNode<>(null);

        // Also, Initially there are no items,
        // so join dummyHead and Tail, we can add items in between them easily.
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    /**
     * Method to detach a random node from the doubly linked list. The node itself will not be removed from the memory.
     * Just that it will be removed from the list and become orphaned.
     *
     * @param node Node to be detached.
     */
    public void detachNode(DoublyLinkedListNode<E> node) {
        // Just Simply modifying the pointers.
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    /**
     * Helper method to add a node at the end of the list.
     *
     * @param node Node to be added.
     */
    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        DoublyLinkedListNode<E> tailPrev = dummyTail.prev;
        tailPrev.next = node;
        node.next = dummyTail;
        dummyTail.prev = node;
        node.prev = tailPrev;
    }

    /**
     * Helper method to add an element at the end.
     *
     * @param element Element to be added.
     * @return Reference to new node created for the element.
     */
    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if (element == null) {
            throw new InvalidElementException();
        }
        DoublyLinkedListNode<E> newNode = new DoublyLinkedListNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }



    public DoublyLinkedListNode<E> getFirstNode() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return dummyHead.next;
    }

    public DoublyLinkedListNode<E> getLastNode() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return dummyTail.prev;
    }

    public boolean isEmpty() {
        return dummyHead.next == dummyTail;
    }
}
