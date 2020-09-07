package com.troy.qiuli.generator;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Date;
import java.util.List;
/**
 * <pre>
 * add pagination using mysql limit.
 * This class is only used in ibator code generator.
 * </pre>
 */

/**
 * mysql 分页生成插件
 */
public class MysqlPagePlugin extends PluginAdapter {

    public MysqlPagePlugin() {
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addImportedType("lombok.ToString");
        topLevelClass.addAnnotation("@ToString");
        topLevelClass.addJavaDocLine("/**");
        String remarks = introspectedTable.getTableConfiguration().getAlias();

        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            System.out.println(remarkLines);
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" * " + remarkLine);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" * ").append(introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(sb.toString());
        sb.setLength(0);
        sb.append(" * @author ").append(System.getProperties().getProperty("user.name"));
        topLevelClass.addJavaDocLine(sb.toString());
        sb.setLength(0);
        sb.append(" * @date ");
        sb.append(getDateString());
        topLevelClass.addJavaDocLine(sb.toString());
        topLevelClass.addJavaDocLine("*/");
        return true;
    }


    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* Created by Mybatis Generator " + getDateString());
        interfaze.addJavaDocLine("*/");
        return true;
    }

    /**
     * 去掉set 方法
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * 去掉get 方法
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    protected String getDateString() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 添加 分页 开始行数 和结束行数 属性
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {
        // add field, getter, setter for limit clause

        addProperty(topLevelClass, introspectedTable, "limitStart", FullyQualifiedJavaType.getIntInstance());

        addProperty(topLevelClass, introspectedTable, "limitEnd", FullyQualifiedJavaType.getIntInstance());

        addProperty(topLevelClass, introspectedTable, "groupByClause", FullyQualifiedJavaType.getStringInstance());

        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }


    /**
     * 添加 映射 文件配置 limit 的配置
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {

//		XmlElement isParameterPresenteElemen = (XmlElement) element.getElements();
        //设置 if 判断 节点
        XmlElement limitElement = new XmlElement("if"); //$NON-NLS-1$

        //给 节点添加 条件运算符
        limitElement.addAttribute(new Attribute("test", "limitEnd > 0")); //$NON-NLS-1$ //$NON-NLS-2$
        //如果条件成立 就进行分页查询
        limitElement.addElement(new TextElement(
                "limit #{limitStart,jdbcType=INTEGER}  , #{limitEnd,jdbcType=INTEGER}"));
        //添加节点到 配置文件中
        element.addElement(limitElement);

        XmlElement groupbyElement = new XmlElement("if"); //$NON-NLS-1$

        //给 节点添加 条件运算符
        groupbyElement.addAttribute(new Attribute("test", "groupByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
        //如果条件成立 就进行分页查询
        groupbyElement.addElement(new TextElement(
                "group by ${groupByClause}"));
        //添加节点到 配置文件中
        element.addElement(groupbyElement);

        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);

    }


    /**
     * 给对应的实体 实体添加 属性字段
     */
    private void addProperty(TopLevelClass topLevelClass,
                             IntrospectedTable introspectedTable, String name, FullyQualifiedJavaType fullyQualifiedJavaType) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(fullyQualifiedJavaType);
        field.setName(name);

//		field.setInitializationString("-1");

        commentGenerator.addFieldComment(field, introspectedTable);

        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(fullyQualifiedJavaType, name));

        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(fullyQualifiedJavaType);
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /**
     * This plugin is always valid - no properties are required
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

//	public static void generate() {
//		String config = MysqlPagePlugin.class.getClassLoader().getResource("generatorConfig-w5Log.xml").getFile();
//		String[] arg = { "-configfile", config, "-overwrite" };
//		ShellRunner.main(arg);
//	}
//	public static void main(String[] args) {
//		generate();
//	}

}