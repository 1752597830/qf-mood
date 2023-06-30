package com.qf.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author sin
 * @Date: 2023/5/20 - 05 - 20 - 22:19
 * @Description: 数据库代码生成器
 * @version: 1.0
 */
public class GeneratorCode {
    //修改为你自己的数据库地址
    private static final String datasourceUrl = "jdbc:mysql://localhost:3306/qfMood?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai";
    //修改为你自己的数据库账号
    private static final String datasourceUsername = "root";
    //修改为你自己的数据库密码
    private static final String datasourcePassword = "root";
    // 项目路径
    private static final String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        FastAutoGenerator.create(datasourceUrl, datasourceUsername, datasourcePassword)
                .globalConfig(builder -> {
                    builder.author("sin") // 设置作者
                            // 禁止打开输出目录
                            .disableOpenDir()
                            .enableSwagger() // 开启 swagger 模式
                            // 注释日期
                            .commentDate("yyyy/MM/dd HH:mm")
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
//                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
//                    if (typeCode == Types.SMALLINT) {
//                        // 自定义类型转换
//                        return DbColumnType.INTEGER;
//                    }
//                    return typeRegistry.getColumnType(metaInfo);
//
//                }))
                .packageConfig(builder -> {
                    builder.parent("com.qf") // 设置父包名
                            .entity("pojo")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mybatis")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig((builder) -> {
                    builder
                    // 增加过滤表前缀
                    //.addTablePrefix("t_")
                    // service策略配置
                    .serviceBuilder()
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl")
                    // entity策略配置
                    .entityBuilder()
                    // 数据库表映射到实体的命名策略
                    .naming(NamingStrategy.underline_to_camel)
                    // 数据库表字段映射到实体的命名策略
                    .columnNaming(NamingStrategy.no_change)
                    // 开启lombok模型
                    .enableLombok()
                    // controller策略设置
                    .controllerBuilder()
                    .formatFileName("%sController")
                    .enableRestStyle()
                    .enableHyphenStyle()
                    // mapper策略设置
                    .mapperBuilder()
                    // 生成通用的resultMap
                    .enableBaseResultMap()
                    .enableBaseColumnList()
                    .superClass(BaseMapper.class)
                    .formatMapperFileName("%sMapper")
                    .enableMapperAnnotation()
                    .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    //    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
