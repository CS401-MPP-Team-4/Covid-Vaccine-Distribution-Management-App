package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.views.crudHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public TableView<T> getCrudTable() {
		return crudTable;
	}

}
