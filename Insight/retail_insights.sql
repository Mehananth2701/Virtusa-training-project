SELECT 
    c.CategoryName,
    SUM(s.TotalAmount) AS TotalRevenue
FROM SalesTransactions s
JOIN Products p ON s.ProductID = p.ProductID
JOIN Categories c ON p.CategoryID = c.CategoryID
WHERE MONTH(s.SaleDate) = MONTH(CURDATE() - INTERVAL 1 MONTH)
  AND YEAR(s.SaleDate) = YEAR(CURDATE() - INTERVAL 1 MONTH)
GROUP BY c.CategoryName
ORDER BY TotalRevenue DESC;