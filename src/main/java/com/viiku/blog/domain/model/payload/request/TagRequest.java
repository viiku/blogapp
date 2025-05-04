package com.viiku.blog.domain.model.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    @NotEmpty(message = "At least one tag should be provided")
    @Size(max = 10, message = "Maximum {max} tags allowed")
    private Set<
            @Size(min = 2, max = 20, message = "Tag name must be between {min} and {max} characters")
//            @Pattern(regexp = )
            String> names;
}
