package com.imooc.ecommerce.service;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数据库表文档生成
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DBDocTest {
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void buildDBDoc() {
        DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);

        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("D:\\WorkSpace\\imooc\\e-commerce-springcloud\\e-commerce-authority-center")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.HTML)
                .produceType(EngineTemplateType.freemarker)
                .build();

        // 生成文档配置，包含自定义版本号、描述等等
        Configuration config = Configuration.builder()
                .version("1.0.0")
                .description("e-commerce-springcloud")
//                .description("金华监管")
                .dataSource(dataSourceMysql)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig())
                .build();

        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * 配置想要生成的数据表和想要忽略的数据表
     * @return
     */
    private ProcessConfig getProcessConfig() {
        // 想要忽略的数据表
        List<String> ignoreTableName = Collections.singletonList("undo_log");
        // 忽略表前缀， 忽略 a、b 开头的数据库表
        List<String> ignorePrefix = Arrays.asList("a","b");
        // 忽略表后缀
        List<String> ingoreSuffix = Arrays.asList("_test","_Test");

        return ProcessConfig.builder()
                // 根据名称指定表生成
                .designatedTableName(Collections.emptyList())
                // 根据表前缀生成
                .designatedTablePrefix(Collections.emptyList())
                // 根据表后缀生成
                .designatedTableSuffix(Collections.emptyList())
                // 忽略表
                .ignoreTableName(ignoreTableName)
                // 忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                // 忽略表后缀
                .ignoreTableSuffix(ingoreSuffix)
                .build();


    }

}
