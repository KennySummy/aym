package com.system.aym.mybatis.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class DefaultCommentGenerator implements CommentGenerator {
    private Properties properties;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String currentDateStr;

    public DefaultCommentGenerator() {
        this.properties = new Properties();
        this.suppressDate = false;
        this.suppressAllComments = false;
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }

    public void addComment(org.mybatis.generator.api.dom.xml.XmlElement xmlElement) {
        if (this.suppressAllComments) {
        }
    }

    public void addRootComment(org.mybatis.generator.api.dom.xml.XmlElement rootElement) {
    }

    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
    }


    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append("@mbggenerated");
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }


    protected String getDateString() {
        if (this.suppressDate) {
            return null;
        }
        return new Date().toString();
    }


    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
        }
    }


    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
        }
    }


    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }

//        StringBuilder sb = new StringBuilder();
//
//        sb.append(" /** ");
//        sb.append(introspectedColumn.getRemarks());
//        sb.append(" ");
//        sb.append(introspectedTable.getFullyQualifiedTable());
//        sb.append('.');
//        sb.append(introspectedColumn.getActualColumnName());
//        sb.append(" */");
//        field.addJavaDocLine(sb.toString());


        // 添加字段注释
        StringBuffer sb = new StringBuffer();
        field.addJavaDocLine("/**");
        if (introspectedColumn.getRemarks() != null)
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        sb.append(" * 表字段 : ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        field.addJavaDocLine(sb.toString());
        // addJavadocTag(field, false);
        field.addJavaDocLine(" */");

    }


    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
        }
    }


    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
        }
    }


    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }

//        StringBuilder sb = new StringBuilder();
//
//        sb.append(" /** 取值：");
//        sb.append(introspectedColumn.getRemarks());
//        sb.append(" */");
//        method.addJavaDocLine(sb.toString());
//        method.addJavaDocLine("/**");
        method.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        sb.setLength(0);
        sb.append(" * @return ");
        sb.append(introspectedColumn.getActualColumnName());
        sb.append(" ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        //      addJavadocTag(method, false);

        method.addJavaDocLine(" */");
    }


    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }

//        StringBuilder sb = new StringBuilder();
//
//        sb.append(" /** 赋值：");
//        sb.append(introspectedColumn.getRemarks());
//        sb.append(" */");
//        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(parm.getName());
        sb.append(" ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        //      addJavadocTag(method, false);

        method.addJavaDocLine(" */");
    }


    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (this.suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString());

        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(properties.getProperty("user.name"));
        sb.append(" ");
        sb.append(currentDateStr);

        //      addJavadocTag(innerClass, markAsDoNotDelete);

        innerClass.addJavaDocLine(" */");
    }


    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

    }
}

