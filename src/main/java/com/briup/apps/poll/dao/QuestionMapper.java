package com.briup.apps.poll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.QuestionExample;


public interface QuestionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    long countByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int deleteByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int insert(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int insertSelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    List<Question> selectByExample(QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    Question selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poll_question
     *
     * @mbg.generated Mon Jun 25 19:36:12 CST 2018
     */
    int updateByPrimaryKey(Question record);
    
}