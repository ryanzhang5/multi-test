package org.ms.springquartz;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("csvItemProcessor")
public class CsvItemProcessor implements ItemProcessor<Student, Student> {

	public Student process(Student student) throws Exception {
		student.setName(student.getId() + "--" + student.getName());
		student.setAge(student.getAge() + 2);
		student.setScore(student.getScore() + 10);
		return student;
	}
}