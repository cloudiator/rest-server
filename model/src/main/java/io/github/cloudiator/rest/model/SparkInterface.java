package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.TaskInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of TaskInterface. Describes how to deploy a Task to Spark. 
 */
@ApiModel(description = "Subtype of TaskInterface. Describes how to deploy a Task to Spark. ")
@Validated

public class SparkInterface extends TaskInterface  {
  @JsonProperty("file")
  private String file = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("arguments")
  @Valid
  private List<String> arguments = null;

  @JsonProperty("sparkArguments")
  private java.util.Map sparkArguments = null;

  @JsonProperty("sparkConfiguration")
  private java.util.Map sparkConfiguration = null;

  /**
   * defines the deploy mode of the Spark job, SUBMIT only deploys the Spark application to an existing cluster, | SCALE adds additional workers to the cluster, | SCALE_SUBMIT adds first additional workers to the cluster and submits the job after the workers are added 
   */
  public enum DeployModeEnum {
    SCALE("SCALE"),
    
    SUBMIT("SUBMIT"),
    
    SCALE_SUBMIT("SCALE_SUBMIT");

    private String value;

    DeployModeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DeployModeEnum fromValue(String text) {
      for (DeployModeEnum b : DeployModeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("deployMode")
  private DeployModeEnum deployMode = null;

  public SparkInterface file(String file) {
    this.file = file;
    return this;
  }

  /**
   * An URI where the executable of the Spark Application can be retrieved. Either a URL pointing to a web endpoint, or a locally available file. 
   * @return file
  **/
  @ApiModelProperty(value = "An URI where the executable of the Spark Application can be retrieved. Either a URL pointing to a web endpoint, or a locally available file. ")


  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  public SparkInterface className(String className) {
    this.className = className;
    return this;
  }

  /**
   * Optional className of the class containing the main method. Only required for Java. 
   * @return className
  **/
  @ApiModelProperty(value = "Optional className of the class containing the main method. Only required for Java. ")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public SparkInterface arguments(List<String> arguments) {
    this.arguments = arguments;
    return this;
  }

  public SparkInterface addArgumentsItem(String argumentsItem) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<String>();
    }
    this.arguments.add(argumentsItem);
    return this;
  }

  /**
   * Array of arguments passed to the application. 
   * @return arguments
  **/
  @ApiModelProperty(value = "Array of arguments passed to the application. ")


  public List<String> getArguments() {
    return arguments;
  }

  public void setArguments(List<String> arguments) {
    this.arguments = arguments;
  }

  public SparkInterface sparkArguments(java.util.Map sparkArguments) {
    this.sparkArguments = sparkArguments;
    return this;
  }

  /**
   * Additional Arguments passed to Spark. 
   * @return sparkArguments
  **/
  @ApiModelProperty(value = "Additional Arguments passed to Spark. ")

  @Valid

  public java.util.Map getSparkArguments() {
    return sparkArguments;
  }

  public void setSparkArguments(java.util.Map sparkArguments) {
    this.sparkArguments = sparkArguments;
  }

  public SparkInterface sparkConfiguration(java.util.Map sparkConfiguration) {
    this.sparkConfiguration = sparkConfiguration;
    return this;
  }

  /**
   * Spark configuration properties. 
   * @return sparkConfiguration
  **/
  @ApiModelProperty(value = "Spark configuration properties. ")

  @Valid

  public java.util.Map getSparkConfiguration() {
    return sparkConfiguration;
  }

  public void setSparkConfiguration(java.util.Map sparkConfiguration) {
    this.sparkConfiguration = sparkConfiguration;
  }

  public SparkInterface deployMode(DeployModeEnum deployMode) {
    this.deployMode = deployMode;
    return this;
  }

  /**
   * defines the deploy mode of the Spark job, SUBMIT only deploys the Spark application to an existing cluster, | SCALE adds additional workers to the cluster, | SCALE_SUBMIT adds first additional workers to the cluster and submits the job after the workers are added 
   * @return deployMode
  **/
  @ApiModelProperty(value = "defines the deploy mode of the Spark job, SUBMIT only deploys the Spark application to an existing cluster, | SCALE adds additional workers to the cluster, | SCALE_SUBMIT adds first additional workers to the cluster and submits the job after the workers are added ")


  public DeployModeEnum getDeployMode() {
    return deployMode;
  }

  public void setDeployMode(DeployModeEnum deployMode) {
    this.deployMode = deployMode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SparkInterface sparkInterface = (SparkInterface) o;
    return Objects.equals(this.file, sparkInterface.file) &&
        Objects.equals(this.className, sparkInterface.className) &&
        Objects.equals(this.arguments, sparkInterface.arguments) &&
        Objects.equals(this.sparkArguments, sparkInterface.sparkArguments) &&
        Objects.equals(this.sparkConfiguration, sparkInterface.sparkConfiguration) &&
        Objects.equals(this.deployMode, sparkInterface.deployMode) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(file, className, arguments, sparkArguments, sparkConfiguration, deployMode, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SparkInterface {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    arguments: ").append(toIndentedString(arguments)).append("\n");
    sb.append("    sparkArguments: ").append(toIndentedString(sparkArguments)).append("\n");
    sb.append("    sparkConfiguration: ").append(toIndentedString(sparkConfiguration)).append("\n");
    sb.append("    deployMode: ").append(toIndentedString(deployMode)).append("\n");
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

