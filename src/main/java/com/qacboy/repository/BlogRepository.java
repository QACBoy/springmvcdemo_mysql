package com.qacboy.repository;

import com.qacboy.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> ,CrudRepository<BlogEntity, Integer> {
    // 修改博文操作
    @Modifying
    @Transactional
    @Query("update BlogEntity blog set blog.title=:qTitle, blog.userByUserId.id=:qUserId," +
            " blog.content=:qContent, blog.pubDate=:qPubDate where blog.id=:qId")
    void updateBlog(@Param("qTitle") String title, @Param("qUserId") int userId, @Param("qContent") String content,
                    @Param("qPubDate") Date pubDate, @Param("qId") int id);

    @Query(nativeQuery = true,value = "select * from blog where user_id= ?1")
    public List<BlogEntity> findByUserId(Integer userId);

    @Query(nativeQuery = true,value = "select * from blog where title= ?1")
    public List<BlogEntity> findByName(String title);


}
