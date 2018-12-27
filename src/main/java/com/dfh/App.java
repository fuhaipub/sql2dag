package com.dfh;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.dfh.visitor.SelectPrintVisitor;
import com.dfh.visitor.TableNameVisitor;

import java.io.StringWriter;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import java.io.StringWriter;

/**
 * Hello world!
 *
 */
public class App 
{
/*
  var state = [
    { id: 1, label: 'V1\n数据同步', class: 'type-suss' },
    { id: 2, label: 'V2\nhive-sql', class: 'type-suss' },
    { id: 3, label: 'V3\nspark-sql', class: 'type-suss' },
    { id: 4, label: 'V4\nshell', class: 'type-suss' },
    { id: 5, label: 'V5\npython', class: 'type-suss' },
    { id: 6, label: 'V6\n虚节点', class: 'type-suss' },
    { id: 7, label: 'V7\nspark-sql', class: 'type-suss' },
    { id: 8, label: 'V8\nshell', class: 'type-suss' },
    { id: 9, label: 'V9\n数据同步', class: 'type-suss' },
    { id: 10, label: 'V10\nshell', class: 'type-suss' },
    { id: 11, label: 'V11\nspark-sql', class: 'type-run' },
    { id: 12, label: 'V12\nspark-sql', class: 'type-suss' },
    { id: 13, label: 'V13\n虚节点', class: 'type-suss' },
    { id: 14, label: 'V14\n丁福海', class: 'type-suss' },
    { id: 0, label: 'V15\nhive-sql', class: 'type-suss' },
  ]
 */

/*
    { start: 1, end: 4, option: {} },
    { start: 1, end: 3, option: {} },
    { start: 1, end: 2, option: {} },
    { start: 6, end: 7, option: {} },
    { start: 1, end: 6, option: {} },
    { start: 9, end: 10, option: {} },
    { start: 8, end: 9, option: {} },
    { start: 11, end: 12, option: {} },
    { start: 0, end: 11, option: {} },
    { start: 0, end: 8, option: {} },
    { start: 0, end: 5, option: {} },
    { start: 0, end: 14, option: {} },
    { start: 0, end: 13, option: {} },
    { start: 0, end: 1, option: {} }
  ]

 */


    public static void main( String[] args )
    {

        String sql = "insert into t_target select * from t1 , t2 where t1.a = t2.a and t1.id=1 and t1.name=ming group by t2.uid limit 1,200 order by ctime ; insert into t_a select * from b";

        // 新建 MySQL Parser
        SQLStatementParser parser = new MySqlStatementParser(sql);

        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement sqlStatement = parser.parseStatement();

        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        sqlStatement.accept(visitor);

        System.out.println("getTables:" + visitor.getTables());
        System.out.println("getParameters:" + visitor.getParameters());
        System.out.println("getOrderByColumns:" + visitor.getOrderByColumns());
        System.out.println("getGroupByColumns:" + visitor.getGroupByColumns());
        System.out.println("---------------------------------------------------------------------------");

        // 使用select访问者进行select的关键信息打印
        SelectPrintVisitor selectPrintVisitor = new SelectPrintVisitor();
        sqlStatement.accept(selectPrintVisitor);

        System.out.println("---------------------------------------------------------------------------");
        // 最终sql输出
        StringWriter out = new StringWriter();
        TableNameVisitor outputVisitor = new TableNameVisitor(out);
        sqlStatement.accept(outputVisitor);
        System.out.println(out.toString());
    }
}
