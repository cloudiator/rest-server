package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Token;
import org.cloudiator.messages.entities.UserEntities;

public class TokenConverter implements TwoWayConverter<Token, UserEntities.Token> {

  @Override
  public Token applyBack(UserEntities.Token prototoken) {
    Token result = new Token().token(prototoken.getToken());
    return result;
  }

  @Override
  public UserEntities.Token apply(Token resttoken) {
    UserEntities.Token.Builder result = UserEntities.Token.newBuilder()
        .setToken(resttoken.getToken());
    return result.build();
  }
}
