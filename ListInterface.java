/** An interface for the ADT list.
    Entries in a list have indexes that begin with 0.

    Modifications by Stephen Ellis to use 0-based index instead of 1-based position.

    @author Frank M. Carrano
    @author Timothy M. Henry
    @version 5.0
*/
public interface ListInterface<T>
{
   /** Adds a new entry to the end of this list.
       Entries currently in the list are unaffected.
       The list's size is increased by 1.
       @param newEntry  The object to be added as a new entry. */
   public void add(T newEntry);
   
   /** Adds a new entry at a specified index within this list.
       Entries originally at and above the specified index
       are at the next higher index within the list.
       The list's size is increased by 1.
       @param index  An integer that specifies the desired
                           index of the new entry.
       @param newEntry     The object to be added as a new entry.
       @throws  IndexOutOfBoundsException if either
                index < 0 or index >= getLength(). */
   public void add(int index, T newEntry);
   
   /** Removes the entry at a given index from this list.
       Entries originally at indexes higher than the given
       index are at the next lower index within the list,
       and the list's size is decreased by 1.
       @param index  An integer that indicates the index of
                             the entry to be removed.
       @return  A reference to the removed entry.
       @throws  IndexOutOfBoundsException if either 
                index < 0 or index >= getLength(). */
   public T remove(int index);
   
   /** Removes all entries from this list. */
   public void clear();
   
   /** Replaces the entry at a given index in this list.
       @param index  An integer that indicates the index of
                             the entry to be replaced.
       @param newEntry  The object that will replace the entry at the
                        index `index`.
       @return  The original entry that was replaced.
       @throws  IndexOutOfBoundsException if either
                index < 0 or index >= getLength(). */
   public T replace(int index, T newEntry);
   
   /** Retrieves the entry at a given index in this list.
       @param index  An integer that indicates the index of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                index < 0 or index >= getLength(). */
   public T getEntry(int index);
   
   /** Retrieves all entries that are in this list in the order in which
       they occur in the list.
       @return  A newly allocated array of all the entries in the list.
                If the list is empty, the returned array is empty. */
   public T[] toArray();
   
   /** Sees whether this list contains a given entry.
       @param anEntry  The object that is the desired entry.
       @return  True if the list contains anEntry, or false if not. */
   public boolean contains(T anEntry);
   
   /** Gets the length of this list.
       @return  The integer number of entries currently in the list. */
   public int getLength();
       
   /** Sees whether this list is empty.
       @return  True if the list is empty, or false if not. */
   public boolean isEmpty();
} // end ListInterface
