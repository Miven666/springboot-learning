package com.miven.springboot.mybatis.generator.plugin;

import com.miven.springboot.mybatis.generator.comment.XmzCommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import tk.mybatis.mapper.generator.MapperPlugin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 自定义mapper插件
 * 基于mapper generator 1.1.5
 * @author mingzhi.xie
 * @date 2019/7/8
 * @since 1.0
 */
public class XmzMapperPlugin extends MapperPlugin {

    /**
     * 修改默认的注释生成器
     */
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        boolean xmzCommentGenerator = !"FALSE".equalsIgnoreCase(context.getProperty("xmzCommentGenerator"));
        if (xmzCommentGenerator) {
            CommentGeneratorConfiguration commentCfg = new CommentGeneratorConfiguration();
            commentCfg.setConfigurationType(XmzCommentGenerator.class.getCanonicalName());
            context.setCommentGeneratorConfiguration(commentCfg);
        }
    }

    /**
     * 添加注释
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addClassComment(topLevelClass);
        super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
        return true;
    }

    /**
     * 添加注释和@Repository注解
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        super.clientGenerated(interfaze, topLevelClass, introspectedTable);
        interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        addInterfaceComment(interfaze);
        interfaze.addAnnotation("@Repository");
        return true;
    }

    /**
     * 添加类注释
     */
    private void addClassComment(TopLevelClass topLevelClass) {
        topLevelClass.addAnnotation("/**");
        topLevelClass.addAnnotation(" * @author " + getProperty("author"));
        topLevelClass.addAnnotation(" * @date " + getLocalDate());
        topLevelClass.addAnnotation(" * @since " + getProperty("version"));
        topLevelClass.addAnnotation(" */");
    }

    private void addInterfaceComment(Interface interfaze) {
        interfaze.addAnnotation("/**");
        interfaze.addAnnotation(" * @author " + getProperty("author"));
        interfaze.addAnnotation(" * @date " + getLocalDate());
        interfaze.addAnnotation(" * @since " + getProperty("version"));
        interfaze.addAnnotation(" */");
    }

    /**
     * 获取当前日期
     */
    private String getLocalDate() {
        return DateTimeFormatter.ofPattern(getProperty("pattern")).format(LocalDate.now());
    }
}
