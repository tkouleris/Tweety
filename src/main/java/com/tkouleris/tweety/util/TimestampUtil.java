package com.tkouleris.tweety.util;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

@Service
public class TimestampUtil {
	
	public Timestamp currentTimestamp()
	{
		return new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp oneMinuteBackTimestamp()
	{
		return new Timestamp(System.currentTimeMillis() - 60000);
	}

}
