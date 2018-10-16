package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Schedules an already created job within the system. 
 */
@ApiModel(description = "Schedules an already created job within the system. ")
@Validated

public class ScheduleNew   {
  @JsonProperty("job")
  private String job = null;

  /**
   * If the instantiation should be handled AUTOMATIC or MANUAL
   */
  public enum InstantiationEnum {
    AUTOMATIC("AUTOMATIC"),
    
    MANUAL("MANUAL");

    private String value;

    InstantiationEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static InstantiationEnum fromValue(String text) {
      for (InstantiationEnum b : InstantiationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("instantiation")
  private InstantiationEnum instantiation = null;

  public ScheduleNew job(String job) {
    this.job = job;
    return this;
  }

  /**
   * The identifier of the job
   * @return job
  **/
  @ApiModelProperty(value = "The identifier of the job")


  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public ScheduleNew instantiation(InstantiationEnum instantiation) {
    this.instantiation = instantiation;
    return this;
  }

  /**
   * If the instantiation should be handled AUTOMATIC or MANUAL
   * @return instantiation
  **/
  @ApiModelProperty(value = "If the instantiation should be handled AUTOMATIC or MANUAL")


  public InstantiationEnum getInstantiation() {
    return instantiation;
  }

  public void setInstantiation(InstantiationEnum instantiation) {
    this.instantiation = instantiation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScheduleNew scheduleNew = (ScheduleNew) o;
    return Objects.equals(this.job, scheduleNew.job) &&
        Objects.equals(this.instantiation, scheduleNew.instantiation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(job, instantiation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduleNew {\n");
    
    sb.append("    job: ").append(toIndentedString(job)).append("\n");
    sb.append("    instantiation: ").append(toIndentedString(instantiation)).append("\n");
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

