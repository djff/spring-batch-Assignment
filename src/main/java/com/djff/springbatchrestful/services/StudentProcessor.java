package com.djff.springbatchrestful.services;

import com.djff.springbatchrestful.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentProcessor.class);
    @Override
    public Student process(Student student) throws Exception {

        final LocalDate currentDate = LocalDate.now();
        final LocalDate birthDate = currentDate.minusYears(student.getAge());
        student.setDate(birthDate);

        log.info("Setting (" + student + ") birth date as " + student.getDate());

        return student;
    }
}
