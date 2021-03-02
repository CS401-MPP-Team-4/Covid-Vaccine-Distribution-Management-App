package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public abstract class AbstractCRUDController<T> implements Initializable {
	@FXML
	TableView<T> crudTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AbstractCRUDController");

		bindColums();
		fillTable();
		init(location, resources);
	}

	public abstract void init(URL location, ResourceBundle resources);

	abstract public List<T> fetchData();

	public void fillTable() {
		ObservableList<T> list = FXCollections.observableList(fetchData());
		crudTable.setItems(list);
	}
	
	public T getById(Integer id) {
		return null;
	}

	private void bindColums() {
		Class<?> cls = this.getClass();

		Stream.of(cls.getDeclaredFields()).filter(f -> f.getType().isAssignableFrom(TableColumn.class)).forEach(f -> {
			if (f.isAnnotationPresent(Bind.class)) {
				Bind annotation = (Bind) f.getAnnotation(Bind.class);
				try {
					f.setAccessible(true);
					TableColumn<?, ?> col = (TableColumn<?, ?>) f.get(this);
					col.setCellValueFactory(new PropertyValueFactory<>(annotation.field()));
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	private void fillDetails(T model) {
		Class<?> cls = this.getClass();

		Stream.of(cls.getDeclaredFields()).filter(f -> f.getType().isAssignableFrom(TableColumn.class)).forEach(f -> {
			if (f.isAnnotationPresent(Bind.class)) {
				Bind annotation = (Bind) f.getAnnotation(Bind.class);
				try {
					f.setAccessible(true);
					Label label = (Label) f.get(this);
					String value = getModelPropertyValue<T>(model.getClass(), model, annotation.field());
					label.setText(value);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
	
	private static <T> String getModelPropertyValue(T model, String propertyName) {
		
		Class<T> cls = (Class<T>) model.getClass();
		
		String getterName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		
		Method getterMethod;
		try {
			getterMethod = cls.getMethod(getterName, null);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		
		try {
			return String.valueOf(getterMethod.invoke(model, null));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public TableView<T> getCrudTable() {
		return crudTable;
	}

}
