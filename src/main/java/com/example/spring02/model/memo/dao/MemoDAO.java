package com.example.spring02.model.memo.dao;

import com.example.spring02.model.memo.dto.MemoDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoDAO {

    String list_memo = "select * from memo order by idx desc";
    String insert_memo = "insert into memo (idx, writer, memo) " +
            "values ((select nvl(max(idx)+1,1) from memo), #{writer}, #{memo})";

    @Select(list_memo)
    List<MemoDTO> list();

    @Insert(insert_memo)
    void insert(@Param("writer") String writer, @Param("memo") String memo);

    @Select("select * from memo where idx=#{idx}")
    MemoDTO memo_view(@Param("idx") int idx);

    @Update("update memo set writer=#{writer}, memo=#{memo} where idx=#{idx}")
    void memo_update(MemoDTO dto);

    @Delete("delete memo where idx=#{idx}")
    void memo_delete(@Param("idx") int idx);

}
