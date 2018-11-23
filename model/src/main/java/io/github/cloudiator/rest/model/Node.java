package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.LoginCredential;
import io.github.cloudiator.rest.model.NodeProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Node
 */
@Validated

public class Node   {
  @JsonProperty("nodeId")
  private String nodeId = null;

  @JsonProperty("name")
  private String name = null;

  /**
   * Gets or Sets state
   */
  public enum StateEnum {
    OK("OK"),
    
    ERROR("ERROR");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("state")
  private StateEnum state = null;

  @JsonProperty("loginCredential")
  private LoginCredential loginCredential = null;

  /**
   * Gets or Sets nodeType
   */
  public enum NodeTypeEnum {
    UNKNOWN_TYPE("UNKNOWN_TYPE"),
    
    VM("VM"),
    
    BYON("BYON"),
    
    CONTAINER("CONTAINER");

    private String value;

    NodeTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NodeTypeEnum fromValue(String text) {
      for (NodeTypeEnum b : NodeTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("nodeType")
  private NodeTypeEnum nodeType = null;

  @JsonProperty("ipAddresses")
  @Valid
  private List<IpAddress> ipAddresses = null;

  @JsonProperty("nodeProperties")
  private NodeProperties nodeProperties = null;

  public Node nodeId(String nodeId) {
    this.nodeId = nodeId;
    return this;
  }

  /**
   * Get nodeId
   * @return nodeId
  **/
  @ApiModelProperty(value = "")


  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public Node name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Node state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")


  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public Node loginCredential(LoginCredential loginCredential) {
    this.loginCredential = loginCredential;
    return this;
  }

  /**
   * Get loginCredential
   * @return loginCredential
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LoginCredential getLoginCredential() {
    return loginCredential;
  }

  public void setLoginCredential(LoginCredential loginCredential) {
    this.loginCredential = loginCredential;
  }

  public Node nodeType(NodeTypeEnum nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  /**
   * Get nodeType
   * @return nodeType
  **/
  @ApiModelProperty(value = "")


  public NodeTypeEnum getNodeType() {
    return nodeType;
  }

  public void setNodeType(NodeTypeEnum nodeType) {
    this.nodeType = nodeType;
  }

  public Node ipAddresses(List<IpAddress> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public Node addIpAddressesItem(IpAddress ipAddressesItem) {
    if (this.ipAddresses == null) {
      this.ipAddresses = new ArrayList<IpAddress>();
    }
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }

  /**
   * Get ipAddresses
   * @return ipAddresses
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<IpAddress> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<IpAddress> ipAddresses) {
    this.ipAddresses = ipAddresses;
  }

  public Node nodeProperties(NodeProperties nodeProperties) {
    this.nodeProperties = nodeProperties;
    return this;
  }

  /**
   * Get nodeProperties
   * @return nodeProperties
  **/
  @ApiModelProperty(value = "")

  @Valid

  public NodeProperties getNodeProperties() {
    return nodeProperties;
  }

  public void setNodeProperties(NodeProperties nodeProperties) {
    this.nodeProperties = nodeProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Node node = (Node) o;
    return Objects.equals(this.nodeId, node.nodeId) &&
        Objects.equals(this.name, node.name) &&
        Objects.equals(this.state, node.state) &&
        Objects.equals(this.loginCredential, node.loginCredential) &&
        Objects.equals(this.nodeType, node.nodeType) &&
        Objects.equals(this.ipAddresses, node.ipAddresses) &&
        Objects.equals(this.nodeProperties, node.nodeProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeId, name, state, loginCredential, nodeType, ipAddresses, nodeProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Node {\n");
    
    sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    loginCredential: ").append(toIndentedString(loginCredential)).append("\n");
    sb.append("    nodeType: ").append(toIndentedString(nodeType)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
    sb.append("    nodeProperties: ").append(toIndentedString(nodeProperties)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

