package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.util.List;

public abstract class RepositoryService<T> {

	abstract public List<T> getAll();
	
	abstract public T getById(Integer id);
	
	abstract public Integer add(T model); // should return new Id
	
	abstract public void update(T model);
	
	abstract public void delete(Integer id);
}
