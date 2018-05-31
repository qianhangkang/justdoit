package com.hongkong.demo.repository;

import com.hongkong.demo.dao.PtpMsmTaskDao;
import com.hongkong.demo.model.PtpMsmTask;
import com.hongkong.demo.query.PtpMsmTaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * the PtpMsmTask repository.
 *
 * @author qianhangkang
 * @since 2018/05/30 16:16
 */
@Repository
public class PtpMsmTaskRepository {

    @Autowired
    private PtpMsmTaskDao ptpMsmTaskDao;

    /**
     * 批量插入记录.
     *
     * @param entities 待插入的业务实体列表
     * @return 业务实体对象列表
     */
    public int batchInsert(List<PtpMsmTask> entities) {
        return ptpMsmTaskDao.batchInsert(entities);
    }

    /**
     * 查询满足条件的总记录数.
     *
     * @param whereEntity 查询条件
     * @return 满足条件的总记录数
     */
    public long count(PtpMsmTaskQuery whereEntity) {
        return ptpMsmTaskDao.count(whereEntity);
    }

    /**
     * 根据条件进行单条记录查询.
     *
     * @param whereEntity 查询条件
     * @return 业务实体PtpMsmTask对象
     */
    public PtpMsmTask findOne(PtpMsmTaskQuery whereEntity) {
        return ptpMsmTaskDao.findOne(whereEntity);
    }

    /**
     * 新插入记录.
     *
     * @param entity 待插入的业务实体
     * @return 业务实体对象
     */
    public long insert(PtpMsmTask entity) {
        return ptpMsmTaskDao.insert(entity);
    }

    /**
     * 根据任务状态查找任务列表
     * @param status 任务状态
     * @return List<PtpMsmTask>
     */
    public List<PtpMsmTask> findListByStatus(int status) {
        PtpMsmTaskQuery query = PtpMsmTaskQuery.builder()
                .status(status)
                .build();
        return ptpMsmTaskDao.findList(query);
    }

    /**
     * 根据name查找job并更新job
     * @param job 更新对象
     * @return 更新记录数
     */
    public int update(PtpMsmTask job) {
        return ptpMsmTaskDao.update(job);
    }

    /**
     * 根据name查找job实体
     * @param name job's name
     * @return PtpMsmTask对象
     */
    public PtpMsmTask findOneByName(String name) {
        PtpMsmTaskQuery query = PtpMsmTaskQuery.builder()
                .jobName(name)
                .build();
        return ptpMsmTaskDao.findOne(query);
    }
}