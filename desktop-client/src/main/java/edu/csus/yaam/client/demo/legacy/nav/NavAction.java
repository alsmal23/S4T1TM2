package edu.csus.yaam.client.demo.legacy.nav;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ryan R
 * @date 3/27/2018
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NavAction {
    String name();

    FontAwesomeIcon icon();

    boolean rightAlign() default false;
}