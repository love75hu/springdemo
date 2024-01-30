package cn.mediinfo.springdemo.controller.javacore2;

/*
 *@title JDBC
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/26 20:59
 */
public class JDBC {
    /**
     * SQL数据类型
     * --------------------------------------------------------------------
     * INTEGER 或 INT  通常为32位整数
     * INT2 通常为16位整数,最大值32767
     * INT4 通常为32位整数,最大值2147483647
     * SMALLINT 通常为16位整数
     * NUMERIC(m,n),DECIMAL(m,n),DEC(m,n)  通常为精确的小数，m为总位数，n为小数位数
     * FLOAT(p)  通常为单精度浮点数，p为有效位数
     * REAL  通常为单精度浮点数
     * DOUBLE PRECISION  通常为64位双精度浮点数
     * CHARACTER(n),CHAR(n)  通常为固定长度的字符串，n为字符串长度
     * VARCHAR(n) 通常为可变长度的字符串，n为字符串长度
     * DATE  通常为日期,日历日期
     * TIME  通常为时间，当前时间
     * TIMESTAMP  通常为日期和时间，当前日期和时间
     * BLOB  通常为二进制大对象
     * CLOB  通常为字符大对象
     */

    /**
     * SQL数据类型对应的JAVA类型
     * --------------------------------------------------------------------
     * INTEGER 或 INT        ->     int
     * SMALLINT             ->     short
     * NUMERIC(m,n)         ->     java.math.BigDecimal
     * DECIMAL(m,n)         ->     java.math.BigDecimal
     * DEC(m,n)             ->     java.math.BigDecimal
     * FLOAT(p)             ->     double
     * REAL                 ->     float
     * CHARACTER(n)         ->     String
     * CHAR(n)              ->     String
     * VARCHAR(n)           ->     String
     * LONG VARCHAR         ->     String
     * Boolean              ->     boolean
     * DATE                 ->     java.sql.Date
     * TIME                 ->     java.sql.Time
     * TIMESTAMP            ->     java.sql.Timestamp
     * BLOB                 ->     java.sql.Blob
     * CLOB                 ->     java.sql.Clob //数据中获得一个LOB或数组并不等于获取了它的实际内容，只有在访问具体值时才会从数据库中取出来，这对改善性能非常有好处，因为这些数据的数据量都非常的大。
     * ARRAY                ->     java.sql.Array //SQL数组，指的是值的序列。例如student表通常会有一个score列，它是一个数组，包含了学生的所有成绩。这个列就应该是ARRAY类型 或者 INTEGER类型。
     * ROWID                ->     java.sql.RowId  //某些数据库支持描述行位置的ROWID。这样就可也非常快速的获取某行值。
     * NCHAR(n)             ->     String //国家属性字符串，按照本地字符编码机制存储字符串，并使用本地排序惯例对这些字符串进行排序。
     * NVARCHAR(n)          ->     String
     * LONGN VARCHAR(n)     ->     String
     * NCLOB                ->     java.sql.NClob
     * SQLXML               ->     java.sql.SQLXML
     */

}
