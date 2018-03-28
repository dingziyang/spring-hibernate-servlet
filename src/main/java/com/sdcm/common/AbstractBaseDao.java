package com.sdcm.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.sdcm.utils.Page;

// Serializable接口是java提供的通用数据保存和读取的接口
public abstract class AbstractBaseDao<T, K extends Serializable> implements BaseDao<T, K> {
	
	private SessionFactory sessionFactory;
	private DataSource dataSource;
	private static final ThreadLocal threadLocal = new ThreadLocal();// 存放数据库连接的线程池
	
	public void add(T obj) throws Exception {
		this.getSession().save(obj);//用了hibernate保存添加的方法save来进行添加
	}
	
	public void update(T obj) throws Exception {
		this.getSession().update(obj);//修改
	}
	
	public void delete(T obj) throws Exception {
		this.getSession().delete(obj);//删除
	}
	
	public T get(Class<T> clasz, K k) throws Exception {
		return (T)this.getSession().get(clasz,k);//查询
	}
	
	/**
	 * 通过hql语句查找实体集合
	 */
	protected List<T> query(String hql, Object[] obj) throws Exception {
		Session session = this.getSession();
		Query<T> query = session.createQuery(hql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				query.setParameter(i, obj[i]);
			}
		}
		return query.getResultList();
	}
	
	/**
	   * 本方法用于执行查询，结果集中不允许重复，则只有一条记录
	   * @param sql 带参的数据库语句
	   * @param params 参数列表
	   * @return
	   * @throws Exception
	   * sql="SELECT * FROM ADMIN where id=?"
	   */
	protected T uniqueQuery(String sql, Object[] params)
	          throws Exception {
	      List<T> list = query(sql, params);
	      if ((list != null) && (list.size() > 0)) {
	          return list.get(0);//这里这个零表示下标，只拿一条且是第一条的数据
	      }
	      return null;
	  }
	
	/**
	 * 获得系列值的方法
	 */
	public Integer getSeq(String sql) throws Exception {
		Session session = this.getSession();
		BigDecimal seq= (BigDecimal)session.createNativeQuery(sql).getResultList().get(0);
		return seq.intValue();
	}
	
	public void delete(String sql) throws Exception {
		this.getSession().createNativeQuery(sql).executeUpdate();
	}
	
	/**
	 * 分页实现方法
	 *
	 * @param hql
	 * @param limit 每页大小
	 * @param page 当前页
	 * @return 分页对象
	 */
	protected Page<T> searchForPager(String hql, Integer page,Integer limit) throws Exception {
		
		int maxPage;
		List<T> results;
		List<Integer> pageNum = new ArrayList<Integer>();

		// sql数据库需要去除order by
		String thql = null;
		if (hql.indexOf("order by") == -1) {
			thql = "select count(*) " + hql;
		} else {
			thql = "select count(*) " + hql.substring(0, hql.indexOf("order by"));
		}

		int count = Integer.valueOf(this.findEntity(thql).get(0).toString());

		// 计算页数
		if (count % limit == 0) {
			maxPage = count / limit;
		} else {
			maxPage = count / limit + 1;
		}

		if (maxPage == 0) {
			maxPage = 1;
			page = 1;
		} else {
			page = page <= maxPage ? (page > 0 ? page : 1) : maxPage;
		}

		// 页码
		if (page < 6) {
			for (int i = 1; i < 10; i++) {
				if (i <= maxPage) {
					pageNum.add(i);
				} else {
					break;
				}
			}
		} else {
			for (int i = page - 4; i <= page + 4; i++) {
				if (i <= maxPage) {
					pageNum.add(i);
				} else {
					break;
				}
			}
		}
		results = this.findEntity(hql, (page - 1) * limit, limit);
		Page<T> p = new Page<T>(page, maxPage, count, pageNum, results);
		return p;
	}
	
	/**
	 * 通过hql语句查找实体集合
	 */
	@Override
	public List<T> findEntity(String hql) throws Exception {
		return this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	}
	
	/**
	 * 通过hql语句查找实体集合
	 */

	@Override
	public List<T> findEntity(final String hql, final Integer start, final Integer limit) throws Exception {
		Query<T> query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start).setMaxResults(limit);
		return query.getResultList();
	}
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * 获得数据库连接对象的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized Session getSession() throws Exception {
		return sessionFactory.getCurrentSession();
	}
    
}
