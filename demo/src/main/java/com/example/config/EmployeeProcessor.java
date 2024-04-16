package com.example.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.example.entity.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{

	// Process data
	@Override
	public Employee process(Employee item) throws Exception {
		// logic that needs to apply on data before writing into db.
		//String[] strArr = item.getDob().toString().split("/");
		//LocalDate date = item.getDob();
//		System.out.println("#################");
//		System.out.println(item.getDob() instanceof LocalDate);
//		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
//        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
//       
//        Date date = format1.parse(item.getDob().toString());
//        String str = format2.format(date);
//        System.out.println("#############");
//        System.out.println(str);
        
        return item;
	}

}