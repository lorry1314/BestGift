package com.wangshiqi.bestgift.model.db;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.wangshiqi.bestgift.model.bean.LiteOrmBean;
import com.wangshiqi.bestgift.ui.app.BestGiftApp;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * 数据库单例工具类
 */
public class LiteOrmInstance {
    private LiteOrm liteOrm;

    private LiteOrmInstance() {
        liteOrm = LiteOrm.newSingleInstance(BestGiftApp.getContext(), "collect.db");
        liteOrm.setDebugged(true);

    }

    private static LiteOrmInstance liteOrmInstance;

    public static LiteOrmInstance getLiteOrmInstance() {
        if (liteOrmInstance == null) {
            synchronized (LiteOrmInstance.class) {
                if (liteOrmInstance == null) {
                    liteOrmInstance = new LiteOrmInstance();
                }
            }
        }
        return liteOrmInstance;
    }


    public <T> long insert(T t) {
        return liteOrm.save(t);
    }

    public void deleteByName(String name) {
        WhereBuilder whereBuilder = new WhereBuilder(LiteOrmBean.class);
        whereBuilder.where("name = ?", new String[]{name});
        liteOrm.delete(whereBuilder);
    }
    /**
     * 删除数据库所有数据
     */
    public void deleteAll() {
        liteOrm.deleteAll(LiteOrmBean.class);
    }

    /**
     * 按name查询数据
     */

    public List<LiteOrmBean> queryByName(String name) {
        QueryBuilder<LiteOrmBean> qb = new QueryBuilder<>(LiteOrmBean.class);
        qb.where("name = ?", new String[]{name});
        return liteOrm.query(qb);
    }

    /**
     * 查询所有
     *
     * @param cla
     * @param <T>
     * @return
     */
    public <T> List<T> getQueryAll(Class<T> cla) {
        return liteOrm.query(cla);
    }

    /**
     * 查询  某字段 等于 Value的值
     *
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> List<T> getQueryByWhere(Class<T> cla, String field, String[] value) {
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value));
    }

    /**
     * 删除一个数据
     *
     * @param t
     * @param <T>
     */
    public <T> void delete(T t) {

        liteOrm.delete(t);
    }

    /**
     * 删除一个表
     *
     * @param cla
     * @param <T>
     */
    public <T> void delete(Class<T> cla) {
        liteOrm.delete(cla);
    }

    /**
     * 删除集合中的数据
     *
     * @param list
     * @param <T>
     */
    public <T> void deleteList(List<T> list) {
        liteOrm.delete(list);
    }

    /**
     * 删除数据库
     */
    public void deleteDatabase() {
        liteOrm.deleteDatabase();
    }
}
