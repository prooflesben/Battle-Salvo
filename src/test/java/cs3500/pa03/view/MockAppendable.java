package cs3500.pa03.view;


import java.io.IOException;

/**
 * MockAppendable used for exception testing
 */
public class MockAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Mock IO error occurred");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Mock IO error occurred");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Mock IO error occurred");
  }
}
