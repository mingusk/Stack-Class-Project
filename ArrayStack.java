package com.king;

/**
 * ArrayStack.java - array-based implementation of our Stack ADT
 *
 */
import java.util.EmptyStackException;
import java.util.Arrays;

public class ArrayStack<T> implements StackInterface<T>
{
    //declare our properties
    private T[] stack;              //the array holding our entries
    private int topIndex;           //index of the top entry
    private final static int DEFAULT_CAPACITY = 25;     
    private final static int MAX_CAPACITY = 1000;       //maximum allowable size
    private boolean integrityOK;    //flag value to signal data is not corrupt

    //define our constructors

    //default constructor
    public ArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }

    //parameterized constructor
    public ArrayStack(int capacity)
    {
        integrityOK = false;

        if (capacity <= 0)
        {
            stack = (T[]) new Object[DEFAULT_CAPACITY];   // capacity too small, use default value
        } else  {  
            checkCapacity(capacity);
            stack = (T[]) new Object[capacity];   // capacity is good
        } 

        topIndex = -1;      
        integrityOK = true;
    }

    //checkIntegrity() - helper method to ensure bag is okay to work with
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Data is corrupt.");
    }

    //checkCapacity() - ensure desired capacity is allowed; else throw exception
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempted to create a bag that exceeds max capacity.");
    }

    //ensureCapacity() -- helper method to double capacity of array when full (if allowed)
    private void ensureCapacity()
    {
        //check if array is full
        if (topIndex >= stack.length - 1)
        {

            int newCapacity = stack.length * 2;

            checkCapacity(newCapacity);     

            //copy contents into new array using copyOf
            stack = Arrays.copyOf(stack, newCapacity);
        }
    }
    
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        checkIntegrity();
        integrityOK = false;
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
        integrityOK = true;
    }
    
    //pop() - removes and returns the entry at the top of this stack
    //   @return the object at the top of the stack
    //   @throws EmptyStackException if stack is empty before the operation
    public T pop()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            integrityOK = false;
            T topData = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            integrityOK = true;
            return topData;
        }
    }
    
    //peek() - returns the entry at the top of this stack
    //   @return the object at the top of the stack
    //   @throws EmptyStackException if stack is empty before the operation
    public T peek()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }
    
    //isEmpty() - checks if Stack is empty
    //   @return TRUE if empty; FALSE otherwise
    public boolean isEmpty()
    {
        return (topIndex < 0);
    }
    
    //clear() - removes all entries from the stack
    public void clear()
    {
        //while(!isEmpty())
        //    pop();
            
        for (int i = 0; i <= topIndex; i++)
            stack[i] = null;
            
        topIndex = -1;
    }
}