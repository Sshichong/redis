package com.example.demo.mapper;

import com.example.demo.model.Illness;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by shichong on 2018/6/25.
 */
@Mapper
public interface IllnessMapper {

  @Select("<script>" +
          "select * from illnes"+
          "<if test='illness!=null'>"+
          "WHERE" +
          " Illness_name LIKE concat(concat('%',#{illnessName}),'%')"+
          "</if>"+
          "</script>")
  @Results({
          @Result(property = "illnessId", column = "Illness_ID"),
          @Result(property = "illnessName", column = "Illness_name"),
          @Result(property = "illnessAdmin", column = "Illness_admin"),
          @Result(property = "illnessRemark", column = "Illness_remark"),
          @Result(property = "illnessState", column = "Illness_State"),
  })
    List<Illness> findIllnessInfoByName(@Param("illnessName") String illnessName);


  @Select("select * from illness")
  @Results({
          @Result(property = "illnessId", column = "Illness_ID"),
          @Result(property = "illnessName", column = "Illness_name"),
          @Result(property = "illnessAdmin", column = "Illness_admin"),
          @Result(property = "illnessRemark", column = "Illness_remark"),
          @Result(property = "illnessState", column = "Illness_State"),
  })
  List<Illness> findIllnessInfos();

  @Select("select count(*) from illness where Illness_name=#{illnessName}")
  int isExistByIllnessName(@Param("illnessName") String illnessName);

  @Insert("insert into illness" +
          "(Illness_name,Illness_admin,Illness_remark,Illness_State) values" +
          "(#{illnessName},#{illnessAdmin},#{illnessRemark},#{illnessState})")
  void saveIllnessInfo(Illness illness);

  @Update("<script>" +
          "update illness" +
          "<trim prefix='set' suffixOverrides=','>" +
          "<if test='illnessName!=null'>Illness_name=#{illnessName},</if>" +
          "<if test='illnessAdmin!=null'>Illness_admin=#{illnessAdmin},</if>" +
          "<if test='illnessRemark!=null'>Illness_remark=#{illnessRemark},</if>" +
          "<if test='illnessName!=null'>Illness_State=#{illnessState},</if>" +
          "</trim>" +
          "where Illness_ID=#{illnessId}" +
          "</script>")
  void updateIllnessInfo(Illness illness);

  /**
   * 批量删除
   * @param ids
   */
  @Delete("<script>" +
          "delete from illness where Illness_ID in" +
          "<foreach item='item' collection='list' open='(' separator=',' close=')' >" +
          "#{item}" +
          "</foreach>" +
          "</script>")
  void deleteIllnessInfos(List<Integer> ids);

  //如果指定为 true，则方法调用后将立即清空所有缓存
  @Delete("delete from illness where Illness_ID =#{id}")
  void deleteIllnessInfo(Integer id);

  @Select("select * from illness where Illness_ID =#{illnessId}")
  @Results({
          @Result(property = "illnessId", column = "Illness_ID"),
          @Result(property = "illnessName", column = "Illness_name"),
          @Result(property = "illnessAdmin", column = "Illness_admin"),
          @Result(property = "illnessRemark", column = "Illness_remark"),
          @Result(property = "illnessState", column = "Illness_State"),
  })
  Illness findIllnessById(@Param("illnessId") Integer illnessId);
}
