package org.fwx.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Payment
 * @Description
 * @Author Fwx
 * @Date 2024/5/18 10:08
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private Long id;
    private String serial;

}
