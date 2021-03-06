package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils;

import java.beans.Introspector;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public final class Utils {

	public static void setBoundedData(Object model, Object controller, Object emptyValue) {
		Class<?> cls = controller.getClass();

		Stream.of(cls.getDeclaredFields())
		.filter(f -> f.getType().isAssignableFrom(Label.class) 
				|| f.getType().isAssignableFrom(TextField.class)
				|| f.getType().isAssignableFrom(ChoiceBox.class)
				).forEach(ctrl -> {
			if (ctrl.isAnnotationPresent(Bind.class)) {
				Bind annotation = (Bind) ctrl.getAnnotation(Bind.class);
				try {
					ctrl.setAccessible(true);
					setControlData((Control) ctrl.get(controller), model, annotation.value(), emptyValue);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	public static void setControlData(Control control, Object model, String propName, Object emptyValue) {
		Optional<Object> value = model == null ? Optional.ofNullable(emptyValue)
				: getModelPropertyValue(model, propName);
		
		if (control instanceof Label) {
			Label lbl = (Label) control;
			lbl.setText(value.map(r -> String.valueOf(r)).orElse((String) emptyValue));
		} else if (control instanceof TextField) {
			TextField txt = (TextField) control;
			txt.setText(value.map(r -> String.valueOf(r)).orElse((String) emptyValue));
		}
		
		else if (control instanceof ChoiceBox<?>) {
			ChoiceBox ccb = (ChoiceBox) control;
			ccb.setValue(value.orElse(null));
		}
	}
	
	public static void getBoundedData(Object model, Object controller, Object emptyValue) {
		Class<?> cls = controller.getClass();

		Stream.of(cls.getDeclaredFields())
		.filter(f -> f.getType().isAssignableFrom(Label.class) 
				|| f.getType().isAssignableFrom(TextField.class)
				|| f.getType().isAssignableFrom(ChoiceBox.class)
				).forEach(ctrl -> {
			if (ctrl.isAnnotationPresent(Bind.class)) {
				Bind annotation = (Bind) ctrl.getAnnotation(Bind.class);
				try {
					ctrl.setAccessible(true);
					getControlData((Control) ctrl.get(controller), model, annotation.value());
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
	
	
	public static void getControlData(Control control, Object model, String propName) {
		Object value = null;
		if (control instanceof TextField) {
			TextField txt = (TextField) control;
			value = txt.getText();
		}
		else if (control instanceof ChoiceBox) {
			ChoiceBox ccb = (ChoiceBox) control;
			value = ccb.getValue();
		}
		setModelPropertyValue(model, propName, value);
	}
	

	private static <T> Optional<Object> getModelPropertyValue(T model, String propertyName) {
		try {
			Class<?> cls = model.getClass();

			return Arrays.stream(Introspector.getBeanInfo(cls).getPropertyDescriptors())
					.filter(pd -> pd.getReadMethod() != null && propertyName.equals(pd.getName())).findAny()
					.map(m -> m.getReadMethod()).map(m -> {
						try {
							return m.invoke(model);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					});

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	private static <T> void setModelPropertyValue(T model, String propertyName, Object value) {
		try {
			Class<?> cls = model.getClass();

			Arrays.stream(Introspector.getBeanInfo(cls).getPropertyDescriptors())
				.filter(pd -> pd.getWriteMethod() != null && propertyName.equals(pd.getName())).findAny()
				.map(m -> m.getWriteMethod())
				.ifPresent(m -> {
					try {
						if(m.getParameters()[0].getType() == int.class) {
							m.invoke(model, Integer.parseInt((String)value));
						}
						else {
							m.invoke(model, value);
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				});

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
