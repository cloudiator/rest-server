package io.github.cloudiator.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Base64IdEncoderTest {

  private final static String TEST_ID = "40dc71f4e9275fe5635d3c4bd1a4aaeb~RegionOne/592b86f9-b88d-4e3e-9d75-386a7e12763f";
  private final static String TEST_ENCODE = "NDBkYzcxZjRlOTI3NWZlNTYzNWQzYzRiZDFhNGFhZWJ-UmVnaW9uT25lLzU5MmI4NmY5LWI4OGQtNGUzZS05ZDc1LTM4NmE3ZTEyNzYzZg==";
  private final static Base64IdEncoder ENCODER = Base64IdEncoder.create();

  @Test
  public void encode() {
    assertThat(TEST_ENCODE, is(equalTo(ENCODER.encode(TEST_ID))));
  }

  @Test
  public void decode() {
    assertThat(TEST_ID, is(equalTo(ENCODER.decode(TEST_ENCODE))));
  }

  @Test
  public void repeat() {
    String repeat = ENCODER.decode(ENCODER.encode(TEST_ID));
    assertThat(repeat, is(equalTo(TEST_ID)));
  }
}
