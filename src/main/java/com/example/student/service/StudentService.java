package com.example.student.service;

import com.example.student.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    // Load CSV automatically when app starts
    @PostConstruct
    public void loadStudentsFromCSV() {
        Set<Integer> uniqueIds = new HashSet<>();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            getClass().getResourceAsStream("/students.csv")
                    )
            );

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double cgpa = Double.parseDouble(data[2]);

                // Ignore duplicate IDs
                if (uniqueIds.add(id)) {
                    students.add(new Student(id, name, cgpa));
                }
            }

            // Sort by ID using Comparable
            Collections.sort(students);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // Sort by CGPA (Descending) using Comparator
    public List<Student> getStudentsSortedByCgpa() {
        List<Student> sortedList = new ArrayList<>(students);

        sortedList.sort((s1, s2) -> Double.compare(s2.getCgpa(), s1.getCgpa()));
        return sortedList;
    }

    // Delete student by ID using Iterator
    public boolean deleteStudentById(int id) {
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove(); // safe removal
                return true;
            }
        }
        return false;
    }
}
