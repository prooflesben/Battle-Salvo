package cs3500.pa03.model;

import java.util.ArrayDeque;


/**
 * The type Stack.
 *
 * @param <T> the type parameter
 */
public class StackImpl<T> implements Stack<T> {
  private final ArrayDeque<T> arrayDeque;

  /**
   * Instantiates a new Stack.
   */
  public StackImpl() {
    this.arrayDeque = new ArrayDeque<>();
  }


  /**
   * Push an item to the top of the stack
   *
   * @param item the item
   */
  @Override
  public void push(T item) {
    arrayDeque.addFirst(item);
  }

  /**
   * Pop off an item to the top of the stack
   *
   * @return item in the stack
   */
  @Override
  public T pop() {
    return arrayDeque.removeFirst();
  }

  /**
   * Method to check if the stack is empty
   *
   * @return the boolean
   */
  @Override
  public boolean isEmpty() {
    return arrayDeque.isEmpty();
  }
}
