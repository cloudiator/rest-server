package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * Represents a new node that is to be created 
 */
@ApiModel(description = "Represents a new node that is to be created ")
@Validated

public class NewNode   {
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

  public NewNode name(String name) {
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

  public NewNode loginCredential(LoginCredential loginCredential) {
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

  public NewNode ipAddresses(List<IpAddress> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public NewNode addIpAddressesItem(IpAddress ipAddressesItem) {
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

  public NewNode nodeProperties(NodeProperties nodeProperties) {
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

  public NewNode reason(String reason) {
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

  public NewNode diagnostic(String diagnostic) {
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

  public NewNode nodeCandidate(String nodeCandidate) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewNode newNode = (NewNode) o;
    return Objects.equals(this.name, newNode.name) &&
        Objects.equals(this.loginCredential, newNode.loginCredential) &&
        Objects.equals(this.ipAddresses, newNode.ipAddresses) &&
        Objects.equals(this.nodeProperties, newNode.nodeProperties) &&
        Objects.equals(this.reason, newNode.reason) &&
        Objects.equals(this.diagnostic, newNode.diagnostic) &&
        Objects.equals(this.nodeCandidate, newNode.nodeCandidate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, loginCredential, ipAddresses, nodeProperties, reason, diagnostic, nodeCandidate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewNode {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    loginCredential: ").append(toIndentedString(loginCredential)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
    sb.append("    nodeProperties: ").append(toIndentedString(nodeProperties)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    diagnostic: ").append(toIndentedString(diagnostic)).append("\n");
    sb.append("    nodeCandidate: ").append(toIndentedString(nodeCandidate)).append("\n");
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

