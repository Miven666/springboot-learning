package com.miven.springboot.mybatis.entity;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table teacher
 */
public class Teacher implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.id
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   姓名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.name
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    private String name;

    /**
     * Database Column Remarks:
     *   年龄
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.age
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    private Integer age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table teacher
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.id
     *
     * @return the value of teacher.id
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.id
     *
     * @param id the value for teacher.id
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.name
     *
     * @return the value of teacher.name
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.name
     *
     * @param name the value for teacher.name
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.age
     *
     * @return the value of teacher.age
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.age
     *
     * @param age the value for teacher.age
     *
     * @mbg.generated Fri Apr 12 11:30:04 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}