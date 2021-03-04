package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.sql.SQLException;
import java.util.List;

public abstract class RepositoryService<T> {

	abstract public List<T> getAll() throws SQLException;
	
	abstract public T getById(Integer id) throws SQLException;
	
	abstract public boolean add(T model);
	
	public Integer addNew(T model) throws SQLException // should return new Id
	{
		return 0;
	}
	
	abstract public boolean update(T model);
	
	abstract public boolean delete(Integer id);
}
