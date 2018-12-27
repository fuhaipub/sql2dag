package com.dfh;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;



public class FormatSQL {
    public static void main(String[] args) {
        String sql = "update t set name = 'x' where id < 100 limit 10";
        String result = SQLUtils.format(sql, JdbcConstants.MYSQL);
        System.out.println(result); // 缺省大写格式

        System.out.println("----------");

        String result_lcase = SQLUtils.format(sql
                , JdbcConstants.MYSQL
                , SQLUtils.DEFAULT_LCASE_FORMAT_OPTION);

        System.out.println(result_lcase); // 小写格式
    }

}
