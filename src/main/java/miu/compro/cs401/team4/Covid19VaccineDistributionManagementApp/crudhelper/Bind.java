package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.UnaryOperator;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Bind {
	String field();
}
