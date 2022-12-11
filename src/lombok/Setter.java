package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Setter {
    /**
     * If you want your setter to be non-public, you can specify an alternate access level here.
     *
     * @return The setter method will be generated with this access modifier.
     */
    lombok.AccessLevel value() default lombok.AccessLevel.PUBLIC;

    /**
     * Any annotations listed here are put on the generated method.
     * The syntax for this feature depends on JDK version (nothing we can do about that; it's to work around javac bugs).<br>
     * up to JDK7:<br>
     *  {@code @Setter(onMethod=@__({@AnnotationsGoHere}))}<br>
     * from JDK8:<br>
     *  {@code @Setter(onMethod_={@AnnotationsGohere})} // note the underscore after {@code onMethod}.
     *
     * @return List of annotations to apply to the generated setter method.
     */
    AnyAnnotation[] onMethod() default {};

    /**
     * Any annotations listed here are put on the generated method's parameter.
     * The syntax for this feature depends on JDK version (nothing we can do about that; it's to work around javac bugs).<br>
     * up to JDK7:<br>
     *  {@code @Setter(onParam=@__({@AnnotationsGoHere}))}<br>
     * from JDK8:<br>
     *  {@code @Setter(onParam_={@AnnotationsGohere})} // note the underscore after {@code onParam}.
     *
     * @return List of annotations to apply to the generated parameter in the setter method.
     */
    AnyAnnotation[] onParam() default {};

    /**
     * Placeholder annotation to enable the placement of annotations on the generated code.
     * @deprecated Don't use this annotation, ever - Read the documentation.
     */
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    @Target({})
    @interface AnyAnnotation {}
}