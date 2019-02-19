package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.NodeCandidate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A valid solution to a matchmaking problem.
 */
@ApiModel(description = "A valid solution to a matchmaking problem.")
@Validated

public class Solution   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("costs")
  private Double costs = null;

  @JsonProperty("time")
  private Float time = null;

  @JsonProperty("isOptimal")
  private Boolean isOptimal = null;

  @JsonProperty("valid")
  private Boolean valid = null;

  @JsonProperty("nodeCandidates")
  @Valid
  private List<NodeCandidate> nodeCandidates = null;

  public Solution id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Solution costs(Double costs) {
    this.costs = costs;
    return this;
  }

  /**
   * Get costs
   * @return costs
  **/
  @ApiModelProperty(value = "")


  public Double getCosts() {
    return costs;
  }

  public void setCosts(Double costs) {
    this.costs = costs;
  }

  public Solution time(Float time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(value = "")


  public Float getTime() {
    return time;
  }

  public void setTime(Float time) {
    this.time = time;
  }

  public Solution isOptimal(Boolean isOptimal) {
    this.isOptimal = isOptimal;
    return this;
  }

  /**
   * Get isOptimal
   * @return isOptimal
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsOptimal() {
    return isOptimal;
  }

  public void setIsOptimal(Boolean isOptimal) {
    this.isOptimal = isOptimal;
  }

  public Solution valid(Boolean valid) {
    this.valid = valid;
    return this;
  }

  /**
   * Get valid
   * @return valid
  **/
  @ApiModelProperty(value = "")


  public Boolean isValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }

  public Solution nodeCandidates(List<NodeCandidate> nodeCandidates) {
    this.nodeCandidates = nodeCandidates;
    return this;
  }

  public Solution addNodeCandidatesItem(NodeCandidate nodeCandidatesItem) {
    if (this.nodeCandidates == null) {
      this.nodeCandidates = new ArrayList<NodeCandidate>();
    }
    this.nodeCandidates.add(nodeCandidatesItem);
    return this;
  }

  /**
   * Get nodeCandidates
   * @return nodeCandidates
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<NodeCandidate> getNodeCandidates() {
    return nodeCandidates;
  }

  public void setNodeCandidates(List<NodeCandidate> nodeCandidates) {
    this.nodeCandidates = nodeCandidates;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Solution solution = (Solution) o;
    return Objects.equals(this.id, solution.id) &&
        Objects.equals(this.costs, solution.costs) &&
        Objects.equals(this.time, solution.time) &&
        Objects.equals(this.isOptimal, solution.isOptimal) &&
        Objects.equals(this.valid, solution.valid) &&
        Objects.equals(this.nodeCandidates, solution.nodeCandidates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, costs, time, isOptimal, valid, nodeCandidates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Solution {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    costs: ").append(toIndentedString(costs)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    isOptimal: ").append(toIndentedString(isOptimal)).append("\n");
    sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
    sb.append("    nodeCandidates: ").append(toIndentedString(nodeCandidates)).append("\n");
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

