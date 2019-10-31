package com.springboot.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@SpringBootTest(classes = TestLauncher.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLauncherTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void main() {}

}