package es.upm.miw.dtos.input;

import es.upm.miw.dtos.validations.ListNotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class FamilySizeInputDto {

    @NotNull
    private String reference;

    @NotNull
    private String description;

    @NotNull
    private String provider;

    @ListNotEmpty
    private ArrayList<String> sizesArray;

    public FamilySizeInputDto() {
        // Empty for framework
    }

    public FamilySizeInputDto(String reference, String description, String provider, ArrayList<String> sizesArray) {
        this.reference = reference;
        this.description = description;
        this.provider = provider;
        this.sizesArray = sizesArray;
    }

    public String getReference() {
        return reference;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getSizesArray() {
        return sizesArray;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public String toString() {
        return "FamilySizeInputDto{" +
                "reference='" + reference + '\'' +
                ", description='" + description + '\'' +
                ", provider='" + provider + '\'' +
                ", sizeArray='" + sizesArray + '\'' +
                '}';
    }
}
