package com.dfh.visitor;

import java.util.List;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;

/**
 * 查询语句访问者
 *
 * @author xiezhengchao
 * @since 2018/6/1 12:08
 */
public class SelectPrintVisitor extends SQLASTVisitorAdapter {

    @Override
    public boolean visit(SQLSelectQueryBlock x) {
        List<SQLSelectItem> selectItemList = x.getSelectList();
        selectItemList.forEach(selectItem -> {
            System.out.println("attr:" + selectItem.getAttributes());
            System.out.println("expr:" + SQLUtils.toMySqlString(selectItem.getExpr()));
        });

        System.out.println("table:" + SQLUtils.toMySqlString(x.getFrom()));
        System.out.println("where:" + SQLUtils.toMySqlString(x.getWhere()));
        System.out.println("order by:" + SQLUtils.toMySqlString(x.getOrderBy().getItems().get(0)));
        System.out.println("limit:" + SQLUtils.toMySqlString(x.getLimit()));

        return true;
    }

}