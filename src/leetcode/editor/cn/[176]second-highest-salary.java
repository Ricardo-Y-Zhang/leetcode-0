//编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。 
//
// +----+--------+
//| Id | Salary |
//+----+--------+
//| 1  | 100    |
//| 2  | 200    |
//| 3  | 300    |
//+----+--------+
// 
//
// 例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。 
//
// +---------------------+
//| SecondHighestSalary |
//+---------------------+
//| 200                 |
//+---------------------+
// 
// Related Topics 数据库 
// 👍 897 👎 0


package leetcode.editor.cn;

//There is no code of Java type for this problem

/*
SELECT MAX(Salary) FROM Employee;

SELECT Id FROM Employee
WHERE Salary = (SELECT MAX(Salary) FROM Employee);
*/

SELECT MAX(Salary) "SecondHighestSalary" FROM Employee
WHERE Id NOT IN (SELECT Id FROM Employee
        WHERE Salary = (SELECT MAX(Salary) FROM Employee));