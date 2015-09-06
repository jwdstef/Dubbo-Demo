package cn.finance.dubbo.framework.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class MyBeanUtils extends BeanUtils {
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	public static <T> T copyBean(Object ori, Class<T> cls) {
		T obj = BeanUtils.instantiate(cls);
		BeanUtils.copyProperties(ori, obj);
		return (T) obj;
	}

	public static <T> List<T> copyBeanList(List<?> oriList, Class<T> cls) {
		List<T> tList = new ArrayList<>();
		for (Object obj : oriList) {
			T t = copyBean(obj, cls);
			tList.add(t);
		}
		return tList;
	}

}