package com.spring.demoSpring;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class SampleController {
    @GetMapping("/home")
    public String home() {
        return "LOGIN ID :- tejasghatbandhe@gmail.com/n PASSWORD :- ********";

    }

    @GetMapping("/msg")
    public String msg() {
//        System.out.println("PRAJYOT - Hiii" +
//                "/n YASH - HELLO " +
//                "/n MOHAN - HEY " +
//                "/n CHANDRAKANT - KASA AAHE BHAU " +
//                "/n VAIBHAV - JAVA C");
        return "PRAJYOT - Hiii  YASH - HELLO  MOHAN - HEY CHANDRAKANT - KASA AAHE BHAU VAIBHAV - JAVA C";
    }

    @GetMapping("/contact")
    public String contact() {
        return "Mobile No. :- 8446776452";

    }

    @GetMapping("/about")
    public String about() {
        return "Share what you love with who you love. Join Facebook and start connecting today. Share updates, watch videos, and find even more ways to connect on Facebook. Chat Online. Play Games With Friends. Follow Interesting Topics. Plan Events. Stay In Touch With Family.";

    }

    @GetMapping("/user/employees")
    public ResponseModel user() {
        ResponseModel responseModel = new ResponseModel();
        List<Object> employeeList = new ArrayList<>();
        Employee emp = new Employee();
        emp.id = 11;
        emp.age = 22;
        emp.name = "Tejas Ghatbandhe";
        emp.gender = "Male";
        employeeList.add(emp);

        Employee emp1 = new Employee();
        emp1.id = 12;
        emp1.age = 23;
        emp1.name = "Yash Baksare";
        emp1.gender = "Male";
        employeeList.add(emp1);

        Employee emp2 = new Employee();
        emp2.id = 13;
        emp2.age = 23;
        emp2.name = "Prajyot Bagde";
        emp2.gender = "Male";
        employeeList.add(emp2);

        responseModel.datalist = employeeList;
        responseModel.message = "Record Found";
        responseModel.ResponseCode = HttpStatus.OK.value();


        return responseModel;

    }

    @GetMapping("/user/employee/{empId}")
    public ResponseModel userById(@PathVariable int empId) {
        ResponseModel responseModel = new ResponseModel();
        if (empId == 13) {
            Employee emp = new Employee();
            emp.id = 13;
            emp.age = 22;
            emp.name = "Prajyot Bagde";
            emp.gender = "Male";
            responseModel.datalist = Collections.singletonList(emp);
            responseModel.message = "Record Found";
            responseModel.ResponseCode = HttpStatus.OK.value();
        } else {
            responseModel.message = "Recod Not Found";
            responseModel.ResponseCode = HttpStatus.NOT_FOUND.value();
        }
        return responseModel;
    }

    @GetMapping("/user/employee")
    public ResponseModel userByQueryparam(@RequestParam("age") int age, @RequestParam(required = false) String gender) {
        ResponseModel responseModel = new ResponseModel();
        if (age == 22 || (Objects.nonNull(gender) && gender.equals("Male"))) {
            Employee emp = new Employee();
            emp.id = 13;
            emp.age = 22;
            emp.name = "Prajyot Bagde";
            emp.gender = "Male";
            responseModel.datalist = Collections.singletonList(emp);
            responseModel.message = "Record Found";
            responseModel.ResponseCode = HttpStatus.OK.value();
        } else {
            responseModel.message = "Recod Not Found";
            responseModel.ResponseCode = HttpStatus.NOT_FOUND.value();
        }
        return responseModel;
    }

//    @GetMapping("/user/employee")
//    public ResponseModel userByQueryparam(@RequestParam("age") int age,
//                                          @RequestParam(required = false) String gender) {
//        ResponseModel responseModel = new ResponseModel();
//        responseModel.data = null;
//        responseModel.message = "Age : " + age + "and Gender :" + gender;
//        responseModel.ResponseCode = HttpStatus.OK.value();
//        return responseModel;
//    }

    @GetMapping("/user/employeeByName")
    public ResponseModel userByQuertParam(@RequestParam List<String> names){
        ResponseModel responseModel = new ResponseModel();
        responseModel.data =  null;
        StringBuilder msg = new StringBuilder();
        for (String name : names){
            msg.append(name).append(" ");
        }
        responseModel.message = msg.toString();
        responseModel.responseCode = HttpStatus.OK.value();
        return responseModel;
    }
}



