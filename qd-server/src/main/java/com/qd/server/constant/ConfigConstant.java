package com.qd.server.constant;

import java.util.HashMap;
import java.util.Map;

public class ConfigConstant {

	/**
	 * 资源类型
	 */
	@SuppressWarnings("serial")
	public static Map<Integer, String> RESOURCE_TYPE_LIST = new HashMap<Integer, String>() {
		{
			put(1, "目录");
			put(2, "菜单");
			put(3, "按钮");
		}
	};

	/**
	 * 配置类型
	 */
	@SuppressWarnings("serial")
	public static Map<String, String> CONFIG_TYPE_LIST = new HashMap<String, String>() {
		{
			put("readonly", "只读文本");
			put("number", "数字");
			put("text", "单行文本");
			put("textarea", "多行文本");
			put("array", "数组");
			put("password", "密码");
			put("radio", "单选框");
			put("checkbox", "复选框");
			put("select", "下拉框");
			put("icon", "字体图标");
			put("date", "日期");
			put("datetime", "时间");
			put("image", "单张图片");
			put("images", "多张图片");
			put("file", "单个文件");
			put("files", "多个文件");
			put("ueditor", "富文本编辑器");
			put("json", "JSON");
		}
	};

}
