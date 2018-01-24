package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a tenant of the system. 
 */
@ApiModel(description = "Represents a tenant of the system. ")
@Validated

public class Tenant   {
  @JsonProperty("tenant")
  private String tenant = null;

  public Tenant tenant(String tenant) {
    this.tenant = tenant;
    return this;
  }

   /**
   * The tenant
   * @return tenant
  **/
  @ApiModelProperty(required = true, value = "The tenant")
  @NotNull


  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tenant tenant = (Tenant) o;
    return Objects.equals(this.tenant, tenant.tenant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tenant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tenant {\n");
    
    sb.append("    tenant: ").append(toIndentedString(tenant)).append("\n");
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

