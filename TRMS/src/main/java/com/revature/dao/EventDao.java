package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Event;

public interface EventDao {

	public abstract void selectEvent(Event event) throws SQLException;
}
