package cs3500.pa03.model;

/**
 * To represent a stack of type T
 *
 * @param <T> an element of type T
 */
public interface Stack<T> {

  /**
   * Push an item to the top of the stack
   *
   * @param item the item
   */
  void push(T item);

  /**
   * Pop off an item to the top of the stack
   *
   * @return item in the stack
   */
  T pop();

  /**
   * Method to check if the stack is empty
   *
   * @return the boolean
   */
  boolean isEmpty();
}
