package com.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.dao.ProductDao;
import com.web.entity.ProductBean;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	SessionFactory factory;

	public Boolean isExist(Integer productNo) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p WHERE p.no = :no";
		ProductBean pb = (ProductBean) session.createQuery(hql).setParameter("no", productNo)
				.uniqueResult();
		if (pb != null)
			return true;
		return false;
	}

	@Override
	public int saveProduct(ProductBean product) {
		Session session = factory.getCurrentSession();
		if (product.getNo() != null)
			// To save an entity bean, primary key must be null
			return 0;
		session.save(product);
		return 1;
	}

	@Override
	public int deleteProductByNo(Integer productNo) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE ProductBean p WHERE p.no = :pno";
		int cnt = session.createQuery(hql).setParameter("pno", productNo).executeUpdate();
		return cnt;
	}

	@Override
	public int deleteProductByName(String productName) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE ProductBean p WHERE p.name = :pname";
		int cnt = session.createQuery(hql).setParameter("pname", productName).executeUpdate();
		return cnt;
	}

	@Override
	public int deleteAll() {
		Session session = factory.getCurrentSession();
		String hql = "DELETE ProductBean";
		int cnt = session.createQuery(hql).executeUpdate();
		return cnt;
	}

	@Override
	public int updateProduct(ProductBean product) {
		Session session = factory.getCurrentSession();
		session.update(product);
		return 1;
	}

	@Override
	public int discontinueAll() {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE ProductBean p SET p.available = :false";
		int cnt = session.createQuery(hql).setParameter("false", Boolean.FALSE).executeUpdate();
		return cnt;
	}

	@Override
	public int continueAll() {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE ProductBean p SET p.available = :true";
		int cnt = session.createQuery(hql).setParameter("true", Boolean.TRUE).executeUpdate();
		return cnt;
	}

	@Override
	public ProductBean getProductByName(String productName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p WHERE p.name = :name";
		ProductBean pb = (ProductBean) session.createQuery(hql).setParameter("name", productName)
				.uniqueResult();
		return pb;
	}

	@Override
	public ProductBean getProductByNo(Integer productNo) {
		// 不直接用session.get(ProductBean.class, productNo)
		// 是因為若該product在current session已取過，而又再取一次會丟exception
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p WHERE p.no = :no";
		ProductBean pb = (ProductBean) session.createQuery(hql).setParameter("no", productNo)
				.uniqueResult();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p ORDER BY p.type";
		List<ProductBean> pb = session.createQuery(hql).list();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getAllAvailableProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p  WHERE p.available = :true ORDER BY p.type";
		List<ProductBean> pb = session.createQuery(hql).setParameter("true", Boolean.TRUE).list();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getAllUnavailableProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p  WHERE p.available = :false ORDER BY p.type";
		List<ProductBean> pb = session.createQuery(hql).setParameter("false", Boolean.FALSE).list();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getProductsByType(String type, Boolean available) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p WHERE p.type = :type AND p.available = :ava";

		List<ProductBean> pb = session.createQuery(hql).setParameter("type", type)
				.setParameter("ava", available).list();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getAllProductsByType(String type) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p WHERE p.type = :type";

		List<ProductBean> pb = session.createQuery(hql).setParameter("type", type).list();
		return pb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getTicketsByVersion(String version) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean p WHERE p.name LIKE :version AND p.type = 'ticket' AND p.available = 1";
		List<ProductBean> pb = session.createQuery(hql).setParameter("version", "%" + version + "%")
				.list();
		return pb;
	}

	// chart
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ProductBean> getProductMemberPerMoon(Date firstDate, Date lastDate) {
//		Session session = factory.getCurrentSession();
//		List<ProductBean> list = new ArrayList<>();
//		list = session
//				.createQuery("FROM ProductBean p WHERE m.registerTime "
//						+ "BETWEEN :fristDate and :lastDate)")
//				.setParameter("fristDate", firstDate).setParameter("lastDate", lastDate).list();
//		System.out.println(list);
//		return list;
//	}

}
