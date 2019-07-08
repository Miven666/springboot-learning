package com.miven.springboot.mybatis.generator.comment;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.xml.XmlElement;
import tk.mybatis.mapper.generator.MapperCommentGenerator;

/**
 * 自定义注释生成器
 * 基于mapper generator 1.1.5
 * @author mingzhi.xie
 * @date 2019/7/8
 * @since 1.0
 */
public class XmzCommentGenerator extends MapperCommentGenerator {

    public XmzCommentGenerator() {}

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // 此处生成的注释会位于文档顶部，而作者、时间、版本注释一般位于导入包和注解之间
        // 故将实现移至 XmzMapperPlugin的 modelBaseRecordClassGenerated()
    }

    /**
     * 去除xml中的注释
     */
    @Override
    public void addComment(XmlElement xmlElement) {}
}
