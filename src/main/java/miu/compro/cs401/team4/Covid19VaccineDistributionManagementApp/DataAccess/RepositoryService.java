package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.util.List;

public abstract class RepositoryService<T> {

	abstract public List<T> getAll();
	
	abstract public T getById(Integer id);
	
	abstract public boolean add(T model); // should return new Id
	
	abstract public boolean update(T model);
	
	abstract public boolean delete(Integer id);
}
