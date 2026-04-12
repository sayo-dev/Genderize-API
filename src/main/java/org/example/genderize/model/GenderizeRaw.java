package org.example.genderize.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenderizeRaw {

    private Integer count;

    private String name;

    private String gender;

    private Double probability;


}
