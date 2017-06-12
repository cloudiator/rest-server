package io.github.cloudiator.rest.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VirtualMachineRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T13:00:29.446+02:00")

public class VirtualMachineRequest {
    @JsonProperty("image")
    private String image = null;

    @JsonProperty("hardware")
    private String hardware = null;

    @JsonProperty("location")
    private String location = null;

    public VirtualMachineRequest image(String image) {
        this.image = image;
        return this;
    }

    /**
     * Get image
     *
     * @return image
     **/
    @ApiModelProperty(value = "")
    @NotNull
    @Valid


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public VirtualMachineRequest hardware(String hardware) {
        this.hardware = hardware;
        return this;
    }

    /**
     * Get hardware
     *
     * @return hardware
     **/
    @ApiModelProperty(value = "")
    @NotNull
    @Valid


    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public VirtualMachineRequest location(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get location
     *
     * @return location
     **/
    @ApiModelProperty(value = "")
    @NotNull
    @Valid


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
        VirtualMachineRequest virtualMachineRequest = (VirtualMachineRequest) o;
        return Objects.equals(this.image, virtualMachineRequest.image) &&
                Objects.equals(this.hardware, virtualMachineRequest.hardware) &&
                Objects.equals(this.location, virtualMachineRequest.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, hardware, location);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VirtualMachineRequest {\n");

        sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    hardware: ").append(toIndentedString(hardware)).append("\n");
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

