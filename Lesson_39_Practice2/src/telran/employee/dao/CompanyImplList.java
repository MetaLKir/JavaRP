package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class CompanyImplList implements Company {
	List<Employee> employees;
	int capacity;// max employee quantity

	public CompanyImplList(int capacity) {
		this.capacity = capacity;
		//employees = new ArrayList<>();
		employees = new LinkedList<>();

	}

	@Override
	public boolean addEmployee(Employee employee) {
		if (employee == null || employees.size() == capacity || findEmployee(employee.getId()) != null) {
			return false;
		}
//		employees[size] = employee;
//		size++;
		return employees.add(employee);
	}

	@Override
	public Employee removeEmployee(int id) {
//		for (int i = 0; i < size; i++) {
//			if (employees[i].getId() == id) {
//				Employee victim = employees[i];
//				employees[i] = employees[size - 1];
//				size--;
//				employees[size] = null;
//				return victim;
//			}
//		}
//		return null;
		
		Employee victim = findEmployee(id);
		employees.remove(victim);
		return victim;
	}

	@Override
	public Employee findEmployee(int id) {
//		for (int i = 0; i < size; i++) {
//			if (employees[i].getId() == id) {
//				return employees[i];
//			}
//		}
//		return null;
		for (Employee e : employees) {
			if(e.getId()==id)
				return e;
		}
		return null;
	}

	@Override
	public double totalSalary() {
		double sum = 0;
//		for (int i = 0; i < size; i++) {
//			sum += employees[i].calcSalary();
//		}
		for (Employee e : employees) {
			sum+=e.calcSalary();
		}
		return sum;
	}

//    @Override
//    public double averageSalary() {
//        return totalSalary() / size;
//    }

	@Override
	public int quantity() {
		return employees.size();
	}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@Override
	public void printEmployees() {
//		for (int i = 0; i < size; i++) {
//			System.out.println(employees[i]);
//		}
		for (Employee e : employees) {
			System.out.println(e);
		}
	}

	@Override
	public double totalSales() {
		double sum = 0;
//		for (int i = 0; i < size; i++) {
//			if (employees[i] instanceof SalesManager) {
//				SalesManager sm = (SalesManager) employees[i];
//				sum += sm.getSalesValue();
//			}
//		}
		for (Employee e : employees) {
			if (e instanceof SalesManager) {
				SalesManager sm = (SalesManager) e;
				sum += sm.getSalesValue();
			}
		}
		return sum;
	}

	@Override
	public Employee[] findEmployeesHoursGreaterThan(int hours) {
		return findEmployeesByPredicate((e) -> e.getHours() >= hours);
	}

	@Override
	public Employee[] findEmployeesSalaryBetween(double min, double max) {
		Predicate<Employee> predicate = new Predicate<Employee>() {
			@Override
			public boolean test(Employee employee) {
				return employee.calcSalary() >= min && employee.calcSalary() < max;
			}
		};
		return findEmployeesByPredicate(predicate);
	}

	private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
//		int count = 0;
//		for (int i = 0; i < size; i++) {
//			if (predicate.test(employees[i])) {
//				count++;
//			}
//		}
//		Employee[] res = new Employee[count];
//		for (int i = 0, j = 0; i < size; i++) {
//			if (predicate.test(employees[i])) {
//				res[j++] = employees[i];
//			}
//		}
//		return res;
		List<Employee> res = new ArrayList<>();
		for (Employee e : employees) {
			if(predicate.test(e))
				res.add(e);
		}
		return res.toArray(new Employee[0]);
		//return res.toArray(new Employee[res.size()]);
				//Object[] toArray()
				//<T> T[] toArray(T[] a)
	}
}
