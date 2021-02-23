package com.qd.server.generate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qd.generator.generate.CodeGenerateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateTest {

	@Autowired
	private CodeGenerateUtils codeGenerateUtils;

	@Test
	public void generate() {
		try {

//			codeGenerateUtils.generateFile("qd_role", "角色管理");
//			codeGenerateUtils.generateFile("qd_user_role", "用户角色关联");
//			codeGenerateUtils.generateFile("qd_resource", "资源管理");
//			codeGenerateUtils.generateFile("qd_user", "用户管理");
//			codeGenerateUtils.generateFile("qd_system", "基础配置");
//			codeGenerateUtils.generateFile("qd_token", "令牌记录");
//			codeGenerateUtils.generateFile("qd_disc", "数据字典集");
//			codeGenerateUtils.generateFile("qd_disc_item", "数据字典项");
//			codeGenerateUtils.generateFile("qd_trie", "树形字典");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
