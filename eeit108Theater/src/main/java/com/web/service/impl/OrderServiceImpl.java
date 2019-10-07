package com.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.dao.OrderDao;
import com.web.dao.SeatDao;
import com.web.entity.OrderBean;
import com.web.entity.OrderItemBean;
import com.web.entity.SeatBean;
import com.web.entity.TimeTableBean;
import com.web.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao odao;
	@Autowired
	SeatDao sdao;

	@Override
	public int saveOrder(OrderBean order) {

		return odao.saveOrder(order);
	}

	@Override
	public int deleteOrderByNo(Integer orderNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrder(OrderBean order) {
		return odao.updateOrder(order);
	}

	@Override
	public int discontinueAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int continueAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderBean getOrderByNo(Integer orderNo) {
		OrderBean ob = odao.getOrderByNo(orderNo);
		cancelLazy(ob);
		return ob;
	}
	
	@Override
	public OrderBean getOrderById(String orderId) {
		OrderBean ob = odao.getOrderById(orderId);
		cancelLazy(ob);
		return ob;
	}

	@Override
	public int setSeatToOrder(OrderBean ob, String[] seats) {
		System.out.println("===setSeatToOrderBegin");
		int tid = ob.getTimeTable().getNo();
		System.out.println("tid = " + tid);
		List<SeatBean> list = new ArrayList<>();
		ob.setSeats(list); // refresh seatList in order
		for (String rowCol : seats) {
			String row = rowCol.substring(0, 1);
			System.out.print("row = " + row);
			Integer col = Integer.valueOf(rowCol.substring(1));
			System.out.println(", col = " + col);
			if (sdao.getSeat(tid, row, col) != null) { // if user-chosen seats has already been in the database, user
														// should choose the seats again.
				System.err.println(" Already been ordered: (tid, row, col) = (" + tid + ", " + row + ", " + col + ")");
				return -1;
			}
			SeatBean sb = new SeatBean(tid, row, col);
			list.add(sb);
		}
		System.out.println(ob.getSeatsString());
		System.out.println("===setSeatToOrderEnd===");
		return seats.length;
	}

	@Override
	public List<OrderBean> getOrdersByOwnerId(String memberId) {
		return odao.getOrdersByOwnerId(memberId);
	}

	@Override
	public List<OrderBean> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderBean> getAllAvailable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderBean> getGuestOrders(String ownerEmail, String ownerPhone, Boolean checkStatus) {
		List<OrderBean> list = odao.getGuestOrders(ownerEmail, ownerPhone, checkStatus);
		for (OrderBean order : list)
			cancelLazy(order);
		return list;
	}

	@Override
	public List<OrderBean> getMemberOrders(String memberId, Boolean checkStatus) {
		List<OrderBean> list = odao.getMemberOrders(memberId, checkStatus);
		for (OrderBean order : list)
			cancelLazy(order);
		return list;
	}

	private void cancelLazy(OrderBean ob) {
		List<OrderItemBean> oiList = ob.getOrderItems();
		if (oiList != null)
			for (OrderItemBean oib : oiList)
				oib.getAvailable();
		List<SeatBean> sList = ob.getSeats();
		if (sList != null)
			for (SeatBean sb : sList)
				sb.getAvailable();
		cancelLazy(ob.getTimeTable());
	}

	private void cancelLazy(TimeTableBean tb) {
		tb.getMovie().getAvailable();
	}


}
