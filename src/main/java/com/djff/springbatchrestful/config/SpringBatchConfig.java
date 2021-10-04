package com.djff.springbatchrestful.config;
import com.djff.springbatchrestful.models.Student;
import com.djff.springbatchrestful.repositories.StudentRepository;
import com.djff.springbatchrestful.services.StudentProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    final JobBuilderFactory jobBuilderFactory;
    final StepBuilderFactory stepBuilderFactory;
    final StudentRepository studentRepository;

    public SpringBatchConfig(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory,
                             StudentRepository studentRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.studentRepository = studentRepository;
    }

    @Bean
    public Job job(ItemReader<Student> itemReader, ItemWriter<Student> itemWriter){
        System.out.println("It has been called here");
        Step step = stepBuilderFactory.get("ETL-file-load")
                        .<Student, Student>chunk(100)
                        .reader(itemReader)
                        .processor(processor())
                        .writer(itemWriter)
                        .build();
        return jobBuilderFactory.get("ETL-Process")
                    .incrementer(new RunIdIncrementer())
                    .flow(step)
                    .end()
                    .build();
    }

    @Bean
    public StudentProcessor processor(){
        return new StudentProcessor();
    }

    @Bean
    public FlatFileItemReader<Student> reader() {
        return new FlatFileItemReaderBuilder<Student>()
                .name("studentFileReader")
                .resource(new ClassPathResource("student.csv"))
                .delimited()
                .names("firstName", "lastName", "gpa", "age")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>()
                {{
                    setTargetType(Student.class);
                }}).build();
    }

    @Bean
    public ItemWriter<Student> itemWriter(){
        return studentRepository::saveAll;
    }

}
