package com.dfh;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;

public class ParseColumn {


        public static void main(String[] args) {
            String sql = "insert into abcd(a,b,c,d) select id,name,column1,column2 from user";

            // 新建 MySQL Parser
            SQLStatementParser parser = new MySqlStatementParser(sql);

            // 使用Parser解析生成AST，这里SQLStatement就是AST
            SQLStatement statement = parser.parseStatement();

            // 使用visitor来访问AST
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            statement.accept(visitor);

            // 从visitor中拿出你所关注的信息
            System.out.println(visitor.getColumns());
        }


}
