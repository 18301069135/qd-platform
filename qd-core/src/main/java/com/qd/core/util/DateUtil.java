package com.qd.core.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import lombok.extern.log4j.Log4j2;

/***
 * 日期工具类
 * 
 * @author zhouqi
 *
 */
@Log4j2
public class DateUtil {

	public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YMDHMS_ZH = "yyyy年MM月dd日 HH:mm:ss";
	public static final String DATE_FORMAT_YMDHMS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_FORMAT_HMS = "HH:mm:ss";
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YM = "yyyy-MM";

	/**
	 * 日期转字符串，格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String getYyyyMMddHHMMDDString(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		try {
			SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
			return destsmf.format(date);
		} catch (Exception e) {
			log.error("格式化日期出错，date=" + date, e);
			return null;
		}
	}

	public static Date getYyyyMMddHHMMDDSSSString(String date) {
		if (Objects.isNull(date)) {
			return null;
		}
		try {
			SimpleDateFormat destsmf = new SimpleDateFormat(DATE_FORMAT_YMDHMS_SSS);
			return destsmf.parse(date);
		} catch (Exception e) {
			log.error("格式化日期出错，date=" + date, e);
			return null;
		}
	}

	/**
	 * 获取当前时间字符串 格式：yyyyMMddHHmmss
	 *
	 * @return
	 */
	public static String getNowDateString() {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return pattern.format(LocalDateTime.now());
	}

}
