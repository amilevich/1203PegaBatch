package com.revature.trms.dao;

import java.util.ArrayList;

import com.revature.trms.models.GradingFormat;

public interface GradingFormatDAO {

	//CRUDE methods
			//Create
			public boolean insertGradingFormat(GradingFormat format);
			
			//Read
			public GradingFormat getGradingFormat(String format);
			public ArrayList<GradingFormat> getAllGradingFormat();
			
			//Update
			public boolean updateGradingFormat(GradingFormat format, String searchFormat);
			
			//Delete
			public boolean deleteGradingFormat(String format);
			
}
