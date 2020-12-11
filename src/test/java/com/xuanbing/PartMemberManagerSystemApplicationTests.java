package com.xuanbing;

import com.xuanbing.mapper.TeacherMapper;
import com.xuanbing.service.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PartMemberManagerSystemApplicationTests {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TeacherServiceImpl teacherService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testTeacherMapper(){
        teacherService.deleteTeacherById("203021");
        System.out.println("success");
    }

}
