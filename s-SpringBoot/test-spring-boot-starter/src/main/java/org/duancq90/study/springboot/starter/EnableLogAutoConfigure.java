package org.duancq90.study.springboot.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/6/18 下午2:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({LogAutoConfigure.class})
public @interface EnableLogAutoConfigure {

}
