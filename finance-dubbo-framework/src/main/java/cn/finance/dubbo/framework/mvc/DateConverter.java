package cn.finance.dubbo.framework.mvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 请求时间格式化
 * @author jianwei.li
 * 2015年9月2日
 */
public class DateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		SimpleDateFormat dateFormat = null;
		if(source.length() == 10){
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		}else{
			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		}
		dateFormat.setLenient(true);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
