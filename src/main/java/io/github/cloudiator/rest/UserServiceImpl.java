package io.github.cloudiator.rest;

import io.github.cloudiator.rest.converter.UserService;

/**
 * Created by volker on 29.05.17.
 */
public class UserServiceImpl implements UserService {

  private final static String DUMMY_USER = "dummy_user_id";

  @Override
  public String getUserId() {
    return DUMMY_USER;
  }
}
