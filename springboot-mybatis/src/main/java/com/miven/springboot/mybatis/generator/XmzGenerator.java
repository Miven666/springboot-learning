package com.miven.springboot.mybatis.generator;

import org.apache.ibatis.io.Resources;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

/**
 * @author mingzhi.xie
 * @date 2019/7/5
 * @since 1.0
 */
public class XmzGenerator {
    private static final String GENERATOR_URL = "generator/xmzGenerator.xml";

    public static void main(String[] args) throws IOException  {
        // runner();
        shellRunner();
    }

    private static void shellRunner() throws IOException {
        String[] args;
        URL resource = Resources.getResourceURL(GENERATOR_URL);
        args = new String[] { "-configfile",resource.getPath(),"-verbose", "-overwrite"};
        ShellRunner.main(args);
    }

    private static void runner() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(getResourceAsStream(GENERATOR_URL));

        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
