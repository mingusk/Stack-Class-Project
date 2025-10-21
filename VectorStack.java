package com.king;

/**
 * VectorStack.java - Vector-based implementation of our Stack ADT
 *
 */
import java.util.EmptyStackException;
import java.util.Vector;

public class VectorStack<T> implements StackInterface<T>
{
    //declare our properties
    private Vector<T> stack;              //the array holding our entries
    private final static int DEFAULT_CAPACITY = 25;     
    private final static int MAX_CAPACITY = 1000;       //maximum allowable size
    private boolean integrityOK;    //flag value to signal data is not corrupt

    //define our constructors

    //default constructor
    public VectorStack()
    {
        this(DEFAULT_CAPACITY);
    }

    //parameterized constructor
    public VectorStack(int capacity)
    {
        integrityOK = false;

        if (capacity <= 0)
        {
            stack = new Vector<T>(DEFAULT_CAPACITY);   // capacity too small, use default value
        } else  {  
            checkCapacity(capacity);
            stack = new Vector<T>(capacity);   // capacity is good
        } 
  
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
    
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        checkIntegrity();
        integrityOK = false;
        stack.add(newEntry);
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
            T topData = stack.remove(stack.size() - 1);
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
            return stack.lastElement();
    }
    
    //isEmpty() - checks if Stack is empty
    //   @return TRUE if empty; FALSE otherwise
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    
    //clear() - removes all entries from the stack
    public void clear()
    {
        stack.clear();
    }
}