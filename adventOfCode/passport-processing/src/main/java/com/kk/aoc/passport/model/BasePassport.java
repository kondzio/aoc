package com.kk.aoc.passport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BasePassport implements Passport {
    @JsonProperty
    private Integer byr; //byr (Birth Year)
    @JsonProperty
    private Integer iyr; //iyr (Issue Year)
    @JsonProperty
    private Integer eyr; //eyr (Expiration Year)
    @JsonProperty
    private String hgtUnit; //hgtUnit(Height)
    @JsonProperty
    private Integer hgtCmValue; //hgtValue(Height)
    @JsonProperty
    private Integer hgtInValue; //hgtValue(Height)
    @JsonProperty
    private String hgt; //hgtValue(Height)
    @JsonProperty
    private String hcl; //hcl (Hair Color)
    @JsonProperty
    private String ecl; //ecl (Eye Color)
    @JsonProperty
    private String pid; //pid (Passport ID)
    @JsonProperty
    private String cid; //cid (Country ID)
}
