package estruturas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 *
 * @author DEI-ISEP
 * @param <E> Generic list element type
 */
public class DoublyLinkedList<E> implements Iterable<E>, Cloneable {

// instance variables of the DoublyLinkedList
    private final Node<E> header;     // header sentinel
    private final Node<E> trailer;    // trailer sentinel
    private int size = 0;       // number of elements in the list
    private int modCount = 0;   // number of modifications to the list (adds or removes)

    /**
     * Creates both elements which act as sentinels
     */
    public DoublyLinkedList() {

        header = new Node<>(null, null, null);      // create header
        trailer = new Node<>(null, header, null);   // trailer is preceded by header
        header.setNext(trailer);                    // header is followed by trailer
    }

    /**
     * Returns the number of elements in the linked list
     *
     * @return the number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty
     *
     * @return true if the list is empty, and false otherwise
     */
    public boolean isEmpty() {
        if (header.getNext() == trailer) {
            return true;
        }
        return false;
    }

    /**
     * Returns (but does not remove) the first element in the list
     *
     * @return the first element of the list
     */
    public E first() {
        if (!isEmpty()) {
            return header.getNext().getElement();
        }
        return null;
    }

    /**
     * Returns (but does not remove) the last element in the list
     *
     * @return the last element of the list
     */
    public E last() {
        if (!isEmpty()) {
            return trailer.getPrev().getElement();
        }
        return null;
    }

// public update methods
    /**
     * Adds an element e to the front of the list
     *
     * @param e element to be added to the front of the list
     */
    public void addFirst(E e) {
        // place just after the header
        Node<E> newNode;
        if (isEmpty()) {
            newNode = new Node<>(e, header, trailer);
            header.setNext(newNode);
            trailer.setPrev(newNode);
        } else {
            Node<E> next = header.getNext();
            newNode = new Node<>(e, header, next);
            header.setNext(newNode);
            next.setPrev(newNode);

        }
        size++;
        modCount++;
    }

    /**
     * Adds an element e to the end of the list
     *
     * @param e element to be added to the end of the list
     */
    public void addLast(E e) {
        Node<E> novoNo;
        if (isEmpty()) {
            novoNo = new Node(e, header, trailer);
            header.setNext(novoNo);
            trailer.setPrev(novoNo);
        } else {
            Node<E> ultimoNo = trailer.getPrev();
            novoNo = new Node(e, ultimoNo, trailer);
            ultimoNo.setNext(novoNo);
            trailer.setPrev(novoNo);
        }
        size++;
        modCount++;
    }

    /**
     * Removes and returns the first element of the list
     *
     * @return the first element of the list
     */
    public E removeFirst() {
        Node<E> removeNode = null;
        if (isEmpty()) {
            return null;
        } else {

            removeNode = header.getNext();
            header.setNext(removeNode.getNext());
            removeNode.getNext().setPrev(header);
            size--;
            modCount++;
        }
        return removeNode.getElement();
    }

    /**
     * Removes and returns the last element of the list
     *
     * @return the last element of the list
     */
    public E removeLast() {
        Node<E> removeNode = null;
        if (isEmpty()) {
            return null;
        } else {
            Node<E> previous = trailer.getPrev().getPrev();
            removeNode = trailer.getPrev();
            trailer.setPrev(previous);
            previous.setNext(trailer);
            size--;
            modCount++;
        }
        return removeNode.getElement();
    }

// private update methods
    /**
     * Adds an element e to the linked list between the two given nodes.
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(e, predecessor, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
        modCount++;
    }

    /**
     * Removes a given node from the list and returns its content.
     */
    private E remove(Node<E> node) {
        Node<E> currentNode = header;
        while (currentNode != trailer) {
            currentNode = currentNode.getNext();
            if (currentNode == node) {
                Node<E> previous = node.getPrev();
                Node<E> next = node.getNext();
                previous.setNext(next);
                next.setPrev(previous);
                size--;
                modCount++;
                return node.getElement();
            }
        }
        return null;
    }

// Overriden methods        
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        DoublyLinkedList<E> otherList = (DoublyLinkedList<E>) obj;

