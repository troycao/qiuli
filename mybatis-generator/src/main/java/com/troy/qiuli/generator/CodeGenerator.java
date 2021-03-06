package com.troy.qiuli.generator;


import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoqiang
 */
public class CodeGenerator {

    static List<String> configs = Arrays.asList(
            "generatorConfig.xml"
//            "generatorConfig-common.xml"
//            ,
    );

    /**
     * 请注意，使用此方法生成Mode时，如果有基类配置，则必须先设置此项目对依赖jar的依赖
     * 具体操作 项目设置-Modules-Dependencies 添加基类jar包依赖
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        for (String path : configs) {
            doGenerator(path);
        }

        System.out.println("\r\nRefresh your infrastructure project to see the latest db mode/mapper/mapperxml");
    }


    public static void doGenerator(String xmlPah) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {

        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp
                .parseConfiguration(CodeGenerator.class.getClassLoader().getResourceAsStream(xmlPah));

        DefaultShellCallback shellCallback = new DefaultShellCallback(true);

        // 清除先前已生成的mapper xmls
//            cleanAutoGeneratedXmlDir(config, shellCallback);


        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("Mybatis generator runned successfully.");
        if (warnings.size() > 0) {
            System.out.println("\r\nWith warnings");
            for (String warning : warnings) {
                System.out.println(warning);
            }
        }
    }


    /**
     * 对于先前已生成的mapper xml, Mybatis3 generator会做append操作。目前没有入口配置为overwrite。
     * 因此，需要此方法在自动生成程序运行之前，清除以前生成的mapper xmls。
     *
     * @param config
     * @param shellCallback
     * @throws ShellException
     * @throws IOException
     */
//    private static void cleanAutoGeneratedXmlDir(Configuration config, ShellCallback shellCallback)
//            throws ShellException, IOException {
//        File directory = shellCallback.getDirectory(
//                config.getContexts().get(0).getSqlMapGeneratorConfiguration().getTargetProject(),
//                config.getContexts().get(0).getSqlMapGeneratorConfiguration().getTargetPackage());
//        FileUtils.cleanDirectory(directory);
//    }

}
