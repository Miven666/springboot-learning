package com.springboot.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@SpringBootTest(classes = TestLauncher.class)
@FixMethodOrder(NAME_ASCENDING)
public class TestLauncherTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void main() {}

}