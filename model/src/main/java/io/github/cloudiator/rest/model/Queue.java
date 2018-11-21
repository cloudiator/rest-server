package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.QueueStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a queued task within the system. Used to execute long running requests. 
 */
@ApiModel(description = "Represents a queued task within the system. Used to execute long running requests. ")
@Validated

public class Queue   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("status")
  private QueueStatus status = null;

  @JsonProperty("start")
  private OffsetDateTime start = null;

  @JsonProperty("end")
  private OffsetDateTime end = null;

  @JsonProperty("diagnosis")
  private String diagnosis = null;

  @JsonProperty("location")
  private String location = null;

  public Queue id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the queued task
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier for the queued task")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Queue status(QueueStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")

  @Valid

  public QueueStatus getStatus() {
    return status;
  }

  public void setStatus(QueueStatus status) {
    this.status = status;
  }

  public Queue start(OffsetDateTime start) {
    this.start = start;
    return this;
  }

  /**
   * Get start
   * @return start
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getStart() {
    return start;
  }

  public void setStart(OffsetDateTime start) {
    this.start = start;
  }

  public Queue end(OffsetDateTime end) {
    this.end = end;
    return this;
  }

  /**
   * Get end
   * @return end
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getEnd() {
    return end;
  }

  public void setEnd(OffsetDateTime end) {
    this.end = end;
  }

  public Queue diagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
    return this;
  }

  /**
   * Gives human-readable feedback
   * @return diagnosis
  **/
  @ApiModelProperty(value = "Gives human-readable feedback")


  public String getDiagnosis() {
    return diagnosis;
  }

  public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }

  public Queue location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Location of the original entity
   * @return location
  **/
  @ApiModelProperty(value = "Location of the original entity")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Queue queue = (Queue) o;
    return Objects.equals(this.id, queue.id) &&
        Objects.equals(this.status, queue.status) &&
        Objects.equals(this.start, queue.start) &&
        Objects.equals(this.end, queue.end) &&
        Objects.equals(this.diagnosis, queue.diagnosis) &&
        Objects.equals(this.location, queue.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, start, end, diagnosis, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Queue {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    diagnosis: ").append(toIndentedString(diagnosis)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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

