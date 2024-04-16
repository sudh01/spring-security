package com.example.config;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Configuration
public class CsvBatchConfig {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	
	// Method to read csv file using ItemReader
	@Bean
	FlatFileItemReader<Employee> empReader() {
		
		FlatFileItemReader<Employee> itemReader= new FlatFileItemReader<>();
		
		// set employees csv file path from where itemReader has to read
		itemReader.setResource(new FileSystemResource("D:\\workspace\\Spring-Mps\\demo\\src\\main\\resources\\employees-data-100.csv"));
		
		// set name to item reader
		itemReader.setName("csvReader");
		
		// Skip header line while reading data from csv file
		itemReader.setLinesToSkip(1);
		
		//Convert csv line to Java employee object
		itemReader.setLineMapper(lineMapper());
		
		return itemReader;
		
		
	}

	// Method to return LineMapper Object
	
	private LineMapper<Employee> lineMapper() {
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
	
		// Set delimiter 
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		
		// set null as column value in case any column value not present
		lineTokenizer.setStrict(false);
		
		// Order of data available
		lineTokenizer.setNames("empId", "firstName", "lastName", "gender", "email", "jobTitle");
		
		// Convert csv line to Employee object
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Employee.class);
		
		// update lineMapper with lineTokenizer & fieldSetMapper
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		// return lineMapper Object
		return lineMapper;
	}

	// Create ItemProcessor
	public EmployeeProcessor empProcessor() {
		return new EmployeeProcessor();
	}
	
	// create ItemWrite
	@Bean
	public RepositoryItemWriter<Employee> empWriter() {
		RepositoryItemWriter<Employee> repoWriter = new RepositoryItemWriter<>();
		
		// set empRepo to write data into db
		repoWriter.setRepository(empRepo);
		
		// JPA method name to be used to insert data into db
		repoWriter.setMethodName("save");
		
		return repoWriter;
	}
	
	// Create Step
	// <inputRecordType, outputRecordType>chunk(10)- process 10 records
	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("csv-step", jobRepository)
				.<Employee, Employee>chunk(10, transactionManager)
				.reader(empReader())
				.processor(empProcessor())
				.writer(empWriter())
				.build();
	}
	
	// Create Job
	@Bean
	public Job runJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("importEmployees", jobRepository)
						.flow(step(jobRepository, transactionManager))
						.end()
						.build();
	}
	
	
	// Create Job Launcher
}