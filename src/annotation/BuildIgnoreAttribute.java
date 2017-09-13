package annotation;

public @interface BuildIgnoreAttribute {
	boolean ignore() default true;
}
