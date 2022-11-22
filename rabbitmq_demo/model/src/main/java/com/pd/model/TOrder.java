package com.pd.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class TOrder implements Serializable {
  private static final long serialVersionUID = 6206628615994972919L;
  private Integer id;
  private String name;
  private String messageId;

  private Integer scoreId;
  private List<Integer> ids;
}
