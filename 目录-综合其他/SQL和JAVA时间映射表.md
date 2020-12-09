## SQL和JAVA时间映射表

https://www.cnblogs.com/xinziyublog/p/5650309.html

| **S** **SQL Server Type****ype** | **JDBC Type** **JDBC Type**            | **Java Type** **Java Type**    | **Mock Default value**  | **说明****说明**                                             |
| -------------------------------- | -------------------------------------- | ------------------------------ | ----------------------- | ------------------------------------------------------------ |
| **bigint**                       | *BIGINT*                               | *long*                         | *0*                     |                                                              |
| **timestamp**                    | *BINARY*                               | *byte[]*                       | *Null*                  | *Use INSERT with a column list to exclude the timestamp column,**or insert a DEFAULT into the timestamp column.* |
| **binary**                       |                                        |                                |                         |                                                              |
| **bit**                          | *BIT*                                  | *boolean*                      | *False*                 |                                                              |
| **char**                         | *CHAR*                                 | *String*                       | *X*                     |                                                              |
| **decimal**                      | *DECIMAL*                              | *java.math.BigDecimal*         | *BigDecimal.ZERO(0)*    |                                                              |
| **money**                        |                                        |                                |                         |                                                              |
| **smallmoney**                   |                                        |                                |                         |                                                              |
| **float**                        | *DOUBLE*                               | *double*                       | *0.0*                   |                                                              |
| **int**                          | *INTEGER*                              | *int*                          | *0*                     |                                                              |
| **image**                        | *LONGVARBINARY*                        | *byte[]*                       | *Null*                  |                                                              |
| **varbinary(max)**               |                                        |                                |                         |                                                              |
| **varchar(max)**                 | *LONGVARCHAR*                          | *String*                       | *“Test”*                |                                                              |
| **text**                         |                                        |                                |                         |                                                              |
| **nchar**                        | *CHAR*                                 | *String*                       | *0*                     |                                                              |
| *NCHAR (Java SE 6.0)*            |                                        |                                |                         |                                                              |
| **nvarchar**                     | *VARCHAR*                              | *String*                       | *"TT"*                  |                                                              |
| *NVARCHAR (Java SE 6.0)*         |                                        |                                |                         |                                                              |
| **nvarchar(max)**                | *LONGVARCHAR*                          | *String*                       | *"TT"*                  |                                                              |
| **ntext**                        |                                        |                                |                         |                                                              |
| **numeric**                      | *NUMERIC*                              | *java.math.BigDecimal*         | *BigDecimal.ZERO(0)*    |                                                              |
| **real**                         | *REAL*                                 | *float*                        | *0.0*                   |                                                              |
| **smallint**                     | *SMALLINT*                             | *Short*                        | *0*                     |                                                              |
| **datetime**                     | *TIMESTAMP*                            | *java.sql.Timestamp*           | *"2012-01-01 10:00:00"* |                                                              |
| **smalldatetime**                |                                        |                                |                         |                                                              |
| **varbinary**                    | *VARBINARY*                            | *byte[]*                       | *Null*                  |                                                              |
| **varchar**                      | *VARCHAR*                              | *String*                       | *“TT”*                  |                                                              |
| **tinyint**                      | *SMALLINT*                             | *Short*                        | *0*                     |                                                              |
| **guid**                         | *NVARCHAR*                             | *String*                       | *“TT”*                  | *Length limit*                                               |
| **xml**                          | *LONGVARCHAR*                          | *String*                       | *“TT”*                  |                                                              |
| **time**                         | *TIME*                                 | *java.sql.Time*                | *"10:00:00"*            |                                                              |
| **date**                         | *DATE*                                 | *java.sql.Date*                | *"2012-01-01"*          |                                                              |
| **datetime2**                    | *TIMESTAMP*                            | *java.sql.Timestamp*           | *"2012-01-01 10:00:00"* |                                                              |
| **datetimeoffset**               | *microsoft.sql.Types.**DATETIMEOFFSET* | *microsoft.sql.DateTimeOffset* | *"2012-01-01 10:00:00"* |                                                              |