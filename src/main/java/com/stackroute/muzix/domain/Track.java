package com.stackroute.muzix.domain;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// Lombok annotations
@Data
public class Track {

  @Id
  private int trackId;

  private String trackName;
  private String comments;

}
