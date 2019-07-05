package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * Representation of a BYON used by Cloudiator
 */
@ApiModel(description = "Representation of a BYON used by Cloudiator")
@Validated

public class ByonNode   {
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

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("allocated")
  private Boolean allocated = null;

  public ByonNode name(String name) {
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

  public ByonNode loginCredential(LoginCredential loginCredential) {
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

  public ByonNode ipAddresses(List<IpAddress> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public ByonNode addIpAddressesItem(IpAddress ipAddressesItem) {
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

  public ByonNode nodeProperties(NodeProperties nodeProperties) {
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

  public ByonNode reason(String reason) {
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

  public ByonNode diagnostic(String diagnostic) {
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

  public ByonNode nodeCandidate(String nodeCandidate) {
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

  public ByonNode id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of this BYON. 
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier of this BYON. ")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ByonNode userId(String userId) {
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

  public ByonNode allocated(Boolean allocated) {
    this.allocated = allocated;
    return this;
  }

  /**
   * Signals if the node was allocated by cloudiator 
   * @return allocated
  **/
  @ApiModelProperty(value = "Signals if the node was allocated by cloudiator ")


  public Boolean isAllocated() {
    return allocated;
  }

  public void setAllocated(Boolean allocated) {
    this.allocated = allocated;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ByonNode byonNode = (ByonNode) o;
    return Objects.equals(this.name, byonNode.name) &&
        Objects.equals(this.loginCredential, byonNode.loginCredential) &&
        Objects.equals(this.ipAddresses, byonNode.ipAddresses) &&
        Objects.equals(this.nodeProperties, byonNode.nodeProperties) &&
        Objects.equals(this.reason, byonNode.reason) &&
        Objects.equals(this.diagnostic, byonNode.diagnostic) &&
        Objects.equals(this.nodeCandidate, byonNode.nodeCandidate) &&
        Objects.equals(this.id, byonNode.id) &&
        Objects.equals(this.userId, byonNode.userId) &&
        Objects.equals(this.allocated, byonNode.allocated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, loginCredential, ipAddresses, nodeProperties, reason, diagnostic, nodeCandidate, id, userId, allocated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ByonNode {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    loginCredential: ").append(toIndentedString(loginCredential)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
    sb.append("    nodeProperties: ").append(toIndentedString(nodeProperties)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    diagnostic: ").append(toIndentedString(diagnostic)).append("\n");
    sb.append("    nodeCandidate: ").append(toIndentedString(nodeCandidate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    allocated: ").append(toIndentedString(allocated)).append("\n");
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

