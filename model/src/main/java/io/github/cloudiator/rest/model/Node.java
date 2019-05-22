package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.LoginCredential;
import io.github.cloudiator.rest.model.NewNode;
import io.github.cloudiator.rest.model.NodeProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Representation of a node used by Cloudiator
 */
@ApiModel(description = "Representation of a node used by Cloudiator")
@Validated

public class Node   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("loginCredential")
  private LoginCredential loginCredential = null;

  @JsonProperty("ipAddresses")
  @Valid
  private List<IpAddress> ipAddresses = null;

  @JsonProperty("nodeProperties")
  private NodeProperties nodeProperties = null;

  @JsonProperty("reason")
  private String reason = null;

  @JsonProperty("diagnostic")
  private String diagnostic = null;

  @JsonProperty("nodeCandidate")
  private String nodeCandidate = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("originId")
  private String originId = null;

  @JsonProperty("userId")
  private String userId = null;

  /**
   * The type of this node. 
   */
  public enum NodeTypeEnum {
    UNKNOWN_TYPE("UNKNOWN_TYPE"),
    
    VM("VM"),
    
    BYON("BYON"),
    
    CONTAINER("CONTAINER"),
    
    FAAS("FAAS");

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

  /**
   * The state the node is currently in. 
   */
  public enum StateEnum {
    PENDING("PENDING"),
    
    RUNNING("RUNNING"),
    
    ERROR("ERROR"),
    
    DELETED("DELETED");

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

  public Node name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name for the node. 
   * @return name
  **/
  @ApiModelProperty(value = "Human-readable name for the node. ")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
   * The public/private ip addresses under which this node is reachable. 
   * @return ipAddresses
  **/
  @ApiModelProperty(value = "The public/private ip addresses under which this node is reachable. ")

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
   * Further properties of this node. 
   * @return nodeProperties
  **/
  @ApiModelProperty(value = "Further properties of this node. ")

  @Valid

  public NodeProperties getNodeProperties() {
    return nodeProperties;
  }

  public void setNodeProperties(NodeProperties nodeProperties) {
    this.nodeProperties = nodeProperties;
  }

  public Node reason(String reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Reason this node was created 
   * @return reason
  **/
  @ApiModelProperty(value = "Reason this node was created ")


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Node diagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
    return this;
  }

  /**
   * Diagnostic information about the node state 
   * @return diagnostic
  **/
  @ApiModelProperty(value = "Diagnostic information about the node state ")


  public String getDiagnostic() {
    return diagnostic;
  }

  public void setDiagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
  }

  public Node nodeCandidate(String nodeCandidate) {
    this.nodeCandidate = nodeCandidate;
    return this;
  }

  /**
   * The node candidate this node was created from if applicable. 
   * @return nodeCandidate
  **/
  @ApiModelProperty(value = "The node candidate this node was created from if applicable. ")


  public String getNodeCandidate() {
    return nodeCandidate;
  }

  public void setNodeCandidate(String nodeCandidate) {
    this.nodeCandidate = nodeCandidate;
  }

  public Node id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of this node. 
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier of this node. ")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Node originId(String originId) {
    this.originId = originId;
    return this;
  }

  /**
   * Original id of this node. Is present of the node was created e.g. at a cloud provider. 
   * @return originId
  **/
  @ApiModelProperty(value = "Original id of this node. Is present of the node was created e.g. at a cloud provider. ")


  public String getOriginId() {
    return originId;
  }

  public void setOriginId(String originId) {
    this.originId = originId;
  }

  public Node userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * User id of the owner of this node. 
   * @return userId
  **/
  @ApiModelProperty(value = "User id of the owner of this node. ")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Node nodeType(NodeTypeEnum nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  /**
   * The type of this node. 
   * @return nodeType
  **/
  @ApiModelProperty(value = "The type of this node. ")


  public NodeTypeEnum getNodeType() {
    return nodeType;
  }

  public void setNodeType(NodeTypeEnum nodeType) {
    this.nodeType = nodeType;
  }

  public Node state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * The state the node is currently in. 
   * @return state
  **/
  @ApiModelProperty(value = "The state the node is currently in. ")


  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
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
    return Objects.equals(this.name, node.name) &&
        Objects.equals(this.loginCredential, node.loginCredential) &&
        Objects.equals(this.ipAddresses, node.ipAddresses) &&
        Objects.equals(this.nodeProperties, node.nodeProperties) &&
        Objects.equals(this.reason, node.reason) &&
        Objects.equals(this.diagnostic, node.diagnostic) &&
        Objects.equals(this.nodeCandidate, node.nodeCandidate) &&
        Objects.equals(this.id, node.id) &&
        Objects.equals(this.originId, node.originId) &&
        Objects.equals(this.userId, node.userId) &&
        Objects.equals(this.nodeType, node.nodeType) &&
        Objects.equals(this.state, node.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, loginCredential, ipAddresses, nodeProperties, reason, diagnostic, nodeCandidate, id, originId, userId, nodeType, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Node {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    loginCredential: ").append(toIndentedString(loginCredential)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
    sb.append("    nodeProperties: ").append(toIndentedString(nodeProperties)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    diagnostic: ").append(toIndentedString(diagnostic)).append("\n");
    sb.append("    nodeCandidate: ").append(toIndentedString(nodeCandidate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    originId: ").append(toIndentedString(originId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    nodeType: ").append(toIndentedString(nodeType)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

