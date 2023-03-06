package com.lhl.trace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhl.trace.entity.Info;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InfoMapper extends BaseMapper<Info> {
    //取出所有Info、段落以及相关图片
    @Select("select * from info")
    @Results(
            {
                    @Result(column = "id", property = "infoId"),
                    @Result(column = "title", property = "infoTitle"),
                    @Result(column = "type", property = "infoType"),
                    @Result(column = "status", property = "infoStatus"),
                    @Result(column = "id", property = "imgList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.ImgMapper.listImgByInfoId")
                    ),
                    @Result(column = "id", property = "infoParaList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.InfoParaMapper.listParaByInfoId")
                    )
            }
    )
    List<Info> listAllInfo();


    //取出相关info，img，infoPara
    @Select("select * from info where title like #{keyword}")
    @Results(
            {
                    @Result(column = "id", property = "infoId"),
                    @Result(column = "title", property = "infoTitle"),
                    @Result(column = "type", property = "infoType"),
                    @Result(column = "status", property = "infoStatus"),
                    @Result(column = "id", property = "imgList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.ImgMapper.listImgByInfoId")
                    ),
                    @Result(column = "id", property = "infoParaList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.InfoParaMapper.listParaByInfoId")
                    )
            }
    )
    List<Info> search(String keyword);

    @Select("select * from info where id = #{id} ")
    @Results(
            {
                    @Result(column = "id", property = "infoId"),
                    @Result(column = "title", property = "infoTitle"),
                    @Result(column = "type", property = "infoType"),
                    @Result(column = "status", property = "infoStatus"),
                    @Result(column = "id", property = "imgList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.ImgMapper.listImgByInfoId")
                    ),
                    @Result(column = "id", property = "infoParaList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.InfoParaMapper.listParaByInfoId")
                    )
            }
    )
    Info selectInfoById(int id);

    //测试
    //useGeneratedKeys :默认 false ,作用：设置是否使用JDBC的getGenereatedKeys方法获取主键
    // 并赋值到keyProperty设置的领域模型属性中。
    //keyProperty、keyColumn：返回的字段。

    @Insert("insert into info(title,content,type) values(#{infoTitle},#{infoContent},#{infoType})")
    @Options(useGeneratedKeys = true, keyProperty = "infoId", keyColumn = "id")
    int insertInfo(Info info);


    //取出特定类别的信息
    @Select("select * from info where type = #{keyword}")
    @Results(
            {
                    @Result(column = "id", property = "infoId"),
                    @Result(column = "title", property = "infoTitle"),
                    @Result(column = "type", property = "infoType"),
                    @Result(column = "status", property = "infoStatus"),
                    @Result(column = "id", property = "imgList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.ImgMapper.listImgByInfoId")
                    ),
                    @Result(column = "id", property = "infoParaList", javaType = List.class,
                            many = @Many(select = "com.lhl.trace.mapper.InfoParaMapper.listParaByInfoId")
                    )
            }
    )
    List<Info> searchByType(String keyword);
}