        if (this.size != otherList.size) {
            return false;
        }
        if (size <= 0) {
            return false;
        }
        /* Testing if equals through nodes
        Node<E> currentNode = header.getNext();
        Node<E> otherNode = otherList.header.getNext();
        while (currentNode != trailer) {
            if (currentNode.getElement() != otherNode.getElement()) {
                return false;
            }
            currentNode = currentNode.getNext();
            otherNode = otherNode.getNext();
        }
         */

        ListIterator<E> itThisList = this.listIterator();
        ListIterator<E> itOtherList = otherList.listIterator();
        while (itThisList.hasNext() && itOtherList.hasNext()) {
            E e1 = itThisList.next();
            E o1 = itOtherList.next();
            if (e1 != o1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ListIterator<E> it = this.listIterator();
        DoublyLinkedList<E> dll = new DoublyLinkedList<>();
        while (it.hasNext()) {
            dll.addLast(it.next());
        }
        return dll;
//        return super.clone();
    }

//---------------- nested DoublyLinkedListIterator class ----------------        
    private class DoublyLinkedListIterator implements ListIterator<E> {

        private DoublyLinkedList.Node<E> nextNode, prevNode, lastReturnedNode; // node that will be returned using next and prev respectively
        private int nextIndex;  // Index of the next element
        private int expectedModCount;  // Expected number of modifications = modCount;

        public DoublyLinkedListIterator() {
            this.prevNode = header;
            this.nextNode = header.getNext();
            lastReturnedNode = null;
            nextIndex = 0;
            expectedModCount = modCount;
        }

        final void checkForComodification() {  // invalidate iterator on list modification outside the iterator
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            if (nextNode == trailer) {
                return false;
            }
            return true;
        }

        @Override
        public E next() throws NoSuchElementException {
            checkForComodification();
            if (nextNode == trailer) {
                final String msg = "End of list reached.";
                throw new NoSuchElementException(msg);
            }
            DoublyLinkedList.Node<E> currentNode = nextNode;
            nextNode = currentNode.getNext();
            prevNode = currentNode;
            lastReturnedNode = currentNode;
            nextIndex++;
            return currentNode.getElement();
        }

        @Override
        public boolean hasPrevious() {
            if (prevNode == header) {
                return false;
            }
            return true;
        }

        @Override
        public E previous() throws NoSuchElementException {
            checkForComodification();

            if (prevNode == header) {
                final String msg = "Beginning of list reached.";
                throw new NoSuchElementException(msg);
            }
            DoublyLinkedList.Node<E> currentNode = prevNode;
            prevNode = currentNode.getPrev();
            nextNode = currentNode;
            lastReturnedNode = currentNode;
            nextIndex--;
            return currentNode.getElement();
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() throws NoSuchElementException {
            checkForComodification();
            if (prevNode == header) {
                throw new NoSuchElementException();
            }
            DoublyLinkedList.this.remove(prevNode);
            prevNode = nextNode.getPrev();
            expectedModCount++;
        }

        @Override
        public void set(E e) throws NoSuchElementException {
            if (lastReturnedNode == null) {
                throw new NoSuchElementException();
            }
            checkForComodification();

            lastReturnedNode.setElement(e);
        }

        @Override
        public void add(E e) {
            checkForComodification();
            DoublyLinkedList.this.addBetween(e, prevNode, nextNode);
            DoublyLinkedList.Node<E> currentNode = prevNode.getNext();
            prevNode = currentNode;
            expectedModCount++;
        }

    }    //----------- end of inner DoublyLinkedListIterator class ----------

//---------------- Iterable implementation ----------------
    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    public ListIterator<E> listIterator() {
        return new DoublyLinkedListIterator();
    }

//---------------- nested Node class ----------------
    private static class Node<E> {

        private E element;      // reference to the element stored at this node
        private Node<E> prev;   // reference to the previous node in the list
        private Node<E> next;   // reference to the subsequent node in the list

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E element) { // Not on the original interface. Added due to list iterator implementation
            this.element = element;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    } //----------- end of nested Node class ----------

}
