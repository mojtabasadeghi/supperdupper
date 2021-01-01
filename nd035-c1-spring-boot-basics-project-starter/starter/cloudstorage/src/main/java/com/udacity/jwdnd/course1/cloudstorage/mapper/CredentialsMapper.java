package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("select * from credentials WHERE username = #{username}")
    List<Credentials> getCredentials(String username);

    @Insert("INSERT INTO credentials (url, username, `key`, `password`, fk_userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{fk_userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid",keyColumn = "credentialid")
    //int insertCredentials(String url,String username,String key1,String password,int fk_userid);
    int insertCredentials(Credentials credentials);
}
