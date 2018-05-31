package com.hongkong.demo.data.dao;

import com.hongkong.demo.data.model.PtpMsmTask;
import com.hongkong.demo.data.query.PtpMsmTaskQuery;
import com.hongkong.demo.data.pager.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * the PtpMsmTask dao interface.
 *
 * @author qianhangkang
 * @since 2018/05/31 14:47
 */
public interface PtpMsmTaskDao {

    /**
     * 根据指定条件进查询,并通过指定字段进行过滤.
     *
     * @param whereEntity 查询条件
     * @param inFiled     过滤字段名
     * @param inValues    过滤字段值
     * @return
     */
    public List<PtpMsmTask> findListWithIn(@Param("query") PtpMsmTaskQuery whereEntity, @Param("inFiled") String inFiled,
                                           @Param("inValues") List<Object> inValues);

    /**
     * 通过查询条件进行记录更新.
     *
     * @param entity      待更新的业务信息
     * @param whereEntity 查询条件
     * @return 更新成功记录数
     */
    public int updateBySelective(@Param("entity") PtpMsmTask entity, @Param("criteria") PtpMsmTaskQuery whereEntity);

    /**
     * 根据Id进行查询.
     *
     * @param ids id列表
     * @return 业务实体PtpMsmTask对象
     */
    public List<PtpMsmTask> batchFindById(List<Long> ids);

    /**
     * 根据条件进行列表查询.
     *
     * @param whereEntity 查询条件
     * @return 业务实体T对象列表
     */
    public List<PtpMsmTask> findList(PtpMsmTaskQuery whereEntity);

    /**
     * 通过指定的条件进行分页查询.
     *
     * @param whereEntity 查询条件对象
     * @param pager       分页信息
     * @return 结果列表
     */
    public List<PtpMsmTask> findPage(@Param("query") PtpMsmTaskQuery whereEntity, @Param("pager") Pager pager);

    /**
     * 批量插入记录.
     *
     * @param entities 待插入的业务实体列表
     * @return 业务实体对象列表
     */
    public int batchInsert(List<PtpMsmTask> entities);

    /**
     * 查询满足条件的总记录数.
     *
     * @param whereEntity 查询条件
     * @return 满足条件的总记录数
     */
    public long count(PtpMsmTaskQuery whereEntity);

    /**
     * 根据条件进行单条记录查询.
     *
     * @param whereEntity 查询条件
     * @return 业务实体PtpMsmTask对象
     */
    public PtpMsmTask findOne(PtpMsmTaskQuery whereEntity);

    /**
     * 新插入记录.
     *
     * @param entity 待插入的业务实体
     * @return 业务实体对象
     */
    public long insert(PtpMsmTask entity);

//    /**
//     * 更新记录.
//     *
//     * @param entity 待更新的业务实体
//     * @return 更新数
//     */
//    public int update(@Param("entity") PtpMsmTask entity);

}