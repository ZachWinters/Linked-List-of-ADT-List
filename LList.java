import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 A linked implemention of the ADT list.

 Modifications by Stephen Ellis to use 0-based index instead of 1-based position

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
public class LList<T> implements Iterable<T>, ListInterface<T>
{
   private Node firstNode;            // Reference to first node of chain
   private int  numberOfEntries;
   
   public LList()
   {
      initializeDataFields();
   } // end default constructor
   
   public void clear()
   {
      initializeDataFields();
   } // end clear
   
   public void add(T newEntry)          // OutOfMemoryError possible
   {
      Node newNode = new Node(newEntry);
      
      if (isEmpty())
         firstNode = newNode;
      else                              // Add to end of nonempty list
      {
         Node lastNode = getNodeAt(numberOfEntries-1);
         lastNode.setNextNode(newNode); // Make last node reference new node
      } // end if
      
      numberOfEntries++;
   } // end add

   public void add(int index, T newEntry) // OutOfMemoryError possible
   {
      if ((index >= 0) && (index < numberOfEntries))
      {
         Node newNode = new Node(newEntry);
         if (index == 0)                // first item
         {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
         }
         else                                    // list is not empty
         {                                       // and index > 0
            Node nodeBefore = getNodeAt(index);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
         } // end if
         numberOfEntries++;
      }
      else
         throw new IndexOutOfBoundsException("Illegal index given to add operation.");
   } // end add

   public T remove(int index)
   {
      T result = null;                           // Return value
      if ((index >= 0) && (index < numberOfEntries))
      {
         // Assertion: !isEmpty()
         if (index == 0)                 // Case 1: Remove first entry
         {
            result = firstNode.getData();        // Save entry to be removed
            firstNode = firstNode.getNextNode(); // Remove entry
         }
         else                                    // Case 2: Not first entry
         {
            Node nodeBefore = getNodeAt(index);
            Node nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();     // Save entry to be removed
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);   // Remove entry
        } // end if
         numberOfEntries--;                      // Update count
         return result;                          // Return removed entry
      }
      else
         throw new IndexOutOfBoundsException("Illegal index given to remove operation.");
   } // end remove

   public T replace(int index, T newEntry)
   {
      if ((index >= 0) && (index < numberOfEntries))
      {
         // Assertion: !isEmpty()
         Node desiredNode = getNodeAt(index);
         T originalEntry = desiredNode.getData();
         desiredNode.setData(newEntry);
         return originalEntry;
      }
      else
         throw new IndexOutOfBoundsException("Illegal index given to replace operation.");
   } // end replace

   public T getEntry(int index)
   {
      if ((index >= 0) && (index < numberOfEntries))
      {
         // Assertion: !isEmpty()
         return getNodeAt(index).getData();
      }
      else
         throw new IndexOutOfBoundsException("Illegal index given to getEntry operation.");
   } // end getEntry
   
   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
                                             
   public boolean contains(T anEntry)
   {
      boolean found = false;
      Node currentNode = firstNode;
      
      while (!found && (currentNode != null))
      {
         if (anEntry.equals(currentNode.getData()))
            found = true;
         else
            currentNode = currentNode.getNextNode();
      } // end while
      
      return found;
   } // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
      return numberOfEntries == 0;
   } // end isEmpty


   // Iterable interface implementation
   public Iterator<T> iterator() {
      return new IteratorForLList();
   }

   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
      firstNode = null;
      numberOfEntries = 0;
   } // end initializeDataFields
   
   // Returns a reference to the node at a given index.
   // Precondition: The chain is not empty;
   //               0 <= index < numberOfEntries.
   private Node getNodeAt(int index)
   {
      // Assertion: (firstNode != null) &&
      //            (0 <= index) && (index < numberOfEntries)
      Node currentNode = firstNode;
      
      // Traverse the chain to locate the desired node
      // (skipped if index is 0)
      for (int counter = 0; counter < index; counter++)
         currentNode = currentNode.getNextNode();
      // Assertion: currentNode != null
      return currentNode;
   } // end getNodeAt
   
   private class Node
   {
      private T    data; // Entry in list
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor
      
      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
   } // end Node

   
   private class IteratorForLList implements Iterator<T>
   {
      // Private variables. You may add more if you'd like.
      private Node prevNode; 
      private Node nextNode;
      private int nextIndex;
      // Constructor for an IteratorForLList
      public IteratorForLList()
      {
         nextNode = firstNode;
      }

      // Returns whether or not the iterator has a next item.
      // If the next item is null, return false
      public boolean hasNext() {
         return nextNode != null;
      }

      // Returns next item (not the next Node). If there is no next item, 
      // throw a NoSuchElementException
      public T next() {
         prevNode = nextNode;
         if(!hasNext())
         {
            throw new NoSuchElementException();
         }else{

            nextNode = nextNode.next;
         }
         
         return prevNode.data;
      }

      // Removes the previous item. If there is no previous item, 
      // throw a NoSuchElementException
      public void remove() {
         if (prevNode == null) {
             throw new NoSuchElementException();
         }
     
         if (prevNode == firstNode) {
             firstNode = firstNode.getNextNode();
         } else {
             Node currentNode = firstNode;
             while (currentNode.getNextNode() != prevNode) {
                 currentNode = currentNode.getNextNode();
             }
             currentNode.setNextNode(prevNode.getNextNode());
         }
     
         prevNode = null;
         numberOfEntries--;
     }
   }
} // end LList



