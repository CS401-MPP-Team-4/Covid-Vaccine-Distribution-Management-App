package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
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

public abstract class AbstractCRUDListController<T> implements Initializable {
	@FXML
	TableView<T> crudTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindColums();
		fillTable();
		bindEvents();
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

		Stream.of(cls.getDeclaredFields()).filter(f -> f.getType().isAssignableFrom(Label.class)).forEach(f -> {
			if (f.isAnnotationPresent(Bind.class)) {
				Bind annotation = (Bind) f.getAnnotation(Bind.class);
				try {
					f.setAccessible(true);
					Label label = (Label) f.get(this);
					String value = getModelPropertyValue(model, annotation.field());
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


		try {
			Class<?> cls = model.getClass();

//			String getterName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);

			return Arrays.stream(Introspector.getBeanInfo(cls).getPropertyDescriptors())
			.filter(pd-> pd.getReadMethod() != null && propertyName.equals(pd.getName()))
			.findAny()
			.map(m-> m.getReadMethod())
			.map(m -> {
				try {
					return m.invoke(model);
				}  catch (Exception e) {
					throw new RuntimeException(e);
				}
			})
			.map(r -> String.valueOf(r)).orElse("");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void bindEvents() {
		getCrudTable().getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> fillDetails(newValue));
	}
	
	public TableView<T> getCrudTable() {
		return crudTable;
	}

}
