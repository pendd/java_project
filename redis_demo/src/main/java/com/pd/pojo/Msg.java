package com.pd.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-06 8:14 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg implements Serializable {

  private static final long serialVersionUID = -7020539265775463658L;
  private Integer id;
  private String message;
}
