package com.rain.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rain.dao.DeptDao;
import com.rain.dao.EmployeeDao;
import com.rain.dao.JobDao;
import com.rain.domain.Dept;
import com.rain.domain.Employee;
import com.rain.domain.Job;
import com.rain.service.RainService;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("RainService")
public class RainServiceImpl implements RainService{

	@Autowired
	private DeptDao deptDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployeeDao employeedao;

	/**
	 * 部门信息的管理
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		return deptDao.selectAllDept();
	}
	@Override
	public void addDept(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.save(dept);
	}
	
	@Override
	public Dept get_Info(Integer id) {
		// TODO Auto-generated method stub
		Dept dept = deptDao.get_Info(id);
		return dept;
	}
	@Override
	public void update_Info(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.update_Info(dept);
	}
	@Override
	public void delete_Info(Integer id) {
		// TODO Auto-generated method stub
		deptDao.delete_Info(id);
	}
	@Override
	public List<Dept> findAllDept(String content) {
		// TODO Auto-generated method stub
		System.out.println(content);
		return deptDao.selectLikeAllDept(content);
	}
	/**
	 * 职位管理的操作
	 */
	@Override
	public List<Job> findAllJob() {
		// TODO Auto-generated method stub
		return jobDao.get_List();
	}
	@Override
	public List<Job> findAllJob(String content) {
		// TODO Auto-generated method stub
		return jobDao.get_LikeList(content);
	}
	
	@Override
	public Job get_JobInfo(Integer id) {
		// TODO Auto-generated method stub
		return jobDao.get_Info(id);
	}
	@Override
	public void update_JobInfo(Job job) {
		// TODO Auto-generated method stub
		jobDao.update_Info(job);
	}
	@Override
	public void insert_JobInfo(Job job) {
		// TODO Auto-generated method stub
		jobDao.insert_Info(job);
	}
	@Override
	public void delete_JobInfo(Integer id) {
		// TODO Auto-generated method stub
		jobDao.delete_Info(id);
	}
	/**
	 * 员工信息的管理
	 */
	@Override
	public List<Employee> get_EmployeeList() {
		// TODO Auto-generated method stub
		/**
		 * 将部门，职位id中的信息提取出来
		 */
		List<Employee> list = employeedao.get_List();
		int size = list.size();
		List<Employee> list2 = new ArrayList<>();
		for(int i = 0;i<size;i++){
			Employee data = list.get(i);
			Dept dept = deptDao.get_Info(data.getDept_id());
			data.setDept(dept);
			Job job = jobDao.get_Info(data.getJob_id());
			data.setJob(job);
			list2.add(i,data);
		}
		return list2;
	}
	@Override
	public List<Employee> get_EmployeeLikeList(String content) {
		// TODO Auto-generated method stub
		/**
		 * 将部门，职位id中的信息提取出来
		 */
		List<Employee> list = employeedao.get_LikeList(content);
		int size = list.size();
		List<Employee> list2 = new ArrayList<>();
		for(int i = 0;i<size;i++){
			Employee data = list.get(i);
			Dept dept = deptDao.get_Info(data.getDept_id());
			data.setDept(dept);
			Job job = jobDao.get_Info(data.getJob_id());
			data.setJob(job);
			list2.add(i,data);
		}
		return list2;
	}
	@Override
	public Employee get_EmployeeInfo(Integer id) {
		// TODO Auto-generated method stub
		Employee data = employeedao.get_Info(id);
		Dept dept = deptDao.get_Info(data.getDept_id());
		data.setDept(dept);
		Job job = jobDao.get_Info(data.getJob_id());
		data.setJob(job);
		return data;
	}
	@Override
	public void update_EmployeeInfo(Employee data) {
		// TODO Auto-generated method stub
		employeedao.update_Info(data);
	}
	@Override
	public void insert_EmployeeInfo(Employee data) {
		// TODO Auto-generated method stub
		/**
		 * 将职位id和部门id提取，或者不管，因为直接存到数据库了，不管
		 */
		Date date = new Date();    
		String year = String.format("%tY", date);   
		String month = String.format("%tB", date);   
		String day = String.format("%te", date);   
		data.setCreate_date(year+month+day);
		employeedao.insert_Info(data);
	}
	@Override
	public void delete_EmployeeInfo(Integer id) {
		// TODO Auto-generated method stub
		employeedao.delete_Info(id);
	}
}
