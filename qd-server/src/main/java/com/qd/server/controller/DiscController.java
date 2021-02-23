package com.qd.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qd.common.annotation.Log;
import com.qd.common.common.BaseController;
import com.qd.common.enums.BusinessType;
import com.qd.common.utils.JsonResult;
import com.qd.server.entity.Disc;
import com.qd.server.query.DiscQuery;
import com.qd.server.service.IDiscService;

/**
 * <p>
 * 数据字典集 前端控制器
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/disc")
public class DiscController extends BaseController {

	@Autowired
	private IDiscService discService;

	/**
	 * 获取数据列表
	 *
	 * @param query 查询条件
	 * @return
	 */
	@PostMapping("/list")
	public JsonResult list(@RequestBody DiscQuery query) {
		return discService.getList(query);
	}

	/**
	 * 添加记录
	 *
	 * @param entity 实体对象
	 * @return
	 */
	@Log(title = "数据字典集", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	public JsonResult add(@RequestBody Disc entity) {
		return discService.edit(entity);
	}

	/**
	 * 获取详情
	 *
	 * @param id 记录ID
	 * @return
	 */
	@GetMapping("/info")
	public JsonResult info(String id) {
		return discService.info(id);
	}

	/**
	 * 更新记录
	 *
	 * @param entity 实体对象
	 * @return
	 */
	@Log(title = "数据字典集", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	public JsonResult edit(@RequestBody Disc entity) {
		return discService.edit(entity);
	}

	/**
	 * 删除记录
	 *
	 * @param entity
	 * @return
	 */
	@Log(title = "数据字典集", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
	public JsonResult delete(@RequestBody Disc entity) {
		return discService.delete(entity);
	}
}